/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.ui;

import de.edgelord.saltyengine.core.Drawable;
import de.edgelord.saltyengine.core.FixedTickRoutine;
import de.edgelord.saltyengine.core.KeyboardInputHandler;
import de.edgelord.saltyengine.core.MouseInputHandler;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class UIElement implements Drawable, FixedTickRoutine, MouseInputHandler, KeyboardInputHandler {

    private Font font = StaticSystem.font;

    private Vector2f position;
    private float width, height;

    public UIElement(Vector2f position, float width, float height) {

        this.position = position;
        this.width = width;
        this.height = height;
    }

    @Override
    public abstract void onFixedTick();

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public abstract void mousePressed(MouseEvent e);

    @Override
    public abstract void mouseReleased(MouseEvent e);

    @Override
    public abstract void mouseClicked(MouseEvent e);

    @Override
    public abstract void mouseMoved(MouseEvent e);

    @Override
    public abstract void keyPressed(KeyEvent e);

    @Override
    public abstract void keyReleased(KeyEvent e);

    @Override
    public abstract void keyTyped(KeyEvent e);

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Vector2f getPosition() {
        return position;
    }

    public Coordinates getCoordinates() {
        return position.convertToCoordinates();
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

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public Transform getTransform() {
        return new Transform(position, new Dimensions(width, height));
    }

    public void setTransform(Transform transform) {
        setPosition(transform.getPosition());
        setWidth(transform.getWidth());
        setHeight(transform.getHeight());
    }
}
