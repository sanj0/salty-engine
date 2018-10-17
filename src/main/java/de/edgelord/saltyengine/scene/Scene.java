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

package de.edgelord.saltyengine.scene;

import de.edgelord.saltyengine.components.SimplePhysicsComponent;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.physics.Force;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.FixedTask;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UISystem;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Scene {

    public static final Object concurrentBlock = "3141592653589793";

    private float gravity = SimplePhysicsComponent.DEFAULT_GRAVITY_ACCELERATION;
    private float friction = Force.DEFAULT_FRICTION;

    private boolean gravityEnabled = true;

    private List<GameObject> gameObjects = Collections.synchronizedList(new ArrayList<>());
    private List<FixedTask> fixedTasks = Collections.synchronizedList(new ArrayList<>());
    private List<DrawingRoutine> drawingRoutines = Collections.synchronizedList(new ArrayList<>());
    private UISystem ui = new UISystem();

    public Scene() {

    }

    public void disableGravity() {
        gravityEnabled = false;
    }

    public void enableGravity() {
        gravityEnabled = true;
    }

    public void addFixedTask(FixedTask fixedTask) {

        synchronized (concurrentBlock) {
            fixedTasks.add(fixedTask);
        }
    }

    public void addDrawingRoutine(DrawingRoutine drawingRoutine) {
        synchronized (concurrentBlock) {
            drawingRoutines.add(drawingRoutine);
        }
    }

    public void addGameObject(GameObject gameObject) {

        synchronized (concurrentBlock) {
            gameObjects.add(gameObject);
        }
    }

    public void addGameObject(int index, GameObject gameObject) {
        synchronized (concurrentBlock) {
            gameObjects.add(index, gameObject);
        }
    }

    public void removeGameObject(GameObject gameObject) {
        synchronized (concurrentBlock) {
            gameObjects.remove(gameObject);
        }
    }

    public void clearGameObjects() {
        synchronized (concurrentBlock) {
            gameObjects.clear();
        }
    }

    public void removeFixedTask(FixedTask fixedTask) {
        synchronized (concurrentBlock) {
            fixedTasks.remove(fixedTask);
        }
    }

    public void clearFixedTasks() {
        synchronized (concurrentBlock) {
            fixedTasks.clear();
        }
    }

    public void removeDrawingRoutine(DrawingRoutine drawingRoutine) {
        synchronized (concurrentBlock) {
            drawingRoutines.remove(drawingRoutine);
        }
    }

    public void clearDrawingRoutines() {
        synchronized (concurrentBlock) {
            drawingRoutines.clear();
        }
    }

    public void doFixedTasks() {

        synchronized (concurrentBlock) {
            for (FixedTask fixedTask : fixedTasks) {

                fixedTask.onFixedTick();
            }
        }
    }

    public void draw(SaltyGraphics saltyGraphics) {


        synchronized (concurrentBlock) {
            for (DrawingRoutine drawingRoutine : drawingRoutines) {
                if (drawingRoutine.getDrawingPosition() == DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS) {
                    drawingRoutine.draw(saltyGraphics);
                }
            }
        }

        synchronized (concurrentBlock) {
            for (GameObject gameObject : gameObjects) {
                AffineTransform before = saltyGraphics.getGraphics2D().getTransform();
                float rotation = gameObject.getTransform().getRotation().getRotationDegrees();
                Vector2f rotationCentre = gameObject.getTransform().getRotation().getCentre();
                saltyGraphics.getGraphics2D().rotate(rotation, rotationCentre.getX(), rotationCentre.getY());

                gameObject.draw(saltyGraphics);
                gameObject.doComponentDrawing(saltyGraphics);

                saltyGraphics.setTransform(before);
            }
        }

        if (ui != null) {
            ui.drawUI(saltyGraphics);
        }

        Game.getDefaultGFXController().doGFXDrawing(saltyGraphics);

        synchronized (concurrentBlock) {
            for (DrawingRoutine drawingRoutine : drawingRoutines) {
                if (drawingRoutine.getDrawingPosition() == DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
                    drawingRoutine.draw(saltyGraphics);
                }
            }
        }
    }

    public void onFixedTick() {

        doFixedTasks();

        synchronized (concurrentBlock) {

            for (int i = 0; i < gameObjects.size(); i++) {
                GameObject gameObject = gameObjects.get(i);

                if (!gameObject.isInitialized()) {
                    gameObject.initialize();
                    gameObject.setInitialized(true);
                }

                gameObject.doCollisionDetection(gameObjects);
                gameObject.doComponentOnFixedTick();
                gameObject.onFixedTick();
            }
        }

        Game.getDefaultGFXController().doGFXFixedTick();

        if (ui != null) {

            ui.onFixedTick();
        }
    }

    public void setUI(UISystem uiSystem) {
        this.ui = uiSystem;
    }

    public UISystem getUI() {
        return ui;
    }

    public int getGameObjectCount() {
        synchronized (concurrentBlock) {
            return gameObjects.size();
        }
    }

    public int getDrawingRoutineCount() {
        synchronized (concurrentBlock) {
            return drawingRoutines.size();
        }
    }

    public int getFixedTaskCount() {
        synchronized (concurrentBlock) {
            return fixedTasks.size();
        }
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public boolean isGravityEnabled() {
        return gravityEnabled;
    }

    public void setGravityEnabled(boolean gravityEnabled) {
        this.gravityEnabled = gravityEnabled;
    }
}
