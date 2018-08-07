/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;

public class HugeImageRenderingTest extends GameObject {

    private BufferedImage image;

    public HugeImageRenderingTest(BufferedImage image, int width, int height) {
        super(new Coordinates(0, 0), width, height, "huge_image_rendering_test");

        this.image = image;
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onCollision(CollisionEvent e) {

        // System.out.println("Something collided with the biogas plant");
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void onTick() {
    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawImage(image, getCoordinates().getX(), getCoordinates().getY(), getWidth(), getHeight(), null);
    }
}
