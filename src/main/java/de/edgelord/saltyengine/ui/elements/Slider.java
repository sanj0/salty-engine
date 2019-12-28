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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.geom.EnumShape;
import de.edgelord.saltyengine.effect.geom.SaltyShape;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.Objects;

public class Slider extends UIElement {

    /**
     * The relative position of the indicator.
     */
    private float indicatorPositionX = 0f;

    private SaltyImage indicatorImage;

    private SaltyShape indicatorShape = Objects.requireNonNull(SaltyShape.createShape(EnumShape.RECTANGLE, new Transform(0, 0, 5, 25)));

    public Slider(Vector2f position, float width, float height) {
        super(position, width, height, null);

        createIndicator(getForegroundColor());
    }

    public Slider(float x, float y, float width, float height) {
        this(new Vector2f(x, y), width, height);
    }

    public Slider(Vector2f position, float width, float height, SaltyImage indicatorImage) {
        super(position, width, height, UIElement.SETTINGS_ELEMENT);

        this.indicatorImage = indicatorImage;
    }

    @Override
    public void drawBackground(SaltyGraphics saltyGraphics) {
        saltyGraphics.outlineRect(this);
    }

    @Override
    public void drawForeground(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(indicatorImage, getX() + indicatorPositionX, (getY() + getHeight() / 2f) - indicatorImage.getHeight() / 2f);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (isFocused()) {
            float xPos = Input.getAbsoluteCursorPosition().getX() - getX();

            if (xPos <= getTransform().getMaxX() && xPos >= getX()) {
                indicatorPositionX = xPos;
            }
        }
    }

    public void setIndicatorColor(Color color) {
        createIndicator(color);
    }

    private void createIndicator(Color color) {
        indicatorImage = ImageUtils.createShapeImage(indicatorShape, color, Game.getHost().getRenderHints());
    }
}
