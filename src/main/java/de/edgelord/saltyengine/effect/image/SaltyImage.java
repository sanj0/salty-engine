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
import de.edgelord.saltyengine.core.interfaces.Disposable;
import de.edgelord.saltyengine.effect.Cosmetic;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.VolatileImage;

public class SaltyImage implements Cosmetic, Disposable {

    private VolatileImage image;
    private ImageProducer producer;

    /**
     * Creates a new instance of <code>SaltyImage</code> with a {@link VolatileImage} with the given width and height.
     * After any rendering to this image using e.g. {@link #createGraphics()}, {@link #saveImage()} has to be called
     * in order to make the image reproducible.
     *
     * @param width  the width of the image
     * @param height the height of the image
     */
    public SaltyImage(float width, float height) {
        image = SaltySystem.createVolatileImage(Math.round(width), Math.round(height));
        producer = ImageProducer.fromVolatileImage(image);
    }

    /**
     * Creates a new instance of <code>SaltyImage</code> with an {@link ImageProducer} that produces the image from the
     * given {@link BufferedImage}. To save any changes made after, use {@link #saveImage()}.
     *
     * @param source the source for this image.
     */
    public SaltyImage(BufferedImage source) {
        image = SaltySystem.createVolatileImage(source.getWidth(), source.getHeight());
        producer = ImageProducer.fromBufferedImage(source);
        producer.produceImage(image);
    }

    /**
     * Creates a new instance of <code>SaltyImage</code> with an {@link ImageProducer} that produces the image from the
     * given {@link VolatileImage}. To save any changes made after, use {@link #saveImage()}.
     *
     * @param source the source for this image.
     */
    public SaltyImage(VolatileImage source) {
        image = SaltySystem.createVolatileImage(source.getWidth(), source.getHeight());
        producer = ImageProducer.fromVolatileImage(source);
        producer.produceImage(image);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, Coordinates2f position, float width, float height) {
        saltyGraphics.drawImage(this, position, new Dimensions(width, height));
    }

    public void reproduceImage() {
        producer.produceImage(image);
    }

    @Override
    public void dispose() {
        flush();
    }

    public VolatileImage getImage() {
        return image;
    }

    public void setImage(VolatileImage image) {
        this.image = image;
    }

    public ImageProducer getProducer() {
        return producer;
    }

    public void setProducer(ImageProducer producer) {
        this.producer = producer;
    }

    public VolatileImage getSubImage(int x, int y, int width, int height) {
        return ImageUtils.getSubImage(image, x, y, width, height);
    }

    public SaltyImage subImage(int x, int y, int width, int height) {
        return new SaltyImage(getSubImage(x, y, width, height));
    }

    public BufferedImage toBufferedImage() {
        return ImageUtils.toBufferedImage(image);
    }

    /**
     * Saves the current state of the {@link VolatileImage} to a new {@link ImageProducer} meaning that every custom drawing
     * to the image will be reproduced after a loss of content.
     */
    public void saveImage() {
        producer = ImageProducer.fromBufferedImage(getSnapshot());
    }

    public BufferedImage getSnapshot() {
        return image.getSnapshot();
    }

    public int getWidth() {
        return image.getWidth();
    }

    public int getHeight() {
        return image.getHeight();
    }

    public Graphics getGraphics() {
        return image.getGraphics();
    }

    public Graphics2D createGraphics() {
        return image.createGraphics();
    }

    public int validate(GraphicsConfiguration gc) {
        return image.validate(gc);
    }

    public boolean contentsLost() {
        return image.contentsLost();
    }

    public ImageCapabilities getCapabilities() {
        return image.getCapabilities();
    }

    public int getTransparency() {
        return image.getTransparency();
    }

    public int getWidth(ImageObserver observer) {
        return image.getWidth(observer);
    }

    public int getHeight(ImageObserver observer) {
        return image.getHeight(observer);
    }

    public Object getProperty(String name, ImageObserver observer) {
        return image.getProperty(name, observer);
    }

    public Image getScaledInstance(int width, int height, int hints) {
        return image.getScaledInstance(width, height, hints);
    }

    public void flush() {
        image.flush();
    }

    public ImageCapabilities getCapabilities(GraphicsConfiguration gc) {
        return image.getCapabilities(gc);
    }

    public void setAccelerationPriority(float priority) {
        image.setAccelerationPriority(priority);
    }

    public float getAccelerationPriority() {
        return image.getAccelerationPriority();
    }
}
