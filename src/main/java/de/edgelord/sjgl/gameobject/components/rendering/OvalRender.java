/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject.components.rendering;

import de.edgelord.sjgl.gameobject.GameObject;

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
    public void draw(final Graphics2D graphics) {

        setUpGraphics(graphics);

        if (isFill()) {
            graphics.fillOval(getParent().getCoordinates().getX(), getParent().getCoordinates().getY(), getParent().getWidth(), getParent().getHeight());
        } else {
            graphics.drawOval(getParent().getCoordinates().getX(), getParent().getCoordinates().getY(), getParent().getWidth(), getParent().getHeight());
        }
    }
}
