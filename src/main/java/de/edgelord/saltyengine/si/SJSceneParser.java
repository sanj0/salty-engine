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
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;
import de.edgelord.sanjo.SanjoFile;
import de.edgelord.sanjo.SanjoParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static de.edgelord.saltyengine.si.SJFormatKeys.KEY_NAME;

/**
 * Parses a {@link de.edgelord.saltyengine.scene.Scene} from a sanjo file.
 */
public abstract class SJSceneParser {

    private final List<String> sanjoData;

    public SJSceneParser(final List<String> sanjoData) {
        this.sanjoData = sanjoData;
    }

    public SJSceneParser(final File f) throws IOException {
        sanjoData = SanjoFile.readLines(f);
    }

    public Scene parseScene(final SJGameObjectParser gameObjectParser) {
        final SJClass dataRoot = new SanjoParser().parse(sanjoData);
        final String name = dataRoot.getValue(KEY_NAME).orElse(SJValue.forValue("")).string();
        final List<Map<String, Object>> objectMaps = new ArrayList<>();

        for (final SJClass child : dataRoot.getChildren()) {
            //TODO: all the parsing
        }
        return new SJScene(gameObjectParser, objectMaps);
    }

    /**
     * Parses a {@link GameObject} from the given map of attributes. The
     * attributes may or may not contain some or all or none of the attributes
     * declared in {@link SJFormatKeys} plus some or none custom attributes that
     * are up for the implementor to parse accordingly and pack into a {@link
     * GameObject}.
     *
     * @param attributes some or no attributes to parse a {@link GameObject}
     *                   from
     *
     * @return a {@link GameObject} handcrafted from the given attributes
     */
    public abstract GameObject parseGameObject(final Map<String, Object> attributes);
}
