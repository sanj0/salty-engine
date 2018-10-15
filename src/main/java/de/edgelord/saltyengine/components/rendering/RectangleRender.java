/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class RectangleRender extends SimpleRenderComponent {

    public RectangleRender(ComponentParent parent, String name) {
        super(parent, name);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        setUpGraphics(saltyGraphics);

        if (isFill()) {
            saltyGraphics.drawRect(getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
        } else {
            saltyGraphics.outlineRect(getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
        }
    }
}
