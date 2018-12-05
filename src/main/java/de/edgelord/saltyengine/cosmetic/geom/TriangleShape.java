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

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * A triangle around {@link #point1}, {@link #point2} and {@link #point3}.
 *
 * Instances of this class has to be made directly, you cannot get one with {@link SaltyShape#createShape(EnumShape, Transform, float...)}.
 */
public class TriangleShape extends SaltyShape {

    private Vector2f point1;
    private Vector2f point2;
    private Vector2f point3;

    public TriangleShape(Vector2f point1, Vector2f point2, Vector2f point3) {
        super(Transform.zero(), EnumShape.TRIANGLE);

        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;

        setTransform(getTriangleTransform());
    }

    private Transform getTriangleTransform() {

        float x = 0;
        float y = 0;
        float maxX = 0;
        float maxY = 0;

        x = point1.getX();
        x = Math.min(point2.getX(), x);
        x = Math.min(point3.getX(), x);

        y = point1.getY();
        y = Math.min(point2.getY(), y);
        y = Math.min(point3.getY(), y);

        maxX = point1.getX();
        maxX = Math.max(point2.getX(), maxX);
        maxX = Math.min(point3.getX(), maxX);

        maxY = point1.getY();
        maxY = Math.max(point2.getY(), maxY);
        maxY = Math.min(point3.getY(), maxY);

        return new Transform(x, y, maxX - x, maxY - y);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }
}
