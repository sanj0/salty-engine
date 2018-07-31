package de.edgelord.sjgl.gameobject.components.rendering;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;

import java.awt.*;
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
     * @see GameObjectComponent
     */
    public ImageRender(GameObject parent, String name, BufferedImage image) {
        super(parent, name);

        this.image = image;
    }

    @Override
    public void draw(Graphics2D graphics) {

        // Draw the image
        graphics.drawImage(image, getParent().getCoordinates().getX(), getParent().getCoordinates().getY(), getParent().getWidth(), getParent().getHeight(), null);
    }
}
