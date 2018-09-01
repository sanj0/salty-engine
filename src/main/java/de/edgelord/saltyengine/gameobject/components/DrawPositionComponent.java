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

public class DrawPositionComponent extends GameObjectComponent {

    public DrawPositionComponent(GameObject parent, String name) {
        super(parent, name, TECHNICAL_DRAW_COMPONENT);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        saltyGraphics.setColor(Color.BLACK);

        saltyGraphics.drawText(String.valueOf(getParent().getX()), getParent().getX(), getParent().getY() - 5);

        String yPosition = String.valueOf(getParent().getY());

        saltyGraphics.drawText(yPosition, getParent().getX() - (yPosition.length() * 7), getParent().getY() + 25);
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
