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

import de.edgelord.saltyengine.collision.collider.Collider;
import de.edgelord.saltyengine.collision.collider.HitboxCollider;
import de.edgelord.saltyengine.components.SimplePhysicsComponent;
import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
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
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * A <code>GameObject</code> can be added to a {@link de.edgelord.saltyengine.scene.Scene} using {@link de.edgelord.saltyengine.scene.Scene#addGameObject(GameObject)}.
 * It will then have collision detection with all of the other <code>GameObject</code>s in the <code>Scene</code>, it will
 * be drawn using {@link #draw(SaltyGraphics)}, initialized using {@link #initialize()} and updated every fixed tick using {@link #onFixedTick()}.
 * <p>
 * For more information, please visit the project's wiki on https://www.github.com/edgelord314/salty-engine/wiki
 */
@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class GameObject extends ComponentContainer implements Drawable, FixedTickRoutine, CollideAble, InitializeAble {

    /**
     * The name of the default {@link SimplePhysicsComponent} {@link #physicsComponent}.
     */
    public static final String DEFAULT_PHYSICS_NAME = "de.edgelord.saltyengine.coreComponents.physics";

    /**
     * The list of {@link Component}s that this <code>GameObject</code> currently has.
     * Due to concurrency, it is a <code>CopyOnWriteArrayList</code>.
     */
    private final List<Component> components = new CopyOnWriteArrayList<>();

    /**
     * The List of collisions that has been occurred in the last collision detection, used for {@link #onCollisionDetectionFinish(List)}.
     */
    private final List<CollisionEvent> collisions = new CopyOnWriteArrayList<>();

    /**
     * Used for internal purposes to make sure that the list of {@link #collisions} are cleared once per collision detection.
     */
    private boolean clearCollisions = true;

    /**
     * The default physics component, used for stopping the <code>GameObject</code> when a collision in a direction
     * occurs and to accelerate it using e.g. {@link #accelerate(float, Directions.Direction)} or {@link #accelerateTo(float, Directions.Direction)}.
     */
    private final SimplePhysicsComponent physicsComponent;

    /**
     * The collider of this <code>GameObject</code>, used to check collisions between this and other <code>GameObject</code>s.
     */
    private Collider collider;

    /**
     * Used for internal purposes to call {@link #onCursorEnters()} and {@link #onCursorExits()}.
     */
    private boolean cursorAlreadyTouching = false;

    /**
     * If this is true, the physics of any other GameObject in the {@link de.edgelord.saltyengine.scene.Scene} will
     * ignore this one on collision, but the collision is still detected.
     */
    private boolean isTrigger = false;

    /**
     * The <code>Hitbox</code> of a <code>GameObject</code> may be used by {@link Collider}s, like for example the default {@link HitboxCollider}.
     */
    private Hitbox hitbox;

    /**
     * The mass of this <code>GameObject</code>. <br>
     * It effects how this <code>GameObject</code> is accelerate and slowed down, but currently not the behaviour when it collides.
     */
    private float mass = 1f;

    /**
     * When this is <code>false</code>, {@link #initialize()} will be called on the next fixed tick. <br>
     * Can be set to <code>false</code> any time again.
     */
    private boolean initialized = false;

    /**
     * A list of tags that the collision detection should ignore in order to save performance.
     */
    private List<String> collisionDetectionIgnore = new LinkedList<>();

    /**
     * The base constructor.
     *
     * @param xPos   the x position in the scene
     * @param yPos   the y position in the scene
     * @param width  the width
     * @param height the height
     * @param tag    the tag
     */
    public GameObject(final float xPos, final float yPos, final float width, final float height, final String tag) {
        super(tag);

        setTransform(new Transform(new Vector2f(xPos, yPos), new Dimensions(width, height)));
        hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);

        physicsComponent = new SimplePhysicsComponent(this, GameObject.DEFAULT_PHYSICS_NAME);
        collider = new HitboxCollider();

        components.add(physicsComponent);
    }

    /**
     * A constructor.
     *
     * @param transform the <code>Transform</code> of the <code>GameObject</code>
     * @param tag       the tag
     */
    public GameObject(Transform transform, String tag) {
        this(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight(), tag);
    }

    /**
     * A constructor.
     *
     * @param coordinates the position
     * @param dimensions  the dimensions
     * @param tag         the tag
     */
    @Deprecated
    public GameObject(Coordinates coordinates, Dimensions dimensions, String tag) {
        this(coordinates.getX(), coordinates.getY(), dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    /**
     * A constructor.
     *
     * @param position   the position
     * @param dimensions the dimensions
     * @param tag        the tag
     */
    public GameObject(Vector2f position, Dimensions dimensions, String tag) {
        this(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    /**
     * A constructor.
     *
     * @param position the position
     * @param width    th width
     * @param height   the height
     * @param tag      the tagfranziska_covert_songs
     */
    public GameObject(Vector2f position, float width, float height, String tag) {
        this(position.getX(), position.getY(), width, height, tag);
    }

    /**
     * A constructor.
     *
     * @param xPos       the x position
     * @param yPos       the y position
     * @param dimensions the dimensions
     * @param tag        the tag
     */
    public GameObject(float xPos, float yPos, Dimensions dimensions, String tag) {
        this(xPos, yPos, dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    /**
     * Initializes this <code>GameObject</code>.
     * The advantage over doing so in the constructor is that, in many cases, the constructor will be called while
     * the previous {@link de.edgelord.saltyengine.scene.Scene} is still active, so e.g. a {@link de.edgelord.saltyengine.components.gfx.LightComponent}
     * would add it's {@link de.edgelord.saltyengine.effect.light.Light} to the wrong <code>Scene</code>.
     * This method is called every fixed tick when {@link #initialized} is false.
     */
    @Override
    public abstract void initialize();

    /**
     * This method is called every fixed tick for every <code>GameObject</code> that touches this one.
     *
     * @param event the <code>CollisionEvent</code> with any necessary information to handle a collision
     */
    @Override
    public abstract void onCollision(CollisionEvent event);

    /**
     * This method is called every fixed tick. <br>
     * Please take a look at the projects wiki at https://www.github.com/edgelord314/salty-engine/wiki for more information.
     *
     * @see de.edgelord.saltyengine.scene.Scene
     */
    @Override
    public abstract void onFixedTick();

    /**
     * This method is called every time the {@link de.edgelord.saltyengine.scene.Scene} is repainted. <br>
     * With this method, you can draw anything using the given {@link SaltyGraphics}.
     *
     * @param saltyGraphics the graphics to draw to
     * @see SaltyGraphics
     */
    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    /**
     * This is called every time the cursor enters the {@link #getTransform()} of this <code>GameObject</code>.
     * As this takes quite a bit of performance, you can disable/enable this feature using {@link de.edgelord.saltyengine.utils.SaltySystem#gameObjectMouseEventsAgent}.
     */
    public void onCursorEnters() {
    }

    /**
     * This is called every time the cursor exits the {@link #getTransform()} of this <code>GameObject</code>.
     * As this takes quite a bit of performance, you can disable/enable this feature using {@link de.edgelord.saltyengine.utils.SaltySystem#gameObjectMouseEventsAgent}.
     */
    public void onCursorExits() {
    }

    /**
     * This is called every time the collision detectio of this <code>GameObject</code> is done. <br>
     * It is especially useful for e.g. processing if this <code>GameObject</code> hits any other <code>GameObject</code> with a specific tag.
     *
     * @param collisions the list of occurred collisions.
     */
    public void onCollisionDetectionFinish(List<CollisionEvent> collisions) {

    }

    /**
     * This method is used internally to call {@link #onFixedTick()} and do some stuff with the physics as well as {@link Hitbox#recalculate() updating} the {@link #hitbox}.
     */
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

        hitbox.recalculate();
        onFixedTick();
    }

    /**franziska_covert_songs
     * This is used internally to handle the event of the cursor entering this <code>GameObject</code>.
     */
    public final void doCursorEnters() {
        doComponentCursorEntersParent();
        onCursorEnters();
    }

    /**
     * This is used internally to handle the event of the cursor exiting this <code>GameObject</code>.
     */
    public final void doCursorExits() {
        doComponentCursorExitsParent();
        onCursorExits();
    }

    /**
     * Returns the list of {@link Component}s.
     *
     * @return the list of {@link Component}s
     */
    @Override
    public List<Component> getComponents() {
        return components;
    }

    /**
     * Adds a component.
     *
     * @param component the component to add
     */
    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    /**
     * Removes the given {@link Component}.
     *
     * @param component the {@link Component} to be removed
     */
    @Override
    public void removeComponent(Component component) {
        components.remove(component);
    }

    /**
     * Removes the first {@link Component} with the given name.
     *
     * @param name the name
     */
    @Override
    public void removeComponent(final String name) {
        components.removeIf(gameObjectComponent -> gameObjectComponent.getName().equals(name));
    }

    /**
     * Returns the first {@link Component} with the given name.
     *
     * @param name the name
     * @return the first {@link Component} with the given name
     */
    @Override
    public Component getComponent(String name) {

        for (Component component : getComponents()) {
            if (component.getName().equals(name)) {
                return component;
            }
        }

        return null;
    }

    /**
     * This method sets the {@link Force#getAcceleration() acceleration} of the default force in the
     * given direction to the given acceleration. On the next fixed tick, this acceleration is reset to 0f.
     * This is the recommended way fro player control in a few cases because the momentum of this GameObject will
     * slowly fade out and so the controls aren't precise. However, this might be useful for some physics-related games.
     *
     * @param acceleration the acceleration to be set to the specific default force
     * @param direction    the direction in which to accelerate the GameObject
     */
    public void accelerate(float acceleration, Directions.Direction direction) {

        if (getLockedDirections().hasDirection(direction)) {
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
     * @param acceleration the target acceleration for all directions that the given <code>Directions</code> has.
     * @param directions   the <code>Directions</code> in which to accelerate.
     * @see #accelerateTo(float, Directions.Direction)
     */
    public void accelerate(float acceleration, Directions directions) {

        if (directions.hasDirection(Directions.Direction.UP)) {
            accelerate(acceleration, Directions.Direction.UP);
        }

        if (directions.hasDirection(Directions.Direction.DOWN)) {
            accelerate(acceleration, Directions.Direction.DOWN);
        }

        if (directions.hasDirection(Directions.Direction.RIGHT)) {
            accelerate(acceleration, Directions.Direction.RIGHT);
        }

        if (directions.hasDirection(Directions.Direction.LEFT)) {
            accelerate(acceleration, Directions.Direction.LEFT);
        }
    }

    /**
     * This method sets the {@link Force#getVelocity() velocity} of the default velocity force with the
     * given direction to the given value. This velocity only rests for one tick.
     * This is the recommended way for player control in most cases because it's more precise than working with acceleration.
     *
     * @param velocity  the velocity to be set to the specific force
     * @param direction the direction of the default force to be manipulated
     */
    public void accelerateTo(float velocity, Directions.Direction direction) {

        if (getLockedDirections().hasDirection(direction)) {
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
                System.out.println("[WARNING] Cannot set the velocity for Direction Directions.Direction.EMPTY!");
                break;
        }
    }

    /**
     * Calls {@link #accelerateTo(float, Directions.Direction)} for every {@link de.edgelord.saltyengine.utils.Directions.Direction}
     * that the given {@link Directions} has.
     *
     * @param velocity   the target velocity for all directions that the given <code>Directions</code> has
     * @param directions the <code>Directions</code> in which to accelerate to the given velocity
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

    /**
     * Returns whether the cursor touches the {@link #getTransform() transform} of this <code>GameObject</code> or not.
     *
     * @return <code>true</code> if the cursor touches the {@link #getTransform() transform} of this <code>GameObject</code> and <code>false</code> if not.
     */
    public boolean isCursorOver() {
        return getTransform().contains(Input.getCursor());
    }

    /**
     * Returns the result of {@link #isCursorOver()}.
     *
     * @return the return value of {@link #isCursorOver()}.
     */
    public boolean mouseTouches() {
        return isCursorOver();
    }

    /**
     * Returns the {@link #hitbox} of this <code>GameObject</code>.
     *
     * @return the {@link #hitbox}
     */
    public Hitbox getHitbox() {
        return hitbox;
    }

    /**
     * Returns the {@link #hitbox} as a {@link SimpleHitbox}, as it is by default.
     *
     * @return the return value of {@link #getHitbox()} casted to {@link SimpleHitbox}
     */
    public SimpleHitbox getHitboxAsSimpleHitbox() {
        return (SimpleHitbox) getHitbox();
    }

    public void setHitbox(final Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public SimplePhysicsComponent getPhysics() {
        return physicsComponent;
    }

    public Collider getCollider() {
        return collider;
    }

    public void setCollider(Collider collider) {
        this.collider = collider;
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

    public boolean isTrigger() {
        return isTrigger;
    }

    public void setTrigger(boolean trigger) {
        isTrigger = trigger;
    }

    public boolean isCursorAlreadyTouching() {
        return cursorAlreadyTouching;
    }

    public void setCursorAlreadyTouching(boolean cursorAlreadyTouching) {
        this.cursorAlreadyTouching = cursorAlreadyTouching;
    }

    public List<CollisionEvent> getCollisions() {
        return collisions;
    }

    public boolean isClearCollisions() {
        return clearCollisions;
    }

    public void setClearCollisions(boolean clearCollisions) {
        this.clearCollisions = clearCollisions;
    }

    public List<String> getCollisionDetectionIgnore() {
        return collisionDetectionIgnore;
    }

    public void setCollisionDetectionIgnore(List<String> collisionDetectionIgnore) {
        this.collisionDetectionIgnore = collisionDetectionIgnore;
    }
}
