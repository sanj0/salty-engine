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

package de.edgelord.saltyengine.core.graphics;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.effect.BasicRenderContext;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.ImageObserver;

public class SaltyGraphics {

    private Graphics2D graphics2D;

    public SaltyGraphics(Graphics2D graphics2D) {
        this.graphics2D = graphics2D;

        setFont(SaltySystem.defaultFont);
    }

    /*
    Rendering bindings
     */

    /*
    Draw a point (Vector2f)
     */

    /**
     * The base method to draw a {@link Vector2f point} with a given diameter.
     * This method draws a circle using {@link #drawOval(float, float, float, float)} aeround the given x and y
     * coordinates with the given diameter
     *
     * @param x        the x position of the centre of the circle that represents the point
     * @param y        the y position of the centre of the circle that represents the point
     * @param diameter the diameter of the circle around the given point
     */
    public void drawPoint(float x, float y, float diameter) {
        float shift = diameter / 2f;
        drawOval(x - shift, y - shift, diameter, diameter);
    }

    /**
     * Draws the given {@link Vector2f point} using {@link #drawPoint(float, float, float)}.
     *
     * @param point    the point to be drawn
     * @param diameter the diameter of the circle around the given point
     */
    public void drawPoint(Vector2f point, float diameter) {
        drawPoint(point.getX(), point.getY(), diameter);
    }

    /*
    Fill a rect
     */

    /**
     * The base method to draw a filled rect. It automatically rounds the given floats to ints
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
     * @param object the ComponentContainer from which to take the Transform
     * @see #drawRect(Transform)
     */
    public void drawRect(TransformedObject object) {
        drawRect(object.getTransform());
    }
    
    /*
    Draw a rect
     */

    /**
     * The base method to draw the outline of a rect. It automatically rounds the given floats to ints
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
     * @param object the object from which to take the transform with the necessary information
     * @see #outlineRect(Transform)
     */
    public void outlineRect(TransformedObject object) {
        outlineRect(object.getTransform());
    }
    
    /*
    Fill an oval
     */

    /**
     * The base method to draw a filled oval. It automatically rounds the given floats to ints.
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
     * @param object the object from which to take the Transform to draw the oval
     */
    public void drawOval(TransformedObject object) {
        drawOval(object.getTransform());
    }
    
    /*
    Draw an oval
     */

    /**
     * The base method to draw the outline of an oval. It automatically rounds the given floats to ints.
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
     * @param object the ComponentContainer from which to take the transform
     * @see #outlineOval(Transform)
     */
    public void outlineOval(TransformedObject object) {
        outlineOval(object.getTransform());
    }

    /*
    Bindings for triangles
     */

    /**
     * Draws a filled polygon from the three given point. All floats are being rounded to int.
     *
     * @param point1 the first point of the triangle
     * @param point2 the second point of the triangle
     * @param point3 the third point of the triangle
     */
    public void drawTriangle(Vector2f point1, Vector2f point2, Vector2f point3) {

        renderPolygon(false, point1, point2, point3);
    }

    /**
     * Outlines a polygon from the three given point. All floats are being rounded to int.
     *
     * @param point1 the first point of the triangle
     * @param point2 the second point of the triangle
     * @param point3 the third point of the triangle
     */
    public void outlineTriangle(Vector2f point1, Vector2f point2, Vector2f point3) {

        renderPolygon(true, point1, point2, point3);
    }

    /*
    Bindings for Polygons
     */

    /**
     * Draws a filled polygon from the given {@link Vector2f}s.
     * You can pass in as many <code>Vector2f</code>s as you want, separated by a comma each.
     *
     * @param points the points of the polygon
     */
    public void drawPolygon(Vector2f... points) {

        renderPolygon(false, points);
    }

    /**
     * Outlines a polygon from the given {@link Vector2f}s.
     * You can pass in as many <code>Vector2f</code>s as you want, separated by a comma each.
     *
     * @param points the points of the polygon
     */
    public void outlinePolygon(Vector2f... points) {

        renderPolygon(true, points);
    }

    private void renderPolygon(boolean outline, Vector2f... points) {
        int[] xPoints = new int[points.length];
        int[] yPoints = new int[points.length];

        for (int i = 0; i < xPoints.length; i++) {
            xPoints[i] = Math.round(points[i].getX());
        }

        for (int i = 0; i < yPoints.length; i++) {
            xPoints[i] = Math.round(points[i].getY());
        }

        if (outline) {
            graphics2D.drawPolygon(xPoints, yPoints, points.length);
        } else {
            graphics2D.fillPolygon(xPoints, yPoints, points.length);
        }
    }

