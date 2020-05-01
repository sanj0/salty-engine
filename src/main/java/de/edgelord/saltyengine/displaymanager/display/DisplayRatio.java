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

package de.edgelord.saltyengine.displaymanager.display;

import de.edgelord.saltyengine.transform.Dimensions;

/**
 * This class represents the dimensions of the {@link Display}.
 * {@link #originalResolution} is the original resolution of the game.
 * {@link #scale} is the current scale of the {@link Display}.
 * {@link #currentDimensions} is the current dimensions of the {@link Display}, calculated using the two factors above.
 */
public class DisplayRatio {

    private final Dimensions originalResolution;
    private final Dimensions currentDimensions;
    private float scale = 1f;

    public DisplayRatio(final Dimensions originalResolution) {
        this.originalResolution = originalResolution;
        this.currentDimensions = new Dimensions(originalResolution.getWidth(), originalResolution.getHeight());
    }

    public void setWidth(final float width) {
        this.scale = width / this.originalResolution.getWidth();
    }

    public void setHeight(final float height) {
        this.scale = height / this.originalResolution.getHeight();
    }

    public float getScale() {
        return scale;
    }

    public void setScale(final float scale) {
        this.scale = scale;
    }

    public Dimensions getCurrentDimensions() {
        currentDimensions.setWidth(scale * originalResolution.getWidth());
        currentDimensions.setHeight(scale * originalResolution.getHeight());
        return currentDimensions;
    }
}
