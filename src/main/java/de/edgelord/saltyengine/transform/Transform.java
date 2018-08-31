package de.edgelord.saltyengine.transform;

public class Transform {

    private Vector2f position;
    private Dimensions dimensions;

    public Transform(Vector2f position, Dimensions dimensions) {
        this.position = position;
        this.dimensions = dimensions;
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
