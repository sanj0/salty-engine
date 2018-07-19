/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.stage;

import de.me.edgelord.sjgl.core.MainLoops;
import de.me.edgelord.sjgl.display.Display;
import de.me.edgelord.sjgl.input.DisplayMouseHandler;
import de.me.edgelord.sjgl.utils.GameStats;
import de.me.edgelord.sjgl.utils.StaticSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

public class Stage extends JPanel {

    private Display display;
    private MainLoops mainLoops;
    private double currentZoomX = 1;
    private double currentZoomY = 1;
    private MouseListener nativeMouseListener = null;
    private DisplayMouseHandler mouseHandler = null;

    public Stage(Display display, MainLoops mainLoops) {
        this.display = display;
        this.mainLoops = mainLoops;

        init();
    }

    private void init() {

        setBounds(0, 0, display.getWidth(), display.getHeight());
        setBackground(Color.WHITE);
        display.add(this);

        nativeMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseClicked(e);
                }

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().mouseClicked(e);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mousePressed(e);
                }

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().mousePressed(e);
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseReleased(e);
                }

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().mouseReleased(e);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseEntered(e);
                }

                GameStats.setPaused(false);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseExited(e);
                }

                GameStats.setPaused(true);
            }

            @Override
            public void mouseWheelMoved(MouseWheelEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseWheelMoved(e);
                }
            }

            @Override
            public void mouseDragged(MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseDragged(e);
                }
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseMoved(e);
                }
            }
        };

        this.addMouseListener(nativeMouseListener);
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);

        Graphics2D graphics2D = (Graphics2D) graphics.create();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        graphics2D.scale(currentZoomX, currentZoomY);

        mainLoops.render(graphics2D);
    }

    public void scale(double zoomX, double zoomY) {

        currentZoomX = zoomX;
        currentZoomY = zoomY;
    }

    public void setMouseHandler(DisplayMouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }
}
