/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing;

import de.edgelord.saltyengine.components.gfx.SceneFade;
import de.edgelord.saltyengine.components.rendering.AnimationRender;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.cosmetic.Animation;
import de.edgelord.saltyengine.cosmetic.Spritesheet;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.image.BufferedImage;

public class Bird extends GameObject {

    private final Animation animation;
    private final Spritesheet spritesheet;

    public Bird(final BufferedImage image, final int xPos, final int yPos) {
        super(xPos * 150, yPos * 101, 150, 101, "testing.bird");

        animation = new Animation(this);
        spritesheet = new Spritesheet(image, getWidthAsInt(), getHeightAsInt());

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        addComponent(new AnimationRender(this, "de.edgelord.saltyengine.testing.bird.animationRender", animation, 90));

        // Improves performance a lot!
        // setStationary(true);
    }

    @Override
    public void initialize() {

        getPhysics().addForce("testing.Bird.testingForce", Directions.Direction.RIGHT);
        // getDefaultAccelerator().accelerate("testing.Bird.testingForce", 0.01f, 100);

        System.out.println("INFO: Initialized " + getClass());
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {

        // getTransform().rotateToPoint(Game.cursorPosition);

        if (Game.keyboardInput.isSpace()) {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(0.01f);
        } else {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(0f);
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        animation.drawCurrentFrame(saltyGraphics);
    }
}