    /*
    Draw an image
     */

    /**
     * The base method to draw an image. It automatically rounds the given floats to ints.
     *
     * @param image  the image to draw
     * @param x      the x position of the image
     * @param y      the y position of the image
     * @param width  the width with which the image should be drawn
     * @param height the height with which the image should be drawn
     * @see Graphics2D#drawImage(Image, int, int, int, int, ImageObserver)
     */
    public void drawImage(Image image, float x, float y, float width, float height) {
        graphics2D.drawImage(image, Math.round(x), Math.round(y), Math.round(width), Math.round(height), null);
    }

    /**
     * Draws the given {@link SaltyImage} at the given position with the given width and height.
     *
     * @param image  the image
     * @param x      the x position
     * @param y      the y position
     * @param width  the width
     * @param height the height
     * @see SaltyImage#draw(SaltyGraphics, Vector2f, float, float)
     */
    public void drawImage(SaltyImage image, float x, float y, float width, float height) {
        image.draw(this, new Vector2f(x, y), width, height);
    }

    /**
     * Draws an image by calling the base method. It will be drawn with its original width and height
     *
     * @param image    the image to be drawn
     * @param position the position of the image
     * @see #drawImage(Image, float, float, float, float)
     */
    public void drawImage(Image image, Vector2f position) {
        drawImage(image, position.getX(), position.getY(), Math.round(image.getWidth(null)), Math.round(image.getHeight(null)));
    }

    /**
     * Draws the given {@link SaltyImage} with its original size at the given position.
     *
     * @param image    the image
     * @param position the position
     * @see SaltyImage#draw(SaltyGraphics, Vector2f)
     */
    public void drawImage(SaltyImage image, Vector2f position) {
        image.draw(this, position);
    }

    /**
     * Draws an image by calling the base method. It will be drawn with its original width and height.
     *
     * @param image the image to be drawn
     * @param x     the x position of the image
     * @param y     the y position of the image
     * @see #drawImage(Image, float, float, float, float)
     */
    public void drawImage(Image image, float x, float y) {
        drawImage(image, x, y, Math.round(image.getWidth(null)), Math.round(image.getHeight(null)));
    }

    /**
     * Draws the given {@link SaltyImage} at the given x and y position.
     *
     * @param image the image
     * @param x     the x position
     * @param y     the y position
     */
    public void drawImage(SaltyImage image, float x, float y) {
        image.draw(this, new Vector2f(x, y));
    }

    /**
     * Draws an image by calling the base method.
     *
     * @param image     the image to be drawn
     * @param transform the transform from which to take the position and dimensions of the image to be drawn with
     * @see #drawImage(Image, float, float, float, float)
     */
    public void drawImage(Image image, Transform transform) {
        drawImage(image, transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight());
    }

    /**
     * Draws the given {@link SaltyImage} with the given {@link Transform}.
     *
     * @param image     the image
     * @param transform the <code>Transform</code>
     */
    public void drawImage(SaltyImage image, Transform transform) {
        drawImage(image, transform.getPosition(), transform.getDimensions());
    }

    /**
     * Draws an image by calling the base method.
     *
     * @param image      the image to be drawn
     * @param position   the position of the image
     * @param dimensions the dimensions with to draw the image
     */
    public void drawImage(Image image, Vector2f position, Dimensions dimensions) {
        drawImage(image, position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight());
    }

    /**
     * Draws the given {@link SaltyImage} with the given {@link Vector2f position} and {@link Dimensions}.
     *
     * @param image      the image
     * @param position   the position
     * @param dimensions the dimensions
     */
    public void drawImage(SaltyImage image, Vector2f position, Dimensions dimensions) {
        image.draw(this, position, dimensions.getWidth(), dimensions.getHeight());
    }

    /**
     * Draws an image by calling {@link #drawImage(Image, Transform)}.
     *
     * @param image  the image to be drawn
     * @param object the ComponentContainer from which to take the Transform to draw the image
     */
    public void drawImage(Image image, TransformedObject object) {
        drawImage(image, object.getTransform());
    }

    /**
     * Draws the given {@link SaltyImage} using {@link #drawImage(SaltyImage, Transform)} and the {@link TransformedObject#getTransform() transform}
     * from the given object.
     *
     * @param image  the image
     * @param object the <code>TransformedObject</code>
     * @see #drawImage(Image, Transform)
     */
    public void drawImage(SaltyImage image, TransformedObject object) {
        drawImage(image, object.getTransform());
    }

