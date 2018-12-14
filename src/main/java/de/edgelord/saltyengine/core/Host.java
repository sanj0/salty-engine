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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.interfaces.CentrePositionProvider;
import de.edgelord.saltyengine.core.interfaces.Creatable;
import de.edgelord.saltyengine.core.interfaces.CurrentDimensionsProvider;
import de.edgelord.saltyengine.core.interfaces.Repaintable;
import de.edgelord.saltyengine.transform.Dimensions;

import java.awt.*;

/**
 * This is the class to implement for the host of a game.
 * The default host is {@link de.edgelord.saltyengine.display.DisplayManager}
 * <p>
 * Every host has to be repaintable, has to provide centre position for better placing of
 * objects inside the game, it has to provide its dimensions, has to be creatable and has to implement {@link #setBackgroundColor(Color)}
 * <p>
 * Apart from that, it can be literally everything. A window, a panel, an applet...
 * <p>
 * A host should also draw the content of {@link Engine}, an example: {@link de.edgelord.saltyengine.stage.Stage#paint(Graphics)}
 */
public abstract class Host implements Repaintable, CentrePositionProvider, CurrentDimensionsProvider, Creatable {

    private boolean fullscreenToggleF = true;

    /**
     * This method sets the background color of the host.
     * This is the color that is seen there, where nothing is drawn over.
     *
     * @param color the new background color
     */
    public abstract void setBackgroundColor(Color color);

    /**
     * @return the {@link RenderingHints} used by this Host to define the quality of the render.
     */
    public abstract RenderingHints getRenderHints();

    /**
     * Sets the dimensions of this host
     *
     * @param dimensions the new dimensions
     */
    public abstract void setDimensions(Dimensions dimensions);

    /**
     * Captures a picture of what is currently drawn and safes it relative to {@link de.edgelord.saltyengine.utils.SaltySystem#defaultOuterResource}
     *
     * @return the name of the saved image
     */
    public abstract String takeScreenshot();

    /**
     * This method toggles fullscreen mode. When this method is called and the game is not in fullscreen mode,
     * the fullscreen mode is enabled and after the next disabled again and so on.
     */
    public abstract void toggleFullscreen();

    /**
     * Decides whether the key "F" should toggle fullscreen mode or not
     *
     * @param toggle true if the key "F" should toggle fullscreen mode, false if not
     * @see #toggleFullscreen()
     */
    public void FToToggleFullscreen(boolean toggle) {
        this.fullscreenToggleF = toggle;
    }

    public boolean isFullscreenToggleF() {
        return fullscreenToggleF;
    }
}
