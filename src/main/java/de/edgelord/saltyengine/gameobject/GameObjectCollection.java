/*
 * Copyright 2018 Malte Dostal
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

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.scene.Scene;

import java.util.LinkedList;
import java.util.List;

public class GameObjectCollection {

    private List<GameObject> gameObjects = new LinkedList<>();

    public GameObjectCollection() {

    }

    public void addGameObject(GameObject gameObject) {

        gameObjects.add(gameObject);
    }

    public void addToScene(Scene scene) {

        for (GameObject gameObject : gameObjects) {
            scene.addGameObject(gameObject);
        }
    }
}
