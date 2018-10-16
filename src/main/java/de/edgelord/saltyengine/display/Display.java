/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.input.DisplayMouseHandler;
import de.edgelord.saltyengine.utils.StaticSystem;

import javax.swing.*;

public class Display extends JFrame {

    private String windowTitle;
    private boolean closeRequested = false;
    private DisplayMouseHandler displayMouseHandler = null;

    public Display(int width, int height, String windowTitle) {

        setSize(width, height);
        this.windowTitle = windowTitle;
    }

    public void create() {

        setTitle(windowTitle + " [Salty Engine " + StaticSystem.versionTag + "]");
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
    }

    public boolean isCloseRequested() {
        return closeRequested;
    }

    public void setCloseRequested(boolean closeRequested) {
        this.closeRequested = closeRequested;
    }

    public DisplayMouseHandler getDisplayMouseHandler() {
        return displayMouseHandler;
    }

    public void setDisplayMouseHandler(DisplayMouseHandler displayMouseHandler) {
        this.displayMouseHandler = displayMouseHandler;
    }
}
