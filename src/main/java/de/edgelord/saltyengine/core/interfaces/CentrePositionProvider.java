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

package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;

public interface CentrePositionProvider {

    /**
     * This method should return the centre position for the given width on the x axis of the coordinates system.
     * A simple implementation would e.g. be:
     *
     * <code>return (float) (this.width / 2) - (width / 2)</code>
     * assuming <code>this.width</code> is the width of the coordinates system.
     *
     * @param width the width for which to return the centre position on the x axis
     * @return the centre position on the x axis for the given width
     */
    float getHorizontalCentrePosition(float width);

    /**
     * This method should return the centre position for the given height on the y axis of the coordinates system.
     * A simple implementation would e.g. be:
     *
     * <code>return (float) (this.height / 2) - (height / 2)</code>
     * assuming <code>this.height</code> is the height of the coordinates system.
     *
     * @param height the width for which to return the centre position on the y axis
     * @return the centre position on the x axis for the given height
     */
    float getVerticalCentrePosition(float height);


    default Vector2f getCentrePosition(float width, float height) {
        return new Vector2f(getHorizontalCentrePosition(width), getVerticalCentrePosition(height));
    }

    default float getHorizontalCentrePosition(GameObject gameObject) {
        return getHorizontalCentrePosition(gameObject.getWidth());
    }

    default float getHorizontalCentrePosition(Dimensions dimensions) {
        return getHorizontalCentrePosition(dimensions.getWidth());
    }

    default float getVerticalCentrePosition(GameObject gameObject) {
        return getVerticalCentrePosition(gameObject.getHeight());
    }

    default float getVerticalCentrePosition(Dimensions dimensions) {
        return getVerticalCentrePosition(dimensions.getHeight());
    }

    default Vector2f getCentrePosition(GameObject gameObject) {
        return getCentrePosition(gameObject.getWidth(), gameObject.getHeight());
    }

    default Vector2f getCentrePosition(Dimensions dimensions) {
        return getCentrePosition(dimensions.getWidth(), dimensions.getHeight());
    }
}
