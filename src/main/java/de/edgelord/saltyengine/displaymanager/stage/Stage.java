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

package de.edgelord.saltyengine.displaymanager.stage;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameListener;
import de.edgelord.saltyengine.core.GraphicsConfiguration;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.input.MouseInputHandler;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static java.awt.RenderingHints.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class Stage extends JPanel {

    public static RenderingHints hqRenderingHints;
    public static RenderingHints lqRenderingHints;
    private final Container container;
    private final int fpsRefreshGate = 25;
    private NativeStageMouseListener nativeMouseListener = null;
    private NativeStageMouseMotionListener nativeMouseMotionListener = null;
    private NativeStageMouseWheelListener nativeMouseWheelListener = null;
    private float currentScale = 1f;
    private int originWidth = 0;
    private int originHeight = 0;
    private Dimensions resolution;
    private float lastFps = 0f;
    private int ticks = 0;
    /**
     * The current image position is the position
     * of the rendered image within this panel,
     * which is changed by the letterbox scaling.
     */
    private Vector2f currentImgPos = new Vector2f(0, 0);

    public Stage(final Container container) {
        this(container, 0, 0, container.getWidth(), container.getHeight());
    }

    public Stage(final Container container, final int x, final int y, final int width, final int height) {
        this.container = container;
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

    protected void init(final int x, final int y, final int width, final int height) {
        setBounds(x, y, width, height);
        this.originWidth = width;
        this.originHeight = height;
        this.resolution = new Dimensions(originWidth, originHeight);
        setBackground(Color.WHITE);
        container.add(this);
        setIgnoreRepaint(true);
        setFocusable(false);

        hqRenderingHints = new RenderingHints(KEY_ANTIALIASING, VALUE_ANTIALIAS_ON);
        putToHQRenderHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        putToHQRenderHints(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        putToHQRenderHints(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_QUALITY);

        lqRenderingHints = new RenderingHints(KEY_ANTIALIASING, VALUE_ANTIALIAS_OFF);
        putToLQRenderHints(KEY_RENDERING, VALUE_RENDER_SPEED);
        putToLQRenderHints(KEY_ALPHA_INTERPOLATION, VALUE_ALPHA_INTERPOLATION_SPEED);
        putToLQRenderHints(KEY_COLOR_RENDERING, VALUE_COLOR_RENDER_SPEED);

        initNativeMouseListener();
    }

    public void putToHQRenderHints(final Key key, final Object value) {
        hqRenderingHints.put(key, value);
    }

    public void putToLQRenderHints(final Key key, final Object value) {
        lqRenderingHints.put(key, value);
    }

    @Override
    protected void paintComponent(final Graphics graphics) {
        super.paintComponent(graphics);
        ticks++;
        final Graphics2D graphics2D = (Graphics2D) graphics.create();

        final SaltyImage renderedImage = renderToImage();

        final float width = Game.getHost().getCurrentWidth();
        final float height = Game.getHost().getCurrentHeight();
        currentScale = Math.min(width / originWidth, height / originHeight);
        final int imageDisplayWidth = (int) (originWidth * currentScale);
        final int imageDisplayHeight = (int) (originHeight * currentScale);
        final int xPos = getWidth() / 2 - imageDisplayWidth / 2;
        final int yPos = Math.max(getHeight() / 2 - imageDisplayHeight / 2, 0);

        currentImgPos = new Vector2f(xPos, yPos);

        graphics2D.drawImage(renderedImage.getImage(), xPos, yPos, imageDisplayWidth, imageDisplayHeight, null);
        renderedImage.flush();
        graphics2D.dispose();
    }

    private void renderToGraphics(final Graphics2D graphics2D) {
        graphics2D.setClip(0, 0, originWidth, originHeight);
        graphics2D.setRenderingHints(GraphicsConfiguration.renderingHints);

        //Game.getCamera().setViewToGraphics(graphics2D);

        final SaltyGraphics saltyGraphics = new SaltyGraphics(graphics2D);

        //engine.render(saltyGraphics);
        saltyGraphics.drawImage(Game.getCamera().render(SceneManager.getCurrentScene()), 0, 0);

        if (Game.isDrawFPS()) {
            if (ticks == fpsRefreshGate) {
                lastFps = Time.getFPS();
                ticks = 0;
            }

            saltyGraphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 12));
            saltyGraphics.setColor(Color.RED);
            final String fps = String.valueOf(Math.round(lastFps));
            saltyGraphics.drawText("FPS: " + fps, 0, 0, SaltyGraphics.TextAnchor.TOP_LEFT_CORNER);
        }
    }

    public SaltyImage renderToImage() {
        SaltyImage image = SaltySystem.createPreferredImage(originWidth, originHeight);

        final Graphics2D graphics2D = image.createGraphics();
        renderToGraphics(graphics2D);
        graphics2D.dispose();

        for (final GameListener gameListener : Game.getGameListeners()) {
            image = gameListener.onRenderFinish(image);
        }
        return image;
    }

    public String newScreenshot() {
        String name = "screenshot_";
        name += LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try {
            ImageUtils.saveImage(ImageUtils.toBufferedImage(renderToImage().getImage()), ImageUtils.IMAGE_FORMAT_PNG, name, SaltySystem.defaultOuterResource);
        } catch (final IOException e) {
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

    public Dimensions getResolution() {
        return resolution;
    }
}
