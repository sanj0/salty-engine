/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.hitbox;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.location.Vector2f;

public class SimpleHitbox implements Hitbox {

    private GameObject parent;
    private Vector2f position;
    private float offsetX, offsetY;
    private int width, height;

    public SimpleHitbox(GameObject parent, int width, int height, float offsetX, float offsetY) {

        this.parent = parent;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.width = width;
        this.height = height;

        float parentXPos = parent.getPosition().getX();
        float parentYPos = parent.getPosition().getY();

        this.position = new Vector2f(parentXPos + offsetX, parentYPos + offsetY);

    }

    public void recalculate() {

        this.position = new Vector2f(parent.getPosition().getX() + offsetX, parent.getPosition().getY() + offsetY);
    }

    public boolean isLeft(GameObject other) {

        return (int) position.getX() + getWidth() < (int) other.getHitbox().getPosition().getX();
    }

    public boolean isRight(GameObject other) {

        return (int) position.getX() > (int) other.getHitbox().getPosition().getX() + other.getHitbox().getWidth();
    }

    public boolean isAbove(GameObject other) {

        return (int) position.getY() > (int) other.getHitbox().getPosition().getY() + other.getHitbox().getHeight();
    }

    public boolean isBelow(GameObject other) {

        return (int) position.getY() + getHeight() < (int) other.getHitbox().getPosition().getY();
    }

    @Override
    public boolean collides(GameObject other) {

        if (isBelow(other)){
            return false;
        } else if(isAbove(other)){
            return false;
        } else if (isLeft(other)){
            return false;
        } else return !isRight(other);

    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public GameObject getParent() {
        return parent;
    }
}
