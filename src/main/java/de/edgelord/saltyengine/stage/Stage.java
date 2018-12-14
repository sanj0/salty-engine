/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.stage;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.MouseInputHandler;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.awt.RenderingHints.*;

public class Stage extends JPanel {

    private final Container container;
    private final Engine engine;
    private NativeStageMouseListener nativeMouseListener = null;
    private NativeStageMouseMotionListener nativeMouseMotionListener = null;
    private NativeStageMouseWheelListener nativeMouseWheelListener = null;

    private float currentScale = 1f;
    private int originWidth = 0;
    private int originHeight = 0;
    private Dimensions resolution;

    private float lastFps = 0f;
    private int ticks = 0;
    private int fpsRefreshGate = 25;

    private Vector2f currentImgPos = new Vector2f(0, 0);

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

        nativeMouseListener = new NativeStageMouseListener(null);

        nativeMouseMotionListener = new NativeStageMouseMotionListener(null, this);

        nativeMouseWheelListener = new NativeStageMouseWheelListener(null);

        addMouseListener(nativeMouseListener);
        addMouseMotionListener(nativeMouseMotionListener);
        addMouseWheelListener(nativeMouseWheelListener);
    }

    protected void init(int x, int y, int width, int height) {

        setBounds(x, y, width, height);
        this.originWidth = width;
        this.originHeight = height;
        this.resolution = new Dimensions(originWidth, originHeight);
        setBackground(Color.WHITE);
        container.add(this);
        setIgnoreRepaint(true);
        setFocusable(false);

        if (highQuality) {
            renderingHints = new RenderingHints(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
            putToRenderHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
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

    protected void paintComponent(Graphics graphics) {
        ticks++;
        final Graphics2D graphics2D = (Graphics2D) graphics;

        BufferedImage renderedImage = renderToImage();

        float width = Game.getHost().getCurrentWidth();
        float height = Game.getHost().getCurrentHeight();
        currentScale = Math.min(width / originWidth, height / originHeight);
        int imageDisplayWidth = (int) (originWidth * currentScale);
        int imageDisplayHeight = (int) (originHeight * currentScale);
        int xPos = getWidth() / 2 - imageDisplayWidth / 2;
        int yPos = Math.max(getHeight() / 2 - imageDisplayHeight / 2, 0);

        currentImgPos = new Vector2f(xPos, yPos);

        graphics2D.drawImage(renderedImage, xPos, yPos, imageDisplayWidth, imageDisplayHeight, null);
    }

    private void renderToGraphics(Graphics2D graphics2D) {
        graphics2D.setClip(0, 0, originWidth, originHeight);

        graphics2D.setRenderingHints(renderingHints);

        Game.getCamera().setViewToGraphics(graphics2D);

        SaltyGraphics saltyGraphics = new SaltyGraphics(graphics2D);

        engine.render(saltyGraphics);

        if (Game.isDrawFPS()) {
            if (ticks == fpsRefreshGate) {
                lastFps = Time.getFPS();
                ticks = 0;
            }

            saltyGraphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            saltyGraphics.setColor(Color.RED);
            String fps = String.valueOf(Math.round(lastFps));
            saltyGraphics.drawText("FPS: " + fps, 0, (float) saltyGraphics.getFontMetrics().getStringBounds(fps, saltyGraphics.getGraphics2D()).getHeight());
        }
    }

    public BufferedImage renderToImage() {
        BufferedImage image = new BufferedImage(originWidth, originHeight, BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = image.createGraphics();
        renderToGraphics(graphics2D);
        graphics2D.dispose();

        return image;
    }

    public String newScreenshot() {
        String name = "screenshot_";
        name += LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try {
            ImageUtils.saveImage(renderToImage(), ImageUtils.IMAGE_FORMAT_PNG, name, SaltySystem.defaultOuterResource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    public Vector2f getImagePosition() {
        return currentImgPos;
    }

    public float getCurrentScale() {
        return currentScale;
    }

    public void setMouseHandler(final MouseInputHandler mouseHandler) {
        this.nativeMouseListener.setMouseHandler(mouseHandler);
        this.nativeMouseMotionListener.setMouseHandler(mouseHandler);
        this.nativeMouseWheelListener.setMouseHandler(mouseHandler);
    }

    public boolean isHighQuality() {
        return highQuality;
    }

    public void setHighQuality(final boolean highQuality) {
        this.highQuality = highQuality;
    }

    public RenderingHints getRenderHints() {
        return renderingHints;
    }

    public Dimensions getResolution() {
        return resolution;
    }
}
