/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.GameObjectComponent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;

public class DrawHitboxComponent extends GameObjectComponent {

    public DrawHitboxComponent(GameObject parent, String name) {
        super(parent, name, TECHNICAL_DRAW_COMPONENT);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(new Color(1, 0, 0, 0.25f));

        saltyGraphics.fillRect(getParent().getHitbox().getTransform().getPosition().getX(), getParent().getHitbox().getTransform().getPosition().getY(), getParent().getHitbox().getTransform().getWidth(), getParent().getHitbox().getTransform().getHeight());
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
