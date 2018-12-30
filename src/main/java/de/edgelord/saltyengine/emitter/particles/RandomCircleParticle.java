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

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.utils.GeneralUtil;

/**
 * A round {@link Particle} with a random diameter between {@link #MIN_DIAMETER} and {@link #MAX_DIAMETER}.
 */
public class RandomCircleParticle extends Particle {

    /**
     * The smallest possible diameter for a new {@link RandomCircleParticle}.
     */
    public static float MIN_DIAMETER = 5f;

    /**
     * The biggest possible diameter for a new {@link RandomCircleParticle}.
     */
    public static float MAX_DIAMETER = 25f;

    /**
     * @param waveNumber the number of the wave that this particle is spawned in.
     */
    public RandomCircleParticle(Integer waveNumber) {
        super(waveNumber);

        int diameter = GeneralUtil.randomInt(MIN_DIAMETER, MAX_DIAMETER);
        setWidth(diameter);
        setHeight(diameter);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawOval(this);
    }
}
