/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.cosmetic.Animation;
import de.edgelord.sjgl.cosmetic.Spritesheet;
import de.edgelord.sjgl.display.DisplayManager;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.components.DrawPositionComponent;
import de.edgelord.sjgl.gameobject.components.SimplePhysicsComponent;
import de.edgelord.sjgl.gameobject.components.animation.BasicGameObjectKeyframeAnimation;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.utils.StaticSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BirdPlayer extends GameObject {

    private final Animation animation;
    private final Spritesheet spritesheet;
    private final DisplayManager displayManager;
    private int ticksForAnim = 0;
    private int ticksForSound = 0;

    private BasicGameObjectKeyframeAnimation keyFrameAnimationX = new BasicGameObjectKeyframeAnimation(this, "mySuperAnimationX", BasicGameObjectKeyframeAnimation.Control.xPos);
    private BasicGameObjectKeyframeAnimation keyFrameAnimationWidth = new BasicGameObjectKeyframeAnimation(this, "mySuperAnimationWidth", BasicGameObjectKeyframeAnimation.Control.width);

    public BirdPlayer(final BufferedImage spriteSheetImage, final DisplayManager displayManager, final Coordinates coordinates) {
        super(coordinates, 0, 101, "testing.birdPlayer");

        this.displayManager = displayManager;

        animation = new Animation(this);
        spritesheet = new Spritesheet(spriteSheetImage, 150, 101);

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        keyFrameAnimationX.addKeyFrame(5000, 0);
        keyFrameAnimationX.addKeyFrame(10000, 500);
        keyFrameAnimationX.addKeyFrame(11000, 1000);

        keyFrameAnimationWidth.addKeyFrame(1000, 0);
        keyFrameAnimationWidth.addKeyFrame(5000, 150);


        addComponent(keyFrameAnimationX);
        addComponent(keyFrameAnimationWidth);
        keyFrameAnimationX.start();
        keyFrameAnimationWidth.start();
        getComponents().add(new DrawPositionComponent(this, "drawPositionDev"));
        // getComponents().add(new DrawHitboxComponent(this, "drawHitboxDev"));
    }

    @Override
    public void initialize() {

        animation.nextFrame();

        System.out.println("INFO: Initialized " + getClass());
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {

        if (StaticSystem.inputUp) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setAcceleration(0.005f);
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setAcceleration(0f);
        }

        if (StaticSystem.inputDown) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setAcceleration(0.005f);
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setAcceleration(0f);
        }

        if (StaticSystem.inputRight) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setAcceleration(0.005f);
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setAcceleration(0f);
        }

        if (StaticSystem.inputLeft) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setAcceleration(0.005f);
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setAcceleration(0f);
        }

        if (ticksForSound == 250) {
            if (getCoordinates().getY() >= displayManager.getHeight()) {

                getPosition().setY(0);
            }

            if (getCoordinates().getX() >= displayManager.getWidth()) {

                getPosition().setX(-getWidth());
            }

            if (getCoordinates().getY() <= -getHeight()) {

                getPosition().setY(displayManager.getHeight() + getHeight());
            }

            if (getCoordinates().getX() <= -getWidth()) {

                getPosition().setX(displayManager.getWidth() + getWidth());
            }
        }

        if (displayManager.isInputUp()) {

            if (ticksForAnim == 75) {
                animation.nextFrame();

            }

            if (ticksForSound == 250) {
                Tester.getAudioSystem().play("bird_flap");
            }

        }
        if (displayManager.isInputDown()) {

            if (ticksForAnim == 75) {
                animation.nextFrame();
            }

            if (ticksForSound == 250) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }
        if (displayManager.isInputLeft()) {

            if (ticksForAnim == 75) {
                animation.nextFrame();
            }

            if (ticksForSound == 250) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }
        if (displayManager.isInputRight()) {

            if (ticksForAnim == 75) {
                animation.nextFrame();
            }

            if (ticksForSound == 250) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }

        if (ticksForAnim == 75) {

            ticksForAnim = 0;
        } else {
            ticksForAnim++;
        }

        if (ticksForSound == 250) {

            ticksForSound = 0;
        } else {
            ticksForSound++;
        }
    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(final Graphics2D graphics) {

        animation.drawCurrentFrame(graphics);
        graphics.drawOval(getMiddle().getX(), getMiddle().getY(), 1, 1);
    }
}
