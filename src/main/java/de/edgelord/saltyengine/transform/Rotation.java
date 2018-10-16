/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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

package de.edgelord.saltyengine.transform;

/**
 * This class describes the rotation of an Object around the position {@link #centre} by {@link #rotationDegrees} degrees.
 */
public class Rotation {
    private Vector2f centre;
    private float rotationDegrees;

    public Rotation(Vector2f centre, float rotationDegrees) {
        this.centre = centre;
        this.rotationDegrees = rotationDegrees;
    }

    public Rotation(Vector2f centre) {
        this(centre, 0f);
    }

    public Rotation(float centreX, float centreY, float rotationDegrees) {
        this(new Vector2f(centreX, centreY), rotationDegrees);
    }

    public Rotation(float centreX, float centreY) {
        this(centreX, centreY, 0f);
    }

    public Rotation() {
        this(0, 0);
    }

    public void rotateToPoint(Vector2f point) {
        throw new UnsupportedOperationException("rotating an entity to face a point");
        /*
        float arc = (float) Math.atan2(point.getY() / centre.getY(), point.getX() / centre.getX());
        rotationDegrees = (float) toDegrees(arc);
        */
    }

    public void rotateToPoint(float x, float y) {
        rotateToPoint(new Vector2f(x, y));
    }

    public Vector2f getCentre() {
        return centre;
    }

    public void setCentre(Vector2f centre) {
        this.centre = centre;
    }

    public float getRotationDegrees() {

        while (rotationDegrees > 360) {
            rotationDegrees -= 360;
        }

        return rotationDegrees;
    }

    public void setRotationDegrees(float rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }
}