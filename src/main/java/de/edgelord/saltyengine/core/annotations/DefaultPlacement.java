/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.core.annotations;

public @interface DefaultPlacement {

    Method method() default Method.TOP_LEFT_CORNER;

    /**
     * The methods for the default placement
     */
    enum Method {
        /**
         * When the placement of the object is
         * done via its top left corner.
         */
        TOP_LEFT_CORNER,
        /**
         * When the placement of the object is
         * done via its centre.
         */
        CENTRE,
        /**
         * The method of placement when the object
         * has the same position (and dimensions)
         * as its parent (e.g. components).
         */
        PARENT,
        /**
         * The method of placement when the object
         * is placed relative to its parent using
         * the {@link de.edgelord.saltyengine.utils.TransformRelationUtil}.
         */
        TRANSFORM_RELATION
    }
}
