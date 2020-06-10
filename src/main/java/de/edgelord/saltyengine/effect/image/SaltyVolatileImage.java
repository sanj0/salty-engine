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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

public class SaltyVolatileImage implements SaltyImage {

    private VolatileImage image;

    /**
     * Creates a new instance with the given width and height.
     *
     * @param width  the width of the image
     * @param height the height of the image
     */
    public SaltyVolatileImage(final float width, final float height) {
        image = SaltySystem.createVolatileImage(Math.round(width), Math.round(height));
    }

    public SaltyVolatileImage(final Image image) {
        this.image = ImageUtils.toVolatileImage(image);
    }

    /**
     * Creates a new instance.
     *
     * @param source the source for this image
     */
    public SaltyVolatileImage(final BufferedImage source) {
        image = SaltySystem.createVolatileImage(source.getWidth(), source.getHeight());
    }

    /**
     * Creates a new instance.
     *
     * @param source the source for this image
     */
    public SaltyVolatileImage(final VolatileImage source) {
        image = SaltySystem.createVolatileImage(source.getWidth(), source.getHeight());
    }

    /**
     * Creates a new instance with the image located at the given path relative to the project root.
     * The image is obtained using {@link SaltySystem#defaultImageFactory}.
     *
     * @param path the relative path of image
     */
    public SaltyVolatileImage(final String path) {
        this(SaltySystem.defaultImageFactory.getPreferredImageResource(path));
    }

    public SaltyVolatileImage(final SaltyImage source) {
        image = source.toVolatileImage();
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics, final Vector2f position, final float width, final float height) {
        if (contentsLost()) {
            validate(SaltySystem.GC);
        }
        saltyGraphics.drawImage(getImage(), position, new Dimensions(width, height));
    }

    public void draw(final SaltyGraphics saltyGraphics, final Vector2f position) {
        if (contentsLost()) {
            validate(SaltySystem.GC);
        }

        saltyGraphics.drawImage(getImage(), position.getX(), position.getY());
    }

    public void draw(final SaltyGraphics saltyGraphics, final float x, final float y) {
        draw(saltyGraphics.copy(), new Vector2f(x, y));
    }

    public VolatileImage getImage() {
        return image;
    }

    public void setImage(final VolatileImage image) {
        this.image = image;
    }

    @Override
    public SaltyImage getSubImage(final int x, final int y, final int width, final int height) {
        return new SaltyVolatileImage(ImageUtils.getSubImage(image, x, y, width, height));
    }

    @Override
    public BufferedImage toBufferedImage() {
        return ImageUtils.toBufferedImage(image);
    }

    @Override
    public VolatileImage toVolatileImage() {
        return image;
    }

    @Override
    public Object getProperty(final String name) {
        return image.getProperty(name, Game.getHost().getImageObserver());
    }

    @Override
    public Image getScaledInstance(final int width, final int height, final int hints) {
        return image.getScaledInstance(width, height, hints);
    }

    @Override
    public void flush() {
        image.flush();
    }

    @Override
    public ImageCapabilities getCapabilities(final GraphicsConfiguration gc) {
        return image.getCapabilities(gc);
    }

    @Override
    public float getAccelerationPriority() {
        return image.getAccelerationPriority();
    }

    @Override
    public void setAccelerationPriority(final float priority) {
        image.setAccelerationPriority(priority);
    }

    @Override
    public ImageCapabilities getCapabilities() {
        return image.getCapabilities();
    }

    @Override
    public int getTransparency() {
        return image.getTransparency();
    }

    @Override
    public int getWidth() {
        return image.getWidth();
    }

    @Override
    public int getHeight() {
        return image.getHeight();
    }

    @Override
    public Graphics2D createGraphics() {
        return image.createGraphics();
    }

    public BufferedImage getSnapshot() {
        return image.getSnapshot();
    }

    public int validate(final GraphicsConfiguration gc) {
        return image.validate(gc);
    }

    public boolean contentsLost() {
        return image.contentsLost();
    }
}
