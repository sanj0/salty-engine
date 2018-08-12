/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;

import java.awt.*;

public class DrawHitboxComponent extends GameObjectComponent {

    public DrawHitboxComponent(GameObject parent, String name) {
        super(parent, name, TECHNICAL_DRAW_COMPONENT);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.setColor(new Color(1, 0, 0, 0.25f));

        graphics.fillRect((int) getParent().getHitbox().getPosition().getX(), (int) getParent().getHitbox().getPosition().getY(), getParent().getHitbox().getWidth(), getParent().getHitbox().getHeight());
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
