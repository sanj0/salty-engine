/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.physics.Force;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.util.LinkedList;
import java.util.List;

public class SimplePhysicsComponent extends Component<GameObject> {

    public static final String DEFAULT_GRAVITY = "de.edgelord.saltyengine.core.physics.default_gravityForce";
    public static final float DEFAULT_GRAVITY_ACCELERATION = 0.005f;
    public static final String DEFAULT_UPWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultUpwardsForce";
    public static final String DEFAULT_DOWNWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultDownwardsForce";
    public static final String DEFAULT_RIGHTWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultRightwardsForce";
    public static final String DEFAULT_LEFTWARDS_FORCE = "de.edgelord.saltyengine.core.physics.defaultLeftwardsForce";
    private final List<Force> forces = new LinkedList<>();

    public SimplePhysicsComponent(final GameObject parent, final String name) {
        super(parent, name, Components.PHYSICS_COMPONENT);

        addDefaultForces();
    }

    private void addGravityForce() {

        forces.add(new Force(SimplePhysicsComponent.DEFAULT_GRAVITY_ACCELERATION, getParent(), Directions.Direction.DOWN, SimplePhysicsComponent.DEFAULT_GRAVITY));
    }

    private void addDefaultForces() {

        addGravityForce();

        addForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE, Directions.Direction.UP);
        addForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE, Directions.Direction.DOWN);
        addForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE, Directions.Direction.RIGHT);
        addForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE, Directions.Direction.LEFT);
    }

    @Override
    public void onFixedTick() {

        float horizontalDelta = 0f;
        float verticalDelta = 0f;
        final int deltaT = (int) StaticSystem.fixedTickMillis;

        for (final Force force : forces) {

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
    public void onCollisionDetectionFinish(List<CollisionEvent> collisions) {

        Directions directions;
        boolean upCollision = false;
        boolean downCollision = false;
        boolean leftCollision = false;
        boolean rightCollision = false;

        for (CollisionEvent collisionEvent : collisions) {
            directions = collisionEvent.getCollisionDirections();

            if (directions.hasDirection(Directions.Direction.UP)) {
                upCollision = true;
            }

            if (directions.hasDirection(Directions.Direction.DOWN)) {
                downCollision = true;
            }

            if (directions.hasDirection(Directions.Direction.LEFT)) {
                leftCollision = true;
            }

            if (directions.hasDirection(Directions.Direction.RIGHT)) {
                rightCollision = true;
            }
        }

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

    public void removeGravity() {

        removeForce(SimplePhysicsComponent.DEFAULT_GRAVITY);
    }
}
