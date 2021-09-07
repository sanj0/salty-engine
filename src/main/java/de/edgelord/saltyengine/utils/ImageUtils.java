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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.graphics.GraphicsConfiguration;
import de.edgelord.saltyengine.graphics.geom.EnumShape;
import de.edgelord.saltyengine.graphics.geom.SaltyShape;
import de.edgelord.saltyengine.graphics.image.SaltyBufferedImage;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.graphics.sprite.Frame;
import de.edgelord.saltyengine.graphics.sprite.SpritesheetAnimation;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.VolatileImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ImageUtils {

    public static String IMAGE_FORMAT_PNG = "png";
    public static String IMAGE_FORMAT_JPG = "jpg";
    public static String IMAGE_FORMAT_GIF = "gif";

    private ImageUtils() {
    }

    public static SpritesheetAnimation flipAnimation(final SpritesheetAnimation animation, final boolean horizontally, final boolean vertically) {
        final SpritesheetAnimation flipped = new SpritesheetAnimation(new ArrayList<>(animation.getFrames().size()));
        for (final Frame f : animation.getFrames()) {
            flipped.addFrame(new Frame(flip(f.getImage(), horizontally, vertically)));
        }
        return flipped;
    }

    public static SaltyImage flip(final SaltyImage image, final boolean horizontally, final boolean vertically) {
        final AffineTransform tx = AffineTransform.getScaleInstance(horizontally ? -1 : 1, vertically ? -1 : 1);
        tx.translate(horizontally ? -image.getWidth() : 0, vertically ? -image.getHeight() : 0);
        final AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
        return new SaltyBufferedImage(op.filter(image.toBufferedImage(), null));
    }

    public static SaltyImage rotate(final SaltyImage source, final float degrees) {
        final int rotatedImageSize = (int) Math.round(Math.sqrt(GeneralUtil.square(source.getWidth()) +
                GeneralUtil.square(source.getHeight())));
        final SaltyImage rotatedImage = SaltySystem.createPreferredImage(rotatedImageSize, rotatedImageSize);
        final SaltyGraphics graphics = rotatedImage.getGraphics();
        graphics.setBackground(ColorUtil.TRANSPARENT_COLOR);
        graphics.clear(0, 0, rotatedImageSize, rotatedImageSize);
        final AffineTransformOp affineTransformOp =
                new AffineTransformOp(AffineTransform.getRotateInstance(Math.toRadians(degrees), source.getWidth() / 2f, source.getHeight() / 2f),
                        AffineTransformOp.TYPE_BILINEAR);
        return new SaltyBufferedImage(affineTransformOp.filter(source.toBufferedImage(), rotatedImage.toBufferedImage()));
    }

    /**
     * Changes the size of the given image by adding borders of the given color.
     * This does not scale the image but rather enlarges it by embedding it in a
     * bigger images of the given color.
     *
     * @param source the source image to scale
     * @param size   the size to scale the image to
     * @param color  the color to use for the letter box margins
     *
     * @return a new image which has the given image in the centre of an image
     * of the given size and color
     */
    public static SaltyImage letterBoxScale(final SaltyImage source, final Dimensions size, final Color color) {
        final SaltyImage image = SaltySystem.createPreferredImage(size.getWidth(), size.getHeight());
        final SaltyGraphics graphics = image.getGraphics();
        graphics.setColor(color);
        graphics.drawRect(Vector2f.zero(), size);
        final float width = size.getWidth();
        final float height = size.getHeight();
        final float scale = Math.min(width / source.getWidth(), height / source.getHeight());
        final int imageDisplayWidth = (int) (source.getWidth() * scale);
        final int imageDisplayHeight = (int) (source.getHeight() * scale);
        final float xPos = (width - imageDisplayWidth) / 2f;
        final float yPos = Math.max((height - imageDisplayHeight) / 2, 0);
        graphics.drawImage(source, xPos, yPos, imageDisplayWidth, imageDisplayHeight);
        return image;
    }

    /**
     * Creates a new {@link SaltyImage} with the given width and height and
     * draws the given image onto it, filling the new image entirely.
     *
     * @param image         the image to resize
     * @param width         the width of the new image
     * @param height        the height of the new image
     * @param interpolation the interpolation rendering hint to be used
     *
     * @return a new image which contains the old image
     * @see RenderingHints
     */
    public static SaltyImage resize(final SaltyImage image, final float width, final float height, final Object interpolation) {
        return resize(image, width, height, new RenderingHints(RenderingHints.KEY_INTERPOLATION, interpolation));
    }

    /**
     * Creates a new {@link SaltyImage} with the given width and height and
     * draws the given image onto it, filling the new image entirely.
     *
     * @param image  the image to resize
     * @param width  the width of the new image
     * @param height the height of the new image
     * @param hints  the RenderingHints to be used for drawing the scaled image
     *
     * @return a new image which contains the old image
     */
    public static SaltyImage resize(final SaltyImage image, final float width, final float height, final RenderingHints hints) {
        final SaltyImage destination = SaltySystem.createPreferredImage(width, height);
        final Graphics2D graphics2D = destination.createGraphics();
        graphics2D.setRenderingHints(hints);
        final AffineTransform transform = AffineTransform.getScaleInstance((double) width / image.getWidth(), (double) height / image.getHeight());
        graphics2D.drawImage(image.getImage(), transform, null);

        return destination;
    }

    /**
     * Returns a sub-image of the given {@link VolatileImage}.
     *
     * @param base   the base image
     * @param x      the x position of the sub-image
     * @param y      the y position of the sub-image
     * @param width  the width of the sub-image
     * @param height the height of the sub-image
     *
     * @return the sub-image from the given base with the given position and
     * dimensions
     */
    public static VolatileImage getSubImage(final VolatileImage base, final int x, final int y, final int width, final int height) {
        return toVolatileImage(toBufferedImage(base).getSubimage(x, y, width, height));
    }

    /**
     * Generates an image with the given width and height and fills it with
     * rectangles of the given size and a random color from teh given vararg
     * each.
     *
     * @param width       teh width of the returned image
     * @param height      the height of teh returned image
     * @param pixelWidth  the width of the rectangles on the image
     * @param pixelHeight the height of the rectangles on the image
     * @param colors      an array of colors from which to randomly choose one
     *                    per rectangle
     *
     * @return a texture filled with randomly colored rectangles
     */
    public static SaltyImage randomPixelTexture(final float width, final float height, final float pixelWidth, final float pixelHeight, final Color... colors) {
        final SaltyImage texture = SaltySystem.createPreferredImage(width, height);
        final SaltyGraphics graphics = texture.getGraphics();
        final List<Color> colorList = Arrays.asList(colors);
        final int pixelCountWidth = (int) Math.ceil(width / pixelWidth);
        final int pixelCountHeight = (int) Math.ceil(height / pixelHeight);

        for (int i = 0; i < pixelCountHeight; i++) {
            for (int k = 0; k < pixelCountWidth; k++) {
                graphics.setColor(GeneralUtil.randomObjectFromList(colorList));
                graphics.drawRect(k * pixelWidth, i * pixelWidth, pixelWidth, pixelHeight);
            }
        }

        graphics.getGraphics2D().dispose();
        return texture;
    }

    /**
     * Converts the given {@link Image} to a compatible, potentially
     * hardware-accelerated {@link VolatileImage}.
     *
     * @param image the image source
     *
     * @return the given image as a {@link VolatileImage}
     */
    public static VolatileImage toVolatileImage(final Image image) {
        final VolatileImage volatileImage = SaltySystem.createVolatileImage(image.getWidth(Game.getHost().getImageObserver()), image.getHeight(Game.getHost().getImageObserver()));
        copyImageTo(image, volatileImage);

        return volatileImage;
    }

    /**
     * Convert the given {@link Image} to a {@link BufferedImage}.
     *
     * @param image the image source
     *
     * @return the given image as a {@link BufferedImage}
     */
    public static BufferedImage toBufferedImage(final Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        final BufferedImage bufferedImage = SaltySystem.GC.createCompatibleImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
        copyImageTo(image, bufferedImage);

        return bufferedImage;
    }

    /**
     * Copies the given {@link BufferedImage} source into the given {@link
     * VolatileImage} target.
     *
     * @param source the source image
     * @param target the target image
     *
     * @return the given {@link VolatileImage} target which now contains the
     * given source image
     */
    public static VolatileImage copyTo(final BufferedImage source, final VolatileImage target) {
        copyImageTo(source, target);
        return target;
    }

    /**
     * Copies the given {@link VolatileImage} source into the given {@link
     * BufferedImage} target.
     *
     * @param source the source image
     * @param target the target image
     *
     * @return the given {@link BufferedImage} target which now contains the
     * given source image
     */
    public static BufferedImage copyTo(final VolatileImage source, final BufferedImage target) {
        copyImageTo(source, target);
        return target;
    }

    public static void copyImageTo(final Image source, final Image target) {
        final Graphics2D graphics2D = (Graphics2D) target.getGraphics();
        graphics2D.setBackground(ColorUtil.TRANSPARENT_COLOR);
        graphics2D.clearRect(0, 0, source.getWidth(null), source.getHeight(null));
        graphics2D.drawImage(source, 0, 0, target.getWidth(null), target.getHeight(null), null);
        graphics2D.dispose();
    }

    /**
     * Returns the {@link Dimensions} of the given image.
     *
     * @param image the image
     *
     * @return the dimensions of the given image
     */
    public static Dimensions getImageDimensions(final Image image) {
        return new Dimensions(image.getWidth(Game.getHost().getImageObserver()), image.getHeight(Game.getHost().getImageObserver()));
    }

    /**
     * Creates a new {@link SaltyImage} with the given size and draws the given
     * {@link Drawable} onto it.
     *
     * @param drawable       what to draw onto the image
     * @param size           the size of the image
     * @param renderingHints the renderhints to use for drawing the image
     *
     * @return a {@link SaltyImage} with the given size and the given {@link
     * Drawable} performed on it
     */
    public static SaltyImage createPrimitiveImage(final Drawable drawable, final Dimensions size, final RenderingHints renderingHints) {

        final SaltyImage image = SaltySystem.createPreferredImage(size.getWidth(), size.getHeight());

        final Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHints(renderingHints);

        drawable.draw(new SaltyGraphics(graphics2D));
        graphics2D.dispose();

        return image;
    }

    /**
     * Creates a {@link SaltyImage} with the size of the given {@link
     * SaltyShape} and draws it gradient-like into.
     * <p>
     * The given {@link Drawable} is called once before the rendering to e.g.
     * call {@link SaltyGraphics#setColor(Color)} or {@link
     * SaltyGraphics#setPaint(Paint)} on the used {@link Graphics2D} of the
     * image. The given {@link RenderingHints} are also set to the graphics,
     * normally you want to use {@link GraphicsConfiguration#renderingHints} for
     * that.
     * <p>
     * The gradient will be radial and it'll have the given alpha in the centre.
     * The given intensity effects how the gradient looks.
     *
     * @param shape           the {@link SaltyShape} to draw as a gradient
     * @param graphicsPrepare a {@link Drawable} to prepare e.g. the color. It
     *                        can be null.
     * @param renderingHints  hints to define the quality. Most likely, you want
     *                        to use {@link GraphicsConfiguration#renderingHints}
     *                        to have the same quality as the rest of the game
     * @param intensity       the intensity of the gradient. That effects hot it
     *                        looks.
     * @param startAlpha      the alpha value of the centre
     *
     * @return a {@link SaltyImage} containing the given {@link SaltyShape}
     * drawn as a gradient.
     */
    public static SaltyImage createPrimitiveGradient(final SaltyShape shape, final Drawable graphicsPrepare, final RenderingHints renderingHints, final float intensity, final double startAlpha) {

        final SaltyImage image = SaltySystem.createPreferredImage(shape.getWidth(), shape.getWidth());
        final Graphics2D graphics = image.createGraphics();

        if (graphicsPrepare != null) {
            graphicsPrepare.draw(new SaltyGraphics(graphics));
        }
        graphics.setRenderingHints(renderingHints);
        final int red = graphics.getColor().getRed();
        final int green = graphics.getColor().getGreen();
        final int blue = graphics.getColor().getBlue();

        final float radius = shape.getWidth() / 2f;

        for (int i = 0; i < radius; i++) {
            final double luma = 1.0D - ((i + 0.001D) / radius);
            final int alpha = Math.min((int) (startAlpha * luma * intensity), 255);
            graphics.setColor(new Color(red, green, blue, alpha));
            final int currentPos = Math.round(radius - i);
            final int currentSize = i * 2;
            shape.setDimensions(new Dimensions(currentSize, currentSize));
            shape.setPosition(new Vector2f(currentPos, currentPos));
            shape.draw(new SaltyGraphics(graphics));
        }

        return image;
    }

    /**
     * Creates an image of a gradient using {@link #createPrimitiveGradient(SaltyShape,
     * Drawable, RenderingHints, float, double)}. For that, a shape is being
     * created using {@link SaltyShape#createShape(EnumShape, Transform,
     * float...)} with the given {@link EnumShape} and the given {@link
     * Dimensions} with a position at 0|0.
     *
     * @param shapeType       the type of the shape
     * @param graphicsPrepare a {@link Drawable} to prepare e.g. the color. It
     *                        can be null
     * @param renderingHints  hints to define the quality. You normally want to
     *                        use {@link GraphicsConfiguration#renderingHints}
     *                        for the same quality as the rest of the game
     * @param intensity       the intensity of the gradient. This defines how it
     *                        looks.
     * @param startAlpha      the alpha value at the centre
     * @param size            the size of the shape and the image
     * @param arcIfRoundRect  an arc if the given {@link EnumShape} is {@link
     *                        EnumShape#ROUND_RECTANGLE}
     *
     * @return a {@link SaltyImage} containing a {@link SaltyShape} drawn as a
     * gradient.
     */
    public static SaltyImage createPrimitiveGradient(final EnumShape shapeType, final Drawable graphicsPrepare, final RenderingHints renderingHints, final float intensity, final double startAlpha, final Dimensions size, final float... arcIfRoundRect) {

        return createPrimitiveGradient(Objects.requireNonNull(SaltyShape.createShape(shapeType, new Transform(0, 0, size.getWidth(), size.getHeight()), arcIfRoundRect)), graphicsPrepare, renderingHints, intensity, startAlpha);
    }

    public static SaltyImage createPrimitiveGradient(final EnumShape shapeType, final Drawable graphicsPrepare, final RenderingHints renderingHints, final float intensity, final Dimensions size, final float... arcIfRoundRect) {
        return createPrimitiveGradient(shapeType, graphicsPrepare, renderingHints, intensity, 255D, size, arcIfRoundRect);
    }

    public static SaltyImage createPrimitiveGradient(final EnumShape shapeType, final RenderingHints renderingHints, final float intensity, final Dimensions size, final float... arcIfRoundRect) {
        return createPrimitiveGradient(shapeType, saltyGraphics -> {
        }, renderingHints, intensity, 255D, size, arcIfRoundRect);
    }

    public static SaltyImage createPrimitiveGradient(final EnumShape shapeType, final float intensity, final Dimensions size, final float... arcIfRoundRect) {
        return createPrimitiveGradient(shapeType, saltyGraphics -> {
        }, GraphicsConfiguration.renderingHints, intensity, 255D, size, arcIfRoundRect);
    }

    /**
     * Creates and returns an image with the given in it drawn with the given
     * Color. The image is as big as the shape, but the shape is being drawn
     * exactly in the middle, using {@link SaltyShape#drawAtZero(Graphics2D)}.
     *
     * @param shape          the shape to draw to the image
     * @param color          the color with which to draw the shape
     * @param renderingHints the quality to render the shape
     *
     * @return a new image containing the given shape in the given color with
     * the given quality
     */
    public static SaltyImage createShapeImage(final SaltyShape shape, final Color color, final RenderingHints renderingHints) {
        final SaltyImage image = SaltySystem.createPreferredImage(shape.getWidth(), shape.getHeight());

        final Graphics2D graphics = image.createGraphics();
        graphics.setColor(color);
        graphics.setRenderingHints(renderingHints);
        shape.drawAtZero(graphics);
        graphics.dispose();

        return image;
    }

    /**
     * Creates a new {@link SaltyImage} with the size of the given {@link
     * SaltyShape} and draws the shape onto it, with having called the draw
     * method of the given {@link Drawable} before to e.g. set the color or
     * paint.
     *
     * @param shape           the {@link SaltyShape} to be rendered to the
     *                        image.
     * @param graphicsPrepare a {@link Drawable} to prepare e.g. the Color of
     *                        the shape
     *
     * @return a new {@link SaltyImage} with the given {@link SaltyShape} drawn
     * onto.
     */
    public static SaltyImage createShapeImage(final SaltyShape shape, final Drawable graphicsPrepare) {
        final SaltyImage image = SaltySystem.createPreferredImage(shape.getWidth(), shape.getHeight());
        final SaltyGraphics graphics = new SaltyGraphics(image.createGraphics());

        graphicsPrepare.draw(graphics.copy());
        shape.drawAtZero(graphics.getGraphics2D());

        graphics.getGraphics2D().dispose();

        return image;
    }

    /**
     * Creates and returns an image using {@link #createShapeImage(SaltyShape,
     * Color, RenderingHints)}. The {@link SaltyShape} is made using {@link
     * SaltyShape#createShape(EnumShape, Transform, float...)}.
     *
     * @param shape          the shape to be drawn to the image
     * @param size           the size of the shape and image
     * @param color          the color of the shape
     * @param renderingHints the quality of the render
     * @param arcIfRoundRect the ard if the given shape is {@link
     *                       EnumShape#ROUND_RECTANGLE}
     *
     * @return a new image with the given size containing the given shape in the
     * given color with the given quality
     */
    public static SaltyImage createShapeImage(final EnumShape shape, final Dimensions size, final Color color, final RenderingHints renderingHints, final float... arcIfRoundRect) {
        return createShapeImage(SaltyShape.createShape(shape, new Transform(Vector2f.zero(), size), arcIfRoundRect), color, renderingHints);
    }

    /**
     * Saves the given image to a file with the given name relative to the given
     * {@link OuterResource}. It saves it with the given format, use one of
     * those three:
     * <p>
     * {@link #IMAGE_FORMAT_PNG} {@link #IMAGE_FORMAT_JPG} {@link
     * #IMAGE_FORMAT_GIF}
     *
     * @param image    the {@link BufferedImage} to safe
     * @param format   the format of the file
     * @param name     the name of the file
     * @param resource the {@link OuterResource} to get the file from
     *
     * @throws IOException when the I/O process fails
     */
    public static void saveImage(final BufferedImage image, final String format, final String name, final OuterResource resource) throws IOException {
        if (SaltySystem.writePrivilege) {
            ImageIO.write(image, format, resource.getFileResource(name + "." + format));
        }
    }
}
