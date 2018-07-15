package de.me.edgelord.sjgl.gameobject;

import de.me.edgelord.sjgl.scene.Scene;

import java.util.LinkedList;
import java.util.List;

public class GameObjectCollection {

    private List<GameObject> gameObjects = new LinkedList<>();

    public GameObjectCollection(){

    }

    public void addGameObject(GameObject gameObject){

        gameObjects.add(gameObject);
    }

    public void addToScene(Scene scene){

        for (GameObject gameObject : gameObjects){
            scene.addGameObject(gameObject);
        }
    }
}
