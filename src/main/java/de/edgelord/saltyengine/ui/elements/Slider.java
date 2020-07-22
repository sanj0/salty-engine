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

import de.edgelord.saltyengine.core.GraphicsConfiguration;
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

    private final SaltyShape indicatorShape = Objects.requireNonNull(SaltyShape.createShape(EnumShape.RECTANGLE, new Transform(0, 0, 5, 25)));
    /**
     * The relative position of the indicator.
     */
    private float indicatorPositionX = 0f;
    private SaltyImage indicatorImage;

    public Slider(final Vector2f position, final float width, final float height) {
        super(position, width, height, null);

        createIndicator(getForegroundColor());
    }

    public Slider(final float x, final float y, final float width, final float height) {
        this(new Vector2f(x, y), width, height);
    }

    public Slider(final Vector2f position, final float width, final float height, final SaltyImage indicatorImage) {
        super(position, width, height, UIElement.SETTINGS_ELEMENT);

        this.indicatorImage = indicatorImage;
    }

    public void stateChanged() {
    }

    /**
     * Returns the value of this <code>Slider</code>
     * between 0f and 1f.
     *
     * @return the 0f-1f value of this <code>Slider</code>
     */
    public float getValue() {
        return indicatorPositionX / getWidth();
    }

    @Override
    public void drawBackground(final SaltyGraphics saltyGraphics) {
        saltyGraphics.outlineRect(this);
    }

    @Override
    public void drawForeground(final SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(indicatorImage, getX() + indicatorPositionX, (getY() + getHeight() / 2f) - indicatorImage.getHeight() / 2f);
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        processInput();
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        processInput();
    }

    private void processInput() {
        if (mouseHoversOver()) {
            final float cursorX = Input.getAbsoluteCursorPosition().getX();

            if (cursorX <= getTransform().getMaxX() && cursorX >= getX()) {
                indicatorPositionX = cursorX - getX();
                stateChanged();
            }
        }
    }

    public void setIndicatorColor(final Color color) {
        createIndicator(color);
    }

    private void createIndicator(final Color color) {
        indicatorImage = ImageUtils.createShapeImage(indicatorShape, color, GraphicsConfiguration.renderingHints);
    }
}
