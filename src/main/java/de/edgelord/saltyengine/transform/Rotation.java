package de.edgelord.saltyengine.transform;

import static java.lang.Math.toDegrees;

/**
 * This class describes the rotation of an Object around the position {@link #centre} by {@link #rotationDegrees} degrees.
 */
public class Rotation {
    private Vector2f centre;
    private float rotationDegrees;

    public Rotation(Vector2f centre, float rotationDegrees) {
        this.centre = centre;
        this.rotationDegrees = rotationDegrees;
    }

    public Rotation(Vector2f centre) {
        this(centre, 0f);
    }

    public Rotation(float centreX, float centreY, float rotationDegrees) {
        this(new Vector2f(centreX, centreY), rotationDegrees);
    }

    public Rotation(float centreX, float centreY) {
        this(centreX, centreY, 0f);
    }

    public Rotation() {
        this(0, 0);
    }

    public void rotateToPoint(Vector2f point) {
        throw new UnsupportedOperationException("rotating an entity to face a point");
        /*
        float arc = (float) Math.atan2(point.getY() / centre.getY(), point.getX() / centre.getX());
        rotationDegrees = (float) toDegrees(arc);
        */
    }

    public void rotateToPoint(float x, float y) {
        rotateToPoint(new Vector2f(x, y));
    }

    public Vector2f getCentre() {
        return centre;
    }

    public void setCentre(Vector2f centre) {
        this.centre = centre;
    }

    public float getRotationDegrees() {

        while (rotationDegrees > 360) {
            rotationDegrees -= 360;
        }

        return rotationDegrees;
    }

    public void setRotationDegrees(float rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }
}