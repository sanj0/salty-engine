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

package de.edgelord.saltyengine.emitter.particles;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.emitter.Particle;

/**
 * A round {@link Particle} whose default diameter is {@link #DEFAULT_DIAMETER}.
 */
@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class CircleParticle extends Particle {

    /**
     * The default diameter for a new <code>CircleParticle</code>.
     */
    public static float DEFAULT_DIAMETER = 10f;

    /**
     * {@inheritDoc}
     */
    public CircleParticle(Integer waveNumber) {
        super(waveNumber);

        setWidth(DEFAULT_DIAMETER);
        setHeight(DEFAULT_DIAMETER);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawOval(this);
    }
}
