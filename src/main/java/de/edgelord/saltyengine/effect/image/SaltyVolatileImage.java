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
    public SaltyVolatileImage(float width, float height) {
        image = SaltySystem.createVolatileImage(Math.round(width), Math.round(height));
    }

    public SaltyVolatileImage(Image image) {
        this.image = ImageUtils.toVolatileImage(image);
    }

    /**
     * Creates a new instance.
     *
     * @param source the source for this image
     */
    public SaltyVolatileImage(BufferedImage source) {
        image = SaltySystem.createVolatileImage(source.getWidth(), source.getHeight());
    }

    /**
     * Creates a new instance.
     *
     * @param source the source for this image
     */
    public SaltyVolatileImage(VolatileImage source) {
        image = SaltySystem.createVolatileImage(source.getWidth(), source.getHeight());
    }

    /**
     * Creates a new instance with the image located at the given path relative to the project root.
     * The image is obtained using {@link SaltySystem#defaultImageFactory}.
     *
     * @param path the relative path of image
     */
    public SaltyVolatileImage(String path) {
        this(SaltySystem.defaultImageFactory.getPreferredImageResource(path));
    }

    public SaltyVolatileImage(SaltyImage source) {
        image = source.toVolatileImage();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, Vector2f position, float width, float height) {
        if (contentsLost()) {
            validate(SaltySystem.gfxConfig);
        }
        saltyGraphics.drawImage(getImage(), position, new Dimensions(width, height));
    }

    public void draw(SaltyGraphics saltyGraphics, Vector2f position) {
        if (contentsLost()) {
            validate(SaltySystem.gfxConfig);
        }

        saltyGraphics.drawImage(getImage(), position.getX(), position.getY());
    }

    public void draw(SaltyGraphics saltyGraphics, float x, float y) {
        draw(saltyGraphics, new Vector2f(x, y));
    }

    public VolatileImage getImage() {
        return image;
    }

    @Override
    public SaltyImage getSubImage(int x, int y, int width, int height) {
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
    public Object getProperty(String name) {
        return image.getProperty(name, Game.getHost().getImageObserver());
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
    public void setAccelerationPriority(float priority) {
        image.setAccelerationPriority(priority);
    }

    @Override
    public float getAccelerationPriority() {
        return image.getAccelerationPriority();
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
    public Graphics getGraphics() {
        return image.getGraphics();
    }

    @Override
    public Graphics2D createGraphics() {
        return image.createGraphics();
    }

    public BufferedImage getSnapshot() {
        return image.getSnapshot();
    }

    public int validate(GraphicsConfiguration gc) {
        return image.validate(gc);
    }

    public boolean contentsLost() {
        return image.contentsLost();
    }

    public void setImage(VolatileImage image) {
        this.image = image;
    }
}
