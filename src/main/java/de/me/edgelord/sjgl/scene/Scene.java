/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.scene;

import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.gameobject.FixedTask;
import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.utils.Directions;

import java.awt.*;
import java.util.LinkedList;

// TODO: 02.07.18 the collision detection in onFixedTick

public class Scene {

    private LinkedList<GameObject> gameObjects = new LinkedList<>();
    private LinkedList<FixedTask> fixedTasks = new LinkedList<>();
    private int xDelta, yDelta;
    private boolean initialized = false;

    public Scene() {

    }

    public void addFixedTask(FixedTask fixedTask) {

        fixedTasks.add(fixedTask);
    }

    public void doFixedTasks() {

        for (FixedTask fixedTask : fixedTasks) {

            fixedTask.onFixedTick();
        }
    }

    public void resetPosition() {

        for (GameObject gameObject : getGameObjects()) {

            gameObject.setX(gameObject.getX() + xDelta);
            gameObject.setY(gameObject.getY() + yDelta);
        }
    }

    public void addGameObject(GameObject gameObject) {

        gameObjects.add(gameObject);
    }

    public void draw(Graphics2D graphics) {

        for (GameObject gameObject : gameObjects) {
            gameObject.draw(graphics);
        }
    }

    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void resetZoom() {

        for (GameObject gameObject : gameObjects) {

            gameObject.resetZoom();
        }
    }

    public void zoom(int zoom, DisplayManager displayManager) {

        for (GameObject gameObject : getGameObjects()) {

            gameObject.zoom(zoom, displayManager);
        }
    }

    public void onFixedTick() {

        doFixedTasks();

        for (GameObject gameObject : getGameObjects()) {
            gameObject.onFixedTick();
        }
    }

    public void onTick() {

        for (GameObject gameObject : getGameObjects()) {
            gameObject.onTick();
        }
    }

    public void initGameObjects() {

        if (initialized) {

            return;
        } else {

            for (GameObject gameObject : getGameObjects()) {
                gameObject.initialize();
            }

            initialized = true;
        }
    }

    public void moveCamera(Directions.BasicDirection direction, int delta) {

        if (direction == Directions.BasicDirection.x) {

            xDelta += delta;

            for (GameObject gameObject : getGameObjects()) {

                gameObject.setX(gameObject.getX() + delta);
            }
        } else {

            yDelta += delta;

            for (GameObject gameObject : getGameObjects()) {

                gameObject.setY(gameObject.getY() + delta);
            }
        }
    }

    public void setGameObjects(LinkedList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public LinkedList<FixedTask> getFixedTasks() {
        return fixedTasks;
    }

    public void setFixedTasks(LinkedList<FixedTask> fixedTasks) {
        this.fixedTasks = fixedTasks;
    }
}
