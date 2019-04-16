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

package de.edgelord.saltyengine.transform;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.geom.Rectangle2D;

/**
 * This class resembles a rectangle with a {@link #position}, {@link #dimensions} and a {@link #rotation}.
 */
public class Transform {

    /**
     * The position of this <code>Transform</code>.
     */
    private Vector2f position;

    /**
     * The {@link Dimensions} of this <code>Transform</code>.
     */
    private Dimensions dimensions;

    /**
     * The {@link Rotation} of this <code>Transform</code>.
     */
    private Rotation rotation;

    /**
     * The base constructor.
     *
     * @param position the {@link #position}
     * @param dimensions the {@link #dimensions}
     */
    public Transform(Vector2f position, Dimensions dimensions) {
        this.position = position;
        this.dimensions = dimensions;
        this.rotation = new Rotation(getWidth() / 2f, getHeight() / 2f);
    }

    /**
     * A constructor.
     *
     * @param x the x position
     * @param y the y position
     * @param width the width
     * @param height the height
     *
     * @see #Transform(Vector2f, Dimensions)
     */
    public Transform(float x, float y, float width, float height) {
        this(new Vector2f(x, y), new Dimensions(width, height));
    }

    /**
     * Constructs a new instance by adapting the values of the given {@link Rectangle2D}.
     *
     * @param rect the rectangle
     *
     * @see #Transform(Vector2f, Dimensions)
     */
    public Transform(Rectangle2D rect) {
        this(new Vector2f((float) rect.getX(), (float) rect.getY()), new Dimensions((float) rect.getWidth(), (float) rect.getHeight()));
    }

    /**
     * Returns a new <code>Transform</code> with {@link Vector2f#zero()} and {@link Dimensions#zero()}.
     *
     * @return a new <code>Transform</code> where everything is <code>0f</code>
     */
    public static Transform zero() {
        return new Transform(Vector2f.zero(), Dimensions.zero());
    }

    /**
     * Returns a new <code>Transform</code> with {@link Vector2f#max()} and {@link Dimensions#max()}.
     *
     * @return a new <code>Transform</code> with max values for position and dimensions
     */
    public static Transform max() {
        return new Transform(Vector2f.max(), Dimensions.max());
    }

    /**
     * Returns a new <code>Transform</code> with {@link Vector2f#min()} and {@link Dimensions#min()}.
     *
     * @return a new <code>Transform</code> with min values for position and dimensions
     */
    public static Transform min() {
        return new Transform(Vector2f.min(), Dimensions.min());
    }

    /**
     * Returns a new random <code>Transform</code>.
     *
     * @param posMin the min value for the position
     * @param posMax the max value for the position
     * @param dimMin the min value for the dimensions
     * @param dimMax the max value for the dimensions
     * @return a new random <code>Transform</code>
     *
     * @see Vector2f#random(int, int)
     * @see Dimensions#random(int, int)
     */
    public static Transform random(int posMin, int posMax, int dimMin, int dimMax) {
        return new Transform(Vector2f.random(posMin, posMax), Dimensions.random(dimMin, dimMax));
    }

    /**
     * Returns a new random <code>Transform</code>.
     *
     * @param min the min value
     * @param max the max value
     * @return a new random <code>Transform</code>
     *
     * @see Vector2f#random(int, int)
     * @see Dimensions#random(int, int)
     */
    public static Transform random(int min, int max) {
        return random(min, max, min, max);
    }

    /**
     * Returns whether the rectangle described by this <code>Transform</code> intersects the one
     * of the given.
     *
     * @param other the other Transform
     * @return whether this Transform intersects the given one
     * @see Rectangle2D#intersects(Rectangle2D)
     */
    public boolean intersects(Transform other) {

        int xOther = Math.round(other.getX()) - 1;
        int yOther = Math.round(other.getY()) - 1;
        int widthOther = Math.round(other.getWidth() - 1);
        int heightOther = Math.round(other.getHeight() - 1);

        int x = Math.round(getX());
        int y = Math.round(getY());
        int width = Math.round(getWidth());
        int height = Math.round(getHeight());

        if (width <= 0 || height <= 0 || widthOther <= 0 || heightOther <= 0) {
            return false;
        }

        return (xOther + widthOther > x &&
                yOther + heightOther > y &&
                xOther < x + height &&
                yOther < y + height);
    }

