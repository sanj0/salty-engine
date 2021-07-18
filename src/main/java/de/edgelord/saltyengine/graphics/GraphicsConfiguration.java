/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.graphics;

import de.edgelord.saltyengine.displaymanager.stage.Stage;

import java.awt.*;

/**
 * Container for static members to configure the quality of any rendering
 * process in the engine.
 */
public class GraphicsConfiguration {

    /**
     * The {@link RenderingHints} that should be used by every render
     * operation.
     */
    public static RenderingHints renderingHints = Stage.hqRenderingHints;
    /**
     * Controls if a {@link de.edgelord.saltyengine.scene.Scene} renders it
     * {@link de.edgelord.saltyengine.graphics.light.LightSystem} or not.
     */
    public static boolean renderLight = true;
    /**
     * Decides whether or not graphics effects should be rendered or not. In the
     * engine, this flag e.g. disables and enables the render process of {@link
     * de.edgelord.saltyengine.emitter.EmitterComponent}
     */
    public static boolean renderGFX = true;

    private GraphicsConfiguration() {
    }
}
