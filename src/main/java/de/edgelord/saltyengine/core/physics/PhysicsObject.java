/*
 * Copyright 2020 Malte Dostal
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

import de.edgelord.saltyengine.core.interfaces.TransformedObject;

/**
 * A <code>PhysicsObject</code> is an object
 * within the virtual {@link World world}, that
 * is effected by other <code>PhysicsObject</code>s
 * within the <code>World</code>, effects other
 * <code>PhysicsObject</code>s in the <code>World</code>
 * and is effected by the <code>World</code> itself.
 */
public class PhysicsObject {

    private TransformedObject parent;
    /*TODO: */private float mass;
    private MotionState motionState;
    /*TODO: */private float surfaceFriction;

    public PhysicsObject(TransformedObject parent) {
        this.parent = parent;
    }

    public void tick(long dt) {
        parent.getPosition().add(motionState.sumForces(dt));
    }

    /**
     * Gets {@link #parent}.
     *
     * @return the value of {@link #parent}
     */
    public TransformedObject getParent() {
        return parent;
    }

    /**
     * Sets {@link #parent}.
     *
     * @param parent the new value of {@link #parent}
     */
    public void setParent(TransformedObject parent) {
        this.parent = parent;
    }

    /**
     * Gets {@link #mass}.
     *
     * @return the value of {@link #mass}
     */
    public float getMass() {
        return mass;
    }

    /**
     * Sets {@link #mass}.
     *
     * @param mass the new value of {@link #mass}
     */
    public void setMass(float mass) {
        this.mass = mass;
    }

    /**
     * Gets {@link #motionState}.
     *
     * @return the value of {@link #motionState}
     */
    public MotionState getMotionState() {
        return motionState;
    }

    /**
     * Sets {@link #motionState}.
     *
     * @param motionState the new value of {@link #motionState}
     */
    public void setMotionState(MotionState motionState) {
        this.motionState = motionState;
    }

    /**
     * Gets {@link #surfaceFriction}.
     *
     * @return the value of {@link #surfaceFriction}
     */
    public float getSurfaceFriction() {
        return surfaceFriction;
    }

    /**
     * Sets {@link #surfaceFriction}.
     *
     * @param surfaceFriction the new value of {@link #surfaceFriction}
     */
    public void setSurfaceFriction(float surfaceFriction) {
        this.surfaceFriction = surfaceFriction;
    }
}
