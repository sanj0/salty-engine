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
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.sanjo.SJValue;

import java.util.List;
import java.util.Map;

public class SJScene extends Scene {

    private final SJGameObjectParser parser;
    private final List<Map<String, Object>> objectMaps;
    private final Map<String, SJValue> rootAttributes;

    public SJScene(final SJGameObjectParser parser, final List<Map<String, Object>> objectMaps,
                   final Map<String, SJValue> rootAttributes, final float gravity) {
        this.parser = parser;
        this.objectMaps = objectMaps;
        this.rootAttributes = rootAttributes;
        setGravity(gravity);
    }

    @Override
    public void initialize() {
        for (final Map<String, Object> objectMap : objectMaps) {
            final GameObject g = parser.parseGameObject(objectMap);
            if (g == null) {
                throw new IllegalArgumentException("couldn't parse gameobject (id=" + objectMap.get(SJFormatKeys.KEY_ID) + ")");
            }
            addGameObject(g);
        }
    }

    public Map<String, SJValue> getRootAttributes() {
        return rootAttributes;
    }
}
