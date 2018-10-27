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

package de.edgelord.saltyengine.graphics;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class SaltyGraphics {

    private Graphics2D graphics2D;

    public SaltyGraphics(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;
    }

    /*
    Rendering bindings
     */

    /*
    Fill a rect
     */

    /**
     * The base method to DRAW a filled rect. It automatically rounds the given floats to ints
     *
     * @param x      the x position of the rect
     * @param y      the y position of the rect
     * @param width  the width of the rect
     * @param height the height of the rect
     * @see Graphics2D#fillRect(int, int, int, int)
     */
    public void drawRect(float x, float y, float width, float height) {
        graphics2D.fillRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
    }

    /**
     * Draws a filled rect by calling the base method
     *
     * @param transform the transform from which to take the needed information
     * @see #drawRect(float, float, float, float)
     */
    public void drawRect(Transform transform) {
        drawRect(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight());
    }

    /**
     * Draws a filled rect by calling the base method
     *
     * @param position   the position of the rect
     * @param dimensions the dimension of the rect
     * @see #drawRect(float, float, float, float)
     */
    public void drawRect(Vector2f position, Dimensions dimensions) {
        drawRect(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight());
    }

    /**
     * Draws a filled rectangle by calling <code>drawRect(Transform)</code>
     *
     * @param parent the ComponentParent from which to take the Transform
     * @see #drawRect(Transform)
     */
    public void drawRect(ComponentParent parent) {
        drawRect(parent.getTransform());
    }
    
    /*
    Draw a rect
     */

    /**
     * The base method to DRAW the outline of a rect. It automatically rounds the given floats to ints
     *
     * @param x      the x position of the rect
     * @param y      the y position of the rect
     * @param width  the width of the rect
     * @param height the height of the rect
     * @see Graphics2D#drawRect(int, int, int, int)
     */
    public void outlineRect(float x, float y, float width, float height) {
        graphics2D.drawRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
    }

    /**
     * Draws the outline of a rect by calling the base method
     *
     * @param transform the transform from which to take the needed information
     * @see #outlineRect(float, float, float, float)
     */
    public void outlineRect(Transform transform) {
        outlineRect(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight());
    }

    /**
     * Draws the outline of a rect by calling the base method
     *
     * @param position   the position of the rect
     * @param dimensions the dimensions of the rect
     * @see #outlineRect(float, float, float, float)
     */
    public void outlineRect(Vector2f position, Dimensions dimensions) {
        outlineRect(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight());
    }

    /**
     * Draws the outline of a rect by calling the method <code>outlineRect(Transform)</code>
     *
     * @param parent the parent from which to take the transform with the necessary information
     * @see #outlineRect(Transform)
     */
    public void outlineRect(ComponentParent parent) {
        outlineRect(parent.getTransform());
    }
    
    /*
    Fill an oval
     */

    /**
     * The base method to DRAW a filled oval. It automatically rounds the given floats to ints.
     *
     * @param x      the x position of the oval
     * @param y      the y position of the oval
     * @param width  the width of the oval
     * @param height the height of the oval
     * @see Graphics2D#fillOval(int, int, int, int)
     */
    public void drawOval(float x, float y, float width, float height) {
        graphics2D.fillOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
    }

    /**
     * Draws a filled oval by calling the base method
     *
     * @param transform the transform from which to take the needed information
     * @see #drawOval(float, float, float, float)
     */
    public void drawOval(Transform transform) {
        drawOval(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight());
    }

    /**
     * Draws a filled oval by calling the base method
     *
     * @param position   the position of the oval
     * @param dimensions the dimensions of the oval
     * @see #drawOval(float, float, float, float)
     */
    public void drawOval(Vector2f position, Dimensions dimensions) {
        drawOval(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight());
    }

    /**
     * Draws a filled oval by calling <code>drawOval(Transform)</code>
     *
     * @param parent the parent from which to take the Transform to DRAW the oval
     */
    public void drawOval(ComponentParent parent) {
        drawOval(parent.getTransform());
    }
    
    /*
    Draw an oval
     */

    /**
     * The base method to DRAW the outline of an oval. It automatically rounds the given floats to ints.
     *
     * @param x      the x position of the oval
     * @param y      the y position of the oval
     * @param width  the width of the oval
     * @param height the height of the oval
     * @see Graphics2D#drawOval(int, int, int, int)
     */
    public void outlineOval(float x, float y, float width, float height) {
        graphics2D.drawOval(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
    }

    /**
     * Draws the outline of an oval by calling the base method
     *
     * @param transform the transform from which to take the needed information
     * @see #outlineOval(float, float, float, float)
     */
    public void outlineOval(Transform transform) {
        outlineOval(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight());
    }

    /**
     * Draws the outline of an oval by calling the base method
     *
     * @param position   the position of the oval
     * @param dimensions the dimensions of the oval
     * @see #outlineOval(float, float, float, float)
     */
    public void outlineOval(Vector2f position, Dimensions dimensions) {
        outlineOval(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight());
    }

    /**
     * Draws the outline of an oval by calling <code>outlineOval(Transform)</code>
     *
     * @param parent the ComponentParent from which to take the transform
     * @see #outlineOval(Transform)
     */
    public void outlineOval(ComponentParent parent) {
        outlineOval(parent.getTransform());
    }

    /*
    Draw an image
     */

    /**
     * The base method to DRAW an image. It automatically rounds the given floats to ints.
     *
     * @param image  the image to DRAW
     * @param x      the x position of the image
     * @param y      the y position of the image
     * @param width  the width with which the image should be drawn
     * @param height the height with which the image should be drawn
     * @see Graphics2D#drawImage(Image, int, int, int, int, ImageObserver)
     */
    public void drawImage(BufferedImage image, float x, float y, float width, float height) {
        graphics2D.drawImage(image, Math.round(x), Math.round(y), Math.round(width), Math.round(height), null);
    }

    /**
     * Draws an image by calling the base method. It will be drawn with its original width and height
     *
     * @param image    the image to be drawn
     * @param position the position of the image
     * @see #drawImage(BufferedImage, float, float, float, float)
     */
    public void drawImage(BufferedImage image, Vector2f position) {
        drawImage(image, position.getX(), position.getY(), Math.round(image.getWidth()), Math.round(image.getHeight()));
    }

    /**
     * Draws an image by calling the base method
     *
     * @param image     the image to be drawn
     * @param transform the transform from which to take the position and dimensions of the image to be drawn with
     * @see #drawImage(BufferedImage, float, float, float, float)
     */
    public void drawImage(BufferedImage image, Transform transform) {
        drawImage(image, transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight());
    }

    /**
     * Draws an image by calling the base method
     *
     * @param image      the image to be drawn
     * @param position   the position of the image
     * @param dimensions the dimensions with to DRAW the image
     */
    public void drawImage(BufferedImage image, Vector2f position, Dimensions dimensions) {
        drawImage(image, position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight());
    }

    /**
     * Draws an image by calling <code>outlineImage(BufferedImage, Transform)</code>
     *
     * @param image  the image to be drawn
     * @param parent the ComponentParent from which to take the Transform to DRAW the image
     */
    public void drawImage(BufferedImage image, ComponentParent parent) {
        drawImage(image, parent.getTransform());
    }

    /*
    Fill a round rect
     */

    /**
     * The base method to DRAW a filled round rect. It automatically rounds the given floats to ints
     *
     * @param x         the x position of the round rect
     * @param y         the y position of the round rect
     * @param width     the width of the round rect
     * @param height    the height of the round rect
     * @param arcWidth  the horizontal diameter of the arc
     * @param arcHeight the vertical diameter of the arc
     * @see Graphics2D#fillRoundRect(int, int, int, int, int, int)
     */
    public void drawRoundRect(float x, float y, float width, float height, float arcWidth, float arcHeight) {
        graphics2D.fillRoundRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height), Math.round(arcWidth), Math.round(arcHeight));
    }

    /**
     * The base method to DRAW a filled round rect with the same arc both horizontally and vertically. It automatically rounds the given floats to ints
     *
     * @param x      the x position of the round rect
     * @param y      the y position of the round rect
     * @param width  the width of the round rect
     * @param height the height of the round rect
     * @param arc    the diameter for the arc both horizontally and vertically
     * @see #drawRoundRect(float, float, float, float, float)
     */
    public void drawRoundRect(float x, float y, float width, float height, float arc) {
        drawRoundRect(x, y, width, height, arc, arc);
    }

    /**
     * Draws a filled round rect by calling the base method
     *
     * @param transform the transform of the round rect
     * @param arcWidth  the diameter of the horizontal arc
     * @param arcHeight the diameter of the vertical arc
     * @see #drawRoundRect(float, float, float, float, float, float)
     */
    public void drawRoundRect(Transform transform, float arcWidth, float arcHeight) {
        drawRoundRect(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight(), arcWidth, arcHeight);
    }

    /**
     * Draws a filled round rect with the same horizontal and vertical arc by calling the base method
     *
     * @param transform the transform of the round rect
     * @param arc       the horizontal and vertical diameter of the arc
     * @see #drawRoundRect(float, float, float, float, float, float)
     */
    public void drawRoundRect(Transform transform, float arc) {
        drawRoundRect(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight(), arc, arc);
    }

    /**
     * Draws a filled rect by calling the base method
     *
     * @param position   the position of the round rect
     * @param dimensions the dimensions of the round rect
     * @param arcWidth   the diameter of the horizontal arc
     * @param arcHeight  the diameter of the vertical arc
     * @see #drawRoundRect(float, float, float, float, float, float)
     */
    public void drawRoundRect(Vector2f position, Dimensions dimensions, float arcWidth, float arcHeight) {
        drawRoundRect(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight(), arcWidth, arcHeight);
    }

    /**
     * Draws a filled round rect with the same horizontal and vertical arc by calling the base method
     *
     * @param position   the position of the round rect
     * @param dimensions the dimensions of the round rect
     * @param arc        the horizontal and vertical diameter of the arc
     * @see #drawRoundRect(float, float, float, float, float, float)
     */
    public void drawRoundRect(Vector2f position, Dimensions dimensions, float arc) {
        drawRoundRect(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight(), arc, arc);
    }

    /**
     * Draws a filled round rect by calling <code>drawRoundRect(Transform, float, float)</code>
     *
     * @param parent    the parent from which to take the transform
     * @param arcWidth  the diameter of the horizontal arc
     * @param arcHeight the diameter of the vertical arc
     * @see #drawRoundRect(Transform, float, float)
     */
    public void drawRoundRect(ComponentParent parent, float arcWidth, float arcHeight) {
        drawRoundRect(parent.getTransform(), arcWidth, arcHeight);
    }

    /**
     * Draws a filled round rect with the same horizontal and vertical arc by calling <code>drawRoundRect(Transform, float, float)</code>
     *
     * @param parent the parent from which to take the transform
     * @param arc    the horizontal and vertical diameter of the arc
     * @see #drawRoundRect(Transform, float, float)
     */
    public void drawRoundRect(ComponentParent parent, float arc) {
        drawRoundRect(parent.getTransform(), arc, arc);
    }
    
    /*
    Draw a round rect
     */

    /**
     * The base method to DRAW the outline of a round rect. It automatically rounds the given floats to ints
     *
     * @param x         the x position of the round rect
     * @param y         the y position of the round rect
     * @param width     the width of the round rect
     * @param height    the height of the round rect
     * @param arcWidth  the horizontal diameter of the arc
     * @param arcHeight the vertical diameter of the arc
     * @see Graphics2D#drawRoundRect(int, int, int, int, int, int)
     */
    public void outlineRoundRect(float x, float y, float width, float height, float arcWidth, float arcHeight) {
        graphics2D.drawRoundRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height), Math.round(arcWidth), Math.round(arcHeight));
    }

    /**
     * The base method to DRAW a round rect with the same arc both horizontally and vertically. It automatically rounds the given floats to ints
     *
     * @param x      the x position of the round rect
     * @param y      the y position of the round rect
     * @param width  the width of the round rect
     * @param height the height of the round rect
     * @param arc    the diameter for the arc both horizontally and vertically
     * @see Graphics2D#drawRoundRect(int, int, int, int, int, int)
     */
    public void outlineRoundRect(float x, float y, float width, float height, float arc) {
        outlineRoundRect(x, y, width, height, arc, arc);
    }

    /**
     * Draws the outline of a round rect by calling the base method
     *
     * @param transform the transform of the round rect
     * @param arcWidth  the diameter of the horizontal arc
     * @param arcHeight the diameter of the vertical arc
     * @see #outlineRoundRect(float, float, float, float, float, float)
     */
    public void outlineRoundRect(Transform transform, float arcWidth, float arcHeight) {
        outlineRoundRect(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight(), arcWidth, arcHeight);
    }

    /**
     * Draws the outline of a round rect with the same horizontal and vertical arc by calling the base method
     *
     * @param transform the transform of the round rect
     * @param arc       the horizontal and vertical diameter of the arc
     * @see #outlineRoundRect(float, float, float, float, float, float)
     */
    public void outlineRoundRect(Transform transform, float arc) {
        outlineRoundRect(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight(), arc, arc);
    }

    /**
     * Draws the outline of a round rect by calling the base method
     *
     * @param position   the position of the round rect
     * @param dimensions the dimensions of the round rect
     * @param arcWidth   the diameter of the horizontal arc
     * @param arcHeight  the diameter of the vertical arc
     * @see #outlineRoundRect(float, float, float, float, float, float)
     */
    public void outlineRoundRect(Vector2f position, Dimensions dimensions, float arcWidth, float arcHeight) {
        outlineRoundRect(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight(), arcWidth, arcHeight);
    }

    /**
     * Draws the outline of a round rect with the same horizontal and vertical arc by calling the base method
     *
     * @param position   the position of the round rect
     * @param dimensions the dimensions of the round rect
     * @param arc        the horizontal and vertical diameter of the arc
     * @see #outlineRoundRect(float, float, float, float, float, float)
     */
    public void outlineRoundRect(Vector2f position, Dimensions dimensions, float arc) {
        outlineRoundRect(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight(), arc, arc);
    }

    /**
     * Draws the outline of a round rect by calling <code>drawRoundRect(Transform, float, float)</code>
     *
     * @param parent    the parent from which to take the transform
     * @param arcWidth  the diameter of the horizontal arc
     * @param arcHeight the diameter of the vertical arc
     * @see #outlineRoundRect(Transform, float, float)
     */
    public void outlineRoundRect(ComponentParent parent, float arcWidth, float arcHeight) {
        outlineRoundRect(parent.getTransform(), arcWidth, arcHeight);
    }

    /**
     * Draws the outline of a round rect with the same horizontal and vertical arc by calling <code>drawRoundRect(Transform, float, float)</code>
     *
     * @param parent the parent from which to take the transform
     * @param arc    the horizontal and vertical diameter of the arc
     * @see #outlineRoundRect(Transform, float, float)
     */
    public void outlineRoundRect(ComponentParent parent, float arc) {
        outlineRoundRect(parent.getTransform(), arc, arc);
    }

    /**
     * The base method to clear a rect.
     * Clears the given rect from the graphics.
     *
     * @param x      the x position of the rect to be cleared
     * @param y      the y position of the rect to be cleared
     * @param width  the width of the rect to be cleared
     * @param height the height of the rect to be cleared
     * @see Graphics2D#clearRect(int, int, int, int)
     */
    public void clear(float x, float y, float width, float height) {
        graphics2D.clearRect(Math.round(x), Math.round(y), Math.round(width), Math.round(height));
    }

    /**
     * Clears a rect build from the given transform by calling the base method.
     *
     * @param transform the {@link Transform} of the rect to be cleared
     * @see #clear(float, float, float, float)
     */
    public void clear(Transform transform) {
        clear(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight());
    }

    /*
    Binding for drawing Shapes and lines
     */

    /**
     * Draws the given Shape
     *
     * @param shape the shape to be drawn
     * @see Graphics2D#draw(Shape)
     */
    public void outlineShape(Shape shape) {
        graphics2D.draw(shape);
    }

    /**
     * Fills the given Shape
     *
     * @param shape the shape to be drawn
     * @see Graphics2D#draw(Shape)
     */
    public void drawShape(Shape shape) {
        graphics2D.fill(shape);
    }

    /**
     * The base method to DRAW a line. The given floats are automatically rounded to ints.
     * This method outlines a line from point p1(x1 | y1) to point p2(x2 | y2)
     *
     * @param x1 x position of the start point
     * @param y1 y position of the start point
     * @param x2 x position of the end point
     * @param y2 y position of the end point
     * @see Graphics2D#drawLine(int, int, int, int)
     */
    public void drawLine(float x1, float y1, float x2, float y2) {
        graphics2D.drawLine(Math.round(x1), Math.round(y1), Math.round(x2), Math.round(y2));
    }

    /**
     * The base method to DRAW a Line with a temporary Stroke. The given floats are automatically rounded to ints.
     * Before the line is drawn, the current stroke gets fetched and the temporary one is set,
     * after the line is drawn, the fetched stroke gets set again.
     *
     * @param x1              x position of the start point
     * @param y1              y position of the start point
     * @param x2              x position of the end point
     * @param y2              y position of the end point
     * @param temporaryStroke the temporary Stroke with which to DRAW the line
     * @see #drawLine(float, float, float, float)
     * @see #getStroke()
     * @see #setStroke(Stroke)
     */
    public void drawLine(float x1, float y1, float x2, float y2, Stroke temporaryStroke) {

        Stroke stroke = getStroke();

        setStroke(temporaryStroke);
        drawLine(x1, y1, x2, y2);

        setStroke(stroke);
    }

    /**
     * Draws a line by calling the base method
     *
     * @param startPoint the start point of the line
     * @param endPoint   the end point of the line
     */
    public void drawLine(Vector2f startPoint, Vector2f endPoint) {
        drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY());
    }

    /**
     * Draws a line with a temporary Stroke by calling the base method
     *
     * @param startPoint      the start point of the line
     * @param endPoint        the end point of the line
     * @param temporaryStroke the temporary Stroke with which to DRAW the line
     * @see #drawLine(float, float, float, float, Stroke)
     */
    public void drawLine(Vector2f startPoint, Vector2f endPoint, Stroke temporaryStroke) {
        drawLine(startPoint.getX(), startPoint.getY(), endPoint.getX(), endPoint.getY(), temporaryStroke);
    }

    /**
     * Draws a line by calling the base method
     *
     * @param x1       x position of the start point
     * @param y1       y position of the start point
     * @param endPoint the end point
     * @see #drawLine(float, float, float, float)
     */
    public void drawLine(float x1, float y1, Vector2f endPoint) {
        drawLine(x1, y1, endPoint.getX(), endPoint.getY());
    }

    /**
     * Draws a line with a temporary Stroke by calling the base method
     *
     * @param x1              x position of the start point
     * @param y1              y position of the start point
     * @param endPoint        the end point
     * @param temporaryStroke the temporary Stroke with which to DRAW the line
     * @see #drawLine(float, float, float, float, Stroke)
     */
    public void drawLine(float x1, float y1, Vector2f endPoint, Stroke temporaryStroke) {
        drawLine(x1, y1, endPoint.getX(), endPoint.getY(), temporaryStroke);
    }

    /**
     * Draws a line by calling the base method
     *
     * @param startPoint the end point
     * @param x2         x position of the end point
     * @param y2         y position of the end point
     * @see #drawLine(float, float, float, float)
     */
    public void drawLine(Vector2f startPoint, float x2, float y2) {
        drawLine(startPoint.getX(), startPoint.getY(), x2, y2);
    }

    /**
     * Draws a line with a temporary Stroke by calling the base method
     *
     * @param startPoint      the end point
     * @param x2              x position of the end point
     * @param y2              y position of the end point
     * @param temporaryStroke the temporary Stroke with which to DRAW the line
     * @see #drawLine(float, float, float, float, Stroke)
     */
    public void drawLine(Vector2f startPoint, float x2, float y2, Stroke temporaryStroke) {
        drawLine(startPoint.getX(), startPoint.getY(), x2, y2, temporaryStroke);
    }

    /*
    Bindings for drawing text
     */

    /**
     * The base method to DRAW a String using the current <code>Font</code> of the graphics.
     * It converts the given {@link Object} to a {@link String} using its {@link Object#toString()}.
     * When the given <code>Object</code> is already a <code>String</code>,
     * <code>toString()</code> simply returns that exact <code>String</code>.
     *
     * @param text the String to be drawn
     * @param x    the x position of the baseLine of the first character
     * @param y    the y position of the baseLine of the first character
     * @see Graphics2D#drawString(String, float, float)
     */
    public void drawText(Object text, float x, float y) {
        graphics2D.drawString(text.toString(), x, y);
    }

    /**
     * The base method to DRAW a String using a temporary <code>Font</code>
     *
     * @param text          the String to be drawn
     * @param x             the x position of the baseLine of the first character
     * @param y             the y position of the baseLine of the first character
     * @param temporaryFont the temporary Font with which to DRAW the text
     * @see #drawText(Object, float, float)
     */
    public void drawText(Object text, float x, float y, Font temporaryFont) {

        Font font = getFont();

        setFont(temporaryFont);
        drawText(text, x, y);

        setFont(font);
    }

    /**
     * Draws the given text by calling the base method
     *
     * @param text     the text to be drawn
     * @param position the position of the baseLine of the first letter
     * @see #drawText(Object, float, float)
     */
    public void drawText(Object text, Vector2f position) {
        drawText(text, position.getX(), position.getY());
    }

    /**
     * Draws the given text with the given temporary Font by calling the base method
     *
     * @param text          the text to be drawn
     * @param position      the position of the baseLine of the first letter
     * @param temporaryFont the temporary Font with which to DRAW the text
     * @see #drawText(Object, float, float, Font)
     */
    public void drawText(Object text, Vector2f position, Font temporaryFont) {
        drawText(text, position.getX(), position.getY(), temporaryFont);
    }

    /*
    Bindings for setting the configuration of the drawing context
     */

    /**
     * Sets the color of the Graphics
     *
     * @param color the color to be set the graphics to
     * @see Graphics2D#setColor(Color)
     */
    public void setColor(Color color) {
        graphics2D.setColor(color);
    }

    /**
     * Sets the <code>Font</code> of the graphics
     *
     * @param font the new defaultFont
     */
    public void setFont(Font font) {
        graphics2D.setFont(font);
    }

    /**
     * Sets the stroke of the Graphics
     *
     * @param stroke the stroke to be set the graphics to
     * @see Graphics2D#setStroke(Stroke)
     */
    public void setStroke(Stroke stroke) {
        graphics2D.setStroke(stroke);
    }

    /**
     * Sets the paint of the graphics
     *
     * @param paint the new paint for the graphics
     * @see Graphics2D#setPaint(Paint)
     */
    public void setPaint(Paint paint) {
        graphics2D.setPaint(paint);
    }

    /**
     * Sets the Composite of the Graphics
     *
     * @param composite the new composite for the graphics
     * @see Graphics2D#setComposite(Composite)
     */
    public void setComposite(Composite composite) {
        graphics2D.setComposite(composite);
    }

    /**
     * Sets the composite of the Graphics
     *
     * @param composite the new composite for the graphics
     * @see Graphics2D#setComposite(Composite)
     */
    public void setAlphaComposite(AlphaComposite composite) {
        graphics2D.setComposite(composite);
    }

    /**
     * Sets the background of the graphics
     *
     * @param color the new background color
     * @see Graphics2D#setBackground(Color)
     */
    public void setBackground(Color color) {
        graphics2D.setBackground(color);
    }

    /**
     * The base method to set the clipping of the graphics.
     * The graphics will from then only be repainted in the specified Shape.
     * This can be used multiple times during the rendering process.
     *
     * @param shape the new clipping area
     * @see Shape
     * @see Graphics2D#setClip(Shape)
     */
    public void setClip(Shape shape) {
        graphics2D.setClip(shape);
    }

    /**
     * Sets the clipping area by calling the base method with the default {@link java.awt.geom.Rectangle2D} of the
     * given {@link Transform}
     *
     * @param transform the {@link Transform} for the new Rectangle clipping area
     * @see Transform
     * @see java.awt.geom.Rectangle2D
     * @see #setClip(Shape)
     */
    public void setClip(Transform transform) {
        setClip(transform.getRect());
    }

    /**
     * Sets the clipping area by calling the base method.
     *
     * @param x      the x position of the clipping rect
     * @param y      the y position of the clipping rect
     * @param width  the width of the clipping rect
     * @param height the height of the clipping rect
     */
    public void setClip(float x, float y, float width, float height) {
        setClip(new Transform(x, y, width, height));
    }

    /**
     * Resets the clipping area to the rectangle of the window.
     * This clipping area is default when never changing the clipping.
     */
    public void resetClip() {
        setClip(0, 0, Game.getHost().getWidth(), Game.getHost().getHeight());
    }

    public void setTransform(AffineTransform affineTransform) {
        graphics2D.setTransform(affineTransform);
    }

    /*
    Bindings for getting the configuration of the drawing context
     */

    /**
     * Returns the current color of the Graphics.
     * With this Color, all of the primitives (e.g. Rectangles, Ovals) are filled and outlined
     *
     * @return the current color of the graphics
     * @see Graphics2D#getColor()
     */
    public Color getColor() {
        return graphics2D.getColor();
    }

    /**
     * Returns the current Font of the Graphics.
     * With this Font, all of the text is drawn
     *
     * @return the current defaultFont of the graphics
     * @see Graphics2D#getFont()
     */
    public Font getFont() {
        return graphics2D.getFont();
    }

    /**
     * Returns the current <code>FontRenderContext</code> of the graphics
     *
     * @return the current <code>FontRenderContext</code> of the graphics
     * @see Graphics2D#getFontMetrics()
     */
    public FontMetrics getFontMetrics() {
        return graphics2D.getFontMetrics();
    }

    /**
     * Returns the current Stroke of the Graphics.
     * The Stroke defines how to DRAW lines (e.g. outlines)
     *
     * @return the current Stroke of the graphics
     * @see Graphics2D#getStroke()
     */
    public Stroke getStroke() {
        return graphics2D.getStroke();
    }

    /**
     * Returns the current Paint of the Graphics.
     * With this paint, all of the primitives (e.g. Rectangles and Ovals) are filled.
     * Default for this is a plain Color
     *
     * @return the current Paint of the graphics
     * @see Graphics2D#getPaint()
     */
    public Paint getPaint() {
        return graphics2D.getPaint();
    }

    /**
     * Returns the current Composite of the graphics
     *
     * @return the current Composite of the graphics
     * @see Graphics2D#getComposite()
     */
    public Composite getComposite() {
        return graphics2D.getComposite();
    }

    /**
     * Returns the current background color of the graphics
     *
     * @return the current background color of the graphics
     * @see Graphics2D#getBackground()
     */
    public Color getBackgroundColor() {
        return graphics2D.getBackground();
    }

    /**
     * @return The {@link Graphics2D} context managed by this Object for further manipulation/rendering.
     */
    public Graphics2D getGraphics2D() {
        return graphics2D;
    }
}
