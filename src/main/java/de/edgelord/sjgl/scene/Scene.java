/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.scene;

import de.edgelord.sjgl.gameobject.DrawingRoutin;
import de.edgelord.sjgl.gameobject.FixedTask;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;
import de.edgelord.sjgl.ui.UISystem;
import de.edgelord.sjgl.utils.Directions;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {

    /*
    private List<GameObject> gameObjects = Collections.synchronizedList(new LinkedList<>());
    private List<FixedTask> fixedTasks = Collections.synchronizedList(new LinkedList<>());
    */
    private CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<FixedTask> fixedTasks = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<DrawingRoutin> drawingRoutins = new CopyOnWriteArrayList<>();
    private UISystem ui = null;
    private float xDelta, yDelta;
    private boolean initialized = false;

    public Scene() {

    }

    public void addFixedTask(FixedTask fixedTask) {

        fixedTasks.add(fixedTask);
    }

    public void doFixedTasks() {

        synchronized (getFixedTasks()) {
            for (FixedTask fixedTask : fixedTasks) {

                fixedTask.onFixedTick();
            }
        }
    }

    public void doCollisionDetection(){

        List<GameObjectComponent> collisionComponents = new ArrayList<>();

        synchronized (getGameObjects()) {

            for (GameObject gameObject : gameObjects) {

                gameObject.doCollisionDetection(getGameObjects(), collisionComponents);
            }
        }
    }

    public void resetPosition() {

        synchronized (getGameObjects()) {
            for (GameObject gameObject : getGameObjects()) {

                gameObject.setX(gameObject.getX() + xDelta);
                gameObject.setY(gameObject.getY() + yDelta);
            }
        }
    }

    public void addGameObject(GameObject gameObject) {

        gameObjects.add(gameObject);
    }

    public void draw(Graphics2D graphics) {

        synchronized (getGameObjects()) {

            for (DrawingRoutin drawingRoutin : drawingRoutins) {
                drawingRoutin.draw(graphics);
            }

            for (GameObject gameObject : gameObjects) {
                gameObject.draw(graphics);
                gameObject.doComponentDrawing(graphics);
            }

            if (ui != null) {
                ui.drawUI(graphics);
            }
        }
    }

    public void setUI(UISystem uiSystem){
        this.ui = uiSystem;
    }

    public UISystem getUI() {
        return ui;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void onFixedTick() {

        doFixedTasks();
        doCollisionDetection();

        synchronized (getGameObjects()) {
            for (GameObject gameObject : getGameObjects()) {
                gameObject.doComponentOnFixedTick();
                gameObject.onFixedTick();
            }
        }
    }

    public void onTick() {

        synchronized (getGameObjects()) {
            for (GameObject gameObject : getGameObjects()) {
                gameObject.onTick();
            }
        }
    }

    public void initGameObjects() {

        if (initialized) {

            return;
        } else {

            synchronized (getGameObjects()) {
                for (GameObject gameObject : getGameObjects()) {
                    gameObject.initialize();
                }
            }

            initialized = true;
        }
    }

    public void moveCamera(Directions.BasicDirection direction, float delta) {

        synchronized (getGameObjects()) {
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
    }

    public void setGameObjects(CopyOnWriteArrayList<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }

    public List<FixedTask> getFixedTasks() {
        return fixedTasks;
    }

    public void setFixedTasks(CopyOnWriteArrayList<FixedTask> fixedTasks) {
        this.fixedTasks = fixedTasks;
    }

    public void addDrawingRoutin(DrawingRoutin drawingRoutin) {
        getDrawingRoutins().add(drawingRoutin);
    }

    public CopyOnWriteArrayList<DrawingRoutin> getDrawingRoutins() {
        return drawingRoutins;
    }

    public void setDrawingRoutins(CopyOnWriteArrayList<DrawingRoutin> drawingRoutins) {
        this.drawingRoutins = drawingRoutins;
    }
}
