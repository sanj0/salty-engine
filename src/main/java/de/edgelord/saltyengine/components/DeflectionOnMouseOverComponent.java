/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.animation.TransitionFunction;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * Uses a {@link KeyframeAnimation} to make its parent grow and shrink again
 * when the cursor touches is.
 */
public class DeflectionOnMouseOverComponent extends Component<ComponentContainer> {

    /**
     * The animation that is used.
     */
    private final KeyframeAnimation keyframeAnimation = new KeyframeAnimation(TransitionFunction.easeInOutSine());
    private final Vector2f returnPosition;
    private float totalDeflection = 0f;

    /**
     * Whether to loop the animation or only playing it whenever the cursor
     * enters the parent
     */
    private boolean loop;
    private boolean shouldPlay = false;
    private boolean singleDeflection = false;
    private boolean impact = false;

    /**
     * The constructor.
     *
     * @param parent   the parent of this component
     * @param name     the name of this component
     * @param distance the distance of the focus-animation
     * @param interval the amount of ticks between the deflections
     * @param loop     whether to loop the animation or only playing it whenever
     *                 the cursor enters the parent
     */
    public DeflectionOnMouseOverComponent(final ComponentContainer parent, final String name, final float distance, final int interval, final boolean loop) {
        super(parent, name, Components.GFX_COMPONENT);

        this.loop = loop;

        keyframeAnimation.appendFrame(0, 0);
        keyframeAnimation.appendFrame(interval, distance);
        keyframeAnimation.appendFrame(interval * 2, 0);

        final Vector2f currentCentre = getParent().getTransform().getCentre();
        returnPosition = new Vector2f(currentCentre.getX(), currentCentre.getY());
    }

    @Override
    public void onCursorEntersParent() {
        if (!loop) {
            singleDeflection = true;
        } else {
            shouldPlay = true;
        }
    }

    @Override
    public void onCursorExitsParent() {
        resetTransform();
        singleDeflection = false;
        shouldPlay = false;
        totalDeflection = 0f;
        keyframeAnimation.reset();
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onFixedTick() {
        if (keyframeAnimation.finished()) {
            if (impact) {
                resetTransform();
                impact = false;
            }

            keyframeAnimation.reset();
            if (!loop) {
                singleDeflection = false;
            }
        }

        if (shouldPlay || singleDeflection || impact) {
            nextFrame();
        }

        updatePosition();
    }

    private void resetTransform() {
        getParent().getDimensions().subtract(totalDeflection, totalDeflection);
        getParent().positionByCentre(returnPosition);
    }

    private void nextFrame() {
        final float delta = keyframeAnimation.nextValue() - totalDeflection;
        totalDeflection += delta;

        getParent().setWidth(getParent().getWidth() + delta);
        getParent().setX(getParent().getX() - (delta / 2f));
        getParent().setHeight(getParent().getHeight() + delta);
        getParent().setY(getParent().getY() - (delta / 2f));
    }

    /**
     * Updates the return position to which this component's parent returns
     * after the mouse leaves it again. This is called every fixed tick, so
     * there should be no need to call it manually.
     */
    public void updatePosition() {
        final Vector2f centre = getParent().getTransform().getCentre();
        returnPosition.setX(centre.getX());
        returnPosition.setY(centre.getY());
    }

    /**
     * Calls {@link #cancel()} before disabling.
     */
    @Override
    public void disable() {
        cancel();
        super.disable();
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(final boolean loop) {
        this.loop = loop;
    }

    public void impact() {
        this.impact = true;
    }

    /**
     * Cancels the animation and resets everything. If {@link #loop} is true,
     * the animation start again when the cursor enters the parent again.
     */
    public void cancel() {
        resetTransform();
        singleDeflection = false;
        impact = false;
        shouldPlay = false;
        totalDeflection = 0f;
        keyframeAnimation.reset();
    }
}
