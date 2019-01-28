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
import de.edgelord.saltyengine.transform.Dimensions;

/**
 * Does arithmetical operations with {@link #factorDimensions} and the dimensions of each {@link Particle}.
 */
public class SizeOperatorParticleModifier extends SpeedOperatorParticleModifier {

    private Dimensions factorDimensions;

    public SizeOperatorParticleModifier(Dimensions factorDimensions, Operation operation) {
        super(0f, operation);

        this.factorDimensions = factorDimensions;
    }

    @Override
    public void modifyParticle(Particle particle) {

        switch (getOperation()) {

            case ADD:
                particle.getDimensions().add(factorDimensions);
                break;
            case SUBTRACT:
                particle.getDimensions().subtract(factorDimensions);
                break;
            case DIVIDE:
                particle.getDimensions().divide(factorDimensions);
                break;
            case MULTIPLY:
                particle.getDimensions().multiply(factorDimensions);
                break;
        }
    }

    public Dimensions getFactorDimensions() {
        return factorDimensions;
    }

    public void setFactorDimensions(Dimensions factorDimensions) {
        this.factorDimensions = factorDimensions;
    }
}
