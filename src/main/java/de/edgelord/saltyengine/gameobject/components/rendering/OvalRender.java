/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.rendering;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;

/**
 * This component is used for simplifying the process of rendering an oval.
 * <p>
 * The only thing that is LEFT for the user to call is
 * <code>GameObject.addComponent(new OvalRender(this, "some_id_name"));</code>
 */
public class OvalRender extends SimpleRenderComponent {

    public OvalRender(final GameObject parent, final String name) {
        super(parent, name);
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        setUpGraphics(saltyGraphics);

        if (isFill()) {
            saltyGraphics.fillOval(getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
        } else {
            saltyGraphics.drawOval(getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
        }
    }
}
