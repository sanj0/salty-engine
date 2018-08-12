/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.core.physics.Force;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;
import de.edgelord.sjgl.utils.Directions;
import de.edgelord.sjgl.utils.StaticSystem;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SimplePhysicsComponent extends GameObjectComponent {

    public static final String DEFAULT_GRAVITY = "de.edgelord.sjgl.core.physics.default_gravityForce";
    public static final float DEFAULT_GRAVITY_ACCELERATION = 0.005f;
    public static final String DEFAULT_UPWARDS_FORCE = "de.edgelord.sjgl.core.physics.defaultUpwardsForce";
    public static final String DEFAULT_DOWNWARDS_FORCE = "de.edgelord.sjgl.core.physics.defaultDownwardsForce";
    public static final String DEFAULT_RIGHTWARDS_FORCE = "de.edgelord.sjgl.core.physics.defaultRightwardsForce";
    public static final String DEFAULT_LEFTWARDS_FORCE = "de.edgelord.sjgl.core.physics.defaultLeftwardsForce";
    private final List<Force> forces = new LinkedList<>();

    public SimplePhysicsComponent(final GameObject parent, final String name) {
        super(parent, name, PHYSICS_COMPONENT);

        addDefaultForces();
    }

    private void addGravityForce() {

        forces.add(new Force(SimplePhysicsComponent.DEFAULT_GRAVITY_ACCELERATION, getParent(), Directions.Direction.down, SimplePhysicsComponent.DEFAULT_GRAVITY));
    }

    private void addDefaultForces() {

        addGravityForce();

        addForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE, Directions.Direction.up);
        addForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE, Directions.Direction.down);
        addForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE, Directions.Direction.right);
        addForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE, Directions.Direction.left);
    }

    @Override
    public void onFixedTick() {

        float horizontalDelta = 0f;
        float verticalDelta = 0f;
        final int deltaT = (int) StaticSystem.fixedTickMillis;

        for (final Force force : forces) {

            switch (force.getDirection()) {
                case right:
                    horizontalDelta += force.deltaDistance(deltaT);
                    break;
                case left:
                    horizontalDelta -= force.deltaDistance(deltaT);
                    break;
                case up:
                    verticalDelta -= force.deltaDistance(deltaT);
                    break;
                case down:
                    verticalDelta += force.deltaDistance(deltaT);
                    break;
            }
        }

        getParent().moveX(horizontalDelta);
        getParent().moveY(verticalDelta);
    }

    @Override
    public void draw(final Graphics2D graphics) {

    }

    @Override
    public void onCollision(final CollisionEvent e) {

        for (final Force force : forces) {

            if (force.getDirection() == e.getCollisionDirection()) {
                force.setCountersCollision(true);
            } else {
                force.setCountersCollision(false);
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
