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

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Dimensions;

/**
 * Uses a {@link LinearKeyframeAnimation} to make its parent grow and shrink again when the cursor touches is.
 */
public class DeflectionOnMouseOverComponent extends Component< ComponentContainer > {

    /**
     * The animation that is used.
     */
    private LinearKeyframeAnimation keyframeAnimation = new LinearKeyframeAnimation();

    /**
     * The {@link Dimensions} to which this component's parent returns when the cursor touches it no more.
     */
    private Dimensions returnDimensions;

    /**
     * Whether or not to loop the animation or only playing it whenever the cursor enters the parent
     */
    private boolean loop;

    private float currentReplacement = 0;

    private boolean singleDeflection = false;

    /**
     * The constructor.
     *
     * @param parent the parent of this component
     * @param name the name of this component
     * @param distance the distance of the focus-animation
     * @param interval the amount of ticks between the deflections
     * @param loop whether to loop the animation or only playing it whenever the cursor enters the parent
     */
    public DeflectionOnMouseOverComponent(ComponentContainer parent, String name, float distance, int interval, boolean loop) {
        super(parent, name, Components.GFX_COMPONENT);

        this.loop = loop;

        keyframeAnimation.add(interval, distance);
        keyframeAnimation.add(interval * 2, 0);
        keyframeAnimation.calculateAnimation();

        returnDimensions = new Dimensions(getParent().getWidth(), getParent().getHeight());
    }

    @Override
    public void onCursorEntersParent() {

        if (!loop) {
            singleDeflection = true;
        }
    }

    @Override
    public void onCursorExitsParent() {
        getParent().setDimensions(returnDimensions);
        getParent().setX(getParent().getX() + currentReplacement);
        getParent().setY(getParent().getY() + currentReplacement);
        currentReplacement = 0;
        singleDeflection = false;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }
    @Override
    public void onFixedTick() {

        if (keyframeAnimation.animationEnded()) {
            keyframeAnimation.restart();
            if (!loop) {
                singleDeflection = false;
            }
        }

        if (loop || singleDeflection) {
            if (Input.getCursor().intersects(getParent().getTransform())) {
                nextFrame();
            }
        }
    }

    private void nextFrame() {
        float delta = keyframeAnimation.nextDelta();
        currentReplacement += delta / 2f;

        getParent().setWidth(getParent().getWidth() + delta);
        getParent().setX(getParent().getX() - (delta / 2f));
        getParent().setHeight(getParent().getHeight() + delta);
        getParent().setY(getParent().getY() - (delta / 2f));
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public Dimensions getReturnDimensions() {
        return returnDimensions;
    }

    public void setReturnDimensions(Dimensions returnDimensions) {
        this.returnDimensions = returnDimensions;
    }

    public boolean isLoop() {
        return loop;
    }

    public void setLoop(boolean loop) {
        this.loop = loop;
    }
}
