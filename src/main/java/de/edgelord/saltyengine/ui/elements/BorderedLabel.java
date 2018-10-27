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

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 * This is the standard implementation of a {@link Label}, with the given text only
 * be drawn within the bounds and no line breaks, meaning this LABEL is one-line.
 */
public class BorderedLabel extends Label {

    public BorderedLabel(String text, Vector2f position, float width, float height) {
        super(text, position, width, height);
    }

    public BorderedLabel(String text, float x, float y, float width, float height) {
        this(text, new Vector2f(x, y), width, height);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        prepareGraphics(saltyGraphics);

        float textXPos = 0f;
        float textYPos = 0f;
        FontMetrics fontMetrics = saltyGraphics.getFontMetrics();

        if (!isSuppressClipping()) {
            saltyGraphics.setClip(getTransform());
        }

        switch (getHorizontalAlignment()) {

            case right:
                textXPos = getHorizontalRightAlignmentPosition(fontMetrics);
                break;
            case left:
                textXPos = getHorizontalLeftAlignmentPosition();
                break;
            case centered:
                textXPos = getHorizontalCenteredAlignmentPosition(fontMetrics);
                break;
        }

        switch (getVerticalAlignment()) {

            case top:
                textYPos = getVerticalTopAlignmentPosition(fontMetrics);
                break;
            case bottom:
                textYPos = getVerticalBottomAlignmentPosition(fontMetrics);
                break;
            case centered:
                textYPos = getVerticalCenteredAlignmentPosition(fontMetrics);
                break;
        }

        saltyGraphics.drawText(getText(), getX() + textXPos, getY() + textYPos);

        if (!isSuppressClipping()) {
            saltyGraphics.resetClip();
        }
    }

    private float getVerticalTopAlignmentPosition(FontMetrics fontMetrics) {
        return fontMetrics.getMaxAscent();
    }

    private float getVerticalBottomAlignmentPosition(FontMetrics fontMetrics) {
        return getHeight() - fontMetrics.getHeight();
    }

    private float getVerticalCenteredAlignmentPosition(FontMetrics fontMetrics) {
        return (getHeight() + fontMetrics.getHeight()) / 2;
    }

    private float getHorizontalLeftAlignmentPosition() {
        return 0;
    }

    private float getHorizontalRightAlignmentPosition(FontMetrics fontMetrics) {
        return getWidth() - fontMetrics.stringWidth(getText());
    }

    private float getHorizontalCenteredAlignmentPosition(FontMetrics fontMetrics) {
        return (getWidth() - fontMetrics.stringWidth(getText())) / 2;
    }
}
