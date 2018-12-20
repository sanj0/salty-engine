/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.components.animation;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.animation.Keyframe;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

/**
 * Animates the basic state of its {@link #getParent()}.
 * That includes rotation, width, height, x position and y position.
 * <p>
 * NOTE: Take care of the hitbox of the {@link #getParent()}, these animations won't change it, you have to do that manually
 * if necessary!
 */
public class BasicGameObjectAnimation extends Component<GameObject> {

    private boolean recalculateOnNextStep = true;
    private LinearKeyframeAnimation animation = new LinearKeyframeAnimation();
    private boolean loop = false;

    private Control control;

    public BasicGameObjectAnimation(GameObject parent, String name, Control control) {
        super(parent, name, Components.ANIMATION_COMPONENT);

        this.control = control;
        disable();
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

        if (animation.animationEnded()) {
            if (isLoop()) {
                animation.restart();
            } else {
                remove();
            }
        }

        float delta = animation.nextDelta();

        switch (control) {

            case WIDTH:
                getParent().setWidth(getParent().getWidth() + delta);
                break;
            case HEIGHT:
                getParent().setHeight(getParent().getHeight() + delta);
                break;
            case X_POS:
                getParent().moveX(delta);
                break;
            case Y_POS:
                getParent().moveY(delta);
                break;

            case ROTATION:
                getParent().getTransform().setRotationDegrees(getParent().getTransform().getRotationDegrees() + delta);
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
        WIDTH,
        HEIGHT,
        X_POS,
        Y_POS,
        ROTATION
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
