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

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;

import java.awt.*;

/**
 * A <code>Drawable</code> is an object than can be drawn
 * using a {@link SaltyGraphics SaltyGraphics object}.
 */
public interface Drawable {

    static Drawable colorPrepare(Color color) {
        return saltyGraphics -> saltyGraphics.setColor(color);
    }

    static Drawable paintPrepare(Paint paint) {
        return saltyGraphics -> saltyGraphics.setPaint(paint);
    }

    static Drawable strokePrepare(Stroke stroke) {
        return saltyGraphics -> saltyGraphics.setStroke(stroke);
    }

    static Drawable colorAndPaintPrepare(Color color, Paint paint) {
        return saltyGraphics -> {
            saltyGraphics.setColor(color);
            saltyGraphics.setPaint(paint);
        };
    }

    static Drawable colorAndStrokePrepare(Color color, Stroke stroke) {
        return saltyGraphics -> {
            saltyGraphics.setColor(color);
            saltyGraphics.setStroke(stroke);
        };
    }

    /**
     * Draws this object using the given {@link SaltyGraphics}.
     *
     * @param saltyGraphics the graphics to draw to
     */
    void draw(SaltyGraphics saltyGraphics);
}
