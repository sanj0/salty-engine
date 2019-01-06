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

package de.edgelord.saltyengine.emitter.particles;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.transform.Dimensions;

/**
 * An oval particle whose default {@link de.edgelord.saltyengine.transform.Dimensions} is {@link #DEFAULT_DIMENSIONS}.
 */
public class OvalParticle extends Particle {

    /**
     * The default dimensions
     */
    public static Dimensions DEFAULT_DIMENSIONS = new Dimensions(5f, 10f);

    /**
     * {@inheritDoc}
     */
    public OvalParticle(Integer waveNumber) {
        super(waveNumber);

        this.setDimensions(DEFAULT_DIMENSIONS);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawOval(this);
    }
}
