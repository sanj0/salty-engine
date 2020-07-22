/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;

/**
 * A <code>Component</code> that
 * visualizes the bounds and position
 * of a <code>GameObject</code>.
 */
public class DebugVisualiseTransform extends Component<ComponentContainer> {

    /**
     * The color used for the box
     * around the bounds and the
     * text displaying the position.
     */
    public static Color color = ColorUtil.RED;

    /**
     * The font used for displaying
     * the position of the GameObject
     */
    public static Font font = SaltySystem.defaultFont.deriveFont(15f);

    /**
     * Signals if there should be a
     * box drawn below the text to make it
     * more readable.
     */
    public static boolean drawBox = true;

    /**
     * The color of the bow drawn
     * below the text if {@link #drawBox}
     * is <code>true</code>.
     */
    public static Color boxColor = ColorUtil.WHITE;

    /**
     * The constructor.
     *
     * @param parent the component's parent
     * @param name   the component's id-name
     */
    public DebugVisualiseTransform(final ComponentContainer parent, final String name) {
        super(parent, name, Components.CORE_COMPONENT);
    }

    /**
     * Draws the debug info as follows:
     * <p>
     * if {@link #drawBox} is <code>true</code>, draws
     * a box with the origin at the top right corner
     * of this component's parent, using the color
     * specified as {@link #boxColor}.
     * <br>
     * Outlines the {@link de.edgelord.saltyengine.transform.Transform}
     * of this component's parent using the color specified
     * as {@link #color} and a {@link BasicStroke} with a width
     * of <code>3</code>
     * <br>
     * Draws two lines of text on top of the box as such:
     * <pre>
     * x: 3,14
     * y: 150
     * </pre>, using the font and color specified as {@link #font}
     * and {@link #color}, respectively, and the method
     * {@link SaltyGraphics#drawMultilineText(Object, float, float)}.
     *
     * @param saltyGraphics the graphcis to render to
     */
    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.setFont(font);

        final String xString = "x: " + getParent().getX();
        final String yString = "y: " + getParent().getY();
        final String positionText = xString + "\n" + yString;

        final String longerNumber = xString.length() > yString.length() ? xString : yString;
        if (drawBox) {
            saltyGraphics.setColor(boxColor);
            saltyGraphics.drawRect(getParent().getTransform().getMaxX() + 5, getParent().getY() + 7,
                    saltyGraphics.getFontMetrics().stringWidth(longerNumber), saltyGraphics.getFontMetrics().getHeight() * 2f);
        }
        saltyGraphics.setColor(color);
        saltyGraphics.setStroke(new BasicStroke(3));
        saltyGraphics.outlineRect(getParent().getTransform());
        saltyGraphics.drawMultilineText(positionText, getParent().getTransform().getMaxX() + 5, getParent().getY());
    }

    @Override
    public void onFixedTick() {
        // empty because not needed
    }

    @Override
    public void onCollision(final CollisionEvent e) {
        // empty because not needed
    }

    /**
     * Gets {@link #color}.
     *
     * @return the value of {@link #color}
     */
    public static Color getColor() {
        return color;
    }

    /**
     * Sets {@link #color}.
     *
     * @param color the new value of {@link #color}
     */
    public static void setColor(final Color color) {
        DebugVisualiseTransform.color = color;
    }

    /**
     * Gets {@link #font}.
     *
     * @return the value of {@link #font}
     */
    public static Font getFont() {
        return font;
    }

    /**
     * Sets {@link #font}.
     *
     * @param font the new value of {@link #font}
     */
    public static void setFont(final Font font) {
        DebugVisualiseTransform.font = font;
    }

    /**
     * Gets {@link #drawBox}.
     *
     * @return the value of {@link #drawBox}
     */
    public static boolean isDrawBox() {
        return drawBox;
    }

    /**
     * Sets {@link #drawBox}.
     *
     * @param drawBox the new value of {@link #drawBox}
     */
    public static void setDrawBox(final boolean drawBox) {
        DebugVisualiseTransform.drawBox = drawBox;
    }

    /**
     * Gets {@link #boxColor}.
     *
     * @return the value of {@link #boxColor}
     */
    public static Color getBoxColor() {
        return boxColor;
    }

    /**
     * Sets {@link #boxColor}.
     *
     * @param boxColor the new value of {@link #boxColor}
     */
    public static void setBoxColor(final Color boxColor) {
        DebugVisualiseTransform.boxColor = boxColor;
    }
}
