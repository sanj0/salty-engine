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

public class Coordinates2f {

    private float x;
    private float y;

    public Coordinates2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates2f(Coordinates2f position) {
        this(position.getX(), position.getY());
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

    public Coordinates2f add(float x1, float y1) {
        x += x1;
        y += y1;

        return this;
    }

    public Coordinates2f add(Coordinates2f pos1) {
        return add(pos1.getX(), pos1.getY());
    }

    public Coordinates2f multiply(float x1, float y1) {
        x *= x1;
        y *= y1;

        return this;
    }

    public Coordinates2f multiply(Coordinates2f pos1) {
        return multiply(pos1.getX(), pos1.getY());
    }

    public Coordinates2f divide(float x1, float y1) {
        x /= x1;
        y /= y1;

        return this;
    }

    public Coordinates2f divide(Coordinates2f pos1) {
        return divide(pos1.getX(), pos1.getY());
    }

    public Coordinates2f subtract(float x1, float y1) {
        x -= x1;
        y -= y1;

        return this;
    }

    public Coordinates2f subtract(Coordinates2f pos1) {
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
        if (obj instanceof Coordinates2f) {
            Coordinates2f other = (Coordinates2f) obj;
            return other.getX() == getX() && other.getY() == getY();
        } else {
            return false;
        }
    }

    public static Coordinates2f zero() {
        return new Coordinates2f(0, 0);
    }

    public static Coordinates2f max() {
        return new Coordinates2f(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public static Coordinates2f min() {
        return new Coordinates2f(Float.MIN_VALUE, Float.MIN_VALUE);
    }

    public static Coordinates2f random(int min, int max) {
        Random random = new Random();
        return new Coordinates2f(random.nextInt(max - min) + min, random.nextInt(max - min) + min);
    }

    public static Coordinates2f one() {
        return new Coordinates2f(1, 1);
    }

    @Override
    public String toString() {
        return "Coordinates2f{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    /**
     * Returns a new <code>Coordinates2f</code> with the same {@link #x} and {@link #y} as this one.
     *
     * @return a "copy" of this <code>Coordinates2f</code>
     */
    @Override
    protected Object clone() {
        return new Coordinates2f(x, y);
    }
}
