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

package de.edgelord.saltyengine.emitter.modifiers;

import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.emitter.ParticleModifier;
import de.edgelord.saltyengine.utils.Directions;

/**
 * A modifier that adds gravity to the particles by moving them down by {@link
 * #speed} amounts of pixels.
 */
public class GravityParticleModifier implements ParticleModifier {

    private final float speed;

    public GravityParticleModifier(final float speed) {
        this.speed = speed;
    }

    @Override
    public void modifyParticle(final Particle particle) {
        particle.basicMove(speed, Directions.BasicDirection.y);
    }
}
