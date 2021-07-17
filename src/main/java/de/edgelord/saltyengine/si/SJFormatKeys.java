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

/**
 * Key string ocnstants for the sj scene format
 */
public class SJFormatKeys {
    public static final String KEY_NAME = "name";
    public static final String KEY_ID = "id";

    /**
     * Includes the {@link de.edgelord.saltyengine.graphics.sprite.TextureAtlas}
     * at the given relative path for parsing of {@link #KEY_SPRITE_NAME} and
     * {@link #KEY_ANIMATION_NAME}.
     */
    public static final String KEY_INCLUDE_TEXTURE = "include-texture";

    public static final String KEY_TRANSFORM = "transform";
    public static final String KEY_POSITION = "position";
    public static final String KEY_SIZE = "size";
    /**
     * {@link de.edgelord.saltyengine.transform.TransformRelationMode} used to
     * position the GameObject. Only x and y affecting ones are supported,
     * meaning:
     * <p>"CENTRE"
     * <br>TOP_LEFT_CORNER
     * <br>BOTTOM_LEFT_CORNER
     * <br>TOP_RIGHT_CORNER
     * <br>BOTTOM_RIGHT_CORNER
     */
    public static final String KEY_POSITION_ANCHOR = "position-anchor";

    public static final String KEY_IMAGE = "image";
    public static final String KEY_COLOR = "color";
    public static final String KEY_SPRITE_NAME = "sprite-name";
    public static final String KEY_ANIMATION_NAME = "animation-name";
    public static final String KEY_ANIMATION_FPS = "animation-fps";
}
