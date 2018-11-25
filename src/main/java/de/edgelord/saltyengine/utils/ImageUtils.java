/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.cosmetic.geom.EnumShape;
import de.edgelord.saltyengine.cosmetic.geom.SaltyShape;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ImageUtils {

    public static String IMAGE_FORMAT_PNG = "png";
    public static String IMAGE_FORMAT_JPG = "jpg";
    public static String IMAGE_FORMAT_GIF = "gif";

    /**
     * Creates a new {@link BufferedImage} with the given size and draws the given {@link Drawable} onto it.
     *
     * @param drawable       what to draw onto the image
     * @param size           the size of the image
     * @param renderingHints the renderhints to use for drawing the image
     * @return a {@link BufferedImage} with the given size and the given {@link Drawable} performed on it
     */
    public static BufferedImage createPrimitiveImage(Drawable drawable, Dimensions size, RenderingHints renderingHints) {

        BufferedImage image = new BufferedImage(Math.round(size.getWidth()), Math.round(size.getHeight()), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHints(renderingHints);

        drawable.draw(new SaltyGraphics(graphics2D));
        graphics2D.dispose();

        return image;
    }

    /**
     * Creates a gradient with the given {@link EnumShape} in a new {@link BufferedImage}.
     * The given {@link Drawable} is executed once before the drawing of the gradient starts, to prepare the graphics
     * with its Color etc.
     * The given float controls the intensity of the gradient. Recommended between 1f and 2f
     * <p>
     * The gradient will be squared, so its width and height will be the same.
     *
     * @param shapeType       the type of shape of the gradient
     * @param graphicsPrepare a {@link Drawable} to prepare the graphics, e.g. set its Color
     * @param renderingHints  the {@link RenderingHints} with which to draw the gradient
     * @param intensity       the intensity of the gradient
     * @param size            the max size of the gradient
     * @param arcIfRoundRect  the arc of the shape if it is {@link EnumShape#ROUND_RECTANGLE}
     * @return a new {@link BufferedImage} with the given size and a gradient with the given shape
     */
    public static BufferedImage createPrimitiveGradient(EnumShape shapeType, Drawable graphicsPrepare, RenderingHints renderingHints, float intensity, double startAlpha, Dimensions size, float... arcIfRoundRect) {

        BufferedImage image = new BufferedImage(Math.round(size.getWidth()), Math.round(size.getHeight()), BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = (Graphics2D) image.getGraphics();

        graphicsPrepare.draw(new SaltyGraphics(graphics));
        graphics.setRenderingHints(renderingHints);
        int red = graphics.getColor().getRed();
        int green = graphics.getColor().getGreen();
        int blue = graphics.getColor().getBlue();

        float radius = size.getWidth() / 2f;

        SaltyShape shape = SaltyShape.createShape(shapeType, Transform.zero(), arcIfRoundRect);

        for (int i = 0; i < radius; i++) {
            double luma = 1.0D - ((i + 0.001D) / radius);
            int alpha = Math.min((int) (startAlpha * luma * intensity), 255);
            graphics.setColor(new Color(red, green, blue, alpha));
            int currentPos = Math.round(radius - i);
            int currentSize = i * 2;
            shape.setDimensions(new Dimensions(currentSize, currentSize));
            shape.setPosition(new Vector2f(currentPos, currentPos));
            shape.draw(new SaltyGraphics(graphics));
        }

        return image;
    }

    public static BufferedImage createPrimitiveGradient(EnumShape shapeType, Drawable graphicsPrepare, RenderingHints renderingHints, float intensity, Dimensions size, float... arcIfRoundRect) {
        return createPrimitiveGradient(shapeType, graphicsPrepare, renderingHints, intensity, 255D, size, arcIfRoundRect);
    }

    public static BufferedImage createPrimitiveGradient(EnumShape shapeType, RenderingHints renderingHints, float intensity, Dimensions size, float... arcIfRoundRect) {
        return createPrimitiveGradient(shapeType, saltyGraphics -> {}, renderingHints, intensity, 255D, size, arcIfRoundRect);
    }

    public static BufferedImage createPrimitiveGradient(EnumShape shapeType, float intensity, Dimensions size, float... arcIfRoundRect) {
        return createPrimitiveGradient(shapeType, saltyGraphics -> {}, Game.getHost().getRenderHints(), intensity, 255D, size, arcIfRoundRect);
    }

    /**
     * Creates and returns an image with the given in it drawn with the given Color.
     * The image is as big as the shape, but the shape is being drawn exactly in the middle, using {@link SaltyShape#drawAtZero(Graphics2D)}.
     *
     * @param shape the shape to draw to the image
     * @param color the color with which to draw the shape
     * @param renderingHints the quality to render the shape
     * @return a new image containing the given shape in the given color with the given quality
     */
    public static BufferedImage createShapeImage(SaltyShape shape, Color color, RenderingHints renderingHints) {
        BufferedImage image = new BufferedImage(Math.round(shape.getWidth()), Math.round(shape.getHeight()), BufferedImage.TYPE_INT_ARGB);

        Graphics2D graphics = image.createGraphics();
        graphics.setColor(color);
        graphics.setRenderingHints(renderingHints);
        shape.drawAtZero(graphics);
        graphics.dispose();

        return image;
    }

    /**
     * Creates and returns an image using {@link #createShapeImage(SaltyShape, Color, RenderingHints)}.
     * The {@link SaltyShape} is made using {@link SaltyShape#createShape(EnumShape, Transform, float...)}.
     *
     * @param shape the shape to be drawn to the image
     * @param size the size of the shape and image
     * @param color the color of the shape
     * @param renderingHints the quality of the render
     * @param arcIfRoundRect the ard if the given shape is {@link EnumShape#ROUND_RECTANGLE}
     * @return a new image with the given size containing the given shape in the given color with the given quality
     */
    public static BufferedImage createShapeImage(EnumShape shape, Dimensions size, Color color, RenderingHints renderingHints, float... arcIfRoundRect) {
        return createShapeImage(SaltyShape.createShape(shape, new Transform(Vector2f.zero(), size), arcIfRoundRect), color, renderingHints);
    }

    /**
     * Saves the given image to a file with the given name relative to the given {@link OuterResource}.
     * It saves it with the given format, use one of those three:
     * <p>
     * {@link #IMAGE_FORMAT_PNG}
     * {@link #IMAGE_FORMAT_JPG}
     * {@link #IMAGE_FORMAT_GIF}
     *
     * @param image    the {@link BufferedImage} to safe
     * @param format   the format of the file
     * @param name     the name of the file
     * @param resource the {@link OuterResource} to get the file from
     * @throws IOException when the I/O process fails
     */
    public static void saveImage(BufferedImage image, String format, String name, OuterResource resource) throws IOException {
        ImageIO.write(image, format, resource.getFileResource(name + "." + format));
    }
}
