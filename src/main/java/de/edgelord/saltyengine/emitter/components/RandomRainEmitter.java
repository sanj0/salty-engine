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
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.GeneralUtil;

/**
 * A basic {@link EmitterComponent} that randomly emits {@link Particle}s from the bottom side of its parent and let them fall down.
 * Both {@link #leftOffset} and {@link #rightOffset} can be used to limit the possible spawning point of new {@link Particle}s.
 * The particles are spawned at the max y value of the parent's transform, meaning at its lowest point. To manipulate that,
 * use {@link #offsetY}
 * <p>
 * The spawned {@link Particle}s are falling down with {@link #speed} pixels per fixed tick and there are constantly spawning new ones.
 * You can the spawning rate using {@link #setWaveDuration(int)}.
 */
@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public class RandomRainEmitter extends EmitterComponent {

    /**
     * The speed with which the particles fall down.
     */
    private float speed = 0.5f;

    /**
     * The offset on the left side
     */
    private float leftOffset = 0f;

    /**
     * the offset of the right side
     */
    private float rightOffset = 0f;

    /**
     * The offset on the y axis for the spawning point of new particles.
     * A positive value means moving the spawning point down, negative meaning to move it up.
     */
    private float offsetY = 0f;

    /**
     * Constructs a new {@link RandomRainEmitter}.
     *
     * @param parent       the parent of this component
     * @param name         the id-name for this component
     * @param particle     the kind of particle to be emitted by this emitter.
     *                     this can be obtained by using e.g. {@code CircleParticle.class}
     * @param amount       the amount of particles per wave
     * @param waveDuration the distance between the waves in terms of time
     */
    public RandomRainEmitter(ComponentContainer parent, String name, Class<? extends Particle> particle, float amount, int waveDuration) {
        super(parent, name, particle, amount, waveDuration);
    }

    @Override
    public void moveParticle(Particle particle) {
        particle.basicMove(speed, Directions.BasicDirection.y);
    }

    @Override
    public void initializeEmitter() {

    }

    @Override
    public Particle spawnParticle() {
        Particle newParticle = createParticle();
        newParticle.setPosition(newRandomSpawnPoint(newParticle));

        return newParticle;
    }

    private Coordinates2f newRandomSpawnPoint(Particle p) {
        return new Coordinates2f(GeneralUtil.randomInt(getParent().getX() + leftOffset, getParent().getTransform().getMaxX() - p.getWidth() - rightOffset), getParent().getTransform().getMaxY() + offsetY);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getLeftOffset() {
        return leftOffset;
    }

    public void setLeftOffset(float leftOffset) {
        this.leftOffset = leftOffset;
    }

    public float getRightOffset() {
        return rightOffset;
    }

    public void setRightOffset(float rightOffset) {
        this.rightOffset = rightOffset;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }
}
