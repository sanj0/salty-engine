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

import de.edgelord.saltyengine.components.rendering.RenderComponent;
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.effect.SpritesheetAnimation;
import de.edgelord.saltyengine.gameobject.Components;

/**
 * A {@link de.edgelord.saltyengine.core.Component} that renders an {@link SpritesheetAnimation}.
 * After each {@link #ticksPerFrame} amount of fixed ticks, the next frame of the spritesheetAnimation will be drawn.
 */
@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public class AnimationRender extends RenderComponent {

    /**
     * The SpritesheetAnimation which should be drawn
     */
    private SpritesheetAnimation spritesheetAnimation = null;

    /**
     * The number of fixed ticks after which the next frame should be drawn
     */
    private int ticksPerFrame = 75;

    /**
     * The current number of fixed ticks; when it reaches #ticksPerFrame it will be reset to 0 again
     */
    private int ticks = 0;

    /**
     * The default super constructor for gameObjectComponent, which takes in the parent GameObject and the
     * name, used as an id, for fishing specific components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the Coordinate info from
     * @param name   the id-name for this Component
     * @see de.edgelord.saltyengine.core.Component
     */
    public AnimationRender(ComponentContainer parent, String name) {
        super(parent, name, Components.RENDER_COMPONENT);
    }

    /**
     * A constructor with all necessary parameters
     *
     * @param parent               the parent of the Component
     * @param name                 the id-name of this Component
     * @param spritesheetAnimation the spritesheetAnimation that should be rendered
     * @param ticksPerFrame        after how many fixed ticks the next frame of the spritesheetAnimation should be triggered
     */
    public AnimationRender(ComponentContainer parent, String name, SpritesheetAnimation spritesheetAnimation, int ticksPerFrame) {
        super(parent, name, Components.RENDER_COMPONENT);
        this.spritesheetAnimation = spritesheetAnimation;
        this.ticksPerFrame = ticksPerFrame;
    }

    /**
     * Draws the current frame of the spritesheetAnimation
     *
     * @param saltyGraphics the SaltyGraphics to which the component should draw
     * @see #onFixedTick()
     * @see #spritesheetAnimation
     * @see de.edgelord.saltyengine.core.Component
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (spritesheetAnimation != null) {
            spritesheetAnimation.drawCurrentFrame(saltyGraphics);
        }
    }

    /**
     * On every fixed tick, {@link #ticks} is incremented; when it reaches {@link #ticksPerFrame},
     * the next frame of {@link #spritesheetAnimation} is triggered and ticks will be reset to 0
     */
    @Override
    public void onFixedTick() {
        if (ticks == ticksPerFrame) {
            if (spritesheetAnimation != null) {
                spritesheetAnimation.nextFrame();
                ticks = 0;
            }
        } else if (spritesheetAnimation != null) {
            ticks++;
        }
    }

    /**
     * @return the spritesheetAnimation that this component renders.
     */
    public SpritesheetAnimation getSpritesheetAnimation() {
        return spritesheetAnimation;
    }

    /**
     * Sets the spritesheetAnimation to be rendered.
     *
     * @param spritesheetAnimation the new spritesheetAnimation
     */
    public void setSpritesheetAnimation(SpritesheetAnimation spritesheetAnimation) {
        this.spritesheetAnimation = spritesheetAnimation;
    }

    /**
     * @return the fixed ticks per frame of the spritesheetAnimation.
     */
    public int getTicksPerFrame() {
        return ticksPerFrame;
    }

    /**
     * Sets how many fixed ticks should last until the next frame of the spritesheetAnimation.
     *
     * @param ticksPerFrame the new value
     */
    public void setTicksPerFrame(int ticksPerFrame) {
        this.ticksPerFrame = ticksPerFrame;
        ticks = 0;
    }
}
