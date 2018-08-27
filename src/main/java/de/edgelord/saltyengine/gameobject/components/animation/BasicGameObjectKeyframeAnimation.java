/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.animation;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.GameObjectComponent;

import java.awt.*;

public class BasicGameObjectKeyframeAnimation extends GameObjectComponent {

    private boolean recalculateOnNextStep = true;
    private KeyframeAnimation animation = new KeyframeAnimation();

    private Control control;

    public BasicGameObjectKeyframeAnimation(GameObject parent, String name, Control control) {
        super(parent, name, ANIMATION_COMPONENT);

        this.control = control;
    }

    public void init() {
        animation.calculateAnimation();
    }

    public void startOver() {
        recalculateOnNextStep = true;
    }

    public void start() {
        enable();
    }

    public void pause() {
        disable();
    }

    public void stop() {
        recalculateOnNextStep = true;
        disable();
    }

    @Override
    public void onFixedTick() {

        if (recalculateOnNextStep) {
            animation.calculateAnimation();
            recalculateOnNextStep = false;
        }

        switch (control) {

            case width:
                getParent().setWidth(getParent().getWidthExact() + animation.nextDelta());
                break;
            case height:
                getParent().setHeight(getParent().getHeightExact() + animation.nextDelta());
                break;
            case xPos:
                getParent().moveX(animation.nextDelta());
                break;
            case yPos:
                getParent().moveY(animation.nextDelta());
                break;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void addKeyFrame(Keyframe keyframe) {
        animation.add(keyframe);
    }

    public void addKeyFrame(int timing, float value) {
        animation.add(timing, value);
    }

    public enum Control {
        width,
        height,
        xPos,
        yPos
    }
}