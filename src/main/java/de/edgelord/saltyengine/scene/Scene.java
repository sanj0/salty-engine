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

package de.edgelord.saltyengine.scene;

import de.edgelord.saltyengine.collision.CollisionDetectionResult;
import de.edgelord.saltyengine.collision.PrioritySceneCollider;
import de.edgelord.saltyengine.collision.SceneCollider;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.core.interfaces.InitializeAble;
import de.edgelord.saltyengine.core.physics.Force;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.FixedTask;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.GraphicsConfiguration;
import de.edgelord.saltyengine.graphics.light.LightSystem;
import de.edgelord.saltyengine.ui.UISystem;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.geom.AffineTransform;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * This class represents what is currently drawn and calculated. This includes:
 * {@link Layer}s within {@link #layers}, {@link FixedTask}s within {@link
 * #fixedTasks}, {@link DrawingRoutine}s within {@link #drawingRoutines} and the
 * {@link UISystem} {@link #ui} as well as a {@link LightSystem} stored in
 * {@link #lightSystem}
 * <p>
 * The current scene is stored in {@link SceneManager#getCurrentScene()}. For
 * more information, refer to the documentation of said class.
 * <p>
 * Any initializing should be done within {@link #initialize()} instead of a
 * constructor, which is called after setting a new instance of a
 * <code>Scene</code> object as the {@link
 * SceneManager#setCurrentScene(Scene) current}.
 */
public abstract class Scene implements Drawable, FixedTickRoutine, InitializeAble {

    public static final Object concurrentBlock = new Object();
    /**
     * The name of the default layer. {@link GameObject}s added to a
     * <code>Scene</code> via {@link #addGameObject(GameObject)} are added to
     * the corresponding layer.
     * <p>
     * The default layer is added to the
     * <code>Scene</code> in its constructor and
     * has an of <code>0</code>.
     */
    public static final String DEFAULT_LAYER = "default-layer";
    // potentially a decent fix of the collision detection problem
    // as the list only stores references and thus does not need
    // much memory
    protected final List<GameObject> allGameObjects = new ArrayList<>();
    private final Map<String, Layer> layers = new ConcurrentHashMap<>();
    private final Comparator<Layer> layerSorter = Comparator.comparingInt(Layer::getIndex);
    private final List<FixedTask> fixedTasks = Collections.synchronizedList(new ArrayList<>());
    private final List<DrawingRoutine> drawingRoutines = new CopyOnWriteArrayList<>();
    /**
     * If this is <code>true</code>, all {@link GameObject}s in his Scene will
     * constantly move down with a force of {@link #gravity}.
     */
    private boolean gravityEnabled = false;
    private List<Layer> layerList = new ArrayList<>();
    /**
     * The gravity used by all {@link GameObject}s in this Scene.
     */
    private float gravity = 1200;
    /**
     * The friction used by all {@link GameObject}s in this Scene.
     */
    private float friction = Force.DEFAULT_FRICTION;
    private LightSystem lightSystem = null;
    private UISystem ui = new UISystem();
    private SceneCollider sceneCollider = new PrioritySceneCollider();

    public Scene() {
        layers.put(DEFAULT_LAYER, new Layer(this, DEFAULT_LAYER, 0));
        updateLayerList();
    }

    /**
     * Initializes the <code>Scene</code> and all its initial components. Is
     * invoked by {@link SceneManager#setCurrentScene(Scene)}
     */
    @Override
    public abstract void initialize();

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        synchronized (concurrentBlock) {
            for (final DrawingRoutine drawingRoutine : drawingRoutines) {
                if (drawingRoutine.getDrawingPosition() == DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS) {
                    drawingRoutine.draw(saltyGraphics.copy());
                }
            }
        }

        synchronized (concurrentBlock) {
            for (final Layer layer : layerList) {
                layer.draw(saltyGraphics.copy());
            }
        }

        synchronized (concurrentBlock) {
            for (final DrawingRoutine drawingRoutine : drawingRoutines) {
                if (drawingRoutine.getDrawingPosition() == DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
                    drawingRoutine.draw(saltyGraphics.copy());
                }
            }
        }

        Game.forEachGameListener(gameListener -> gameListener.onGameRenderFinish(saltyGraphics));

        //Game.getCamera().tmpResetViewToGraphics(saltyGraphics);
        saltyGraphics.setTransform(new AffineTransform());

        if (lightSystem != null && GraphicsConfiguration.renderLight) {
            lightSystem.draw(saltyGraphics);
        }

        Game.forEachGameListener(gameListener -> gameListener.onPostLightRenderFinish(saltyGraphics));

        if (ui != null) {
            ui.drawUI(saltyGraphics.copy());
        }

        Game.getDefaultGFXController().doGFXDrawing(saltyGraphics);

        synchronized (concurrentBlock) {
            for (final DrawingRoutine drawingRoutine : drawingRoutines) {
                if (drawingRoutine.getDrawingPosition() == DrawingRoutine.DrawingPosition.LAST) {
                    drawingRoutine.draw(saltyGraphics.copy());
                }
            }
        }
    }

    @Override
    public void onFixedTick() {
        synchronized (concurrentBlock) {
            doFixedTasks();

            for (int i = 0; i < layerList.size(); i++) {
                final Layer layer = layerList.get(i);
                layer.onFixedTick();
            }

            for (int i = 0; i < allGameObjects.size(); i++) {
                final GameObject gameObject = allGameObjects.get(i);
                if (gameObject.isStatic()) continue;
                for (int j = i + 1; j < allGameObjects.size(); j++) {
                    final GameObject otherGameObject = allGameObjects.get(j);
                    final CollisionDetectionResult result = sceneCollider.checkCollision(gameObject, otherGameObject);
                    if (result.isCollision()) {
                        final CollisionEvent event1 = result.toCollisionEvent(otherGameObject);
                        final CollisionEvent event2 = new CollisionEvent(gameObject,
                                Directions.mirrorDirection(result.getRootCollisionDirection()));
                        Game.forEachGameListener(gl -> gl.onCollision(gameObject, event1));
                        gameObject.onCollision(event1);
                        gameObject.doComponentOnCollision(event1);
                        otherGameObject.onCollision(event2);
                        otherGameObject.doComponentOnCollision(event2);

                        gameObject.getCollisions().add(event1);
                        otherGameObject.getCollisions().add(event2);
                    }
                }
                gameObject.onCollisionDetectionFinish(gameObject.getCollisions());
                gameObject.doComponentOnCollisionDetectionFinish(gameObject.getCollisions());
                gameObject.getCollisions().clear();
            }

            Game.getDefaultGFXController().doGFXFixedTick();

            if (ui != null) {
                ui.onFixedTick();
            }
        }
    }

    public void addFixedTask(final FixedTask fixedTask) {
        synchronized (concurrentBlock) {
            fixedTasks.add(fixedTask);
        }
    }

    public void addDrawingRoutine(final DrawingRoutine drawingRoutine) {
        synchronized (concurrentBlock) {
            drawingRoutines.add(drawingRoutine);
        }
    }

    public void addGameObject(final GameObject gameObject, final String layer, final int index) {
        synchronized (concurrentBlock) {
            gameObject.getPhysics().setGravityEnabled(gravityEnabled);
            layers.get(layer).add(index, gameObject);
            allGameObjects.add(gameObject);
        }
    }

    public void addGameObject(final GameObject gameObject, final String layer) {
        synchronized (concurrentBlock) {
            gameObject.getPhysics().setGravityEnabled(gravityEnabled);
            layers.get(layer).add(gameObject);
            allGameObjects.add(gameObject);
        }
    }

    public void addGameObject(final GameObject gameObject) {
        addGameObject(gameObject, DEFAULT_LAYER);
    }

    public boolean removeGameObject(final GameObject gameObject, final String layer) {
        synchronized (concurrentBlock) {
            final boolean removed = layers.get(layer).remove(gameObject);
            if (removed) {
                allGameObjects.remove(gameObject);
            }
            return removed;
        }
    }

    /**
     * Removes the first occurrence of the given
     * <code>GameObject</code> in any of the
     * {@link #layers}.
     *
     * @param gameObject the <code>GameObject</code> to remove from one of the
     *                   {@link #layers}
     *
     * @return <code>true</code> if the given
     * <code>GameObject</code> was in any
     * of the {@link #layers}
     */
    public boolean removeGameObject(final GameObject gameObject) {
        final Set<String> layerNames = layers.keySet();

        synchronized (concurrentBlock) {
            for (final String layerName : layerNames) {
                if (removeGameObject(gameObject, layerName)) {
                    return true;
                }
            }
        }

        return false;
    }

    public void clearGameObjects() {
        synchronized (concurrentBlock) {
            for (int i = 0; i < layers.size(); i++) {
                layerList.get(i).clear();
            }
            allGameObjects.clear();
        }
    }

    public void removeFixedTask(final FixedTask fixedTask) {
        synchronized (concurrentBlock) {
            fixedTasks.remove(fixedTask);
        }
    }

    public void clearFixedTasks() {
        synchronized (concurrentBlock) {
            fixedTasks.clear();
        }
    }

    public void removeDrawingRoutine(final DrawingRoutine drawingRoutine) {
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
            for (final FixedTask fixedTask : fixedTasks) {

                fixedTask.onFixedTick();
            }
        }
    }

    /**
     * Iterates through the {@link #layers} and adds all {@link GameObject}s
     * from every {@link Layer} to a <code>List</code> and returns that list.
     *
     * @return a constructed list of all
     * <code>GameObjects</code> from all {@link
     * #layers}
     */
    public List<GameObject> getGameObjects() {
        final List<GameObject> gameObjects = new ArrayList<>();
        synchronized (concurrentBlock) {
            for (int i = 0; i < layerList.size(); i++) {
                gameObjects.addAll(layerList.get(i).getGameObjects());
            }
        }

        return gameObjects;
    }

    private void updateLayerList() {
        synchronized (concurrentBlock) {
            layerList = new ArrayList<>(layers.values());
            layerList.sort(layerSorter);
        }
    }

    public void addLayer(final Layer layer) {
        layers.put(layer.getTag(), layer);
        allGameObjects.addAll(layer.getGameObjects());
        updateLayerList();
    }

    public void addLayer(final String name, final int index) {
        layers.put(name, new Layer(this, name, index));
        updateLayerList();
    }

    /**
     * Adds a copy of the given {@link Layer} to this <code>Scene</code>.
     *
     * @param layer the <code>Layer</code> to copy from
     * @param name  the name of the new <code>Layer</code>
     * @param index the  of the new <code>Layer</code>
     *
     * @see Layer#Layer(Layer, Scene, String, int)
     */
    public void copyAddLayer(final Layer layer, final String name, final int index) {
        layers.put(name, new Layer(layer, this, name, index));
        updateLayerList();
    }

    public Layer getLayer(final String name) {
        return layers.get(name);
    }

    public void removeLayer(final String name) {
        allGameObjects.removeAll(layers.remove(name).getGameObjects());
        updateLayerList();
    }

    public void removeLayer(final Layer layer) {
        layers.remove(layer.getTag(), layer);
        allGameObjects.removeAll(layer.getGameObjects());
        updateLayerList();
    }

    public List<Layer> getLayerList() {
        return layerList;
    }

    public SceneCollider getSceneCollider() {
        return sceneCollider;
    }

    public void setSceneCollider(final SceneCollider sceneCollider) {
        this.sceneCollider = sceneCollider;
    }

    public UISystem getUI() {
        return ui;
    }

    public void setUI(final UISystem uiSystem) {
        this.ui = uiSystem;
    }

    public int getGameObjectCount() {
        int count = 0;
        synchronized (concurrentBlock) {
            for (int i = 0; i < layers.size(); i++) {
                count += layerList.get(i).size();
            }
        }

        return count;
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

    public void setGravity(final float gravity) {
        this.gravity = gravity;
    }

    /**
     * Gets {@link #gravityEnabled}.
     *
     * @return the value of {@link #gravityEnabled}
     */
    public boolean isGravityEnabled() {
        return gravityEnabled;
    }

    /**
     * Sets {@link #gravityEnabled}.
     *
     * @param gravityEnabled the new value of {@link #gravityEnabled}
     */
    public void setGravityEnabled(final boolean gravityEnabled) {
        this.gravityEnabled = gravityEnabled;
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(final float friction) {
        this.friction = friction;
    }

    public LightSystem getLightSystem() {
        return lightSystem;
    }

    public void setLightSystem(final LightSystem lightSystem) {
        this.lightSystem = lightSystem;
    }

    public List<FixedTask> getFixedTasks() {
        return fixedTasks;
    }

    public List<DrawingRoutine> getDrawingRoutines() {
        return drawingRoutines;
    }
}
