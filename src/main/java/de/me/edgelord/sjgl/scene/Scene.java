/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.scene;

import de.me.edgelord.sjgl.gameobject.FixedTask;
import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.ui.UISystem;
import de.me.edgelord.sjgl.utils.Directions;

import java.awt.*;
import java.util.LinkedList;

public class Scene {

    private LinkedList<GameObject> gameObjects = new LinkedList<>();
    private LinkedList<FixedTask> fixedTasks = new LinkedList<>();
    private UISystem ui = null;
    private float xDelta, yDelta;
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

    public void doCollisionDetection(){

        for (GameObject gameObject : gameObjects){

            gameObject.doCollisionDetection(getGameObjects());
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
            gameObject.doComponentDrawing(graphics);
        }

        if (ui != null){
            ui.drawUI(graphics);
        }
    }

    public void setUI(UISystem uiSystem){
        this.ui = uiSystem;
    }

    public UISystem getUI() {
        return ui;
    }

    public LinkedList<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void onFixedTick() {

        doFixedTasks();
        doCollisionDetection();

        for (GameObject gameObject : getGameObjects()) {
            gameObject.doComponentOnFixedTick();
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

    public void moveCamera(Directions.BasicDirection direction, float delta) {

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
