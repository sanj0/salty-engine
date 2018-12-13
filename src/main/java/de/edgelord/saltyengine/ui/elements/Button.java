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

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.event.MouseEvent;

public abstract class Button extends UIElement {

    private String text;
    private int textWidth, textHeight;
    private Color hoverColor = ColorUtil.changeBrightness(getBackgroundColor(), -0.15f);
    private Color clickColor = ColorUtil.changeBrightness(getBackgroundColor(), 0.15f);
    private Color currentBackgroundColor = getBackgroundColor();
    private int arc = 15;

    public Button(String text, Vector2f position, int width, int height) {
        super(position, width, height, BUTTON);

        this.text = text;
    }

    public Button(String text, Transform transform, String tag) {
        this(text, transform.getPosition(), transform.getWidthAsInt(), transform.getHeightAsInt());
    }

    public Button(String text, float x, float y, int width, int height) {
        this(text, new Vector2f(x, y), width, height);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (!isSuppressClipping()) {
            saltyGraphics.setClip(getTransform());
        }
        drawButton(saltyGraphics);
        drawText(saltyGraphics);
        if (!isSuppressClipping()) {
            saltyGraphics.resetClip();
        }
    }

    public void drawText(SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(getFont());

        textWidth = saltyGraphics.getFontMetrics().stringWidth(text);
        textHeight = saltyGraphics.getFontMetrics().getAscent();

        saltyGraphics.setColor(getForegroundColor());

        saltyGraphics.drawText(text, getX() + ((getWidth() - textWidth) / 2), getY() + ((getHeight() + textHeight) / 2));
    }

    public void drawButton(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(currentBackgroundColor);

        saltyGraphics.drawRoundRect(getX(), getY(), getWidth(), getHeight(), arc);
    }

    public abstract void onClick(MouseEvent e);

    @Override
    public void mouseMoved(MouseEvent e) {

        if (mouseHoversOver()) {

            currentBackgroundColor = hoverColor;
        } else {
            currentBackgroundColor = getBackgroundColor();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (mouseHoversOver()) {
            currentBackgroundColor = clickColor;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        currentBackgroundColor = getBackgroundColor();

        if (mouseHoversOver()) {
            onClick(e);
        }
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        super.setBackgroundColor(backgroundColor);
        this.currentBackgroundColor = backgroundColor;
        this.hoverColor = ColorUtil.changeBrightness(backgroundColor, -0.15f);
        this.clickColor = ColorUtil.changeBrightness(backgroundColor, 0.15f);
    }

    public int getArc() {
        return arc;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }
}
