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

package de.edgelord.saltyengine.cosmetic.geom;

import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

public abstract class SaltyShape implements Drawable, TransformedObject {

    private EnumShape shapeType;
    private Transform transform;
    private boolean filled = true;

    public SaltyShape(TransformedObject parent, EnumShape shapeType) {
        this(parent.getTransform(), shapeType);
    }

    public SaltyShape(Transform transform, EnumShape shapeType) {
        this.shapeType = shapeType;
        this.transform = transform;
    }

    public SaltyShape(Vector2f position, Dimensions dimensions, EnumShape shapeType) {
        this(new Transform(position, dimensions), shapeType);
    }

    public SaltyShape(float x, float y, float width, float height, EnumShape shapeType) {
        this(new Transform(x, y, width, height), shapeType);
    }

    public static SaltyShape createShape(EnumShape shapeType, Transform transform, float... arcIfRoundRect) {
        switch (shapeType) {

            case RECTANGLE:
                return new RectangleShape(transform);
            case OVAL:
                return new OvalShape(transform);
            case ROUND_RECTANGLE:
                if (arcIfRoundRect.length < 1) {
                    return new RoundRectShape(transform);
                } else {
                    return new RoundRectShape(transform, arcIfRoundRect[0]);
                }
            case LINE:
                return new LineShape(transform);
        }

        return null;
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public Transform getTransform() {
        return this.transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void drawAtZero(Graphics2D graphics) {
        Vector2f pos = getPosition();
        setPosition(Vector2f.zero());
        draw(new SaltyGraphics(graphics));
        setPosition(pos);
    }

    public EnumShape getShapeType() {
        return shapeType;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }
}
