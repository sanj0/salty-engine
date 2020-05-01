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
import de.edgelord.saltyengine.emitter.EmitterComponent;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.transform.Dimensions;

/**
 * A rectangular <code>Particle</code> with rounded corners. The default {@link Dimensions} is {@link #DEFAULT_DIMENSIONS}
 * and the diameter is {@link #DIAMETER}.
 */
public class RoundRectangleParticle extends Particle {

    /**
     * The default dimensions.
     */
    public static Dimensions DEFAULT_DIMENSIONS = new Dimensions(10f, 10f);

    /**
     * The diameter for the rounded corners
     */
    public static float DIAMETER = 2.5f;

    /**
     * {@inheritDoc}
     */
    public RoundRectangleParticle(final Integer waveNumber, final Integer restLifetime, final Float speed, final EmitterComponent parent) {
        super(waveNumber, restLifetime, speed, parent);

        setDimensions(DEFAULT_DIMENSIONS);
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.drawRoundRect(this, DIAMETER);
    }
}
