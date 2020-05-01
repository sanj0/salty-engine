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

package de.edgelord.saltyengine.core.camera;

import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Rotation;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;

/**
 * An interface for a simple 2D camera.
 * <p>
 * A camera, in this sense, takes input and generate an according {@link de.edgelord.saltyengine.effect.image.SaltyImage image}.
 */
public interface Camera {

    /**
     * Renders the given Drawable onto a {@link SaltyImage} and returns it.
     * <p>
     * The image returned by this method will always have the
     * exact {@link Dimensions} of {@link #getSize()}.
     *
     * @param subject the Drawable to render
     * @return a visual representation of the given Drawable as this camera sees it
     */
    SaltyImage render(Drawable subject);

    /**
     * Returns the position of this camera as set and
     * described by {@link #setPosition(Vector2f)}.
     *
     * @return the current position of this camera
     */
    Vector2f getPosition();

    /**
     * Sets the position of this camera.
     * <p>
     * The position of this camera is relative to
     * the normal user space and directly influences
     * the output of this camera produced with
     * {@link #render(Drawable)}.
     *
     * @param position the new position of this camera
     */
    void setPosition(Vector2f position);

    /**
     * Returns the size of this camera
     * as set and described in {@link #setSize(Dimensions)}.
     *
     * @return the current size of this camera
     */
    Dimensions getSize();

    /**
     * Sets the size of this camera.
     * <p>
     * The size of the camera directly determines
     * the size of its output produced with
     * {@link #render(Drawable)}.
     *
     * @param size the new size of this camera
     */
    void setSize(Dimensions size);

    /**
     * Returns the current resolution of this camera
     * as set and described in {@link #setResolution(Dimensions)}.
     *
     * @return the current resolution of this camera
     */
    Dimensions getResolution();

    /**
     * Sets the resolution of this camera.
     * <p>
     * The resolution decsribes with how many
     * pixel the camera can render. <br>
     * This does not affect the size
     * of the image produced with {@link #render(Drawable)}.
     *
     * @param resolution the new resolution of this camera
     */
    void setResolution(Dimensions resolution);

    /**
     * Returns the rotation of this camera
     * as set and described in {@link #setResolution(Dimensions)}.
     *
     * @return the current rotation of this camera
     */
    Rotation getRotation();

    /**
     * Sets the rotation of this camera.
     * <p>
     * This does not affect the size of the output produced
     * with {@link #render(Drawable)}.
     *
     * @param rotation the new rotation of this camera
     */
    void setRotation(Rotation rotation);

    /**
     * Returns the scale as set and described in {@link #setScale(float)}.
     *
     * @return the current scale of this camera
     */
    float getScale();

    /**
     * Sets the current scale of the camera.
     * <p>
     * A scale of <code>1f</code> is default and does not change the image,
     * a scale of <code>0f</code> would make the camera see nothing and a scale
     * of <code>2f</code> would make everything twice as big.
     * <p>
     * This scale does not affect the size of the image produced by {@link #render(Drawable)}.
     *
     * @param scale the new scale
     */
    void setScale(float scale);

    /**
     * Returns an instance of {@link AffineTransform} that directly
     * represents the values of this camera in <br>
     * {@link #getScale() scale} <br>
     * {@link #getPosition() position} <br>
     * {@link #getRotation() rotation} <br>
     *
     * @return a {@link AffineTransform} that directly represents the state of this camera
     */
    default AffineTransform getAffineTransform() {
        final double scale = getScale();
        final Vector2f position = getPosition();
        final double rotation = getRotation().getRotationDegrees();
        final Vector2f rotationAnchor = getRotation().getCentre();
        final double scaleTranslateAmountX = getResolution().getWidth() - getSize().getWidth();
        final double scaleTranslateAmountY = getResolution().getHeight() - getSize().getHeight();

        final AffineTransform transform = new AffineTransform();
        transform.rotate(Math.toRadians(rotation), rotationAnchor.getX(), rotationAnchor.getY());
        transform.translate(scaleTranslateAmountX, scaleTranslateAmountY);
        transform.scale(scale, scale);
        transform.translate(-position.getX(), -position.getY());

        return transform;
    }

    /**
     * Returns the position of the given Vector relative to the position of the camera.
     * Example:
     *
     * <pre>
     *     {@code
     *     // Create a Vector2f at 100|100
     *     Vector2f absolutePosition = new Vector2f(100, 100);
     *
     *     // Moves the camera to 10|10
     *     Game.getCamera().setPosition(10, 10);
     *
     *     // The Vector2f relativePosition would be at 90|90
     *     // so that when you draw a GameObject at this position with the camera being moved to 10|10,
     *     // it still has those original 100|100 relative to the window.
     *     Vector2f relativePosition = Game.getCamera().getRelativePosition(absolutePosition);
     *     }
     * </pre>
     *
     * @param absolutePosition an absolute position
     * @return the absolute position with considering the position of this camera for the given relative position
     */
    default Vector2f getRelativePosition(final Vector2f absolutePosition) {
        final AffineTransform transform = getAffineTransform();
        try {
            transform.invert();
        } catch (final NoninvertibleTransformException e) {
            e.printStackTrace();
        }
        final Point2D srcPoint = new Point2D.Float(absolutePosition.getX(), absolutePosition.getY());
        final Point2D dstPoint = new Point2D.Float();
        transform.transform(srcPoint, dstPoint);

        return new Vector2f((float) dstPoint.getX(), (float) dstPoint.getY());
    }

    /**
     * Returns the cursor as a {@link Transform} manipulated in a way that it fits the camera position.
     * You can obtain this value easier by using {@link Input#getCursor()}
     *
     * @return the cursor with the position of this camera considered.
     * @see #getRelativePosition(Vector2f)
     */
    default Transform getRelativeCursor() {
        return new Transform(getRelativeCursorPosition(), Dimensions.one());
    }

    /**
     * Returns the cursor position relative to the camera position.
     * You can obtain this value easier by using {@link Input#getCursorPosition()} ()}.
     *
     * @return the cursor position manipulated in a way that it fits the position of this camera.
     */
    default Vector2f getRelativeCursorPosition() {
        return Input.getCursorPosition();
    }
}
