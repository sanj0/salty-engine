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

/**
 * This is the standard implementation of a {@link Label}, with the given text only
 * be drawn at the centre of the bounds and no line breaks, meaning this label is one-line.
 */
@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class BorderedLabel extends Label {

    public BorderedLabel(String text, Vector2f position, float width, float height) {
        super(text, position, width, height);
    }

    public BorderedLabel(String text, float x, float y, float width, float height) {
        this(text, new Vector2f(x, y), width, height);
    }

    @Override
    public void drawBackground(SaltyGraphics saltyGraphics) {
    }

    @Override
    public void drawForeground(SaltyGraphics saltyGraphics) {
        Vector2f centre = getTransform().getCentre();
        saltyGraphics.drawText(getText(), centre.getX(), centre.getY(), SaltyGraphics.TextAnchor.CENTRE);
    }
}
