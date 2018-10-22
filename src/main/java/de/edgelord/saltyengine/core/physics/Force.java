/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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
        this.acceleration = acceleration / (VALUE_SCALE * VALUE_SCALE);
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
