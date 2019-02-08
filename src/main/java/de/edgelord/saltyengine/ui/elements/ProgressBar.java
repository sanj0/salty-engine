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
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.ui.UIElement;

/**
 * A simple {@link UIElement} that outlines a rectangle with {@link #getBackgroundColor()} and then
 * draws the same rectangle filled above, with a length calculated by {@link #maxValue} and {@link #currentValue}.
 */
public class ProgressBar extends UIElement {

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
     * The current dimensions of the bar.
     */
    private Dimensions currentBar = Dimensions.zero();

    /**
     * A constructor that takes everything in as floats.
     *
     * @param x      the position on the x axis.
     * @param y      the position on the y axis.
     * @param width  the width.
     * @param height the height.
     */
    public ProgressBar(float x, float y, float width, float height) {
        this(new Transform(x, y, width, height));
    }

    /**
     * {@inheritDoc}
     */
    public ProgressBar(Coordinates2f position, float width, float height) {
        super(position, width, height, STATE_DISPLAY_ELEMENT);
    }

    /**
     * {@inheritDoc}
     */
    public ProgressBar(Transform transform) {
        super(transform, STATE_DISPLAY_ELEMENT);
    }

    @Override
    public void drawBackground(SaltyGraphics saltyGraphics) {

        saltyGraphics.outlineRoundRect(this, cornerArc);

        saltyGraphics.drawRoundRect(getPosition(), currentBar, cornerArc);
    }

    @Override
    public void drawForeground(SaltyGraphics saltyGraphics) {

    }

    /**
     * Recalculates the dimensions of the bar.
     */
    public void recalculateBar() {
        currentBar.setHeight(getHeight());
        currentBar.setWidth(getWidth() / (maxValue / currentValue));
    }

    public float getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(float maxValue) {
        this.maxValue = maxValue;
        recalculateBar();
    }

    public float getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(float currentValue) {
        this.currentValue = currentValue;
        recalculateBar();
    }

    public float getCornerArc() {
        return cornerArc;
    }

    public void setCornerArc(float cornerArc) {
        this.cornerArc = cornerArc;
    }

    public Dimensions getCurrentBar() {
        return currentBar;
    }
}
