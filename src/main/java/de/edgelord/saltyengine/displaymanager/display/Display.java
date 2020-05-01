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

package de.edgelord.saltyengine.displaymanager.display;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.input.MouseInputHandler;
import de.edgelord.saltyengine.utils.SaltySystem;

import javax.swing.*;
import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class Display extends JFrame {

    private final String windowTitle;
    private final GraphicsDevice device = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
    private MouseInputHandler displayMouseHandler = null;
    private boolean fullscreen = false;

    public Display(final DisplayRatio displayRatio, final String windowTitle) {

        getContentPane().setPreferredSize(new Dimension((int) displayRatio.getCurrentDimensions().getWidth(), (int) displayRatio.getCurrentDimensions().getHeight()));
        pack();
        this.windowTitle = windowTitle;
    }

    public void create() {

        setTitle(windowTitle + " [Salty Engine " + SaltySystem.versionTag + "]");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new NativeDisplayListener());
    }

    public MouseInputHandler getDisplayMouseHandler() {
        return displayMouseHandler;
    }

    public void setDisplayMouseHandler(final MouseInputHandler displayMouseHandler) {
        this.displayMouseHandler = displayMouseHandler;
    }

    public boolean isFullscreen() {
        return fullscreen;
    }

    public void setFullscreen(final boolean fullscreen) {
        this.fullscreen = fullscreen;

        if (fullscreen) {
            device.setFullScreenWindow(this);
        } else {
            device.setFullScreenWindow(null);
        }
    }

    public String getWindowTitle() {
        return windowTitle;
    }
}
