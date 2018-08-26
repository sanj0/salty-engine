/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.cosmetic.Animation;
import de.edgelord.saltyengine.cosmetic.Spritesheet;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.components.FixedRate;
import de.edgelord.saltyengine.gameobject.components.gfx.WobblingEffect;
import de.edgelord.saltyengine.gameobject.components.rendering.AnimationRender;
import de.edgelord.saltyengine.location.Coordinates;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Bird extends GameObject {

    private final int windowWidth = 1200;
    private final int windowHeight = 909;
    private final Animation animation;
    private final Spritesheet spritesheet;

    private FixedRate refreshPositionTiming = new FixedRate(this, "refreshPositionTiming", 15);

    public Bird(final BufferedImage image, final int xPos, final int yPos) {
        super(new Coordinates(xPos * 150, yPos * 101), 150, 101, "testing.bird");

        animation = new Animation(this);
        spritesheet = new Spritesheet(image, getWidth(), getHeight());

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        // addComponent(new DrawPositionComponent(this, "de.edgelord.saltyengine.testing.bird.drawPosition"));
        // addComponent(new DrawHitboxComponent(this, "de.edgelord.saltyengine.testing.bird.drawHitbox"));
        addComponent(new AnimationRender(this, "de.edgelord.saltyengine.testing.bird.animationRender", animation, 90));

        WobblingEffect wobblingEffect = new WobblingEffect(this, "wobblingGFX");
        wobblingEffect.init(5, 5, -5, -5);
        wobblingEffect.startGFX();

        addComponent(wobblingEffect);
        addComponent(refreshPositionTiming);
    }

    @Override
    public void initialize() {

        animation.nextFrame();
        getPhysics().addForce("testing.Bird.testingForce", Directions.Direction.RIGHT);
        // getDefaultAccelerator().accelerate("testing.Bird.testingForce", 0.01f, 100);

        System.out.println("INFO: Initialized " + getClass());
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {

        if (StaticSystem.lastInputKey == '-') {
            getDefaultAccelerator().accelerate("testing.Bird.testingForce", 0.01f, 1);
        }

        if (refreshPositionTiming.now()) {

            if (getCoordinates().getY() >= windowHeight) {

                getPosition().setY(0);
            }

            if (getCoordinates().getX() >= windowWidth) {

                getPosition().setX(-getWidth());
                // getPosition().setY(getY() + getHeight());
            }
        }
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
