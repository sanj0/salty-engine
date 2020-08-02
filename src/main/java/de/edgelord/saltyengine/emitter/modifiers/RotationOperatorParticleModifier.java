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

/**
 * A {@link de.edgelord.saltyengine.emitter.ParticleModifier} that extends
 * {@link SpeedOperatorParticleModifier} so that it modifies the {@link
 * Particle}'s rotation instead of its speed.
 */
public class RotationOperatorParticleModifier extends SpeedOperatorParticleModifier {

    /**
     * The constructor.
     *
     * @param factor    the factory to be used as the second part of the math
     *                  operations.
     * @param operation the math operation do apply to each {@link Particle}'s
     *                  speed
     */
    public RotationOperatorParticleModifier(final float factor, final Operation operation) {
        super(factor, operation);
    }

    @Override
    public void modifyParticle(final Particle particle) {

        switch (getOperation()) {

            case ADD:
                particle.setRotationDegrees(particle.getRotationDegrees() + getFactor());
                break;
            case SUBTRACT:
                particle.setRotationDegrees(particle.getRotationDegrees() - getFactor());
                break;
            case DIVIDE:
                particle.setRotationDegrees(particle.getRotationDegrees() / getFactor());
                break;
            case MULTIPLY:
                particle.setRotationDegrees(particle.getRotationDegrees() * getFactor());
                break;
        }
    }
}
