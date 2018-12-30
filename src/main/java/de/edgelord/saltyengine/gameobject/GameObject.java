/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.components.RecalculateHitboxComponent;
import de.edgelord.saltyengine.components.SimplePhysicsComponent;
import de.edgelord.saltyengine.components.collider.ColliderComponent;
import de.edgelord.saltyengine.components.collider.HitboxCollider;
import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.CollideAble;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.core.interfaces.InitializeAble;
import de.edgelord.saltyengine.core.physics.Force;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.hitbox.Hitbox;
import de.edgelord.saltyengine.hitbox.SimpleHitbox;
import de.edgelord.saltyengine.input.Input;
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

public abstract class GameObject extends ComponentContainer implements Drawable, FixedTickRoutine, CollideAble, InitializeAble {

    public static final String DEFAULT_PHYSICS_NAME = "de.edgelord.saltyengine.coreComponents.physics";
    public static final String DEFAULT_RECALCULATE_HITBOX_NAME = "de.edgelord.saltyengine.coreComponents.recalculateHitbox";
    public static final String DEFAULT_COLLIDER_COMPONENT_NAME = "de.edgelord.saltyengine.coreComponents.collider";

    private final List<Component> components = new CopyOnWriteArrayList<>();

    private final SimplePhysicsComponent physicsComponent;
    private final RecalculateHitboxComponent recalculateHitboxComponent;
    private final HitboxCollider defaultCollider;

    private Directions lockedDirections = new Directions();

    /**
     * If this is set to true, this GameObject will not have a collision detection. Use this for
     * stationary objects like obstacles, houses and generally all kind of GameObjects that
     * aren't moving due to heavy performance improvements.
     * Whenever the {@link #onCollision(CollisionEvent)} implementation is empty, set this to true
     * to improve the performance of the game a lot. Other GameObjects still collide with this one then,
     * but this one will never collide with others which is redundant when the {@link #onCollision(CollisionEvent)}
     * implementation is empty.
     * Please note that gravity and other forces won't take effect on stationary GameObjects
     */
    private boolean stationary = false;

    /**
     * If this is true, the physics of any other GameObject in the {@link de.edgelord.saltyengine.scene.Scene} will
     * ignore this one on collision, but the collision is still detected.
     */
    private boolean isTrigger = false;

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
        defaultCollider = new HitboxCollider(this, DEFAULT_COLLIDER_COMPONENT_NAME);

        components.add(physicsComponent);
        components.add(recalculateHitboxComponent);
        components.add(defaultCollider);
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
        // Remove acceleration from default forces
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setAcceleration(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setAcceleration(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setAcceleration(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setAcceleration(0f);

        // Remove velocity from default velocity forces
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_VELOCITY_FORCE).setVelocity(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_VELOCITY_FORCE).setVelocity(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_VELOCITY_FORCE).setVelocity(0f);
        getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_VELOCITY_FORCE).setVelocity(0f);

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

                            Directions.Direction currentCollisionDirection =
                                    requestCollider().getCollisionDirection(other);

                            collisionDirections.setDirection(currentCollisionDirection);
                            final CollisionEvent eSelf = new CollisionEvent(other, collisionDirections, currentCollisionDirection);

                            collisions.add(eSelf);
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
    public List<Component> getComponents() {
        return components;
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

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
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

    public ColliderComponent requestCollider() {

        return (ColliderComponent) getComponent(colliderComponent);
    }

    /**
     * This method sets the {@link Force#getAcceleration()} of the default force in the
     * given direction to the given acceleration. On the next fixed tick, this acceleration is reset to 0f.
     * This is the recommended way fro player control in a few cases because the momentum of this GameObject will
     * slowly fade out and so the controls aren't precise. However, this might be useful for some physics-related games.
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
     * Calls {@link #accelerate(float, Directions.Direction)} for every {@link de.edgelord.saltyengine.utils.Directions.Direction}
     * that the given {@link Directions} has.
     *
     * @param velocity   the target acceleration for all directions that the given <code>Directions</code> has.
     * @param directions the <code>Directions</code> in which to accelerate.
     * @see #accelerateTo(float, Directions.Direction)
     */
    public void accelerate(float velocity, Directions directions) {

        if (directions.hasDirection(Directions.Direction.UP)) {
            accelerate(velocity, Directions.Direction.UP);
        }

        if (directions.hasDirection(Directions.Direction.DOWN)) {
            accelerate(velocity, Directions.Direction.DOWN);
        }

        if (directions.hasDirection(Directions.Direction.RIGHT)) {
            accelerate(velocity, Directions.Direction.RIGHT);
        }

        if (directions.hasDirection(Directions.Direction.LEFT)) {
            accelerate(velocity, Directions.Direction.LEFT);
        }
    }

    /**
     * This method sets the {@link Force#getVelocity()} of the default velocity force with the
     * given direction to the given value. This velocity only rests for one tick.
     * This is the recommended way for player control in most cases because it's more precise than working with acceleration.
     *
     * @param velocity  the velocity to be set to the specific force
     * @param direction the directon of the default force to be manipulated.
     */
    public void accelerateTo(float velocity, Directions.Direction direction) {

        if (lockedDirections.hasDirection(direction)) {
            return;
        }

        switch (direction) {

            case RIGHT:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_VELOCITY_FORCE).setVelocity(velocity);
                break;
            case LEFT:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_VELOCITY_FORCE).setVelocity(velocity);
                break;
            case UP:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_VELOCITY_FORCE).setVelocity(velocity);
                break;
            case DOWN:
                getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_VELOCITY_FORCE).setVelocity(velocity);
                break;
            case EMPTY:
                System.out.println("[WARNING] Can not set the velocity for Direction Directions.Direction.EMPTY!");
                break;
        }
    }

    /**
     * Calls {@link #accelerateTo(float, Directions.Direction)} for every {@link de.edgelord.saltyengine.utils.Directions.Direction}
     * that the given {@link Directions} has.
     *
     * @param velocity   the target velocity for all directions that the given <code>Directions</code> has.
     * @param directions the <code>Directions</code> in which to accelerate to.
     * @see #accelerateTo(float, Directions.Direction)
     */
    public void accelerateTo(float velocity, Directions directions) {

        if (directions.hasDirection(Directions.Direction.UP)) {
            accelerateTo(velocity, Directions.Direction.UP);
        }

        if (directions.hasDirection(Directions.Direction.DOWN)) {
            accelerateTo(velocity, Directions.Direction.DOWN);
        }

        if (directions.hasDirection(Directions.Direction.RIGHT)) {
            accelerateTo(velocity, Directions.Direction.RIGHT);
        }

        if (directions.hasDirection(Directions.Direction.LEFT)) {
            accelerateTo(velocity, Directions.Direction.LEFT);
        }
    }

    public boolean isCursorOver() {
        return getTransform().contains(Input.getCursor());
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

    public SimplePhysicsComponent getPhysics() {
        return physicsComponent;
    }

    public RecalculateHitboxComponent getRecalculateHitboxComponent() {
        return recalculateHitboxComponent;
    }

    public ColliderComponent getDefaultColliderCollider() {
        return defaultCollider;
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

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }
}
