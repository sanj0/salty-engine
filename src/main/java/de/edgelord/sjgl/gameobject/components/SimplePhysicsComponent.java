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

    private List<Force> forces = new LinkedList<>();
    public static final String DEFAULT_GRAVITY_NAME = "de.edgelord.sjgl.core.physics.default_gravityForce";

    public SimplePhysicsComponent(GameObject parent, String name) {
        super(parent, name);

        addGravityForce();
    }

    private void addGravityForce() {

        forces.add(new Force(0.005f, getParent(), Directions.Direction.down, DEFAULT_GRAVITY_NAME));
    }

    @Override
    public void onFixedTick() {

        for (Force force : forces) {

            getParent().move(force.deltaDistance((int) StaticSystem.fixedTicksMillis), force.getDirection());
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

        for (Force force : forces) {

            for (Directions.Direction direction : e.getCollisionDirections()) {
                if (force.getDirection() == direction) {
                    force.setAcceleration(0f);
                    force.setVelocity(0f);
                }
            }
        }
    }

    public void removeForce(String name) {
        forces.removeIf(force -> force.getName().equals(name));
    }

    public void addForce(String name, Directions.Direction direction) {
        forces.add(new Force(0, getParent(), direction, name));
    }

    public void addForce(Force force) {

        forces.add(force);
    }

    public Force getForce(String name) {
        for (Force force : forces) {

            if (force.getName().equals(name)) {
                return force;
            }
        }

        return null;
    }

    public void removeGravity() {

        removeForce(DEFAULT_GRAVITY_NAME);
    }
}
