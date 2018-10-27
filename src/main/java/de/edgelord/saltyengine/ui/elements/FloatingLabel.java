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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 * This implementation of {@link Label} allows pixel-precise positioning of a one-line text
 * because the size of it fits the text. Also, the placing is natural and not from
 * the baseLine of the text but from its upper left corner.
 */
public class FloatingLabel extends Label {

    private boolean centreOnXAxis = false;
    private boolean centreOnYAxis = false;

    public FloatingLabel(String text, Vector2f position) {
        super(text, position, 0, 0);
    }

    public FloatingLabel(String text, float x, float y) {
        this(text, new Vector2f(x, y));
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        prepareGraphics(saltyGraphics);

        recalculateSize(saltyGraphics.getFontMetrics());

        if (centreOnXAxis) {
            setX(Game.getHost().getHorizontalCentrePosition(getWidth()));
        }

        if (centreOnYAxis) {
            setY(Game.getHost().getVerticalCentrePosition(getHeight()));
        }

        if (!isSuppressClipping()) {
            saltyGraphics.setClip(getTransform());
        }

        saltyGraphics.drawText(getText(), getX(), getY() + saltyGraphics.getFontMetrics().getMaxAscent());

        if (!isSuppressClipping()) {
            saltyGraphics.resetClip();
        }
    }

    private void recalculateSize(FontMetrics metrics) {
        setWidth(metrics.stringWidth(getText()));
        setHeight(metrics.getMaxAscent() + metrics.getMaxDescent());
    }

    public void centreOnXAxis(boolean centre) {
        this.centreOnXAxis = centre;
    }

    public void centreOnYAxis(boolean centre) {
        this.centreOnYAxis = centre;
    }
}
