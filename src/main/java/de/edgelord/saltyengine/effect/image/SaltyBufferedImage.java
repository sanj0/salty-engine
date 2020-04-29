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
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;

public class SaltyBufferedImage implements SaltyImage {

    private final BufferedImage image;

    public SaltyBufferedImage(int width, int height) {
        this.image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
    }

    public SaltyBufferedImage(int width, int height, int imageType) {
        this.image = new BufferedImage(width, height, imageType);
    }

    public SaltyBufferedImage(Image image) {
        this.image = ImageUtils.toBufferedImage(image);
    }

    public SaltyBufferedImage(BufferedImage image) {
        this.image = image;
    }

    public SaltyBufferedImage(VolatileImage source) {
        this.image = ImageUtils.toBufferedImage(source);
    }

    public SaltyBufferedImage(String path) {
        this.image = SaltySystem.defaultImageFactory.getImageResource(path).toBufferedImage();
    }

    public SaltyBufferedImage(SaltyImage source) {
        this.image = source.toBufferedImage();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, Vector2f position, float width, float height) {
        saltyGraphics.drawImage(image, position, new Dimensions(width, height));
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, Vector2f position) {
        saltyGraphics.drawImage(image, position);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, float x, float y) {
        saltyGraphics.drawImage(image, x, y);
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public SaltyImage getSubImage(int x, int y, int width, int height) {
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
        return image.getCapabilities(SaltySystem.gfxConfig);
    }

    @Override
    public int getTransparency() {
        return image.getTransparency();
    }

    @Override
    public Object getProperty(String name) {
        return image.getProperty(name);
    }

    @Override
    public Image getScaledInstance(int width, int height, int hints) {
        return image.getScaledInstance(width, height, hints);
    }

    @Override
    public void flush() {
        image.flush();
    }

    @Override
    public ImageCapabilities getCapabilities(GraphicsConfiguration gc) {
        return image.getCapabilities(gc);
    }

    @Override
    public float getAccelerationPriority() {
        return image.getAccelerationPriority();
    }

    @Override
    public void setAccelerationPriority(float priority) {
        image.setAccelerationPriority(priority);
    }
}
