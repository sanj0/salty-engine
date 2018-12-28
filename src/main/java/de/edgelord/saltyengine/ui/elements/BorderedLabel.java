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

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 * This is the standard implementation of a {@link Label}, with the given text only
 * be drawn within the bounds and no line breaks, meaning this label is one-line.
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
