/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.scene;

import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.FixedTask;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UISystem;

import java.awt.geom.AffineTransform;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Scene {

    /*
    private List<GameObject> gameObjects = Collections.synchronizedList(new LinkedList<>());
    private List<FixedTask> fixedTasks = Collections.synchronizedList(new LinkedList<>());
    */
    private CopyOnWriteArrayList<GameObject> gameObjects = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<FixedTask> fixedTasks = new CopyOnWriteArrayList<>();
    private CopyOnWriteArrayList<DrawingRoutine> drawingRoutines = new CopyOnWriteArrayList<>();
    private UISystem ui = new UISystem();
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

    public void addGameObject(GameObject gameObject) {

        gameObjects.add(gameObject);
    }

    public void draw(SaltyGraphics saltyGraphics) {

        synchronized (getGameObjects()) {

            for (DrawingRoutine drawingRoutine : drawingRoutines) {
                if (drawingRoutine.getDrawingPosition() == DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS) {
                    drawingRoutine.draw(saltyGraphics);
                }
            }

            for (GameObject gameObject : gameObjects) {
                AffineTransform before = saltyGraphics.getGraphics2D().getTransform();
                float rotation = gameObject.getTransform().getRotation().getRotationDegrees();
                Vector2f rotationCentre = gameObject.getTransform().getRotation().getCentre();
                saltyGraphics.getGraphics2D().rotate(rotation, rotationCentre.getX(), rotationCentre.getY());

                gameObject.draw(saltyGraphics);
                gameObject.doComponentDrawing(saltyGraphics);

                saltyGraphics.setTransform(before);
                /*
                saltyGraphics.setColor(Color.red);
                saltyGraphics.fillRect(0, 0, Game.getHost().getWidth(), Game.getHost().getHeight());
                */
            }

            if (ui != null) {
                ui.drawUI(saltyGraphics);
            }

            for (DrawingRoutine drawingRoutine : drawingRoutines) {
                if (drawingRoutine.getDrawingPosition() == DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
                    drawingRoutine.draw(saltyGraphics);
                }
            }
        }
    }

    public void setUI(UISystem uiSystem) {
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

        synchronized (getGameObjects()) {
            for (GameObject gameObject : getGameObjects()) {
                gameObject.doCollisionDetection(getGameObjects());
                gameObject.doComponentOnFixedTick();
                gameObject.onFixedTick();
            }
        }

        if (ui != null) {

            ui.onFixedTick();
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

    public List<FixedTask> getFixedTasks() {
        return fixedTasks;
    }

    public void addDrawingRoutin(DrawingRoutine drawingRoutine) {
        getDrawingRoutines().add(drawingRoutine);
    }

    public CopyOnWriteArrayList<DrawingRoutine> getDrawingRoutines() {
        return drawingRoutines;
    }
}
