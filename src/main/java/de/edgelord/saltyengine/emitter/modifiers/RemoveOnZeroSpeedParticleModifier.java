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

/**
 * A {@link de.edgelord.saltyengine.emitter.ParticleModifier} that removes a {@link de.edgelord.saltyengine.emitter.Particle}
 * when its speed is {@link #gate} or smaller. With the default constructor, the default value for {@link #gate} is <code>0.01f</code>.
 */
public class RemoveOnZeroSpeedParticleModifier implements ParticleModifier {

    private float gate = 0.01f;

    public RemoveOnZeroSpeedParticleModifier() {
    }

    public RemoveOnZeroSpeedParticleModifier(final float gate) {
        this.gate = gate;
    }

    @Override
    public void modifyParticle(final Particle particle) {
        if (particle.getSpeed() <= gate) {
            particle.setRestLifetime(0);
        }
    }

    public float getGate() {
        return gate;
    }

    public void setGate(final float gate) {
        this.gate = gate;
    }
}