    /**
     * Returns whether this transform is on screen or not, considering the position of the {@link Game#getCamera()}
     *
     * @return whether the rectangle described by this transform is inside the view of the {@link Game#getCamera() camera} or not
     */
    public boolean isVisible() {
        return intersects(new Transform(Game.getCamera().getPosition(), Game.getGameDimensions()));
    }

    /**
     * Returns true if the rectangle described by the given Transform is
     * completely within this <code>Transform</code>.
     *
     * @param other the other <code>Transform</code>
     * @return whether this rectangle contains the given
     * @see Rectangle2D#contains(Rectangle2D)
     */
    public boolean contains(Transform other) {
        return getRect().contains(other.getRect());
    }

    /**
     * Returns the relation between two non-intersecting <code>Transform</code>s;
     * <code>this</code> and the given. <br>
     * This method only considers the horizontal axis,
     * so it'll return either {@link de.edgelord.saltyengine.utils.Directions.Direction#RIGHT}
     * or {@link de.edgelord.saltyengine.utils.Directions.Direction#LEFT}
     *
     * @param other another <code>Transform</code>
     * @return the relation on the horizontal (x) axis between <code>this</code> and the given <code>Transform</code>
     */
    public Directions.Direction getFreeRelationX(Transform other) {

        if (this.getX() + this.getWidth() < other.getX()) {
            return Directions.Direction.LEFT;
        } else if (this.getX() > other.getX() + other.getWidth()) {
            return Directions.Direction.RIGHT;
        }

        return Directions.Direction.EMPTY;
    }

    /**
     * Returns the relation between two non-intersecting <code>Transform</code>s;
     * <code>this</code> and the given. <br>
     * This method only considers the vertical axis,
     * so it'll return either {@link de.edgelord.saltyengine.utils.Directions.Direction#UP}
     * or {@link de.edgelord.saltyengine.utils.Directions.Direction#DOWN}
     *
     * @param other another <code>Transform</code>
     * @return the relation on the vertical (y) axis between <code>this</code> and the given <code>Transform</code>
     */
    public Directions.Direction getFreeRelationY(Transform other) {

        if (this.getY() + this.getHeight() < other.getY()) {
            return Directions.Direction.UP;
        } else if (this.getY() > other.getY() + other.getHeight()) {
            return Directions.Direction.DOWN;
        }

        return Directions.Direction.EMPTY;
    }

    /**
     * This method returns the relation between this and the given {@link Transform} as a Direction.
     * That'll only work properly if the two Transforms intersect.
     *
     * @param other the <code>Transform</code> from which the relation to
     *              <code>this</code> will be returned
     * @return the relation between the two {@link Transform}s as a Direction from
     * the perspective of this <code>Transform</code>
     */
    public Directions.Direction getRelation(final Transform other) {

        float thisBottom = this.getY() + this.getHeight();
        float otherBottom = other.getY() + other.getHeight();
        float thisRight = this.getX() + this.getWidth();
        float otherRight = other.getX() + other.getWidth();

        float bottomCollision = otherBottom - this.getY();
        float topCollision = thisBottom - other.getY();
        float leftCollision = thisRight - other.getX();
        float rightCollision = otherRight - this.getX();

        if (topCollision < bottomCollision && topCollision < leftCollision && topCollision < rightCollision) {
            return Directions.Direction.DOWN;
        }

        if (bottomCollision < topCollision && bottomCollision < leftCollision && bottomCollision < rightCollision) {
            return Directions.Direction.UP;
        }

        if (rightCollision < leftCollision && rightCollision < topCollision && rightCollision < bottomCollision) {
            return Directions.Direction.LEFT;
        }

        if (leftCollision < rightCollision && leftCollision < bottomCollision && leftCollision < topCollision) {
            return Directions.Direction.RIGHT;
        }

        return Directions.Direction.EMPTY;
    }

