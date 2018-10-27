/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.cosmetic.Animation;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class AnimationRender extends SimpleRenderComponent {

    // The Animation which should be drawn
    private Animation animation = null;

    // The number of fixed ticks (default would be something like 1 milli; set i the Engine constructor)
    // after which the next frame should be drawn
    private int ticksPerFrame = 75;

    // The current number of fixed ticks; when it reaches #ticksPerFrame it will be reset to 0 again
    private int ticks = 0;

    /**
     * The default super constructor for gameObjectComponent, which takes in the parent GameObject and the
     * name, used as an id, for fishing specific components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the Coordinate info from
     * @param name   the id-name for this Component
     * @see de.edgelord.saltyengine.core.Component
     */
    public AnimationRender(ComponentParent parent, String name) {
        super(parent, name);
    }

    /**
     * A constructor with all necessary parameters
     *
     * @param parent        the parent of the Component, so where to take e.g. the Coordinates info from
     * @param name          the id-name of this Component
     * @param animation     the animation that should be rendered
     * @param ticksPerFrame after how many fixed ticks the next frame of the animation should be triggered
     */
    public AnimationRender(ComponentParent parent, String name, Animation animation, int ticksPerFrame) {
        super(parent, name);
        this.animation = animation;
        this.ticksPerFrame = ticksPerFrame;
    }

    /**
     * Draws the current frame of the animation
     *
     * @param saltyGraphics the SaltyGraphics to which the component should DRAW
     * @see #onFixedTick()
     * @see #animation
     * @see de.edgelord.saltyengine.core.Component
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (isEnabled()) {
            if (animation != null) {
                animation.drawCurrentFrame(saltyGraphics);
            }
        }
    }

    /**
     * On every fixed tick, #ticks is incremented; when it reaches #ticksPerFrame,
     * the next frame of #animation gets triggered and ticks will be reset to 0
     */
    @Override
    public void onFixedTick() {
        if (isEnabled()) {
            if (ticks == ticksPerFrame) {
                if (animation != null) {
                    animation.nextFrame();
                    ticks = 0;
                }
            } else if (animation != null) {
                ticks++;
            }
        }
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public int getTicksPerFrame() {
        return ticksPerFrame;
    }

    public void setTicksPerFrame(int ticksPerFrame) {
        this.ticksPerFrame = ticksPerFrame;
    }
}
