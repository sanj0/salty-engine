/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, Germany, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.hitbox;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Vector2f;

import java.awt.*;

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

        float parentXPos = parent.getVector2f().getX();
        float parentYPos = parent.getVector2f().getY();

        this.position = new Vector2f(parentXPos + offsetX, parentYPos + offsetY);

    }

    public void recalculate() {

        this.position = new Vector2f(parent.getVector2f().getX() + offsetX, parent.getVector2f().getY() + offsetY);
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
        } else if (isRight(other)){
            return false;
        }

        return true;
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
