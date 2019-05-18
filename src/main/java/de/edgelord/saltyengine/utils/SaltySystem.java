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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.effect.image.SaltyBufferedImage;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.effect.image.SaltyVolatileImage;
import de.edgelord.saltyengine.factory.FontFactory;
import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.awt.image.VolatileImage;

public class SaltySystem {

    public static String versionTag = "0.14.15-SNAPSHOT";
    public static String version = "0.14.15";
    public static VersionMode versionMode = VersionMode.SNAPSHOT;

    public static long fixedTickMillis = 1;

    public static GraphicsConfiguration gfxConfig = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDefaultConfiguration();

    public static boolean audioEnabled = true;

    /**
     * The type of image that should be used by e.g. the {@link de.edgelord.saltyengine.displaymanager.stage.Stage},
     * the {@link ImageUtils} and the {@link de.edgelord.saltyengine.components.rendering.PrimitivesRenderComponent}.
     */
    public static ImageType preferredImageType = ImageType.BUFFERED;

    public static InnerResource defaultResource = new InnerResource();
    public static OuterResource defaultHiddenOuterResource;
    public static OuterResource defaultOuterResource;
    public static ImageFactory defaultImageFactory;
    public static FontFactory defaultFontFactory;

    /**
     * Determines whether to check for events on the cursor entering and leaving a {@link de.edgelord.saltyengine.gameobject.GameObject}
     * which can be very performance-expensive.
     */
    public static boolean gameObjectMouseEventsAgent = true;

    public static Font defaultFont;

    public static VolatileImage createVolatileImage(int width, int height) {

        VolatileImage image = gfxConfig.createCompatibleVolatileImage(width, height, Transparency.TRANSLUCENT);
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setBackground(ColorUtil.TRANSPARENT_COLOR);
        graphics2D.clearRect(0, 0, image.getWidth(), image.getHeight());
        graphics2D.dispose();
        return image;
    }

    /**
     * Sets the {@link Font} {@link #defaultFont} to all {@link UIElement}s in the
     * current {@link de.edgelord.saltyengine.scene.Scene}s {@link de.edgelord.saltyengine.ui.UISystem}
     */
    public static void updateDefaultFontGlobally() {
        updateFontGlobally(defaultFont);
    }

    public static void updateFontGlobally(Font font) {
        for (UIElement uiElement : SceneManager.getCurrentScene().getUI().getElements()) {
            uiElement.setFont(font);
        }
    }

    public static SaltyImage createPreferredImage(float width, float height) {

        int imageWidth = Math.round(width);
        int imageHeight = Math.round(height);

        switch (preferredImageType) {
            case BUFFERED:
                return new SaltyBufferedImage(imageWidth, imageHeight);
            case VOLATILE:
                return new SaltyVolatileImage(width, height);
        }

        return null;
    }

    public static SaltyImage createPreferredImage(String path) {
        switch (preferredImageType) {
            case BUFFERED:
                return new SaltyBufferedImage(path);
            case VOLATILE:
                return new SaltyVolatileImage(path);
        }

        return null;
    }

    public static SaltyImage toPreferredImage(SaltyImage source) {
        switch (preferredImageType) {
            case BUFFERED:
                return new SaltyBufferedImage(source);
            case VOLATILE:
                return new SaltyVolatileImage(source);
        }

        return null;
    }

    public static SaltyImage createPreferredImage(Image source) {
        switch (preferredImageType) {
            case BUFFERED:
                return new SaltyBufferedImage(source);
            case VOLATILE:
                return new SaltyVolatileImage(source);
        }

        return null;
    }

    public enum VersionMode {
        SNAPSHOT,
        ALPHA,
        BETA,
        RELEASE
    }

    /**
     * An enum to specify whether a {@link de.edgelord.saltyengine.effect.image.SaltyBufferedImage buffered image} or a
     * {@link de.edgelord.saltyengine.effect.image.SaltyVolatileImage volatile image} should be used.
     */
    public enum ImageType {

        /**
         * A {@link de.edgelord.saltyengine.effect.image.SaltyBufferedImage buffered image} should be used. <br>
         * While buffered images can potentially be a lot slower, they are stable and do not unload.
         */
        BUFFERED,

        /**
         * A {@link de.edgelord.saltyengine.effect.image.SaltyVolatileImage volatile image} should be used <br>
         * While volatile images can potentially be a lot faster, they are volatile and therefore can be unloaded at any time.
         */
        VOLATILE
    }
}
