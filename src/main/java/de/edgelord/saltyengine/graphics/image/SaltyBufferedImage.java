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

package de.edgelord.saltyengine.graphics.image;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

/**
 * An implementation of {@link SaltyImage} that uses a {@link BufferedImage} as
 * the underlying storage for the image data.
 */
public class SaltyBufferedImage implements SaltyImage {

    private final BufferedImage image;

    public SaltyBufferedImage(final int width, final int height) {
        this.image = SaltySystem.GC.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

    public SaltyBufferedImage(final int width, final int height, final int imageType) {
        this.image = SaltySystem.GC.createCompatibleImage(width, height, Transparency.TRANSLUCENT);
    }

    public SaltyBufferedImage(final Image image) {
        this.image = ImageUtils.toBufferedImage(image);
    }

    public SaltyBufferedImage(final BufferedImage image) {
        this.image = image;
    }

    public SaltyBufferedImage(final VolatileImage source) {
        this.image = ImageUtils.toBufferedImage(source);
    }

    public SaltyBufferedImage(final String path) {
        this.image = SaltySystem.defaultImageFactory.getImageResource(path).toBufferedImage();
    }

    public SaltyBufferedImage(final SaltyImage source) {
        this.image = source.toBufferedImage();
    }

    private void accelerate() {
        setAccelerationPriority(1);
    }

    @Override
    public Color colorAt(final int x, final int y) {
        return new Color(image.getRGB(x, y));
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics, final Vector2f position, final float width, final float height) {
        saltyGraphics.drawImage(image, position, new Dimensions(width, height));
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics, final Vector2f position) {
        saltyGraphics.drawImage(image, position);
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics, final float x, final float y) {
        saltyGraphics.drawImage(image, x, y);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public SaltyImage getSubImage(final int x, final int y, final int width, final int height) {
        return new SaltyBufferedImage(image.getSubimage(x, y, width, height));
    }

    @Override
    public BufferedImage toBufferedImage() {
        return image;
    }

    @Override
    public VolatileImage toVolatileImage() {
        return ImageUtils.toVolatileImage(image);
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

    @Override
    public ImageCapabilities getCapabilities() {
        return image.getCapabilities(SaltySystem.GC);
    }

    @Override
    public int getTransparency() {
        return image.getTransparency();
    }

    @Override
    public Object getProperty(final String name) {
        return image.getProperty(name);
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
}