    /**
     * Positions this <code>Transform</code> centre at the given {@link Vector2f}.
     *
     * @param centre the next centre of this <code>Transform</code>
     */
    public void positionByCentre(Vector2f centre) {
        float centreShiftX = getWidth() / 2f;
        float centreShiftY = getHeight() / 2;
        position = new Vector2f(centre.getX() - centreShiftX, centre.getY() - centreShiftY);
    }

    /**
     * Returns the centre of the rectangle described by this <code>Transform</code>.
     * <p>
     * <code>centre(x) = getX() + getWidth() / 2f</code>
     * <code>centre(y) = getY() + getHeight() / 2f</code>
     *
     * @return the centre of this <code>Transform</code>
     */
    public Vector2f getCentre() {
        return new Vector2f(getX() + getWidth() / 2f, getY() + getHeight() / 2f);
    }

    /**
     * Rotats this <code>Transform</code> to directly face the given {@link Vector2f point}.
     *
     * @param point the point to rotate to
     */
    public void rotateToPoint(Vector2f point) {
        rotation.rotateToPoint(point, this);
    }

    /**
     * Calls {@link #rotateToPoint(Vector2f)} to make this <code>Transform</code> face the given point.
     *
     * @param x the x position of the point
     * @param y the y position od the point
     *
     * @see  #rotateToPoint(Vector2f)
     */
    public void rotateToPoint(float x, float y) {
        rotation.rotateToPoint(x, y, this);
    }

    /**
     * Returns a {@link Rectangle2D} with the exact position and dimensions of this <code>Transform</code>.
     *
     * @return a {@link Rectangle2D} that resembles this <code>Transform</code> without rotation
     */
    public Rectangle2D getRect() {
        return new Rectangle2D.Float(getX(), getY(), getWidth(), getHeight());
    }

    /**
     * Returns the width of this <code>Transform</code>.
     *
     * @return the width of this <code>Transform</code>
     *
     * @see Dimensions#getWidth()
     */
    public float getWidth() {
        return dimensions.getWidth();
    }

    /**
     * Returns the width of this <code>Transform</code> rounded to an integer.
     *
     * @return the width of this <code>Transform</code> rounded to an integer
     *
     * @see Dimensions#getWidth()
     * @see Math#round(float)
     */
    public int getWidthAsInt() {
        return Math.round(getWidth());
    }

    /**
     * Sets the width of the {@link #dimensions}.
     *
     * @param width the new width
     */
    public void setWidth(float width) {
        dimensions.setWidth(width);
    }

    /**
     * Returns the height of this <code>Transform</code>.
     *
     * @return the height of this <code>Transform</code>
     *
     * @see Dimensions#getHeight() ()
     */
    public float getHeight() {
        return dimensions.getHeight();
    }

    /**
     * Returns the height of this <code>Transform</code> rounded to an integer.
     *
     * @return the height of this <code>Transform</code> rounded to an integer
     *
     * @see Dimensions#getHeight()
     * @see Math#round(float)
     */
    public int getHeightAsInt() {
        return Math.round(getHeight());
    }

    /**
     * Sets the height of the {@link #dimensions}.
     *
     * @param height the new height
     */
    public void setHeight(float height) {
        dimensions.setHeight(height);
    }

    /**
     * Returns the height of this <code>Transform</code>.
     *
     * @return the height of this <code>Transform</code>
     *
     * @see Dimensions#getHeight()
     */
    public float getX() {
        return position.getX();
    }

    /**
     * Returns the maximum x value of this rectangle, which is {@code x + width}.
     *
     * @return the maximum x value of the rectangle described by this Transform
     */
    public float getMaxX() {
        return getX() + getWidth();
    }

    /**
     * Sets the x position of this <code>Transform</code>.
     *
     * @param x the new value of this <code>Transform</code>'s x position
     *
     * @see Vector2f#setX(float)
     */
    public void setX(float x) {
        position.setX(x);
    }

