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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;

/**
 * A simple {@link UIElement} that outlines a rectangle with {@link #getBackgroundColor()} and then
 * draws the same rectangle filled above, with a length calculated by {@link #maxValue} and {@link #currentValue}. <p>
 * The outline is seen as the foreground and therefore uses the {@link #getForegroundColor() foreground color} while the bar itself is seen
 * as the background and therefore uses the {@link #getBackgroundColor() background color}.
 */
public class ProgressBar extends UIElement {

    /**
     * The current dimensions of the bar.
     */
    private final Dimensions currentBar = Dimensions.zero();
    /**
     * The maximum value, when this is reached, the progress bar will be full.
     */
    private float maxValue = 100;
    /**
     * The current value of the progress.
     */
    private float currentValue = 0;
    /**
     * The diameter of the rounding at each corner of the two rectangles.
     * <p>
     * This is zero by default.
     */
    private float cornerArc = 0;
    /**
     * The stroke used for outlining the bar.
     * <p>
     * This is <code>null</code> by default to use the one from the {@link SaltyGraphics} passed into {@link #draw(SaltyGraphics)}
     */
    private Stroke outlineStroke = null;

    /**
     * A constructor that takes everything in as floats.
     *
     * @param x      the position on the x axis.
     * @param y      the position on the y axis.
     * @param width  the width.
     * @param height the height.
     */
    public ProgressBar(final float x, final float y, final float width, final float height) {
        this(new Transform(x, y, width, height));
    }

    /**
     * {@inheritDoc}
     */
    public ProgressBar(final Vector2f position, final float width, final float height) {
        super(position, width, height, STATE_DISPLAY_ELEMENT);
    }

    /**
     * {@inheritDoc}
     */
    public ProgressBar(final Transform transform) {
        super(transform, STATE_DISPLAY_ELEMENT);
    }

    @Override
    public void drawBackground(final SaltyGraphics saltyGraphics) {
        saltyGraphics.drawRoundRect(getPosition(), currentBar, cornerArc);
    }

    @Override
    public void drawForeground(final SaltyGraphics saltyGraphics) {
        if (outlineStroke != null) {
            saltyGraphics.setStroke(outlineStroke);
        }

        saltyGraphics.outlineRoundRect(this, cornerArc);
    }

    /**
     * Recalculates the dimensions of the bar.
     */
    public void recalculateBar() {
        currentBar.setHeight(getHeight());
        currentBar.setWidth(getWidth() / (maxValue / currentValue));
    }

    /**
     * Gets {@link #maxValue}.
     *
     * @return the value of {@link #maxValue}
     */
    public float getMaxValue() {
        return maxValue;
    }

    /**
     * Sets {@link #maxValue} and {@link #recalculateBar() recalculates} the bar.
     *
     * @param maxValue the new value of {@link #maxValue}
     */
    public void setMaxValue(final float maxValue) {
        this.maxValue = maxValue;
        recalculateBar();
    }

    /**
     * Gets {@link #currentValue}.
     *
     * @return the value of {@link #currentValue}
     */
    public float getCurrentValue() {
        return currentValue;
    }

    /**
     * Sets {@link #currentValue} and {@link #recalculateBar() recalculates} the bar.
     *
     * @param currentValue the new value of {@link #currentValue}
     */
    public void setCurrentValue(final float currentValue) {
        this.currentValue = currentValue;
        recalculateBar();
    }

    /**
     * Gets {@link #cornerArc}.
     *
     * @return the value of {@link #cornerArc}
     */
    public float getCornerArc() {
        return cornerArc;
    }

    /**
     * Sets {@link #cornerArc}.
     *
     * @param cornerArc the new value of {@link #cornerArc}
     */
    public void setCornerArc(final float cornerArc) {
        this.cornerArc = cornerArc;
    }

    /**
     * Gets {@link #outlineStroke}.
     *
     * @return the value of {@link #outlineStroke}
     */
    public Stroke getOutlineStroke() {
        return outlineStroke;
    }

    /**
     * Sets {@link #outlineStroke}.
     *
     * @param outlineStroke the new value of {@link #outlineStroke}
     */
    public void setOutlineStroke(final Stroke outlineStroke) {
        this.outlineStroke = outlineStroke;
    }
}
