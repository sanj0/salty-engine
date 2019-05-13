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

package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;

public interface CentrePositionProvider {

    /**
     * This method returns the centre position for the given width on the x axis of the coordinates system.
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
     * This method returns the centre position for the given height on the y axis of the coordinates system.
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
