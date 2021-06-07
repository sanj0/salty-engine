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

package de.edgelord.saltyengine.si;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.util.Map;

/**
 * Parses GameObjects from sanjo data
 */
@FunctionalInterface
public interface SJGameObjectParser {

    static Transform parseTransform(final Map<String, Object> attributes,
                                    final Vector2f defaultPosition,
                                    final Dimensions defaultDimensions) {
        final Vector2f position = (Vector2f) attributes.getOrDefault(SJFormatKeys.KEY_POSITION, defaultPosition);
        final Dimensions size = (Dimensions) attributes.getOrDefault(SJFormatKeys.KEY_SIZE, defaultDimensions);

        return new Transform(position, size);
    }

    /**
     * Parses a GameObject from the given attributes. The attributes may contain
     * but are not limited to the ones defined in {@link SJFormatKeys}
     *
     * @param attributes the attributes to parse a GameObject
     *
     * @return a GameObject parsed from the given attributes
     */
    GameObject parseGameObject(final Map<String, Object> attributes);
}
