/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.stage;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.DisplayMouseHandler;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.awt.event.*;

public class Stage extends Canvas {

    private final Container container;
    private final Engine engine;
    private MouseListener nativeMouseListener = null;
    private MouseMotionListener nativeMouseMotionListener = null;
    private MouseWheelListener nativeMouseWheelListener = null;
    private DisplayMouseHandler mouseHandler = null;
    private boolean doubleBufferCreated = false;

    private boolean highQuality = false;
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

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mouseClicked(e);
                }
            }

            @Override
            public void mousePressed(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mousePressed(e);
                }

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mousePressed(e);
                }
            }

            @Override
            public void mouseReleased(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseReleased(e);
                }

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mouseReleased(e);
                }

                Game.mouseDrags = false;
                Game.mousePresses = false;
            }

            @Override
            public void mouseEntered(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseEntered(e);
                }

                Game.setPaused(false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseExited(e);
                }

                Game.setPaused(true);
            }
        };

        nativeMouseMotionListener = new MouseAdapter() {

            @Override
            public void mouseDragged(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseDragged(e);
                }

                Game.mouseDrags = true;
            }

            @Override
            public void mouseMoved(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseMoved(e);
                }

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mouseMoved(e);
                }

                Game.cursorPosition = new Vector2f(e.getX(), e.getY());
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
            renderingHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_SPEED);
            renderingHints.put(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED);
            renderingHints.put(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_SPEED);
        }

        initNativeMouseListener();
    }

    @Override
    public void paint(Graphics graphics) {

        if (!doubleBufferCreated) {
            createBufferStrategy(3);
            doubleBufferCreated = true;
        }

        final Graphics2D graphics2D = (Graphics2D) getBufferStrategy().getDrawGraphics();
        graphics2D.clearRect(0, 0, getWidth(), getHeight());
        graphics2D.setClip(0, 0, Math.round(Game.getHost().getWidth()), Math.round(Game.getHost().getWidth()));

        graphics2D.setRenderingHints(renderingHints);

        Game.camera.setViewToGraphics(graphics2D);

        SaltyGraphics saltyGraphics = new SaltyGraphics(graphics2D);

        engine.render(saltyGraphics);

        // graphics2D.dispose();
        getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
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
