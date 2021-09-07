/*
 * Copyright 2021 Malte Dostal
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

package de.edgelord.saltyengine.transform;

/**
 * To be statically imported for the various methods "t" to easily create
 * Transform out of any form of inputs.
 */
public class TransformCreator {

    private TransformCreator() {
    }

    /**
     * Constructs a {@link Transform} from the given textual representation in
     * the format described by the below linked function the input is being
     * passed to.
     *
     * @param s the textual representation of a Transform in the format
     *          described by {@link Transform#parseTransform(String)}
     *
     * @return a Transform parsed from the given input String
     * @see Transform#parseTransform(String)
     */
    public static Transform t(final String s) {
        return Transform.parseTransform(s);
    }

    public static Transform t(final float x, final float y, final float w, final float h) {
        return new Transform(new Vector2f(x, y), new Dimensions(w, h));
    }

    public static Transform t(final Vector2f pos, final Dimensions size) {
        return new Transform(pos, size);
    }

    public static Transform t(final Vector2f pos, final float w, final float h) {
        return new Transform(pos, new Dimensions(w, h));
    }

    public static Transform t(final float x, final float y, final Dimensions size) {
        return new Transform(new Vector2f(x, y), size);
    }
}
