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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class Button extends UIElement {

    private String text;
    private Color hoverColor = ColorUtil.changeBrightness(getBackgroundColor(), -0.15f);
    private Color clickColor = ColorUtil.changeBrightness(getBackgroundColor(), 0.15f);
    private Color disabledColor = ColorUtil.blend(ColorUtil.LIGHT_GRAY, getBackgroundColor(), 0.20f);
    private Color currentBackgroundColor = getBackgroundColor();
    private int arc = 15;

    private boolean enabled = true;

    public Button(final String text, final Vector2f position, final int width, final int height) {
        super(position, width, height, BUTTON);

        this.text = text;
    }

    public Button(final String text, final Transform transform, final String tag) {
        this(text, transform.getPosition(), transform.getWidthAsInt(), transform.getHeightAsInt());
    }

    public Button(final String text, final float x, final float y, final int width, final int height) {
        this(text, new Vector2f(x, y), width, height);
    }

    @Override
    public void drawForeground(final SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(getFont());
        saltyGraphics.setColor(getForegroundColor());
        saltyGraphics.drawText(text, getTransform().getCentre(), SaltyGraphics.TextAnchor.CENTRE);
    }

    @Override
    public void drawBackground(final SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(currentBackgroundColor);

        saltyGraphics.drawRoundRect(getX(), getY(), getWidth(), getHeight(), arc);
    }

    public abstract void onClick(MouseEvent e);

    @Override
    public void mouseMoved(final MouseEvent e) {

        if (enabled) {
            if (mouseHoversOver()) {
                currentBackgroundColor = hoverColor;
            } else {
                currentBackgroundColor = getBackgroundColor();
            }
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {

        if (mouseHoversOver() && enabled) {
            currentBackgroundColor = clickColor;
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {

        if (enabled) {
            currentBackgroundColor = getBackgroundColor();

            if (mouseHoversOver()) {
                onClick(e);
            }
        }
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }

    @Override
    public void setBackgroundColor(final Color backgroundColor) {
        super.setBackgroundColor(backgroundColor);
        this.currentBackgroundColor = backgroundColor;
        this.hoverColor = ColorUtil.changeBrightness(backgroundColor, -0.15f);
        this.clickColor = ColorUtil.changeBrightness(backgroundColor, 0.15f);
        disabledColor = ColorUtil.blend(ColorUtil.LIGHT_GRAY, getBackgroundColor(), 0.20f);
    }

    public int getArc() {
        return arc;
    }

    public void setArc(final int arc) {
        this.arc = arc;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(final boolean enabled) {
        if (enabled) {
            enable();
        } else {
            disable();
        }
    }

    public void disable() {
        currentBackgroundColor = disabledColor;
        enabled = false;
    }

    public void enable() {
        currentBackgroundColor = getBackgroundColor();
        enabled = true;
    }
}
