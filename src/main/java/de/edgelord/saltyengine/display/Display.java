/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.input.DisplayMouseHandler;
import de.edgelord.saltyengine.utils.StaticSystem;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Display extends JFrame {

    private int width, height;
    private String windowTitle;
    private boolean closeRequested = false;
    private DisplayMouseHandler displayMouseHandler = null;

    public Display(int width, int height, String windowTitle) {

        this.width = width;
        this.height = height;
        this.windowTitle = windowTitle;
    }

    public void create() {

        setSize(width, height);
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
