/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.location;

public class Vector2f {

    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Coordinates convertToCoordinates() {

        return new Coordinates(Integer.parseInt(String.valueOf(getX())), Integer.parseInt(String.valueOf(getY())));
    }

    public void parseVector2f(Coordinates coordinates) {

        setX((float) coordinates.getX());
        setY((float) coordinates.getY());
    }
}
