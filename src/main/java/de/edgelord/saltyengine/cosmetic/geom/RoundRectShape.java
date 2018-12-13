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

package de.edgelord.saltyengine.cosmetic.geom;

import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

public class RoundRectShape extends SaltyShape {

    private float arc = 15f;

    public RoundRectShape(TransformedObject parent) {
        super(parent, EnumShape.ROUND_RECTANGLE);
    }

    public RoundRectShape(Transform transform) {
        super(transform, EnumShape.ROUND_RECTANGLE);
    }

    public RoundRectShape(Vector2f position, Dimensions dimensions) {
        super(position, dimensions, EnumShape.ROUND_RECTANGLE);
    }

    public RoundRectShape(TransformedObject parent, float arc) {
        super(parent, EnumShape.ROUND_RECTANGLE);

        this.arc = arc;
    }

    public RoundRectShape(Transform transform, float arc) {
        super(transform, EnumShape.ROUND_RECTANGLE);

        this.arc = arc;
    }

    public RoundRectShape(Vector2f position, Dimensions dimensions, float arc) {
        super(position, dimensions, EnumShape.ROUND_RECTANGLE);

        this.arc = arc;
    }

    public RoundRectShape(float x, float y, float width, float height, float arc) {
        super(x, y, width, height, EnumShape.ROUND_RECTANGLE);
        this.arc = arc;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        if (isFilled()) {
            saltyGraphics.drawRoundRect(this, arc);
        } else {
            saltyGraphics.outlineRoundRect(this, arc);
        }
    }

    public float getArc() {
        return arc;
    }

    public void setArc(float arc) {
        this.arc = arc;
    }
}
