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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.sanjo.*;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static de.edgelord.saltyengine.si.SJFormatKeys.*;

/**
 * Parses a {@link de.edgelord.saltyengine.scene.Scene} from a sanjo file.
 */
public class SJSceneParser {

    public static final String ORIGINAL_VALUE_PREFIX = "orig.";
    private final List<String> sanjoData;

    public SJSceneParser(final List<String> sanjoData) {
        this.sanjoData = sanjoData;
    }

    public SJSceneParser(final File f) throws IOException {
        sanjoData = SanjoFile.readLines(f);
    }

    public static Map<String, Object> readAttributes(final SJClass data) {
        final Map<String, Object> attributes = new HashMap<>();
        for (final SJValue value : data.getValues().values()) {
            attributes.put(value.getKey(), parseAttribute(value));
            attributes.put(ORIGINAL_VALUE_PREFIX + value.getKey(), value.string());
        }
        return attributes;
    }

    public static Object parseAttribute(final SJValue value) {
        if (!(value.getValue() instanceof String)) {
            return value.getValue();
        }
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
            case KEY_IMAGE:
                return new InnerResource().getImageResource(value.string());
            case KEY_NAME:
            case KEY_ID:
            default:
                return value.string();
        }
    }

    public SJScene parseScene(final SJGameObjectParser gameObjectParser) {
        final SJClass dataRoot = new SanjoParser().parse(sanjoData);
        final List<Map<String, Object>> objectMaps = new ArrayList<>();
        final Map<String, SJValue> rootAttributes = dataRoot.getValues();
        final Optional<SJClass> sceneClass = dataRoot.getChild("scene");
        final Optional<SJValue> gravity = dataRoot.get(SJAddress.forString(">scene?gravity"));
        final Optional<SJValue> camPos = dataRoot.get(SJAddress.forString(">scene?camera-position"));
        final Optional<SJValue> camScale = dataRoot.get(SJAddress.forString(">scene?camera-scale"));

        camPos.ifPresent(v -> Game.getCamera().setPosition(Vector2f.parseVector2f(v.string())));
        camScale.ifPresent(v -> Game.getCamera().setScale(v.floatValue()));
        sceneClass.ifPresent(sjClass -> dataRoot.getChildren().remove(sjClass));

        for (final SJClass child : dataRoot.getChildren()) {
            objectMaps.add(readAttributes(child));
        }
        return new SJScene(gameObjectParser, objectMaps, rootAttributes, gravity.orElse(SJValue.forValue(1000)).floatValue());
    }

    public static String getOriginalValue(final Map<String, Object> attribs, final String key) {
        return attribs.get(ORIGINAL_VALUE_PREFIX + key).toString();
    }
}
