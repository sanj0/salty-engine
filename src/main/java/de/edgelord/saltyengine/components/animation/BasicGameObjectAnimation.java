/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.components.animation;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.animation.Keyframe;
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class BasicGameObjectAnimation extends Component<GameObject> {

    private boolean recalculateOnNextStep = true;
    private KeyframeAnimation animation = new KeyframeAnimation();

    private Control control;

    public BasicGameObjectAnimation(GameObject parent, String name, Control control) {
        super(parent, name, Components.ANIMATION_COMPONENT);

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

        float delta = animation.nextDelta();

        switch (control) {

            case width:
                getParent().setWidth(getParent().getWidth() + delta);
                getParent().getHitbox().getTransform().setWidth(getParent().getHitbox().getTransform().getWidth() + delta);
                break;
            case height:
                getParent().setHeight(getParent().getHeight() + delta);
                getParent().getHitbox().getTransform().setHeight(getParent().getHitbox().getTransform().getHeight() + delta);
                break;
            case xPos:
                getParent().moveX(delta);
                break;
            case yPos:
                getParent().moveY(delta);
                break;
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

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
