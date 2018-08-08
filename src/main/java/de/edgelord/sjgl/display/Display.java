/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.display;

import de.edgelord.sjgl.input.DisplayMouseHandler;
import de.edgelord.sjgl.utils.StaticSystem;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Display extends JFrame {

    private int width, height;
    private String windowTitle;
    private boolean closeRequested = false;
    private int oldWidth;
    private int oldHeight;
    private int newWidth;
    private int newHeight;
    private DisplayManager displayManager;
    private DisplayMouseHandler displayMouseHandler = null;

    public Display(int width, int height, String windowTitle, DisplayManager displayManager) {

        this.width = width;
        this.height = height;
        this.windowTitle = windowTitle;
        this.oldHeight = height;
        this.oldWidth = width;
        this.displayManager = displayManager;

    }

    public void create() {

        setSize(width, height);
        setTitle(windowTitle + "    -- sjgl " + StaticSystem.versionTag);
        setLocationRelativeTo(null);
        setResizable(StaticSystem.withExperimentalFeatures);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        ComponentListener resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                newHeight = e.getComponent().getHeight();
                newWidth = e.getComponent().getWidth();

                System.out.println("Old height , new height: " + oldHeight + " , " + newHeight);

                System.out.println("Display trying to scale the graphics to " + (double) newHeight / oldHeight);

                if (StaticSystem.withExperimentalFeatures) {

                    displayManager.scale((double) newWidth / oldWidth, (double) newHeight / oldHeight);
                }
            }
        };
        addComponentListener(resizeListener);
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
