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
import de.edgelord.saltyengine.graphics.image.ImageObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.TransformRelationMode;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.TransformRelationUtil;

import java.util.Map;

/**
 * Parses GameObjects from sanjo data
 */
@FunctionalInterface
public interface SJGameObjectParser {

    /**
     * Parses default objects.
     * <br>Inlcudes:
     * <ul>
     *     <li>{@link de.edgelord.saltyengine.graphics.image.ImageObject}</li>
     * </ul>
     *
     * @param attributes the attributes map
     *
     * @return a defaultly parsed object or null
     */
    static GameObject defaultParsing(final Map<String, Object> attributes) {
        final Transform t = parseTransform(attributes, Vector2f.zero(), Dimensions.zero());
        switch (attributes.get(SJFormatKeys.KEY_ID).toString()) {
            case ImageObject.TAG:
                final String imgPath = SJSceneParser.getOriginalValue(attributes, SJFormatKeys.KEY_IMAGE);
                final SaltyImage img = (SaltyImage) attributes.get(SJFormatKeys.KEY_IMAGE);
                return new ImageObject(parseTransform(attributes, Vector2f.zero(), img.getDimensions()), img, imgPath);
            default:
                return null;
        }
    }

    static Transform parseTransform(final Map<String, Object> attributes,
                                    final Vector2f defaultPosition,
                                    final Dimensions defaultDimensions) {
        if (attributes.containsKey(SJFormatKeys.KEY_TRANSFORM)) {
            return (Transform) attributes.get(SJFormatKeys.KEY_TRANSFORM);
        }
        final Vector2f position = (Vector2f) attributes.getOrDefault(SJFormatKeys.KEY_POSITION, defaultPosition);
        final Dimensions size = (Dimensions) attributes.getOrDefault(SJFormatKeys.KEY_SIZE, defaultDimensions);
        final Transform transform = new Transform(position, size);

        if (attributes.containsKey(SJFormatKeys.KEY_POSITION_ANCHOR)) {
            final TransformRelationMode anchor = TransformRelationMode.valueOf(attributes.get(SJFormatKeys.KEY_POSITION_ANCHOR).toString());
            TransformRelationUtil.positionRelativeTo(anchor, new Transform(position, Dimensions.zero()), transform);
        }

        return transform;
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
