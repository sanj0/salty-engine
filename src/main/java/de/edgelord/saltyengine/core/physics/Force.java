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

import de.edgelord.saltyengine.transform.Vector2f;

/**
 * A <code>Force</code> represents a push to
 * a given {@link de.edgelord.saltyengine.transform.Vector2f vector}
 * with a given acceleration and velocity.
 *
 * The intention is that all <code>Force</code>s
 * of of one <code>MotionState</code> object
 * are added using vector maths.
 */
public class Force {
    private Vector2f direction;
    private float acceleration;
    private float velocity;

    private long accelerationDuration = -1;

    public Force(Vector2f direction, float acceleration, float velocity) {
        this.direction = direction.normalize();
        this.acceleration = acceleration;
        this.velocity = velocity;
    }

    public Force(Vector2f direction, float acceleration) {
        this(direction, acceleration, 0);
    }

    public float deltaDistance(long dt) {

        if (accelerationDuration != -1) {
            if (accelerationDuration - dt <= 0) {
                acceleration = 0;
            }
        }

        float v0 = velocity;
        velocity = v0 + acceleration * dt;

        return velocity * dt;
    }

    public void setAcceleration(float acceleration, long duration) {
        setAcceleration(acceleration);
        accelerationDuration = duration;
    }

    /**
     * Gets {@link #direction}.
     *
     * @return the value of {@link #direction}
     */
    public Vector2f getDirection() {
        return direction;
    }

    /**
     * Sets {@link #direction}.
     *
     * @param direction the new value of {@link #direction}
     */
    public void setDirection(Vector2f direction) {
        this.direction = direction.normalize();
    }

    /**
     * Gets {@link #acceleration}.
     *
     * @return the value of {@link #acceleration}
     */
    public float getAcceleration() {
        return acceleration;
    }

    /**
     * Sets {@link #acceleration}.
     *
     * @param acceleration the new value of {@link #acceleration}
     */
    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    /**
     * Gets {@link #velocity}.
     *
     * @return the value of {@link #velocity}
     */
    public float getVelocity() {
        return velocity;
    }

    /**
     * Sets {@link #velocity}.
     *
     * @param velocity the new value of {@link #velocity}
     */
    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }
}
