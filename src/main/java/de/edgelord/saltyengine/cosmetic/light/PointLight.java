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

package de.edgelord.saltyengine.cosmetic.light;

import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;

public class PointLight extends Light {

    private Color lightColor = Color.white;
    private Paint paint;
    private int targetAlpha = 0;

    public PointLight(Transform transform) {
        super(transform);

        updatePaint();
    }

    public PointLight(float x, float y, float radius) {
        this(new Transform(x, y, radius, radius));
    }

    public PointLight(Vector2f position, float radius) {
        this(new Transform(position, new Dimensions(radius, radius)));

        updatePaint();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setColor(lightColor);
        graphics2D.setPaint(paint);
        graphics2D.fillOval(Math.round(getX()), Math.round(getY()), Math.round(getWidth()), Math.round(getHeight()));
    }

    public int getTargetAlpha() {
        return targetAlpha;
    }

    public void setTargetAlpha(int targetAlpha) {
        this.targetAlpha = targetAlpha;
    }

    public void updatePaint() {
        this.paint = ColorUtil.createRadialGradientPaint(getTransform(), lightColor, targetAlpha);
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        super.setDimensions(dimensions);
        updatePaint();
    }

    @Override
    public void setPosition(Vector2f position) {
        super.setPosition(position);
        updatePaint();
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        updatePaint();
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        updatePaint();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        updatePaint();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        updatePaint();
    }

    @Override
    public void positionByCentre(Vector2f centre) {
        super.positionByCentre(centre);
        updatePaint();
    }
}
