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
 * A {@link ParticleModifier} that modifies the <code>speed</code> of the {@link Particle}s with the given {@link Operation}
 * by the given {@link #factor}.
 */
public class SpeedOperatorParticleModifier implements ParticleModifier {

    /**
     * The factory to be used as the second part of the math operations.
     */
    private float factor;

    /**
     * The math operation do apply to each {@link Particle}'s speed.
     */
    private Operation operation;

    /**
     * The constructor.
     *
     * @param factor    the factory to be used as the second part of the math operations.
     * @param operation the math operation do apply to each {@link Particle}'s speed
     */
    public SpeedOperatorParticleModifier(final float factor, final Operation operation) {
        this.factor = factor;
        this.operation = operation;
    }

    @Override
    public void modifyParticle(final Particle particle) {
        switch (operation) {

            case ADD:
                particle.setSpeed(particle.getSpeed() + factor);
                break;
            case SUBTRACT:
                particle.setSpeed(particle.getSpeed() - factor);
                break;
            case DIVIDE:
                particle.setSpeed(particle.getSpeed() / factor);
                break;
            case MULTIPLY:
                particle.setSpeed(particle.getSpeed() * factor);
                break;
        }
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(final Operation operation) {
        this.operation = operation;
    }

    public float getFactor() {
        return factor;
    }

    public void setFactor(final float factor) {
        this.factor = factor;
    }

    /**
     * The kind of math operation to complete with the speed of the particles.
     */
    public enum Operation {
        /**
         * Adding {@link #factor} to the speed of the {@link Particle}s each fixed tick.
         */
        ADD,

        /**
         * Subtracting {@link #factor} from the speed of the {@link Particle}s each fixed tick.
         */
        SUBTRACT,

        /**
         * Dividing the speed of the {@link Particle}s by {@link #factor} each fixed tick.
         */
        DIVIDE,

        /**
         * Multiply the speed of the {@link Particle}s by {@link #factor} each fixed tick.
         */
        MULTIPLY
    }
}
