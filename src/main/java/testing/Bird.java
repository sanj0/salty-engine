/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.cosmetic.Animation;
import de.edgelord.sjgl.cosmetic.Spritesheet;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.components.DrawHitboxComponent;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.utils.Directions;
import de.edgelord.sjgl.utils.StaticSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends GameObject {

    private final int windowWidth = 1200;
    private final int windowHeight = 909;
    private final Animation animation;
    private final Spritesheet spritesheet;
    private int fixedTicks = 0;

    public Bird(final BufferedImage image, final int xPos, final int yPos) {
        super(new Coordinates(xPos * 150, yPos * 101), 150, 101, "testing.bird");

        animation = new Animation(this);
        spritesheet = new Spritesheet(image, getWidth(), getHeight());

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        // addComponent(new DrawPositionComponent(this, "de.edgelord.sjgl.testing.bird.drawPosition"));
        addComponent(new DrawHitboxComponent(this, "de.edgelord.sjgl.testing.bird.drawHitbox"));
        // addComponent(new AnimationRender(this, "de.edgelord.sjgl.testing.bird.animationRender", animation, 90));
    }

    @Override
    public void initialize() {

        animation.nextFrame();
        getPhysics().addForce("testing.Bird.testingForce", Directions.Direction.RIGHT);
        getDefaultAccelerator().accelerate("testing.Bird.testingForce", 0.01f, 100);

        System.out.println("INFO: Initialized " + getClass());
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {

        if (StaticSystem.inputKey == '-') {
            getDefaultAccelerator().accelerate("testing.Bird.testingForce", 0.01f, 1);
        }

        if (fixedTicks == 15) {

            if (getCoordinates().getY() >= windowHeight) {

                getPosition().setY(0);
            }

            if (getCoordinates().getX() >= windowWidth) {

                getPosition().setX(-getWidth());
                // getPosition().setY(getY() + getHeight());
            }

            fixedTicks = 0;
            return;
        }

        fixedTicks++;
    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(final Graphics2D graphics) {

        // animation.drawCurrentFrame(graphics);

        graphics.drawOval(getMiddle().getX(), getMiddle().getY(), 1, 1);
    }
}