    /*
    Fill a round rect
     */

    /**
     * The base method to draw a filled round rect. It automatically rounds the given floats to ints.
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
     * The base method to draw a filled round rect with the same arc both horizontally and vertically. It automatically rounds the given floats to ints.
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
     * Draws a filled round rect by calling the base method.
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
     * @param object    the object from which to take the transform
     * @param arcWidth  the diameter of the horizontal arc
     * @param arcHeight the diameter of the vertical arc
     * @see #drawRoundRect(Transform, float, float)
     */
    public void drawRoundRect(TransformedObject object, float arcWidth, float arcHeight) {
        drawRoundRect(object.getTransform(), arcWidth, arcHeight);
    }

    /**
     * Draws a filled round rect with the same horizontal and vertical arc by calling <code>drawRoundRect(Transform, float, float)</code>
     *
     * @param object the object from which to take the transform
     * @param arc    the horizontal and vertical diameter of the arc
     * @see #drawRoundRect(Transform, float, float)
     */
    public void drawRoundRect(TransformedObject object, float arc) {
        drawRoundRect(object.getTransform(), arc, arc);
    }
    
    /*
    Draw a round rect
     */

    /**
     * The base method to draw the outline of a round rect. It automatically rounds the given floats to ints
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
     * The base method to draw a round rect with the same arc both horizontally and vertically. It automatically rounds the given floats to ints
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
     * @param object    the object from which to take the transform
     * @param arcWidth  the diameter of the horizontal arc
     * @param arcHeight the diameter of the vertical arc
     * @see #outlineRoundRect(Transform, float, float)
     */
    public void outlineRoundRect(TransformedObject object, float arcWidth, float arcHeight) {
        outlineRoundRect(object.getTransform(), arcWidth, arcHeight);
    }

