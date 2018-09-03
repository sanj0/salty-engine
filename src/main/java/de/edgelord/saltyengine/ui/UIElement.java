/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.ui;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class UIElement extends GameObject {

    private Font font = StaticSystem.font;

    public UIElement(Vector2f position, float width, float height) {
        super(position.getX(), position.getY(), width, height, "UIElement");

        removeComponent(DEFAULT_PHYSICS_NAME);
        removeComponent(DEFAULT_RECALCULATE_HITBOX_NAME);
        removeComponent(DEFAULT_ACCELERATOR_NAME);
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onCollision(CollisionEvent e) {
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onTick() {
    }

    public abstract void draw(SaltyGraphics saltyGraphics);

    public abstract void mousePressed(MouseEvent e);

    public abstract void mouseReleased(MouseEvent e);

    public abstract void mouseClicked(MouseEvent e);

    public abstract void mouseMoved(MouseEvent e);

    public abstract void keyPressed(KeyEvent e);

    public abstract void keyReleased(KeyEvent e);

    public abstract void keyTyped(KeyEvent e);

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }
}
