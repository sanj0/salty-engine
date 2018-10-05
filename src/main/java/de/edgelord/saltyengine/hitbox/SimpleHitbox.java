/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.hitbox;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

public class SimpleHitbox implements Hitbox {

    private final GameObject parent;
    private Transform transform;
    private float offsetX, offsetY;

    public SimpleHitbox(final GameObject parent, final float width, final float height, final float offsetX, final float offsetY) {

        this.parent = parent;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.transform = new Transform(new Vector2f(parent.getX() + offsetX, parent.getY() + offsetY), new Dimensions(width, height));
    }

    public void recalculate() {

        transform.setPosition(new Vector2f(parent.getPosition().getX() + offsetX, parent.getPosition().getY() + offsetY));
    }

    @Override
    public boolean collides(final GameObject other) {

        return transform.intersects(other.getTransform());
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(final float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(final float offsetY) {
        this.offsetY = offsetY;
    }

    public Vector2f getPosition() {
        return transform.getPosition();
    }

    public void setPosition(final Vector2f position) {
        this.transform.setPosition(position);
    }

    public float getWidthExact() {
        return transform.getWidth();
    }

    public int getWidth() {
        return transform.getWidthAsInt();
    }

    public void setWidth(final float width) {
        this.transform.setWidth(width);
    }

    public float getHeightExact() {
        return transform.getHeight();
    }

    public int getHeight() {
        return transform.getHeightAsInt();
    }

    public void setHeight(final float height) {
        this.transform.setHeight(height);
    }

    public GameObject getParent() {
        return parent;
    }
}
