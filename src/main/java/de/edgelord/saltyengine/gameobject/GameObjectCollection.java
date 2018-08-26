/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
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
