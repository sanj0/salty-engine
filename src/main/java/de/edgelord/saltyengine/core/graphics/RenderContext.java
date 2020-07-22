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

package de.edgelord.saltyengine.core.graphics;

import de.edgelord.saltyengine.effect.BasicRenderContext;

import java.awt.*;

/**
 * An interface to define a specific state of the
 * configuration of a {@link SaltyGraphics}. A
 * solid implementation is e.g. {@link
 * BasicRenderContext}.
 */
public interface RenderContext {

    /**
     * Applies the configuration described by
     * this
     * <code>RenderContext</code> to the given
     * {@link SaltyGraphics}.
     *
     * @param graphics the graphics to apply the
     *                 config to
     */
    void applyConfiguration(SaltyGraphics graphics);

    /**
     * Creates a new {@link SaltyGraphics} with
     * the given {@link Graphics2D} and applies
     * the configuration described by this
     * <code>RenderContext</code> to it using
     * {@link #applyConfiguration(SaltyGraphics)}.
     *
     * @param graphics2D the backend graphics for
     *                   the {@link SaltyGraphics}
     *                   to render to
     *
     * @return a new {@link SaltyGraphics} from
     * the given {@link Graphics2D} with this
     * <code>RenderContext</code> applied to.
     */
    default SaltyGraphics createGraphics(final Graphics2D graphics2D) {
        final SaltyGraphics graphics = new SaltyGraphics(graphics2D);
        applyConfiguration(graphics);

        return graphics;
    }
}
