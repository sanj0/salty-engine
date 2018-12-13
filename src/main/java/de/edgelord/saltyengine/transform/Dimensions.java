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

public class Dimensions {

    private float width, height;

    public Dimensions(float width, float height) {
        this.width = width;
        this.height = height;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Dimensions) {
            Dimensions other = (Dimensions) obj;
            return other.getWidth() == getWidth() && other.getHeight() == getHeight();
        } else {
            return false;
        }
    }

    public static Dimensions zero() {
        return new Dimensions(0, 0);
    }

    public static Dimensions max() {
        return new Dimensions(Float.MAX_VALUE, Float.MAX_VALUE);
    }

    public static Dimensions min() {
        return new Dimensions(Float.MIN_VALUE, Float.MIN_VALUE);
    }

    public static Dimensions random(int min, int max) {
        Random random = new Random();
        return new Dimensions(random.nextInt(max + min) - min, random.nextInt(max + min) - min);
    }

    public static Dimensions one() {
        return new Dimensions(1, 1);
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    /**
     * Returns a new <code>Dimensions</code> with the same {@link #width} and {@link #height} as this one.
     *
     * @return a "copy" of this <code>Dimensions</code>
     */
    @Override
    protected Object clone() {
        return new Dimensions(width, height);
    }
}
