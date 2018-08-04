/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.edgelord.sjgl.management;

import de.edgelord.sjgl.gameobject.GameObject;

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
