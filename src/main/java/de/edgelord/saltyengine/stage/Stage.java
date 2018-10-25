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
import de.edgelord.saltyengine.core.interfaces.MouseInputHandler;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Time;

import java.awt.*;
import java.awt.event.*;

import static java.awt.RenderingHints.*;

public class Stage extends Canvas {

    private final Container container;
    private final Engine engine;
    private MouseListener nativeMouseListener = null;
    private MouseMotionListener nativeMouseMotionListener = null;
    private MouseWheelListener nativeMouseWheelListener = null;
    private MouseInputHandler mouseHandler = null;
    private boolean doubleBufferCreated = false;

    private float lastFps = 0f;
    private int ticks = 0;
    private int fpsRefreshGate = 25;

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
                    mouseHandler.mouseEnteredScreen(e);
                }

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mouseEnteredScreen(e);
                }

                Game.setPaused(false);
            }

            @Override
            public void mouseExited(final MouseEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseExitedScreen(e);
                }

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mouseExitedScreen(e);
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

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mouseDragged(e);
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
                Game.cursor = new Transform(Game.cursorPosition, Dimensions.one());
            }
        };

        nativeMouseWheelListener = new MouseAdapter() {

            @Override
            public void mouseWheelMoved(final MouseWheelEvent e) {
                if (mouseHandler != null) {
                    mouseHandler.mouseWheelMoved(e);
                }

                if (SceneManager.getCurrentScene().getUI() != null) {
                    SceneManager.getCurrentScene().getUI().mouseWheelMoved(e);
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
            renderingHints = new RenderingHints(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
            putToRenderHints(KEY_RENDERING, VALUE_RENDER_QUALITY);
        } else {
            renderingHints = new RenderingHints(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);
            putToRenderHints(KEY_RENDERING, VALUE_RENDER_SPEED);
            putToRenderHints(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_SPEED);
            putToRenderHints(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_SPEED);
        }

        initNativeMouseListener();
    }

    public void putToRenderHints(Key key, Object value) {
        renderingHints.put(key, value);
    }

    @Override
    public void paint(Graphics graphics) {

        ticks++;

        if (!doubleBufferCreated) {
            createBufferStrategy(3);
            doubleBufferCreated = true;
        }

        final Graphics2D graphics2D = (Graphics2D) getBufferStrategy().getDrawGraphics();
        graphics2D.clearRect(0, 0, getWidth(), getHeight());
        graphics2D.setClip(0, 0, getWidth(), getWidth());

        graphics2D.setRenderingHints(renderingHints);

        Game.camera.setViewToGraphics(graphics2D);

        SaltyGraphics saltyGraphics = new SaltyGraphics(graphics2D);

        engine.render(saltyGraphics);

        if (Game.isDrawFPS()) {
            if (ticks == fpsRefreshGate) {
                lastFps = Time.getFPS();
                ticks = 0;
            }

            Game.camera.tmpResetViewToGraphics(saltyGraphics);

            saltyGraphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            saltyGraphics.setColor(Color.RED);
            String fps = String.valueOf(Math.round(lastFps));
            saltyGraphics.drawText("FPS: " + fps, 0, (float) saltyGraphics.getFontMetrics().getStringBounds(fps, saltyGraphics.getGraphics2D()).getHeight());
        }

        getBufferStrategy().show();
        Toolkit.getDefaultToolkit().sync();
    }

    public void setMouseHandler(final MouseInputHandler mouseHandler) {
        this.mouseHandler = mouseHandler;
    }

    public boolean isHighQuality() {
        return highQuality;
    }

    public void setHighQuality(final boolean highQuality) {
        this.highQuality = highQuality;
    }
}
