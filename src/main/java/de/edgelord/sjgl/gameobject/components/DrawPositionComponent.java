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

public class DrawPositionComponent extends GameObjectComponent {

    public DrawPositionComponent(GameObject parent, String name) {
        super(parent, name);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.setFont(new Font(Font.MONOSPACED, 0, 15));
        graphics.setColor(Color.BLACK);

        graphics.drawString(String.valueOf(getParent().getX()), getParent().getCoordinates().getX(), getParent().getCoordinates().getY() - 5);

        String yPosition = String.valueOf(getParent().getY());

        graphics.drawString(yPosition, getParent().getCoordinates().getX() - (yPosition.length() * 7), getParent().getCoordinates().getY() + 25);
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
