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

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.transform.Dimensions;

/**
 * This class represents the dimensions of the {@link Display}.
 * {@link #originalResolution} is the original resolution of the game.
 * {@link #scale} is the current scale of the {@link Display}.
 * {@link #currentDimensions} is the current dimensions of the {@link Display}, calculated using the two factors above.
 *
 */
public class DisplayRatio {

    private float scale = 1f;
    private Dimensions originalResolution;
    private Dimensions currentDimensions;

    public DisplayRatio(Dimensions originalResolution) {
        this.originalResolution = originalResolution;
        this.currentDimensions = new Dimensions(originalResolution.getWidth(), originalResolution.getHeight());
    }

    public void setWidth(float width) {
        this.scale = width / this.originalResolution.getWidth();
        updateCurrentDimensions();
    }

    public void setHeight(float height) {
        this.scale = height / this.originalResolution.getHeight();
        updateCurrentDimensions();
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public float getScale() {
        return scale;
    }

    public Dimensions getCurrentDimensions() {
        return currentDimensions;
    }

    private void updateCurrentDimensions() {
        currentDimensions.setWidth(scale * originalResolution.getWidth());
        currentDimensions.setHeight(scale * originalResolution.getHeight());
    }
}
