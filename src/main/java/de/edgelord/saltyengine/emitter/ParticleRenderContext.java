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

package de.edgelord.saltyengine.emitter;

import de.edgelord.saltyengine.core.RenderContext;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;

public abstract class ParticleRenderContext extends RenderContext {
    public ParticleRenderContext(Color color, Paint paint, Stroke stroke) {
        super(color, paint, stroke);
    }

    /**
     * Sets the next configuration ot the given graphics. As for some particles needed, the result may be random generated.
     *
     * @param graphics the graphics to set up.
     * @param subject the particle to be rendered next. This may be used e.g. to have a {@link Particle} specif
     *                configuration set based on its hash.
     */
    public abstract void nextParticleRenderConfig(SaltyGraphics graphics, Particle subject);
}
