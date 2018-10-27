/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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

package de.edgelord.saltyengine.transform;

import java.awt.geom.Rectangle2D;

public class Transform {

    private Vector2f position;
    private Dimensions dimensions;
    private Rotation rotation;

    public Transform(Vector2f position, Dimensions dimensions) {
        this.position = position;
        this.dimensions = dimensions;
        this.rotation = new Rotation(getWidth() / 2f, getHeight() / 2f);
    }

    public Transform(float x, float y, float width, float height) {
        this(new Vector2f(x, y), new Dimensions(width, height));
    }

    /**
     * Returns whether the rectangle described by this Transform intersects the one
     * of the given.
     *
     * @param other the other Transform
     * @return whether this Transform intersects the given one
     * @see Rectangle2D#intersects(Rectangle2D)
     */
    public boolean intersects(Transform other) {

        return getRect().intersects(other.getRect());
    }

    /**
     * Returns true if the rectangle described by the given Transform is
     * completely within this.
     *
     * @param other the Transform to test containing from
     * @return whether this rectangle contains the given
     * @see Rectangle2D#contains(Rectangle2D)
     */
    public boolean contains(Transform other) {
        return getRect().contains(other.getRect());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Transform) {
            Transform other = (Transform) obj;
            return other.getDimensions().equals(dimensions)
                    && other.getPosition().equals(position)
                    && other.getRotation().equals(rotation);
        } else {
            return false;
        }
    }

    public static Transform zero() {
        return new Transform(Vector2f.zero(), Dimensions.zero());
    }

    public static Transform max() {
        return new Transform(Vector2f.max(), Dimensions.max());
    }

    public static Transform min() {
        return new Transform(Vector2f.min(), Dimensions.min());
    }

    public static Transform random(int posMin, int posMax, int dimMin, int dimMax) {
        return new Transform(Vector2f.random(posMin, posMax), Dimensions.random(dimMin, dimMax));
    }

    public static Transform random(int min, int max) {
        return random(min, max, min, max);
    }

    public static Transform random(int bound) {
        return random(bound, bound, bound, bound);
    }

    public Transform addToPosition(float xDelta, float yDelta) {
        Transform newTransform = new Transform((Vector2f) position.clone(), (Dimensions) dimensions.clone());

        newTransform.position.add(xDelta, yDelta);

        return newTransform;
    }

    public Transform addToPosition(Vector2f delta) {
        return addToPosition(delta.getX(), delta.getY());
    }

    public Transform subtractFromPosition(float xDelta, float yDelta) {
        Transform newTransform = new Transform((Vector2f) position.clone(), (Dimensions) getDimensions().clone());

        newTransform.getPosition().subtract(xDelta, yDelta);

        return newTransform;
    }

    public Transform subtractFromPosition(Vector2f delta) {
        return subtractFromPosition(delta.getX(), delta.getY());
    }

    public Vector2f getMiddle() {
        return new Vector2f(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    public void rotateToPoint(Vector2f point) {
        rotation.rotateToPoint(point);
    }

    public Rectangle2D getRect() {
        return new Rectangle2D.Float(getX(), getY(), getWidth(), getHeight());
    }

    public float getWidth() {
        return dimensions.getWidth();
    }

    public int getWidthAsInt() {
        return (int) dimensions.getWidth();
    }

    public void setWidth(float width) {
        dimensions.setWidth(width);
    }

    public float getHeight() {
        return dimensions.getHeight();
    }

    public int getHeightAsInt() {
        return (int) dimensions.getHeight();
    }

    public void setHeight(float height) {
        dimensions.setHeight(height);
    }

    public float getX() {
        return position.getX();
    }

    public void setX(float x) {
        position.setX(x);
    }

    public float getY() {
        return position.getY();
    }

    public void setY(float y) {
        position.setY(y);
    }

    public Coordinates getCoordinates() {
        return position.convertToCoordinates();
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    public Rotation getRotation() {
        return rotation;
    }

    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    public float getRotationDegrees() {
        return rotation.getRotationDegrees();
    }

    public void setRotationDegrees(float rotationDegrees) {
        rotation.setRotationDegrees(rotationDegrees);
    }

    public Vector2f getRotationCentre() {
        return rotation.getCentre();
    }

    public void setRotationCentre(Vector2f rotationCentre) {
        rotation.setCentre(rotationCentre);
    }

    public void setRotationCentreToMiddle() {
        rotation.setCentre(new Vector2f(getWidth() / 2f, getHeight() / 2f));
    }

    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", dimensions=" + dimensions +
                ", rotation=" + rotation +
                '}';
    }
}
