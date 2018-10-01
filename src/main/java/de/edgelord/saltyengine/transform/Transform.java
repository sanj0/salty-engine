package de.edgelord.saltyengine.transform;

import java.awt.geom.Rectangle2D;

public class Transform {

    private Vector2f position;
    private Dimensions dimensions;

    public Transform(Vector2f position, Dimensions dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public Transform(float x, float y, float width, float height) {
        position = new Vector2f(x, y);
        dimensions = new Dimensions(width, height);
    }

    /**
     * Returns whether the rectangle described by this Transform intersects the one
     * of the given.
     *
     * @param other the other Transform
     * @return whether this Transform intersects the given one
     *
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
     *
     * @see Rectangle2D#contains(Rectangle2D)
     */
    public boolean contains(Transform other) {
        return getRect().contains(other.getRect());
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
}
