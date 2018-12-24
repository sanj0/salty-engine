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

import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.utils.Directions;

import java.util.List;

/**
 * A basic {@link EmitterComponent} that randomly emits {@link Particle}s from the bottom side of its parent and let them fall down until they are not visible anymore.
 * Both {@link #offsetX} and {@link #offsetY} can be used to limit the possible spawning point of new {@link Particle}s.
 *
 * The spawned {@link Particle}s are falling down with {@link #speed} pixels per fixed tick and there are constantly spawning new ones.
 * You can the spawning rate using {@link #setWaveDuration(int)}.
 */
public class RandomRainEmitter extends EmitterComponent {

    /**
     * The speed with which the particles fall down.
     */
    private float speed = 0.5f;

    /**
     * The offset on the x axis of the possible spawning point of {@link Particle}s relative to the bottom side of the parent
     * of this component
     */
    private float offsetX = 0f;

    /**
     * The offset on the y axis of the possible spawning point of {@link Particle}s relative to the bottom side of the parent
     * of this component
     */
    private float offsetY = 0f;

    public RandomRainEmitter(ComponentContainer parent, String name, String tag, float amount, int waveDuration) {
        super(parent, name, tag, amount, waveDuration);
    }

    @Override
    public void fixedParticleMove(List<Particle> particles) {
        for (int i = 0; i < particles.size(); i++) {
            Particle particle = particles.get(i);

            particle.basicMove(speed, Directions.BasicDirection.y);
        }
    }

    @Override
    public void initializeEmitter() {

    }

    @Override
    public void startWave() {

    }
}
