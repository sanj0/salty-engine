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

import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * Represents a line from the top left corner of the transform to its bottom right corner
 */
public class LineShape extends SaltyShape {
    public LineShape(TransformedObject parent) {
        super(parent, EnumShape.LINE);
    }

    public LineShape(Transform transform) {
        super(transform, EnumShape.LINE);
    }

    public LineShape(Vector2f position, Dimensions dimensions) {
        super(position, dimensions, EnumShape.LINE);
    }

    public LineShape(float x, float y, float width, float height) {
        super(x, y, width, height, EnumShape.LINE);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawLine(getX(), getY(), getTransform().getMaxX(), getTransform().getMaxY());
    }
}
