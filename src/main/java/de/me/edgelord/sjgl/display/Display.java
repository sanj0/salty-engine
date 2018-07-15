/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.display;

import de.me.edgelord.sjgl.input.DisplayMouseHandler;
import de.me.edgelord.sjgl.utils.GameStats;
import de.me.edgelord.sjgl.utils.StaticSystem;

import javax.swing.*;
import java.awt.event.*;

public class Display extends JFrame {

    private int width, height;
    private boolean closeRequested = false;
    private int oldWidth;
    private int oldHeight;
    private int newWidth;
    private int newHeight;
    private DisplayManager displayManager;
    private MouseListener nativeMouseListener;
    private DisplayMouseHandler displayMouseHandler = null;

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

        nativeMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mouseClicked(e);
                }

                if (StaticSystem.currentScene.getUI() != null){
                    StaticSystem.currentScene.getUI().mouseClicked(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mousePressed(e);
                }

                if (StaticSystem.currentScene.getUI() != null){
                    StaticSystem.currentScene.getUI().mousePressed(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mouseReleased(e);
                }

                if (StaticSystem.currentScene.getUI() != null){
                    StaticSystem.currentScene.getUI().mouseReleased(e);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mouseEntered(e);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mouseExited(e);
                }

                GameStats.setPaused(true);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mouseWheelMoved(e);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mouseDragged(e);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (displayMouseHandler != null){
                    displayMouseHandler.mouseMoved(e);
                }
            }
        };

        ComponentListener resizeListener = new ComponentAdapter() {
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
        addMouseListener(nativeMouseListener);
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
