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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.physics.Force;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class SimplePhysicsComponent extends Component<GameObject> {

    public static final String DEFAULT_GRAVITY = "de.edgelord.saltyengine.core.physics.default_gravityForce";
    public static final float DEFAULT_GRAVITY_ACCELERATION = 2000f;
    /**
     * This is the String constant for the default upwards force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_UPWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultUpwardsForce";
    /**
     * This is the String constant for the default downwards force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_DOWNWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultDownwardsForce";
    /**
     * This is the String constant for the default rightwards force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_RIGHTWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultRightwardsForce";
    /**
     * This is the String constant for the default leftwards force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_LEFTWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultLeftwardsForce";
    /**
     * This is the String constant for the default upwards velocity force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_UPWARDS_VELOCITY_FORCE = "de.edgelord.saltyengine.core.physics.defaultUpwardsVelocityForce";
    /**
     * This is the String constant for the default downwards velocity force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_DOWNWARDS_VELOCITY_FORCE = "de.edgelord.saltyengine.core.physics.defaultDownwardsVelocityForce";
    /**
     * This is the String constant for the default rightwards velocity force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_RIGHTWARDS_VELOCITY_FORCE = "de.edgelord.saltyengine.core.physics.defaultRightwardsVelocityForce";
    /**
     * This is the String constant for the default leftwards velocity force. It is not recommended to use these because they're
     * getting manipulated internally.
     */
    public static final String DEFAULT_LEFTWARDS_VELOCITY_FORCE = "de.edgelord.saltyengine.core.physics.defaultLeftwardsVelocityForce";
    private final List<Force> forces = new LinkedList<>();
    private final List<String> tagsToIgnore = new ArrayList<>();
    private boolean gravityForThisEnabled = true;
    /**
     * The minimal value of the {@link Force#deltaDistance(int)} before its {@link Force#getAcceleration()} is set ot 0f.
     * The default value (which works fine for the default gravity, friction and mass of the {@link GameObject}s) is <code>0.5f</code>
     */
    private float threshold = 0.5f;

    public SimplePhysicsComponent(final GameObject parent, final String name) {
        super(parent, name, Components.PHYSICS_COMPONENT);

        addDefaultForces();
    }

    private void addGravityForce() {

        forces.add(new Force(DEFAULT_GRAVITY_ACCELERATION, getParent(), Directions.Direction.DOWN, SimplePhysicsComponent.DEFAULT_GRAVITY));
    }

    private void addDefaultForces() {

        addGravityForce();

        addForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE, Directions.Direction.UP);
        addForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE, Directions.Direction.DOWN);
        addForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE, Directions.Direction.RIGHT);
        addForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE, Directions.Direction.LEFT);

        addForce(SimplePhysicsComponent.DEFAULT_UPWARDS_VELOCITY_FORCE, Directions.Direction.UP);
        addForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_VELOCITY_FORCE, Directions.Direction.DOWN);
        addForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_VELOCITY_FORCE, Directions.Direction.RIGHT);
        addForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_VELOCITY_FORCE, Directions.Direction.LEFT);
    }

    @Override
    public void onFixedTick() {

        if (gravityForThisEnabled) {
            getForce(DEFAULT_GRAVITY).setAcceleration(SceneManager.getCurrentScene().getGravity());
        } else {
            getForce(DEFAULT_GRAVITY).setAcceleration(0f);
        }

        float horizontalDelta = 0f;
        float verticalDelta = 0f;
        final int deltaT = (int) SaltySystem.fixedTickMillis;

        for (final Force force : forces) {

            final float deltaDistance = force.deltaDistance(deltaT);

            if (deltaDistance <= threshold) {
                force.setAcceleration(0f);
                continue;
            }

            switch (force.getDirection()) {
                case RIGHT:
                    horizontalDelta += force.deltaDistance(deltaT);
                    break;
                case LEFT:
                    horizontalDelta -= force.deltaDistance(deltaT);
                    break;
                case UP:
                    verticalDelta -= force.deltaDistance(deltaT);
                    break;
                case DOWN:
                    verticalDelta += force.deltaDistance(deltaT);
                    break;
            }
        }

        getParent().moveX(horizontalDelta);
        getParent().moveY(verticalDelta);
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public void onCollisionDetectionFinish(final List<CollisionEvent> collisions) {

        final Directions collisionDirections = new Directions();
        boolean upCollision = false;
        boolean downCollision = false;
        boolean leftCollision = false;
        boolean rightCollision = false;

        for (final CollisionEvent collisionEvent : collisions) {

            if (!tagsToIgnore.contains(collisionEvent.getOtherGameObject().getTag()) && !collisionEvent.getOtherGameObject().isTrigger()) {

                final Directions.Direction direction = collisionEvent.getCollisionDirection();
                collisionDirections.addDirection(direction);

                switch (direction) {

                    case RIGHT:
                        rightCollision = true;
                        break;
                    case LEFT:
                        leftCollision = true;
                        break;
                    case UP:
                        upCollision = true;
                        break;
                    case DOWN:
                        downCollision = true;
                        break;
                    case EMPTY:
                        break;
                }
            }
        }

        getParent().setLockedDirections(collisionDirections);

        for (final Force force : forces) {

            switch (force.getDirection()) {

                case RIGHT:
                    force.setCountersCollision(rightCollision);
                    break;
                case LEFT:
                    force.setCountersCollision(leftCollision);
                    break;
                case UP:
                    force.setCountersCollision(upCollision);
                    break;
                case DOWN:
                    force.setCountersCollision(downCollision);
                    break;
            }
        }
    }

    public void removeForce(final String name) {
        forces.removeIf(force -> force.getName().equals(name));
    }

    public void addForce(final String name, final Directions.Direction direction) {
        forces.add(new Force(0, getParent(), direction, name));
    }

    public void addForce(final Force force) {

        forces.add(force);
    }

    public Force getForce(final String name) {
        for (final Force force : forces) {

            if (force.getName().equals(name)) {
                return force;
            }
        }

        return null;
    }

    public boolean addTagToIgnore(final String s) {
        return tagsToIgnore.add(s);
    }

    public boolean removeTagToIgnore(final Object o) {
        return tagsToIgnore.remove(o);
    }

    public boolean removeAllTagsToIgnore(final Collection<?> c) {
        return tagsToIgnore.removeAll(c);
    }

    public void clearTagsToIgnore() {
        tagsToIgnore.clear();
    }

    public void addTagToIgnore(final int index, final String element) {
        tagsToIgnore.add(index, element);
    }

    public String removeTagToIgnore(final int index) {
        return tagsToIgnore.remove(index);
    }

    public void setGravityEnabled(final boolean enabled) {
        this.gravityForThisEnabled = enabled;
    }

    /**
     * Gets {@link #threshold}.
     *
     * @return the value of {@link #threshold}
     */
    public float getThreshold() {
        return threshold;
    }

    /**
     * Sets {@link #threshold}.
     *
     * @param threshold the new value of {@link #threshold}
     */
    public void setThreshold(final float threshold) {
        this.threshold = threshold;
    }
}
