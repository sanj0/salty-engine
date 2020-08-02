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
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 * This implementation of {@link Label} allows pixel-precise positioning of a
 * one-line text because the size of it fits the text. Also, the placing is
 * natural and not from the baseLine of the text but from its upper left
 * corner.
 */
@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class FloatingLabel extends Label {

    public FloatingLabel(final String text, final Vector2f position) {
        super(text, position, 0, 0);
    }

    public FloatingLabel(final String text, final float x, final float y) {
        this(text, new Vector2f(x, y));
    }

    @Override
    public void drawForeground(final SaltyGraphics saltyGraphics) {

        recalculateSize(saltyGraphics.getFontMetrics());
        saltyGraphics.drawText(getText(), getX(), getY(), SaltyGraphics.TextAnchor.TOP_LEFT_CORNER);
    }

    @Override
    public void drawBackground(final SaltyGraphics saltyGraphics) {

    }

    public void recalculateSize(final FontMetrics metrics) {
        setWidth(metrics.stringWidth(getText()));
        setHeight((float) metrics.getMaxAscent() + metrics.getMaxDescent());
    }
}
