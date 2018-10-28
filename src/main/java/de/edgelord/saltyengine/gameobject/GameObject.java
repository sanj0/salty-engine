/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.components.Accelerator;
import de.edgelord.saltyengine.components.ColliderComponent;
import de.edgelord.saltyengine.components.RecalculateHitboxComponent;
import de.edgelord.saltyengine.components.SimplePhysicsComponent;
import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.interfaces.CollideAble;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.core.interfaces.InitializeAble;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.hitbox.Hitbox;
import de.edgelord.saltyengine.hitbox.SimpleHitbox;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static de.edgelord.saltyengine.scene.Scene.concurrentBlock;

public abstract class GameObject extends ComponentParent implements Drawable, FixedTickRoutine, CollideAble, InitializeAble {

    public static final String DEFAULT_PHYSICS_NAME = "de.edgelord.saltyengine.coreComponents.physics";
    public static final String DEFAULT_RECALCULATE_HITBOX_NAME = "de.edgelord.saltyengine.coreComponents.recalculateHitbox";
    public static final String DEFAULT_ACCELERATOR_NAME = "de.edgelord.saltyengine.coreComponents.accelerator";
    public static final String DEFAULT_COLLIDER_COMPONENT_NAME = "de.edgelord.saltyengine.coreComponents.collider";

    private final List<Component> components = new CopyOnWriteArrayList<>();

    private final SimplePhysicsComponent physicsComponent;
    private final RecalculateHitboxComponent recalculateHitboxComponent;
    private final Accelerator defaultAccelerator;
    private final ColliderComponent collider;

    private Directions lockedDirections = new Directions();

    /**
     * If this is set to true, this GameObject will not have a collision detection. Use this for
     * stationary objects like obstacles, houses and generally all kind of GameObjects that
     * aren't moving due to heavy performance improvements.
     * When ever the {@link #onCollision(CollisionEvent)} implementation is empty, set this to true
     * to improve the performance of the game a lot. Other GameObjects still collide with this one then,
     * but this one will never collide with others which is redundant when the {@link #onCollision(CollisionEvent)}
     * implementation is empty.
     * Please note that gravity and other forces won't take effect on stationary GameObjects
     */
    private boolean stationary = false;

    private String colliderComponent = DEFAULT_COLLIDER_COMPONENT_NAME;

    private Transform transform;
    private Hitbox hitbox;
    private float mass = 1f;

    private boolean initialized = false;

    public GameObject(final float xPos, final float yPos, final float width, final float height, final String tag) {
        super(tag);

        transform = new Transform(new Vector2f(xPos, yPos), new Dimensions(width, height));
        hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);

        physicsComponent = new SimplePhysicsComponent(this, GameObject.DEFAULT_PHYSICS_NAME);
        recalculateHitboxComponent = new RecalculateHitboxComponent(this, GameObject.DEFAULT_RECALCULATE_HITBOX_NAME);
        defaultAccelerator = new Accelerator(this, GameObject.DEFAULT_ACCELERATOR_NAME);
        collider = new ColliderComponent(this, DEFAULT_COLLIDER_COMPONENT_NAME, ColliderComponent.Type.HITBOX);

