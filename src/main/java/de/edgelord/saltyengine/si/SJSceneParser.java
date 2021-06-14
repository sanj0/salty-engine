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

import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;
import de.edgelord.sanjo.SanjoFile;
import de.edgelord.sanjo.SanjoParser;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static de.edgelord.saltyengine.si.SJFormatKeys.*;

/**
 * Parses a {@link de.edgelord.saltyengine.scene.Scene} from a sanjo file.
 */
public class SJSceneParser {

    private final List<String> sanjoData;

    public SJSceneParser(final List<String> sanjoData) {
        this.sanjoData = sanjoData;
    }

    public SJSceneParser(final File f) throws IOException {
        sanjoData = SanjoFile.readLines(f);
    }

    public SJScene parseScene(final SJGameObjectParser gameObjectParser) {
        final SJClass dataRoot = new SanjoParser().parse(sanjoData);
        final String name = dataRoot.getValue(KEY_NAME).orElse(SJValue.forValue("")).string();
        final List<Map<String, Object>> objectMaps = new ArrayList<>();
        final Map<String, SJValue> rootAttributes = dataRoot.getValues();

        for (final SJClass child : dataRoot.getChildren()) {
            final Map<String, Object> attributes = new HashMap<>();
            for (final SJValue value : child.getValues().values()) {
                attributes.put(value.getKey(), parseAttribute(value));
            }
            objectMaps.add(attributes);
        }
        return new SJScene(gameObjectParser, objectMaps, rootAttributes);
    }

    private Object parseAttribute(final SJValue value) {
        switch (value.getKey()) {
            case KEY_ANIMATION_FPS:
                return value.intValue();
            case KEY_COLOR:
                return ColorUtil.parseColor(value.string());
            case KEY_POSITION:
                return Vector2f.parseVector2f(value.string());
            case KEY_SIZE:
                return Dimensions.parseDimensions(value.string());
            case KEY_TRANSFORM:
                return Transform.parseTransform(value.string());
            case KEY_NAME:
            case KEY_ID:
            default:
                return value.string();
        }
    }
}
