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
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;

/**
 * Animates the {@link de.edgelord.saltyengine.transform.Transform}
 * of its {@link #getParent()} using a {@link
 * LinearKeyframeAnimation}. That includes
 * rotation, width, height, x and y position.
 * <p>
 * Usage:
 *
 * <pre>
 *     {@code
 *
 *     public class Player extends GameObject {
 *
 *         // [...]
 *
 *         public void initialize() {
 *             LinearTransformAnimations animationX = new LinearTransformAnimations(this, "animX", LinearTransformAnimations.Control.X_POS);
 *
 *             // Adds a keyframe at 1000 with a value of 200
 *             animationX.addKeyframe(1000, 200);
 *
 *             // Adds the component to the list
 *             addComponent(animationX);
 *
 *             // Let the animation start. during the next 1000 fixed ticks, the Player will slide to the right by 200 pixels.
 *             animationX.start();
 *         }
 *     }
 *     }
 * </pre>
 * <p>
 * NOTE: Take care of the hitbox of the {@link
 * #getParent()}, these animations won't change
 * it, you have to do that manually if necessary!
 */
public class LinearTransformAnimations extends Component {

    /**
     * The keyframe animation used to animate the
     * {@link de.edgelord.saltyengine.transform.Transform}.
     */
    private final LinearKeyframeAnimation animation = new LinearKeyframeAnimation();
    /**
     * What the animation should control
     */
    private final Control control;
    /**
     * If this is true, the {@link #animation}
     * will be recalculated using {@link
     * LinearKeyframeAnimation#calculateAnimation()}
     * at the next fixed tick, and this boolean
     * will be reset to false again.
     */
    private boolean recalculateOnNextStep = true;
    /**
     * Whether the animation should be looped or
     * not. Default getters and setters exist.
     */
    private boolean loop = false;

    /**
     * {@inheritDoc}
     *
     * @param control what this component should
     *                animate
     */
    public LinearTransformAnimations(final ComponentContainer parent, final String name, final Control control) {
        super(parent, name, Components.ANIMATION_COMPONENT);

        this.control = control;
        disable();
    }

    /**
     * Makes the animation start at 0 again by
     * setting {@link #recalculateOnNextStep} to
     * true.
     */
    public void startOver() {
        recalculateOnNextStep = true;
    }

    /**
     * Starts the animation by enabling this
     * component using {@link #enable()}.
     */
    public void start() {
        enable();
    }

    /**
     * Pauses the animations by using {@link
     * #disable()}.
     */
    public void pause() {
        disable();
    }

    /**
     * Stops the animation by calling {@link
     * #animation}.setCurrentFrame(0) and
     * disabling this component using {@link
     * #disable()}.
     */
    public void stop() {
        animation.setCurrentFrame(0);
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

        final float delta = animation.nextDelta();

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
    public void draw(final SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    /**
     * Adds the given {@link Keyframe} to the
     * {@link #animation} by using {@link
     * LinearKeyframeAnimation#add(Keyframe)}.
     *
     * @param keyframe the keyframe to be added
     */
    public void addKeyframe(final Keyframe keyframe) {
        animation.add(keyframe);
    }

    /**
     * Adds anew {@link Keyframe} with the given
     * timing and value to the {@link
     * #animation}.
     *
     * @param timing the timing of the new
     *               keyframe
     * @param value  the value of the keyframe
     */
    public void addKeyframe(final int timing, final float value) {
        animation.add(timing, value);
    }

    /**
     * @return the value of {@link #loop}.
     */
    public boolean isLoop() {
        return loop;
    }

    /**
     * Sets the value of {@link #loop} to the
     * given boolean.
     *
     * @param loop the new value for {@link
     *             #loop}.
     */
    public void setLoop(final boolean loop) {
        this.loop = loop;
    }

    /**
     * Describes which part of the {@link
     * de.edgelord.saltyengine.transform.Transform}
     * is to be animated.
     *
     * <code>WIDTH</code> meaning the width of it
     * ({@link de.edgelord.saltyengine.transform.Transform#setWidth(float)})
     * <code>HEIGHT</code> meaning the height of
     * it ({@link de.edgelord.saltyengine.transform.Transform#setHeight(float)})
     * <code>X_POS</code> meaning the x position
     * of it ({@link de.edgelord.saltyengine.transform.Transform#setX(float)})
     * <code>Y_POS</code> meaning the y position
     * of it ({@link de.edgelord.saltyengine.transform.Transform#setY(float)})
     * <code>ROTATION</code> meaning the rotation
     * of it ({@link de.edgelord.saltyengine.transform.Transform#setRotationDegrees(float)})
     */
    public enum Control {
        WIDTH,
        HEIGHT,
        X_POS,
        Y_POS,
        ROTATION
    }
}
