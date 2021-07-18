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
import de.edgelord.saltyengine.displaymanager.DisplayManager;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

/**
 * This is the class to implement for the host of a game. The default host is
 * {@link DisplayManager}
 * <p>
 * Every host has to be repaintable, has to provide centre position for better
 * placing of objects inside the game, it has to provide its dimensions, has to
 * be creatable and has to implement {@link #setBackgroundColor(Color)}
 * <p>
 * Apart from that, it can be literally everything. A window, a panel, an
 * applet...
 * <p>
 * A host should also draw the content of {@link Engine}, an example: {@link
 * de.edgelord.saltyengine.displaymanager.stage.Stage#paint(Graphics)}
 */
public abstract class Host implements Repaintable, CentrePositionProvider, CurrentDimensionsProvider, Creatable {

    private boolean fullscreenToggleF = true;

    /**
     * Returns the background color of the game.
     *
     * @return the background color of the game
     */
    public abstract Color getBackgroundColor();

    /**
     * This method sets the background color of the host. This is the color that
     * is seen there, where nothing is drawn.
     *
     * @param color the new background color
     */
    public abstract void setBackgroundColor(Color color);

    public abstract boolean showConfirmDialog(String message);

    /**
     * Returns an {@link ImageObserver} the can be used to e.g. render animated
     * gifs.
     *
     * @return a usable {@link ImageObserver}
     */
    public abstract ImageObserver getImageObserver();

    /**
     * Sets the dimensions of this host
     *
     * @param dimensions the new dimensions
     */
    public abstract void setDimensions(Dimensions dimensions);

    /**
     * Captures a picture of what is currently drawn and safes it relative to
     * {@link de.edgelord.saltyengine.utils.SaltySystem#defaultOuterResource}
     *
     * @return the name of the saved image
     */
    public abstract String takeScreenshot();

    /**
     * Returns a screenshot of the current rendered game without saving it
     * anywhere.
     *
     * @return a screenshot of the game
     */
    public abstract SaltyImage getScreenshot();

    /**
     * Adds the given <code>JComponent</code> to the <code>Host</code>. If the
     * Host does not allow for that, it throws an {@link
     * UnsupportedOperationException}
     *
     * @param component the <code>JComponent</code> to add to the
     *                  <code>Host</code>
     *
     * @throws UnsupportedOperationException if the
     *                                       <code>Host</code>
     *                                       cannot add the
     *                                       <code>JComponent</code>
     */
    public abstract void addComponent(JComponent component) throws UnsupportedOperationException;

    /**
     * Removes the given <code>JComponent</code> from the <code>Host</code>. If
     * the Host does not allow for that, it throws an {@link
     * UnsupportedOperationException}
     *
     * @param component the <code>JComponent</code> to remove from the
     *                  <code>Host</code>
     *
     * @throws UnsupportedOperationException if the
     *                                       <code>Host</code>
     *                                       cannot remove the <code>JComponent</code>
     */
    public abstract void removeComponent(JComponent component) throws UnsupportedOperationException;

    /**
     * This method toggles fullscreen mode. When this method is called and the
     * game is not in fullscreen mode, the fullscreen mode is enabled and after
     * the next disabled again and so on.
     */
    public abstract void toggleFullscreen();

    /**
     * Decides whether the key "F" should toggle fullscreen mode or not
     *
     * @param toggle true if the key "F" should toggle fullscreen mode, false if
     *               not
     *
     * @see #toggleFullscreen()
     */
    public void fToToggleFullscreen(final boolean toggle) {
        this.fullscreenToggleF = toggle;
    }

    public boolean isFullscreenToggleF() {
        return fullscreenToggleF;
    }

    /**
     * Is the host currently in fullscreen?
     *
     * @return whether the host is in fullscreen mode or not
     */
    public abstract boolean isFullscreen();
}
