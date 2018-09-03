/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.rendering;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class RectangleRender extends SimpleRenderComponent {

    public RectangleRender(GameObject parent, String name) {
        super(parent, name);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        setUpGraphics(saltyGraphics);

        if (isFill()) {
            saltyGraphics.fillRect(getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
        } else {
            saltyGraphics.drawRect(getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
        }
    }
}
