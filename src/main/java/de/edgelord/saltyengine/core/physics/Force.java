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

package de.edgelord.saltyengine.core.physics;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.Directions;

public class Force {

    private static final float VALUE_SCALE = 1000;

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
            counterAcceleration = -(counterForce * parent.getMass());
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
            this.velocity = velocity / (VALUE_SCALE * 10);
        }
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(final float acceleration) {

        if (!countersCollision) {
            this.acceleration = acceleration / (VALUE_SCALE * VALUE_SCALE);
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
