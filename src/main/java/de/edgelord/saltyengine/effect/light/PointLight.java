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

package de.edgelord.saltyengine.effect.light;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.effect.geom.EnumShape;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Coordinates2f;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class PointLight extends GradientLight {

    public PointLight(Transform transform) {
        super(transform, EnumShape.OVAL);
    }

    public PointLight(float x, float y, float radius) {
        this(new Transform(x, y, radius, radius));
    }

    public PointLight(Coordinates2f position, float radius) {
        this(new Transform(position, new Dimensions(radius, radius)));
    }
}
