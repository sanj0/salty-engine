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

package de.edgelord.saltyengine.graphics.light;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.graphics.geom.EnumShape;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class PointLight extends GradientLight {

    public PointLight(final Transform transform) {
        super(transform, EnumShape.OVAL);
    }

    public PointLight(final float x, final float y, final float radius) {
        this(new Transform(x, y, radius, radius));
    }

    public PointLight(final Vector2f position, final float radius) {
        this(new Transform(position, new Dimensions(radius, radius)));
    }

    public PointLight(final Transform transform, final Color color) {
        super(transform, color, EnumShape.OVAL);
    }

    public PointLight(final Vector2f position, final Dimensions dimensions, final Color color) {
        super(position, dimensions, color, EnumShape.OVAL);
    }

    public PointLight(final Vector2f position, final Dimensions dimensions) {
        super(position, dimensions, EnumShape.OVAL);
    }

    public PointLight(final float x, final float y, final float width, final float height, final Color color) {
        super(x, y, width, height, color, EnumShape.OVAL);
    }

    public PointLight(final float x, final float y, final float width, final float height) {
        super(x, y, width, height, EnumShape.OVAL);
    }

    public PointLight(final Transform transform, final Color color, final float brightness, final float intensity) {
        super(transform, color, brightness, intensity, EnumShape.OVAL);
    }
}
