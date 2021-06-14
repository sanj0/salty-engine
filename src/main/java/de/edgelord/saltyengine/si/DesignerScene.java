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

import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DesignerScene extends Scene {
    private final Map<String, Map<String, Object>> defaults = new HashMap<>();
    private final SJGameObjectParser gameObjectParser;

    public DesignerScene(final String configPath, final String scenePath, final SJGameObjectParser gameObjectParser) throws IOException {
        this.gameObjectParser = gameObjectParser;
        getGameObjects().addAll(new SJSceneParser(new InnerResource().getFileResource(scenePath)).parseScene(gameObjectParser).getGameObjects());
    }

    @Override
    public void initialize() {

    }
}
