/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.rendering;

import de.edgelord.saltyengine.core.interfaces.ComponentParent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.image.BufferedImage;

public class ImageRender extends SimpleRenderComponent {

    // The image to render
    private BufferedImage image;

    /**
     * The default super constructor for gameObjectComponent, which takes in the parent GameObject and the
     * name, used as an id, for fishing specific Components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the Coordinate info from
     * @param name   the id-name for this Component
     * @param image  the image to be drawn by this component
     * @see de.edgelord.saltyengine.core.Component
     */
    public ImageRender(ComponentParent parent, String name, BufferedImage image) {
        super(parent, name);

        this.image = image;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        // Draw the image
        saltyGraphics.drawImage(image, getParent().getX(), getParent().getY(), getParent().getWidth(), getParent().getHeight());
    }
}
