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

package de.edgelord.saltyengine.graphics.sprite;

import de.edgelord.saltyengine.graphics.image.SaltyImage;

import java.util.Map;

/**
 * The index of a texture as parsed by the {@link TextureAtlasParser}
 */
public class TextureAtlas {

    /**
     * The map holding the objects.
     */
    private final Map<String, Object> index;

    /**
     * Constructs an atlas with the given map
     *
     * @param index the {@link #index}
     */
    public TextureAtlas(final Map<String, Object> index) {
        this.index = index;
    }

    public Object get(final String id) {
        return index.get(id);
    }

    public Object getOrDefault(final String id, final Object defaultValue) {
        return index.getOrDefault(id, defaultValue);
    }

    public <T> T getTyped(final String id) {
        return (T) get(id);
    }

    public <T> T getTypedOrDefault(final String id, final T defaultValue) {
        return (T) getOrDefault(id, defaultValue);
    }

    public SaltyImage getImage(final String id) {
        return (SaltyImage) index.get(id);
    }

    public SaltyImage getImageOrDefault(final String id, final SaltyImage defaultImage) {
        return (SaltyImage) getOrDefault(id, defaultImage);
    }

    public SpritesheetAnimation getAnimation(final String id) {
        return (SpritesheetAnimation) index.get(id);
    }

    public SpritesheetAnimation getAnimationOrDefault(final String id, final SpritesheetAnimation defaultAnimation) {
        return (SpritesheetAnimation) getOrDefault(id, defaultAnimation);
    }

    /**
     * Gets {@link #index}.
     *
     * @return the value of {@link #index}
     */
    public Map<String, Object> getIndex() {
        return index;
    }
}
