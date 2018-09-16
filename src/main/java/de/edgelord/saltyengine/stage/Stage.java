/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.stage;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.display.Display;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.DisplayMouseHandler;
import de.edgelord.saltyengine.utils.StaticSystem;
import de.edgelord.saltyengine.utils.Time;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Ellipse2D;

public class Stage extends Canvas {

    private final Container container;
    private final Engine engine;
    private double currentZoomX = 1;
    private double currentZoomY = 1;
    private MouseListener nativeMouseListener = null;
    private MouseMotionListener nativeMouseMotionListener = null;
    private MouseWheelListener nativeMouseWheelListener = null;
    private DisplayMouseHandler mouseHandler = null;
    private boolean doubleBufferCreated = false;

    private float fps;
    private String fpsString = "";
    private float startNanos;
    private final float nanosToSeconds = 1000000f;
    private int ticks = 0;

    private boolean highQuality = true;
    private RenderingHints renderingHints;

    public Stage(final Container container, final Engine engine) {
        this(container, engine, 0, 0, container.getWidth(), container.getHeight());
    }

    public Stage(final Container container, final Engine engine, int x, int y, int width, int height) {
        this.container = container;
        this.engine = engine;

        init(x, y, width, height);
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
        };

        nativeMouseMotionListener = new MouseAdapter() {

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

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().mouseMoved(e);
                }
            }
        };

        nativeMouseWheelListener = new MouseAdapter() {

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseWheelMoved(e);
                }
            }
        };

        addMouseListener(nativeMouseListener);
        addMouseMotionListener(nativeMouseMotionListener);
        addMouseWheelListener(nativeMouseWheelListener);
    }

    protected void init(int x, int y, int width, int height) {

        setBounds(x, y, width, height);
        setBackground(Color.WHITE);
        container.add(this);
        setIgnoreRepaint(true);
        setFocusable(false);

        if (highQuality) {
            renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        } else {
            renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        }

        initNativeMouseListener();
    }

    @Override
    public void repaint() {

        startNanos = Time.getDeltaNanos();

        if (!doubleBufferCreated) {
            createBufferStrategy(3);
            doubleBufferCreated = true;
        }

        final Graphics2D graphics2D = (Graphics2D) getBufferStrategy().getDrawGraphics();
        graphics2D.clearRect(0, 0, getWidth(), getHeight());

        graphics2D.setRenderingHints(renderingHints);
        graphics2D.scale(currentZoomX, currentZoomY);

        SaltyGraphics saltyGraphics = new SaltyGraphics(graphics2D);

        engine.render(saltyGraphics);

        // Compute fps

        if (ticks == 50) {
            fps = 1 / ((startNanos) / nanosToSeconds);
            ticks = 0;
        } else {
            ticks++;
        }

        Time.setFps(fps);

        if (StaticSystem.drawFPS) {
            fpsString = String.valueOf(fps);

            graphics2D.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
            graphics2D.setColor(Color.RED);
            graphics2D.drawString(String.valueOf("FPS: " + (int) Time.getFps()), 0, graphics2D.getFontMetrics(graphics2D.getFont()).getAscent());
        }

        graphics2D.dispose();
        getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void scale(final double zoomX, final double zoomY) {

        currentZoomX = zoomX;
        currentZoomY = zoomY;
    }

    public void setMouseHandler(final DisplayMouseHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    public boolean isHighQuality() {
        return highQuality;
    }

    public void setHighQuality(final boolean highQuality) {
        this.highQuality = highQuality;
    }
}
