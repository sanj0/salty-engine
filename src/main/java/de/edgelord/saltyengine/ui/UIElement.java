/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.ui;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.interfaces.*;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Consumer;

public abstract class UIElement extends ComponentParent implements Drawable, FixedTickRoutine, MouseInputHandler, KeyboardInputHandler {

    private Font font = StaticSystem.font;

    private Transform transform;

    private List<Component> components = new CopyOnWriteArrayList<>();

    public UIElement(Vector2f position, float width, float height) {

        this.transform = new Transform(position, new Dimensions(width, height));
    }

    public UIElement(Transform transform) {
        this(transform.getPosition(), transform.getWidth(), transform.getHeight());
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

    @Override
    public abstract void mouseDragged(MouseEvent e);

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void removeComponent(String identifier) {
        components.removeIf(component -> component.getName().equals(identifier));
    }

    @Override
    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public List<Component> getComponents() {
        return components;
    }

    @Override
    public Component getComponent(String identifier) {

        for (Component component : components) {
            if (component.getName().equals(identifier)) {
                return component;
            }
        }

        return null;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Coordinates getCoordinates() {
        return getPosition().convertToCoordinates();
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
