/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.management;

import de.edgelord.saltyengine.gameobject.GameObject;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Random;

@Deprecated
public class StaticGameObjectsManager {

    private static LinkedHashMap<Integer, GameObject> gameObjects = new LinkedHashMap<>();
    private static Random IDGenerator = new Random();

    public static void addGameObject(GameObject gameObject, int id) {

        gameObjects.put(id, gameObject);
    }

    public static int getNewID() {

        int id = IDGenerator.nextInt();

        while (gameObjects.containsKey(id))
            id = IDGenerator.nextInt();

        return id;
    }

    public static GameObject getGameObject(int id) {
        return gameObjects.get(id);
    }

    public static LinkedList<GameObject> getAllGameObjects() {

        LinkedList<GameObject> allGameObjects = new LinkedList<>();
        allGameObjects.addAll(gameObjects.values());

        return allGameObjects;
    }
}
