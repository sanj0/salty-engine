/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.effect.image;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

public interface SaltyImage {

    default SaltyGraphics getGraphics() {
        return new SaltyGraphics(createGraphics());
    }

    /**
     * Draws the given {@link Drawable} to the image
     * using {@link Drawable#draw(SaltyGraphics)} like so:
     * {@code drawable.draw(getGraphics())}.
     *
     * @param drawable the {@code Drawable} to draw to this image
     */
    default void drawTo(Drawable drawable) {
        drawable.draw(getGraphics());
    }

    /**
     * Clears the content of this image with a
     * {@link de.edgelord.saltyengine.utils.ColorUtil#TRANSPARENT_COLOR transparent color}.
     */
    default void erase() {
        SaltyGraphics graphics = getGraphics();

        graphics.setBackground(ColorUtil.TRANSPARENT_COLOR);
        graphics.clear(0, 0, getWidth(), getHeight());
        graphics.getGraphics2D().dispose();
    }

    void draw(SaltyGraphics saltyGraphics, Vector2f position, float width, float height);

    void draw(SaltyGraphics saltyGraphics, Vector2f position);

    void draw(SaltyGraphics saltyGraphics, float x, float y);

    Image getImage();

    SaltyImage getSubImage(int x, int y, int width, int height);

    default SaltyImage subImage(int x, int y, int width, int height) {
        return getSubImage(x, y, width, height);
    }

    BufferedImage toBufferedImage();

    VolatileImage toVolatileImage();

    int getWidth();

    int getHeight();

    Graphics2D createGraphics();

    ImageCapabilities getCapabilities();

    int getTransparency();

    default Dimensions getDimensions() {
        return new Dimensions(getWidth(), getHeight());
    }

    Object getProperty(String name);

    Image getScaledInstance(int width, int height, int hints);

    void flush();

    ImageCapabilities getCapabilities(GraphicsConfiguration gc);

    float getAccelerationPriority();

    void setAccelerationPriority(float priority);
}
