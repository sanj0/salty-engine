/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.location;

public class Coordinates {

    private int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
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
