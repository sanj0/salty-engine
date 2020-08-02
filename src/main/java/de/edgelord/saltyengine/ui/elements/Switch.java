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
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * A simple <code>UIElement</code> that works like a switch. <br> It has - like
 * a switch - two states, held by {@link #active}. <br> When this
 * <code>UIElement</code> is clicked on, its
 * states changes to the opposite of the state before. <br> The by default empty
 * method {@link #stateChanged()} is called every time the state changes. <br>
 * The {@link #getBackgroundColor() background color} of this element changes
 * according to its state to either {@link #activatedColor} or {@link
 * #deactivatedColor}.
 */
public class Switch extends UIElement {

    /**
     * The color for the background for when the switch is {@link #active}.
     */
    private Color activatedColor = ColorUtil.ACTIVE_GREEN;

    /**
     * The color for the background for when the switch is not {@link #active}.
     */
    private Color deactivatedColor = ColorUtil.FIREBRICK_RED;

    /**
     * A boolean that holds the current state of the switch.
     */
    private boolean active;

    /**
     * The current position of the indicator within the switch.
     */
    private Vector2f indicatorPosition;

    /**
     * The constructor.
     *
     * @param position   the position of the switch within the {@link
     *                   de.edgelord.saltyengine.scene.Scene}
     * @param sizeFactor the factor of the size. The width is {@code sizeFactor
     *                   * 25f}, the height {@code sizeFactor * 10f}
     * @param active     the initial state of this switch
     */
    public Switch(final Vector2f position, final float sizeFactor, final boolean active) {
        super(position, sizeFactor * 25f, sizeFactor * 10f, UIElement.SETTINGS_ELEMENT);

        this.active = active;

        updateState();
    }

    /**
     * Draws a rounded rectangle with the current background color.
     *
     * @param saltyGraphics the graphics to draw to
     */
    @Override
    public void drawBackground(final SaltyGraphics saltyGraphics) {

        saltyGraphics.drawRoundRect(this, getHeight());
    }

    /**
     * Draws the indicator (a circle) at {@link #indicatorPosition}.
     *
     * @param saltyGraphics the graphics to draw to
     */
    @Override
    public void drawForeground(final SaltyGraphics saltyGraphics) {

        saltyGraphics.drawOval(indicatorPosition, new Dimensions(getHeight(), getHeight()));
    }

    /**
     * Checks if the cursor touches this
     * <code>UIElement</code> and if it does,
     * toggles {@link #active} and calls both {@link #updateState()} and {@link
     * #stateChanged()}.
     *
     * @param e the event
     */
    @Override
    public void mouseClicked(final MouseEvent e) {

        if (mouseHoversOver()) {
            active = !active;
            updateState();
            stateChanged();
        }
    }

    /**
     * A by default empty method that is called whenever the {@link #active
     * state} of this switch changes.
     */
    public void stateChanged() {
    }

    /**
     * Gets {@link #activatedColor}.
     *
     * @return the value of {@link #activatedColor}
     */
    public Color getActivatedColor() {
        return activatedColor;
    }

    /**
     * Sets {@link #activatedColor}.
     *
     * @param activatedColor the new value of {@link #activatedColor}
     */
    public void setActivatedColor(final Color activatedColor) {
        this.activatedColor = activatedColor;
    }

    /**
     * Gets {@link #deactivatedColor}.
     *
     * @return the value of {@link #deactivatedColor}
     */
    public Color getDeactivatedColor() {
        return deactivatedColor;
    }

    /**
     * Sets {@link #deactivatedColor}.
     *
     * @param deactivatedColor the new value of {@link #deactivatedColor}
     */
    public void setDeactivatedColor(final Color deactivatedColor) {
        this.deactivatedColor = deactivatedColor;
    }

    /**
     * Gets {@link #active}.
     *
     * @return the value of {@link #active}
     */
    public boolean isActive() {
        return active;
    }

    /**
     * Sets {@link #active}.
     *
     * @param active the new value of {@link #active}
     */
    public void setActive(final boolean active) {
        this.active = active;
        updateState();
        stateChanged();
    }

    /**
     * Updates the background color and {@link #indicatorPosition} according to
     * {@link #active}.
     */
    private void updateState() {
        if (active) {
            setBackgroundColor(activatedColor);
            indicatorPosition = new Vector2f(getX() + (getWidth() - getHeight()), getY());
        } else {
            setBackgroundColor(deactivatedColor);
            indicatorPosition = new Vector2f(getX(), getY());
        }
    }
}
