/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.core.physics;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.Directions;

public class Force {

    public static float DEFAULT_FRICTION = 0.0025f;
    private float acceleration;
    private float velocity;
    private float distance;
    private float deltaDistance;
    private Directions.Direction direction;
    private String name;
    private GameObject parent;
    private boolean countersCollision = false;

    public Force(final float acceleration, final GameObject parent, final Directions.Direction direction, final String name) {
        this(acceleration, 0, 0, parent, direction, name);
    }

    public Force(final float acceleration, final float velocity, final GameObject parent, final Directions.Direction direction, final String name) {
        this(acceleration, velocity, 0, parent, direction, name);
    }

    public Force(final float acceleration, final float velocity, final float distance, final GameObject parent, final Directions.Direction direction, final String name) {
        this.acceleration = acceleration;
        this.velocity = velocity;
        this.distance = distance;
        this.parent = parent;
        this.direction = direction;
        this.name = name;
    }

    public float deltaDistance(final int deltaT) {

        final float counterForce = velocity * SceneManager.getCurrentScene().getFriction();
        float counterAcceleration = 0f;

        if (!countersCollision) {
            counterAcceleration = -(counterForce / parent.getMass());
        }

        final float accelerationRes = acceleration + counterAcceleration;

        velocity += accelerationRes * deltaT;

        if (countersCollision) {
            velocity = 0;
        }

        deltaDistance = velocity * deltaT;
        distance += deltaDistance;
        if (countersCollision) {
            return 0;
        }
        return deltaDistance;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(final float distance) {
        this.distance = distance;
    }

    public float getDeltaDistance() {
        return deltaDistance;
    }

    public void setDeltaDistance(final float deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(final float velocity) {
        if (!countersCollision) {
            this.velocity = velocity;
        }
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(final float acceleration) {

        if (!countersCollision) {
            this.acceleration = acceleration;
        }
    }

    public Directions.Direction getDirection() {
        return direction;
    }

    public void setDirection(final Directions.Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(final GameObject parent) {
        this.parent = parent;
    }

    public boolean isCountersCollision() {
        return countersCollision;
    }

    public void setCountersCollision(final boolean countersCollision) {
        this.countersCollision = countersCollision;
    }
}
