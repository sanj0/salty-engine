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
 * <p>
 * The intention is that all <code>Force</code>s
 * of one <code>MotionState</code> object
 * are added using vector maths.
 */
public class Force {

    /**
     * The direction that this <code>Force</code> points to.
     *
     * At any given time, this is a unit vector (direction vector)
     * and therefore has a {@link Vector2f#magnitude() magnitude} of
     * exactly <code>1</code>, as it is {@link Vector2f#normalize() normalized}
     * whenever it is set, will it be in the constructor or using the
     * {@link #setDirection(Vector2f) setter}.
     */
    private Vector2f direction;

    /**
     * The acceleration of this <code>Force</code>.
     */
    private float acceleration;

    /**
     * The current velocity of this force.
     * This value is changed with every {@link #deltaPixels(long) tick}
     * but is also taken into account when the <code>Force</code> is applied.
     */
    private float velocity;

    /**
     * The duration after which the {@link #acceleration}
     * is reset to <code>0</code>.
     * This is triggered using {@link #setAcceleration(float, long)}.
     *
     * @see #setAcceleration(float, long)
     * @see #acceleration
     */
    private long accelerationDuration = -1;

    /**
     * The constructor.
     *
     * @param direction the {@link #direction} of this <code>Force</code>
     * @param acceleration the {@link #acceleration} of this <code>Force</code>
     * @param velocity the {@link #velocity} of this <code>Force</code>
     */
    public Force(Vector2f direction, float acceleration, float velocity) {
        this.direction = direction.normalize();
        this.acceleration = acceleration;
        this.velocity = velocity;
    }

    /**
     * A constructor.
     *
     * @param direction the {@link #direction} of this <code>Force</code>
     * @param acceleration the {@link #acceleration} of this <code>Force</code>
     */
    public Force(Vector2f direction, float acceleration) {
        this(direction, acceleration, 0);
    }

    /**
     * Calculates the delta distance that this Force
     * applies after a given delta time, using real-world physics
     * formulas and laws, but not fully and ultimately.
     * The formula used is:
     * <pre>
     * <code>
     *
     * v0 = v
     * aTotal = a - v0 * DRAG * METERS_TO_PIXELS
     * v = v0 + aTotal * dt
     *
     * dx = METERS_TO_PIXELS * v * dt
     * </code>
     * </pre>
     * with <code>v</code> being the <code>velocity</code> <br>
     * <code>aTotal</code> being the acceleration with drag taken into account <br>
     * <code>a</code> being the {@link #acceleration} <br>
     * <code>DRAG</code> being a {@link World#getDrag() constant} <br>
     * <code>METERS_TO_PIXELS</code> being a unit-conversion {@link World#METERS_TO_PIXELS constant} <br>
     * <code>dt</code> being the given delta time <br>
     * and <code>dx</code> being the returned delta distance converted
     * from (somewhat) meters to pixels.
     *
     * @param dt the time that passed since the last tick
     * @return the delta distance in pixels that this Force applies after the given time
     */
    public float deltaPixels(long dt) {

        float v0 = velocity;

        float accelerationTotal = acceleration - velocity * World.getDrag() * World.METERS_TO_PIXELS;
        velocity = v0 + accelerationTotal * dt;

        if (accelerationDuration != -1) {
            if (accelerationDuration - dt <= 0) {
                acceleration = 0;
            }
        }

        return World.METERS_TO_PIXELS * velocity * dt;
    }

    /**
     * Sets the {@link #acceleration} of this <code>Force</code>
     * to teh given value for a given time, after which
     * it is reset to 0 again.
     * This reset process is cancelled with a call
     * to {@link #setAcceleration(float)} and updated with
     * a call to {@link #setAcceleration(float, long)}.
     *
     * @param acceleration the target acceleration
     * @param duration the duration after which the {@link #acceleration} is reset
     *                 to 0
     */
    public void setAcceleration(float acceleration, long duration) {
        if (duration > 0) {
            setAcceleration(acceleration);
            accelerationDuration = duration;
        }
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
