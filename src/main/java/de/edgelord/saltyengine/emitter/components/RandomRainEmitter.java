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

package de.edgelord.saltyengine.emitter.components;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.GeneralUtil;

/**
 * A basic {@link EmitterComponent} that randomly emits {@link Particle}s from
 * the bottom side of its parent and lets them fall down. Both {@link
 * #leftOffset} and {@link #rightOffset} can be used to limit the possible
 * spawning points of new {@link Particle}s. The particles are spawned at the
 * max y value of the parent's transform, meaning at its lowest point. To
 * manipulate that, use {@link #offsetY}
 * <p>
 * The spawned {@link Particle}s are falling down with {@link #getSpeed()}
 * pixels per fixed tick and there are constantly spawning new ones. You can
 * change the spawning rate using {@link #setWaveInterval(int)}.
 * <p>
 * This implementation of {@link EmitterComponent} ignores the suggested spawn
 * point. You can control the actual used spawn point with both offsets.
 */
@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public class RandomRainEmitter extends EmitterComponent {

    /**
     * The offset on the left side
     */
    private float leftOffset = 0f;

    /**
     * the offset of the right side
     */
    private float rightOffset = 0f;

    /**
     * The offset on the y axis for the spawning point of new particles. A
     * positive value means moving the spawning point down, negative meaning to
     * move it up.
     */
    private float offsetY = 0f;

    /**
     * {@inheritDoc}
     */
    public RandomRainEmitter(final ComponentContainer parent, final String name, final Class<? extends Particle> particle, final float speed, final float amount, final int waveInterval) {
        super(parent, name, particle, speed, amount, waveInterval);
    }

    /**
     * {@inheritDoc}
     */
    public RandomRainEmitter(final ComponentContainer parent, final String name, final Class<? extends Particle> particle, final float speed, final float amount) {
        super(parent, name, particle, speed, amount);
    }

    @Override
    public void moveParticle(final Particle particle) {
        particle.basicMove(particle.getSpeed(), Directions.BasicDirection.y);
    }

    @Override
    public void initializeEmitter() {

    }

    @Override
    public Particle spawnParticle() {
        final Particle newParticle = createParticle();
        newParticle.setPosition(newRandomSpawnPoint(newParticle));

        return newParticle;
    }

    private Vector2f newRandomSpawnPoint(final Particle p) {
        return new Vector2f(GeneralUtil.randomInt(getParent().getX() + leftOffset, getParent().getTransform().getMaxX() - p.getWidth() - rightOffset), getParent().getTransform().getMaxY() + offsetY);
    }

    /**
     * Gets {@link #leftOffset}.
     *
     * @return the value of {@link #leftOffset}
     */
    public float getLeftOffset() {
        return leftOffset;
    }

    /**
     * Sets {@link #leftOffset}.
     *
     * @param leftOffset the new value of {@link #leftOffset}
     */
    public void setLeftOffset(final float leftOffset) {
        this.leftOffset = leftOffset;
    }

    /**
     * Gets {@link #rightOffset}.
     *
     * @return the value of {@link #rightOffset}
     */
    public float getRightOffset() {
        return rightOffset;
    }

    /**
     * Sets {@link #rightOffset}.
     *
     * @param rightOffset the new value of {@link #rightOffset}
     */
    public void setRightOffset(final float rightOffset) {
        this.rightOffset = rightOffset;
    }

    /**
     * Gets {@link #offsetY}.
     *
     * @return the value of {@link #offsetY}
     */
    public float getOffsetY() {
        return offsetY;
    }

    /**
     * Sets {@link #offsetY}.
     *
     * @param offsetY the new value of {@link #offsetY}
     */
    public void setOffsetY(final float offsetY) {
        this.offsetY = offsetY;
    }
}
