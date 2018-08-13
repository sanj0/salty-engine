/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.stage;

import de.edgelord.sjgl.core.Engine;
import de.edgelord.sjgl.display.Display;
import de.edgelord.sjgl.input.DisplayMouseHandler;
import de.edgelord.sjgl.utils.StaticSystem;
import de.edgelord.sjgl.utils.Time;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseWheelEvent;

public class Stage extends Canvas {

    private final Display display;
    private final Engine engine;
    private double currentZoomX = 1;
    private double currentZoomY = 1;
    private MouseListener nativeMouseListener = null;
    private DisplayMouseHandler mouseHandler = null;
    private boolean doubleBufferCreated = false;

    private float fps;
    private String fpsString = "";
    private float deltaNanos;
    private final float nanosToSeconds = 1000000f;
    private int ticks = 0;

    private boolean antialising = true;

    public Stage(final Display display, final Engine engine) {
        this.display = display;
        this.engine = engine;

        init();
    }

    protected void initNativeMouseListener() {

        nativeMouseListener = new MouseAdapter() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseClicked(e);
                }

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().mouseClicked(e);
                }
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mousePressed(e);
                }

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().mousePressed(e);
                }
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseReleased(e);
                }

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().mouseReleased(e);
                }
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseEntered(e);
                }

                StaticSystem.setPaused(false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseExited(e);
                }

                StaticSystem.setPaused(true);
            }

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseWheelMoved(e);
                }
            }

            @Override
            public void mouseDragged(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseDragged(e);
                }
            }

            @Override
            public void mouseMoved(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseMoved(e);
                }
            }
        };

        addMouseListener(nativeMouseListener);
    }

    protected void init() {

        setBounds(0, 0, display.getWidth(), display.getHeight());
        setBackground(Color.WHITE);
        display.add(this);
        setIgnoreRepaint(true);
        setFocusable(false);

        initNativeMouseListener();
    }

    @Override
    public void repaint() {

        if (!doubleBufferCreated) {
            createBufferStrategy(2);
            doubleBufferCreated = true;
        }

        final Graphics2D graphics2D = (Graphics2D) getBufferStrategy().getDrawGraphics();

        graphics2D.clearRect(0, 0, getWidth(), getHeight());

        if (antialising) {
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        }

        graphics2D.scale(currentZoomX, currentZoomY);

        engine.render(graphics2D);

        // Compute fps

        deltaNanos = Time.getDeltaNanos();

        if (ticks == 50) {
            fps = 1 / (deltaNanos / nanosToSeconds);
            ticks = 0;
        } else {
            ticks++;
        }

        Time.setFps(fps);

        if (StaticSystem.drawFPS) {
            fpsString = String.valueOf(fps);

            graphics2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            graphics2D.setColor(Color.RED);
            graphics2D.drawString(String.valueOf("FPS: " + (int) Time.getFps()), 0, (int) graphics2D.getFontMetrics(graphics2D.getFont()).getStringBounds(fpsString, graphics2D).getHeight());
        }

        getBufferStrategy().show();
    }

    public void scale(final double zoomX, final double zoomY) {

        currentZoomX = zoomX;
        currentZoomY = zoomY;
    }

    public void setMouseHandler(final DisplayMouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    public boolean isAntialising() {
        return antialising;
    }

    public void setAntialising(final boolean antialising) {
        this.antialising = antialising;
    }
}
