/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.core.physics;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.utils.Directions;

public class Force {

    private float acceleration;
    private float velocity;
    private float distance;
    private float deltaDistance;
    private final float counterforceConst = 0.0025f;
    private Directions.Direction direction;
    private String name;
    private GameObject parent;

    public Force(final float acceleration, final GameObject parent, Directions.Direction direction, String name) {
        this(acceleration, 0, 0, parent, direction, name);
    }

    public Force(final float acceleration, final float velocity, final GameObject parent, Directions.Direction direction, String name) {
        this(acceleration, velocity, 0, parent, direction, name);
    }

    public Force(final float acceleration, final float velocity, final float distance, final GameObject parent, Directions.Direction direction, String name) {
        this.acceleration = acceleration;
        this.velocity = velocity;
        this.distance = distance;
        this.parent = parent;
        this.direction = direction;
        this.name = name;
    }

    public float deltaDistance(final int deltaT) {

        float counterForce = velocity * counterforceConst;
        float counterAcceleration = -(counterForce / parent.getMass());

        if (velocity < 0) {
            counterAcceleration = 0;
            velocity = 0;
        }

        float accelerationRes = acceleration + counterAcceleration;
        velocity += accelerationRes * deltaT;
        deltaDistance = velocity * deltaT;
        distance += deltaDistance;
        return deltaDistance;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public float getDeltaDistance() {
        return deltaDistance;
    }

    public void setDeltaDistance(float deltaDistance) {
        this.deltaDistance = deltaDistance;
    }

    public float getVelocity() {
        return velocity;
    }

    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public Directions.Direction getDirection() {
        return direction;
    }

    public void setDirection(Directions.Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }
}
