/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.emitter.components;

import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.GeneralUtil;

/**
 * An emitter that spawns its {@link Particle}s at the centre of its parent and gives them a random rotation between 0 and 360 degrees.
 * {@link #lockedDirections} are the directions in which the particles can't move.
 */
public class RandomRadialEmitter extends EmitterComponent {

    /**
     * The directions in which the particles aren't allowed to move.
     */
    private Directions lockedDirections = new Directions();

    /**
     * The min angle for the emitted particles.
     */
    private float minAngle = 0;

    /**
     * The max angle for the emitted particles.
     */
    private float maxAngle = 360;

    /**
     * {@inheritDoc}
     */
    public RandomRadialEmitter(final ComponentContainer parent, final String name, final Class<? extends Particle> particle, final float speed, final float amount, final int waveInterval) {
        super(parent, name, particle, speed, amount, waveInterval);
    }

    /**
     * {@inheritDoc}
     */
    public RandomRadialEmitter(final ComponentContainer parent, final String name, final Class<? extends Particle> particle, final float speed, final float amount) {
        super(parent, name, particle, speed, amount);
    }

    @Override
    public void initializeEmitter() {

    }

    @Override
    public Particle spawnParticle() {
        final Particle particle = createParticle();

        particle.positionByCentre(getSpawnPoint());
        particle.setRotationDegrees(GeneralUtil.randomInt(minAngle, maxAngle));
        particle.setLockedDirections(lockedDirections);

        return particle;
    }

    @Override
    public void moveParticle(final Particle particle) {
        particle.moveToFacedDirection(particle.getSpeed());
    }

    public Directions getLockedDirections() {
        return lockedDirections;
    }

    public void setLockedDirections(final Directions lockedDirections) {
        this.lockedDirections = lockedDirections;
    }

    public float getMinAngle() {
        return minAngle;
    }

    public void setMinAngle(final float minAngle) {
        this.minAngle = minAngle;
    }

    public float getMaxAngle() {
        return maxAngle;
    }

    public void setMaxAngle(final float maxAngle) {
        this.maxAngle = maxAngle;
    }
}
