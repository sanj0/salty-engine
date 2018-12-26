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

package de.edgelord.saltyengine.effect.geom;

import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * Represents a line from the top left corner of the transform to its bottom right corner
 */
public class LineShape extends SaltyShape {
    public LineShape(TransformedObject parent) {
        super(parent, EnumShape.LINE);
    }

    public LineShape(Transform transform) {
        super(transform, EnumShape.LINE);
    }

    public LineShape(Vector2f position, Dimensions dimensions) {
        super(position, dimensions, EnumShape.LINE);
    }

    public LineShape(float x, float y, float width, float height) {
        super(x, y, width, height, EnumShape.LINE);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawLine(getX(), getY(), getTransform().getMaxX(), getTransform().getMaxY());
    }
}
