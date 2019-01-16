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

package de.edgelord.saltyengine.emitter;

import java.util.List;

/**
 * An interface that can modify individual {@link Particle}s in any way, like e.g. its speed or size or even manipulate it directly by moving it.
 * An {@link EmitterComponent} has a list of them stored in and used from {@link EmitterComponent#getModifierStack()}.
 */
public interface ParticleModifier {

    /**
     * Modifies a single {@link Particle}.
     *
     * @param particle the <code>Particle</code> to be modified.
     */
    void modifyParticle(Particle particle);

    /**
     * Calls {@link #modifyParticle(Particle)} for every {@link Particle} in the given list.
     *
     * @param particles the particles to modify.
     */
    default void modifyParticles(List<Particle> particles) {

        for (int i = 0; i < particles.size(); i++) {
            modifyParticle(particles.get(i));
        }
    }
}