        components.add(physicsComponent);
        components.add(recalculateHitboxComponent);
        components.add(defaultAccelerator);
        components.add(collider);
    }

    public GameObject(Transform transform, String tag) {
        this(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight(), tag);
    }

    public GameObject(Coordinates coordinates, Dimensions dimensions, String tag) {
        this(coordinates.getX(), coordinates.getY(), dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    public GameObject(Vector2f position, Dimensions dimensions, String tag) {
        this(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    public GameObject(Vector2f position, float width, float height, String tag) {
        this(position.getX(), position.getY(), width, height, tag);
    }

    public GameObject(float xPos, float yPos, Dimensions dimensions, String tag) {
        this(xPos, yPos, dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    @Override
    public abstract void initialize();

    @Override
    public abstract void onCollision(CollisionEvent event);

    @Override
    public abstract void onFixedTick();

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    /**
     * This method can be overridden but It's not necessary and you won't need this often, so it's not abstract
     *
     * @param collisions the detected collisions of this run
     */
    public void onCollisionDetectionFinish(List<CollisionEvent> collisions) {

    }

    public void doFixedTick() {
        // Clear the forces
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setAcceleration(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setAcceleration(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setAcceleration(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setAcceleration(0f);

        onFixedTick();
    }

    public void doCollisionDetection(final List<GameObject> gameObjects) {

        Directions collisionDirections = new Directions();
        List<CollisionEvent> collisions = new ArrayList<>();

        if (!stationary) {

            synchronized (concurrentBlock) {

                for (int i = 0; i < gameObjects.size(); i++) {
                    GameObject other = gameObjects.get(i);

                    if (other == this) {
                        continue;
                    }

                    if (!stationary) {
                        if (requestCollider().requestCollision(other)) {

                            Directions.appendRelation(this.getTransform(), other.getTransform(), collisionDirections);

                            // final CollisionEvent e = new CollisionEvent(other, collisionDirections);
                            final CollisionEvent eSelf = new CollisionEvent(other, collisionDirections);


                            collisions.add(eSelf);
                            // other.ON_COLLISION(e);
                            onCollision(eSelf);

                            components.forEach(component -> component.onCollision(eSelf));

                        }
                    }
                }
            }

            components.forEach(component -> component.onCollisionDetectionFinish(collisions));
            onCollisionDetectionFinish(collisions);
        }
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void removeComponent(final String name) {
        components.removeIf(gameObjectComponent -> gameObjectComponent.getName().equals(name));
    }

    @Override
    public Component getComponent(String name) {

        for (Component component : getComponents()) {
            if (component.getName().equals(name)) {
                return component;
            }
        }

        return null;
    }

    public ColliderComponent requestCollider() {

        return (ColliderComponent) getComponent(colliderComponent);
    }

    /**
     * This method sets the {@link de.edgelord.saltyengine.core.physics.Force#acceleration} of the default force in the
     * given direction to the given acceleration. On the next fixed tick, this acceleration is reset to 0f.
     * This is the recommended way to accelerate forces for player controls.
     *
     * @param acceleration the acceleration to be set to the specific default force
     * @param direction    the direction in which to accelerate the GameObject
     */
    public void accelerate(float acceleration, Directions.Direction direction) {

        if (lockedDirections.hasDirection(direction)) {
            return;
        }

        switch (direction) {

            case RIGHT:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setAcceleration(acceleration);
                break;
            case LEFT:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setAcceleration(acceleration);
                break;
            case UP:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setAcceleration(acceleration);
                break;
            case DOWN:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setAcceleration(acceleration);
                break;
            case EMPTY:
                System.out.println("[WARNING] Can not accelerate in Direction Directions.Direction.EMPTY!");
                break;
        }
    }

    /**
     * This method sets the {@link de.edgelord.saltyengine.core.physics.Force#velocity} of the default force with the
     * given direction to the given value. this has to be reset manually if needed.
     *
     * @param velocity  the velocity to be set to the specific force
     * @param direction the directon of the default force to be manipulated.
     */
    public void setVelocity(float velocity, Directions.Direction direction) {

        if (lockedDirections.hasDirection(direction)) {
            return;
        }

        switch (direction) {

            case RIGHT:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setVelocity(velocity);
                break;
            case LEFT:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setVelocity(velocity);
                break;
            case UP:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setVelocity(velocity);
                break;
            case DOWN:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setVelocity(velocity);
                break;
            case EMPTY:
                System.out.println("[WARNING] Can not set the velocity for Direction Directions.Direction.EMPTY!");
                break;
        }
    }

    @Override
    public void move(float delta, Directions.Direction direction) {
        if (lockedDirections.hasDirection(direction)) {
            return;
        }

        super.move(delta, direction);
    }

    @Override
    public void moveY(float delta) {

        if (delta > 0f) {
            if (lockedDirections.hasDirection(Directions.Direction.DOWN)) {
                return;
            }
        } else {
            if (lockedDirections.hasDirection(Directions.Direction.UP)) {
                return;
            }
        }
        super.moveY(delta);
    }

    @Override
    public void moveX(float delta) {

        if (delta > 0f) {
            if (lockedDirections.hasDirection(Directions.Direction.RIGHT)) {
                return;
            }
        } else {
            if (lockedDirections.hasDirection(Directions.Direction.LEFT)) {
                return;
            }
        }
        super.moveX(delta);
    }

    public Coordinates getCoordinates() {
        return transform.getCoordinates();
    }

    public int getWidthAsInt() {
        return transform.getWidthAsInt();
    }

    public int getHeightAsInt() {
        return transform.getHeightAsInt();
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public SimpleHitbox getHitboxAsSimpleHitbox() {
        return (SimpleHitbox) getHitbox();
    }

    public void setHitbox(final Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public List<Component> getComponents() {
        return components;
    }

    public SimplePhysicsComponent getPhysics() {
        return physicsComponent;
    }

    public RecalculateHitboxComponent getRecalculateHitboxComponent() {
        return recalculateHitboxComponent;
    }

    public Accelerator getDefaultAccelerator() {
        return defaultAccelerator;
    }

    public ColliderComponent getCollider() {
        return collider;
    }

    public void setColliderComponent(String colliderComponent) {
        this.colliderComponent = colliderComponent;
    }

    public boolean isStationary() {
        return stationary;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(final float mass) {
        this.mass = mass;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void removeFromCurrentScene() {
        SceneManager.getCurrentScene().removeGameObject(this);
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public Directions getLockedDirections() {
        return lockedDirections;
    }

    public void setLockedDirections(Directions lockedDirections) {
        this.lockedDirections = lockedDirections;
    }
}
