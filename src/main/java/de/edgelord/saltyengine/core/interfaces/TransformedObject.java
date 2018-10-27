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

package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

public interface TransformedObject {

    Transform getTransform();

    void setTransform(Transform transform);

    default Dimensions getDimensions() {
        return getTransform().getDimensions();
    }

    default Vector2f getPosition() {
        return getTransform().getPosition();
    }

    default float getWidth() {
        return getDimensions().getWidth();
    }

    default float getHeight() {
        return getDimensions().getHeight();
    }

    default float getX() {
        return getPosition().getX();
    }

    default float getY() {
        return getPosition().getY();
    }

    default void setDimensions(Dimensions dimensions) {
        getTransform().setDimensions(dimensions);
    }

    default void setPosition(Vector2f position) {
        getTransform().setPosition(position);
    }

    default void setWidth(float width) {
        getTransform().setWidth(width);
    }

    default void setHeight(float height) {
        getTransform().setHeight(height);
    }

    default void setX(float x) {
        getTransform().setX(x);
    }

    default void setY(float y) {
        getTransform().setY(y);
    }

    default void basicMove(final float delta, final Directions.BasicDirection direction) {

        if (direction == Directions.BasicDirection.x) {
            setX(getX() + delta);
        } else {
            setY(getY() + delta);
        }
    }

    default void move(float delta, final Directions.Direction direction) {

        // Check if delta is negative and if so, mirror its value
        if (delta < 0f) {
            delta = delta * (-1);
        }

        switch (direction) {

            case RIGHT:
                basicMove(delta, Directions.BasicDirection.x);
                break;
            case LEFT:
                basicMove(-delta, Directions.BasicDirection.x);
                break;
            case UP:
                basicMove(-delta, Directions.BasicDirection.y);
                break;
            case DOWN:
                basicMove(delta, Directions.BasicDirection.y);
                break;
        }
    }

    default void moveY(final float delta) {
        getTransform().setY(getY() + delta);
    }

    default void moveX(final float delta) {
        getTransform().setX(getX() + delta);
    }
}
