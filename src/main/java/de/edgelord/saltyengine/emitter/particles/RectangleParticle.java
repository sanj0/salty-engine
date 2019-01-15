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
 * A recatngular particle whose default {@link de.edgelord.saltyengine.transform.Dimensions} is {@link #DEFAULT_DIMENSIONS}.
 */
public class RectangleParticle extends Particle {

    /**
     * The default <code>Dimensions</code> for new <code>RectangleParticle</code>s.
     */
    public static Dimensions DEFAULT_DIMENSIONS = new Dimensions(10f, 10f);

    /**
     * {@inheritDoc}
     */
    public RectangleParticle(Integer waveNumber, Float speed) {
        super(waveNumber, speed);

        setDimensions(DEFAULT_DIMENSIONS);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawRect(this);
    }
}
