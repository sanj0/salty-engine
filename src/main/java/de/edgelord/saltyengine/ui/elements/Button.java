/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.cosmetic.ColorUtil;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

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
