/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.rendering;

import de.edgelord.saltyengine.gameobject.GameObject;

import java.awt.*;

public class RectangleRender extends SimpleRenderComponent {

    public RectangleRender(GameObject parent, String name) {
        super(parent, name);
    }

    @Override
    public void draw(Graphics2D graphics) {

        setUpGraphics(graphics);

        if (isFill()) {
            graphics.fillRect(getParent().getCoordinates().getX(), getParent().getCoordinates().getY(), getParent().getWidth(), getParent().getHeight());
        } else {
            graphics.drawRect(getParent().getCoordinates().getX(), getParent().getCoordinates().getY(), getParent().getWidth(), getParent().getHeight());
        }
    }
}
