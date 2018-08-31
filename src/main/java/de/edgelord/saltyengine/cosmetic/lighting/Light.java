package de.edgelord.saltyengine.cosmetic.lighting;

import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Light {

    private BufferedImage image;
    private Shape shape;

    public Light(BufferedImage lightSource) {
        this.image = lightSource;

        shape = Shape.CUSTOM;
    }

    public Light(Shape shape) {
        this.shape = shape;

        switch (shape) {

            case ROUND:
                this.image = StaticSystem.defaultImageFactory.getOptimizedImageResource("res/pictures/light/default_light_round.png");
                break;
            case CUSTOM:
                System.err.println("ERROR: CAN'T SET SHAPE OF LIGHT TO CUSTOM WITHOUT HAVING A CUSTOM IMAGE!");
                break;
        }
    }

    public void draw(Graphics2D graphics) {

    }

    public enum Shape {ROUND, CUSTOM}
}