    /**
     * Draws the outline of a round rect with the same horizontal and vertical arc by calling <code>drawRoundRect(Transform, float, float)</code>
     *
     * @param object the object from which to take the transform
     * @param arc    the horizontal and vertical diameter of the arc
     * @see #outlineRoundRect(Transform, float, float)
     */
    public void outlineRoundRect(TransformedObject object, float arc) {
        outlineRoundRect(object.getTransform(), arc, arc);
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
     * The base method to draw a line. The given floats are automatically rounded to ints.
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
     * The base method to draw a Line with a temporary Stroke. The given floats are automatically rounded to ints.
     * Before the line is drawn, the current stroke gets fetched and the temporary one is set,
     * after the line is drawn, the fetched stroke gets set again.
     *
     * @param x1              x position of the start point
     * @param y1              y position of the start point
     * @param x2              x position of the end point
     * @param y2              y position of the end point
     * @param temporaryStroke the temporary Stroke with which to draw the line
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
     * @param temporaryStroke the temporary Stroke with which to draw the line
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
     * @param temporaryStroke the temporary Stroke with which to draw the line
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
     * @param temporaryStroke the temporary Stroke with which to draw the line
     * @see #drawLine(float, float, float, float, Stroke)
     */
    public void drawLine(Vector2f startPoint, float x2, float y2, Stroke temporaryStroke) {
        drawLine(startPoint.getX(), startPoint.getY(), x2, y2, temporaryStroke);
    }

    /*
    Bindings for drawing text
     */

    public enum TextAnchor {
        TOP_LEFT_CORNER,
        BOTTOM_LEFT_CORNER,
        TOP_RIGHT_CORNER,
        BOTTOM_RIGHT_CORNER,
        CENTRE
    }

    /**
     * The base method to draw a String using the current <code>Font</code> of the graphics.
     * It converts the given {@link Object} to a {@link String} using its {@link Object#toString()}.
     * When the given <code>Object</code> is already a <code>String</code>,
     * <code>toString()</code> simply returns that exact <code>String</code>.
     *
     * @param text the String to be drawn
     * @param x    the x position of the baseLine of the first character
     * @param y    the y position of the baseLine of the first character
     * @see Graphics2D#drawString(String, float, float)
     */
    public void drawText(Object text, float x, float y, TextAnchor anchor) {

        String string = text.toString();
        float xPos = 0;
        float yPos = 0;
        FontMetrics fontMetrics = getFontMetrics();

        switch (anchor) {
            case TOP_LEFT_CORNER:
                xPos = x;
                yPos = y + fontMetrics.getAscent();
                break;
            case BOTTOM_LEFT_CORNER:
                xPos = x;
                yPos = y - fontMetrics.getDescent();
                break;
            case TOP_RIGHT_CORNER:
                xPos = x - fontMetrics.stringWidth(string);
                yPos = y + fontMetrics.getAscent();
                break;
            case BOTTOM_RIGHT_CORNER:
                xPos = x - fontMetrics.stringWidth(string);
                yPos = y - fontMetrics.getDescent();
                break;
            case CENTRE:
                xPos = x - (fontMetrics.stringWidth(string) / 2f);
                yPos = y + fontMetrics.getAscent() - ((fontMetrics.getAscent() + fontMetrics.getDescent()) / 2f);
                break;
        }
        graphics2D.drawString(string, xPos, yPos);
    }

    /**
     * Draws the given text by calling the base method
     *
     * @param text     the text to be drawn
     * @param position the position of the baseLine of the first letter
     * @see #drawText(Object, float, float, TextAnchor)
     */
    public void drawText(Object text, Vector2f position, TextAnchor anchor) {
        drawText(text, position.getX(), position.getY(), anchor);
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
     * Sets the rotation of the graphics around the given point to the given amount of degrees with considering the current rotation.
     *
     * @param rotation the new rotation
     * @param centre   the centre of the rotation.
     * @see Graphics2D#rotate(double, double, double)
     */
    public void setRotation(float rotation, Vector2f centre) {

        graphics2D.rotate(Math.toRadians(rotation + Game.getCamera().getRotation().getRotationDegrees()), centre.getX(), centre.getY());
    }

    /**
     * Sets the rotation of the graphics to the given amount of degrees.
     *
     * @param rotation the new rotation.
     * @see Graphics2D#rotate(double)
     */
    public void setRotation(float rotation) {
        graphics2D.rotate(Math.toRadians(rotation + Game.getCamera().getRotation().getRotationDegrees()));
    }

    /**
     * Resets the rotation made by the given object to this graphics.
     *
     * @param object the object.
     */
    public void resetObjectRotation(TransformedObject object) {
        setRotation(-object.getRotationDegrees(), object.getTransform().getRotationCentreAbsolute());
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
        setClip(0, 0, Game.getGameWidth(), Game.getGameHeight());
    }


    ///**
    // * Rotates the graphics by the given degrees plus the game's camera rotation.
    // *
    // * @param degrees the degrees to rotate
    // */
    /*
    public void rotate(float degrees) {
        graphics2D.rotate(degrees + Game.getCamera().getRotation() + currentRotation);
    }
    */


    ///**
    // * Rotates the graphics around the given point by the given amount of degrees
    // * @param degrees
    // * @param centre
    // */
    /*
    public void rotate(float degrees, Vector2f centre) {

    }
    */

    public void setTransform(AffineTransform affineTransform) {
        graphics2D.setTransform(affineTransform);
    }

    /**
     * Applies the configuration of the given {@link RenderContext} to this graphics. <br>
     * <code>graphics.setRenderContext(renderContext)</code> <br>has an equal result to calling <br><code>renderContext.applyConfiguration(graphics)</code>.
     *
     * @param renderContext the {@link RenderContext} whose configuration is to be applied to this graphics.
     */
    public void setRenderContext(RenderContext renderContext) {
        renderContext.applyConfiguration(this);
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
     * The Stroke defines how to draw lines (e.g. outlines)
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

    /**
     * Return the length of the given text when rendered.
     *
     * @param text the string from which to compute the length when rendered
     * @return the length of the given text when rendered
     */
    public float getTextLength(String text) {
        return getFontMetrics().stringWidth(text);
    }

    public float getLineDistance() {
        return getFontMetrics().getLineMetrics("Hello World!", graphics2D).getLeading();
    }

    /**
     * Returns a new {@link BasicRenderContext} with the current basic state of this graphics, including: <br>
     * - the {@link Color} {@link #getColor()} <br>
     * - the {@link Paint} {@link #getPaint()} ()} <br>
     * - the {@link Stroke} {@link #getStroke()} ()} <br>
     * - the alpha value <code>ColorUtil.intARGBToFloat(getColor().getAlpha())</code> <br>
     * - the {@link Font} {@link #getFont()} ()} <br>
     *
     * @return a new {@link BasicRenderContext} that represents the current basic configuration of this graphics
     */
    public BasicRenderContext exportRenderContext() {
        BasicRenderContext renderContext = new BasicRenderContext(getColor(), getPaint(), getStroke());
        renderContext.setAlpha(getColor().getAlpha());
        renderContext.setFont(getFont());

        return renderContext;
    }
}
