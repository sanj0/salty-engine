/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.camera;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Camera {

    private Vector2f position = new Vector2f(0f, 0f);
    // The rotation in degrees. That also rotates the UI and brings several problems.
    private float rotation = 0f;

    private AffineTransform originalTransform = null;

    private Vector2f lastPosition = new Vector2f(0f, 0f);

    public Camera() {
    }

    public void setViewToGraphics(Graphics2D graphics) {
        if (originalTransform == null) {
            originalTransform = graphics.getTransform();
        }

        graphics.translate(lastPosition.getX() + position.getX(), lastPosition.getY() + position.getY());
        graphics.rotate(Math.toRadians(rotation), Game.getHost().getWidth() / 2, Game.getHost().getHeight() / 2);
    }

    public void tmpResetViewToGraphics(SaltyGraphics graphics) {
        graphics.setTransform(originalTransform);
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void setX(float x) {
        position.setX(x);
    }

    public void setY(float y) {
        position.setY(y);
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getRotation() {
        return rotation;
    }
}
