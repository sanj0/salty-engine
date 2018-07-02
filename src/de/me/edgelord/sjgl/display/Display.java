/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.display;

import de.me.edgelord.sjgl.StaticVars.GameStats;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

public class Display extends JFrame {

    private int width, height;
    private boolean closeRequested = false;
    private ComponentListener resizeListener;
    private int oldWidth;
    private int oldHeight;
    private int newWidth;
    private int newHeight;
    private DisplayManager displayManager;

    public Display(int width, int height, DisplayManager displayManager) {

        this.width = width;
        this.height = height;
        this.oldHeight = height;
        this.oldWidth = width;
        this.displayManager = displayManager;

    }

    public void create() {

        setSize(width, height);
        setLocationRelativeTo(null);
        setResizable(GameStats.withExperimentalFeatures);
        setVisible(true);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

        resizeListener = new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {

                newHeight = e.getComponent().getHeight();
                newWidth = e.getComponent().getWidth();

                System.out.println("Old height , new height: " + oldHeight + " , " + newHeight);

                System.out.println("Display trying to scale the graphics to " + (double) newHeight / oldHeight);

                if (GameStats.withExperimentalFeatures) {

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
}
