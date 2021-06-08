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

import de.edgelord.saltyengine.utils.GeneralUtil;

import java.util.Objects;

public class Dimensions {

    private float width, height;

    public Dimensions(final float width, final float height) {
        this.width = width;
        this.height = height;
    }

    /**
     * Constructs a new objects with the {@link #width} and {@link #height} of
     * the given object.
     *
     * @param dimensions the dimensions for this new object
     */
    public Dimensions(final Dimensions dimensions) {
        this(dimensions.width, dimensions.height);
    }

    public static Dimensions parseDimensions(final String s) {
        final String[] components = s.split(",");
        return new Dimensions(Float.parseFloat(components[0].trim()), Float.parseFloat(components[1].trim()));
    }

    public Vector2f toVector2f() {
        return new Vector2f(width, height);
    }

    public Dimensions abs() {
        width = Math.abs(width);
        height = Math.abs(height);
        return this;
    }

    public Dimensions absed() {
        final Dimensions absed = new Dimensions(this);
        return absed.abs();
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

    public static Dimensions random(final int min, final int max) {
        return new Dimensions(GeneralUtil.randomInt(min, max), GeneralUtil.randomInt(min, max));
    }

    public static Dimensions one() {
        return new Dimensions(1, 1);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(final float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(final float height) {
        this.height = height;
    }

    public Dimensions add(final float width1, final float height1) {
        width += width1;
        height += height1;

        return this;
    }

    public Dimensions add(final Dimensions dimensions1) {
        return add(dimensions1.getWidth(), dimensions1.getHeight());
    }

    public Dimensions multiply(final float width1, final float height1) {
        width *= width1;
        height *= height1;

        return this;
    }

    public Dimensions multiply(final Dimensions dimensions1) {
        return multiply(dimensions1.getWidth(), dimensions1.getHeight());
    }

    public Dimensions divide(final float width1, final float height1) {
        width /= width1;
        height /= height1;

        return this;
    }

    public Dimensions divide(final Dimensions dimensions1) {
        return divide(dimensions1.getWidth(), dimensions1.getHeight());
    }

    public Dimensions subtract(final float width1, final float height1) {
        width -= width1;
        height -= height1;

        return this;
    }

    public Dimensions subtract(final Dimensions dimensions1) {
        return subtract(dimensions1.getWidth(), dimensions1.getHeight());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Dimensions) {
            final Dimensions other = (Dimensions) obj;
            return other.getWidth() == getWidth() && other.getHeight() == getHeight();
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }

    @Override
    public String toString() {
        return "Dimensions{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    /**
     * Returns a new <code>Dimensions</code> with the same {@link #width} and
     * {@link #height} as this one.
     *
     * @return a "copy" of this <code>Dimensions</code>
     */
    @Override
    protected Object clone() {
        return new Dimensions(width, height);
    }
}