    /**
     * Returns the width of this <code>Transform</code>.
     *
     * @return the width of this <code>Transform</code>
     *
     * @see Dimensions#getWidth()
     */
    public float getY() {
        return position.getY();
    }

    /**
     * @return the maximum y value of the rectangle described by this Transform. That position is
     * <p>
     * {@code y + height}
     */
    public float getMaxY() {
        return getY() + getHeight();
    }

    /**
     * Sets the y position of this <code>Transform</code>.
     *
     * @param y the new value of this <code>Transform</code>'s y position
     *
     * @see Vector2f#setY(float)
     */
    public void setY(float y) {
        position.setY(y);
    }

    /**
     * Returns the {@link #position} as {@link Coordinates}.
     *
     * @return {@link #position} as {@link Coordinates}
     *
     * @see Vector2f#convertToCoordinates()
     */
    public Coordinates getCoordinates() {
        return position.convertToCoordinates();
    }

    /**
     * Returns the absolute position of the centre of rotation of this <code>Transform</code>'s {@link #rotation}.
     *
     * @return the absolute position of the rotation of this <code>Transform</code>
     *
     * @see Rotation#getCentreAbsolute(Transform)
     */
    public Vector2f getRotationCentreAbsolute() {
        return rotation.getCentreAbsolute(this);
    }

    /**
     * Returns the degrees by which this <code>Transform</code> is rotated.
     *
     * @return the rotation of this <code>Transform</code>
     *
     * @see Rotation#getRotationDegrees()
     */
    public float getRotationDegrees() {
        return rotation.getRotationDegrees();
    }

    /**
     * Sets the degrees by which this <code>Transform</code> is rotated.
     *
     * @param rotationDegrees the nex rotation
     */
    public void setRotationDegrees(float rotationDegrees) {
        rotation.setRotationDegrees(rotationDegrees);
    }

    /**
     * Returns the centre of {@link #rotation} relative to the {@link #position}.
     *
     * @return the relative rotation centre
     * @see Rotation#getCentre()
     * @see #getRotationCentreAbsolute()
     */
    public Vector2f getRotationCentre() {
        return rotation.getCentre();
    }

    /**
     * Sets the centre of {@link #rotation} relative to the {@link #position}.
     *
     * @param rotationCentre the nex relative rotation centre
     *
     * @see Rotation#setCentre(Vector2f)
     */
    public void setRotationCentre(Vector2f rotationCentre) {
        rotation.setCentre(rotationCentre);
    }

    /**
     * Sets the centre of {@link #rotation} to the centre.
     *
     * @see #setRotationCentre(Vector2f)
     * @see #getCentre()
     */
    public void setRotationCentreToCentre() {
        rotation.setCentre(new Vector2f(getWidth() / 2f, getHeight() / 2f));
    }

    /**
     * Gets {@link #position}.
     *
     * @return the value of {@link #position}
     */
    public Vector2f getPosition() {
        return position;
    }

    /**
     * Sets {@link #position)}.
     *
     * @param position the new value of {@link #position}
     */
    public void setPosition(Vector2f position) {
        this.position = position;
    }

    /**
     * Gets {@link #dimensions}.
     *
     * @return the value of {@link #dimensions}
     */
    public Dimensions getDimensions() {
        return dimensions;
    }

    /**
     * Sets {@link #dimensions)}.
     *
     * @param dimensions the new value of {@link #dimensions}
     */
    public void setDimensions(Dimensions dimensions) {
        this.dimensions = dimensions;
    }

    /**
     * Gets {@link #rotation}.
     *
     * @return the value of {@link #rotation}
     */
    public Rotation getRotation() {
        return rotation;
    }

    /**
     * Sets {@link #rotation)}.
     *
     * @param rotation the new value of {@link #rotation}
     */
    public void setRotation(Rotation rotation) {
        this.rotation = rotation;
    }

    @Override
    public String toString() {
        return "Transform{" +
                "position=" + position +
                ", dimensions=" + dimensions +
                ", rotation=" + rotation +
                '}';
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

    @Override
    public Object clone() {
        return new Transform((Vector2f) position.clone(), (Dimensions) dimensions.clone());
    }
}
