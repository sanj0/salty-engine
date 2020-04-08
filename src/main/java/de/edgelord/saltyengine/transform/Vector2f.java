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

package de.edgelord.saltyengine.transform;

import java.util.Random;

public class Vector2f {

    private float x;
    private float y;

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Vector2f(Vector2f position) {
        this(position.getX(), position.getY());
    }

    public Vector2f withMagnitude(float newMagnitude) {
        double magnitude = magnitude();
        double ratio = newMagnitude / magnitude;
        float x = (float) (this.x * ratio);
        float y = (float) (this.y * ratio);

        return new Vector2f(x, y);
    }

    /**
     * Sets a new magnitude to this <code>vector</code>
     * by changing its <code>x</code> and <code>y</code>
     * coordinates.
     *
     * @param newMagnitude the new magnitude for this <code>vector</code>
     * @return <code>this</code> vector
     */
    public Vector2f setMagnitude(float newMagnitude) {
        Vector2f newVector = withMagnitude(newMagnitude);

        setX(newVector.x);
        setY(newVector.y);

        return this;
    }

    public Vector2f normalized() {
        return normalize(magnitude());
    }

    public Vector2f normalize() {
        Vector2f newVector = normalize(magnitude());

        setX(newVector.x);
        setY(newVector.y);

        return this;
    }

    private Vector2f normalize(double magnitude) {
        float x = (float) (this.x / magnitude);
        float y = (float) (this.y / magnitude);

        return new Vector2f(x, y);
    }

    public double magnitude() {
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Returns the distance between this and the given point.
     *
     * @param point a point
     * @return the distance between this and the given point
     */
    public float distance(Vector2f point) {
        double ac = Math.abs(point.y - y);
        double cb = Math.abs(point.x - x);

        return (float) Math.hypot(ac, cb);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public Vector2f added(Vector2f vec1) {
        Vector2f vec = new Vector2f(this);

        return vec.add(vec1);
    }

    public Vector2f add(float x1, float y1) {
        x += x1;
        y += y1;

        return this;
    }

    public Vector2f add(Vector2f pos1) {
        return add(pos1.getX(), pos1.getY());
    }

    public Vector2f multiplied(Vector2f vec1) {
        Vector2f vec = new Vector2f(this);

        return vec.multiply(vec1);
    }

    public Vector2f multiply(float x1, float y1) {
        x *= x1;
        y *= y1;

        return this;
    }

    public Vector2f multiply(Vector2f pos1) {
        return multiply(pos1.getX(), pos1.getY());
    }

    public Vector2f divided(Vector2f vec1) {
        Vector2f vec = new Vector2f(this);

        return vec.divide(vec1);
    }

    public Vector2f divide(float x1, float y1) {
        x /= x1;
        y /= y1;

        return this;
    }

    public Vector2f divide(Vector2f pos1) {
        return divide(pos1.getX(), pos1.getY());
    }

    public Vector2f subtracted(Vector2f vec1) {
        Vector2f vec = new Vector2f(this);

        return vec.subtract(vec1);
    }

    public Vector2f subtract(float x1, float y1) {
        x -= x1;
        y -= y1;

        return this;
    }

    public Vector2f subtract(Vector2f pos1) {
        return subtract(pos1.getX(), pos1.getY());
    }

    public Coordinates convertToCoordinates() {

        return new Coordinates((int) getX(), (int) getY());
    }

    public void parseCoordinates2f(Coordinates coordinates) {

        setX((float) coordinates.getX());
        setY((float) coordinates.getY());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Vector2f) {
            Vector2f other = (Vector2f) obj;
            return other.getX() == getX() && other.getY() == getY();
        } else {
            return false;
        }
    }

    public static Vector2f zero() {
        return new Vector2f(0, 0);
    }

    public static Vector2f max() {
        return new Vector2f(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public static Vector2f min() {
        return new Vector2f(Float.MIN_VALUE, Float.MIN_VALUE);
    }

    public static Vector2f random(int min, int max) {
        Random random = new Random();
        return new Vector2f(random.nextInt(max - min) + min, random.nextInt(max - min) + min);
    }

    public static Vector2f one() {
        return new Vector2f(1, 1);
    }

    @Override
    public String toString() {
        return "Vector2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Returns a new <code>Vector2f</code> with the same {@link #x} and {@link #y} as this one.
     *
     * @return a "copy" of this <code>Vector2f</code>
     */
    @Override
    protected Object clone() {
        return new Vector2f(x, y);
    }
}
