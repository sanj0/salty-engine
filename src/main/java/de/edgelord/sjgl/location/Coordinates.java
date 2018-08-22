/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.location;

public class Coordinates {

    private int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public boolean isAbove(Coordinates other) {
        return this.getY() < other.getY();
    }

    public boolean isBelow(Coordinates other) {
        return this.getY() > other.getY();
    }

    public boolean isLeft(Coordinates other) {
        return this.getX() < other.getX();
    }

    public boolean isRight(Coordinates other) {
        return this.getX() > other.getX();
    }

    public void parseCoordinates(Vector2f vector2f) {

        this.setX((int) vector2f.getX());
        this.setY((int) vector2f.getY());
    }

    public int getX() {
        return x;
    }

    public void changeX(int delta) {

        setX(getX() + delta);
    }

    public void changeY(int delta) {

        setY(getY() + delta);
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setCoordinates(Coordinates coordinates) {

        setX(coordinates.getX());
        setY(coordinates.getY());
    }
}
