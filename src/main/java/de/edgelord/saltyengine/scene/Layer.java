/*
 * Copyright 2020 Malte Dostal
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
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A <code>Layer</code> is a sub-unit
 * of a {@link Scene}.
 * <p>
 * <code>Scene</code>s store their
 * {@link de.edgelord.saltyengine.gameobject.GameObject}s
 * in <code>Layer</code>s to enable sorting and
 * encapsulated operations on a "physical"
 * layer of <code>GameObject</code>s.
 * <p>
 * To the outside, a <code>Layer</code> acts
 * like a <code>GameObject</code>.
 * As such, it has a transform which can be used
 * to {@link java.awt.Graphics2D#clip(Shape) clip}
 * a <code>Layer</code>.
 */
public class Layer extends GameObject {

    /**
     * The list of {@link GameObject}s
     * that this <code>Layer</code> contains.
     */
    private final List<GameObject> gameObjects = Collections.synchronizedList(new ArrayList<>());

    /**
     * The <code>Scene</code> that contains
     * this <code>Layer</code>
     */
    private final Scene container;

    /**
     * The index (imaginary z position)
     * of this <code>Layer</code> within a
     * {@link Scene}.
     * In a <code>Scene</code> with two <code>Layer</code>s
     * <code>l1</code> with an index of 0 and <code>l2</code>
     * with an index of <code>1</code>, <code>l2</code>
     * would be drawn after <code>l1</code>, meaning that
     * <code>l2</code> will be drawn on top of <code>l1</code>.
     * Therefore, the higher the index, the later a <code>Layer</code>
     * is being rendered.
     */
    private int index;

    /**
     * A transform that describes how the graphics
     * is transformed before this <code>Layer</code>
     * is being rendered. E.g. This transform
     * transforms this <code>Layer</code>
     */
    private AffineTransform affineTransform = new AffineTransform();

    /**
     * Constructs a new <code>Layer</code> with
     * the {@link Game#getGameTransform() transform of the game}
     *
     * @param container the <code>Scene</code> that contains this <code>Layer</code>
     * @param tag       the id-name of this <code>Layer</code>
     * @param index     the {@link #index} of this layers
     */
    public Layer(final Scene container, final String tag, final int index) {
        super(Game.getGameTransform(), tag);

        this.container = container;
        this.index = index;
    }

    /**
     * Constructs a new <code>Layer</code> with
     * all {@link GameObject}s of the given <code>Layer</code>
     * and the {@link de.edgelord.saltyengine.transform.Transform} of
     * it.
     *
     * @param layer     the <code>Layer</code> from which to add all <code>GameObject</code>s to this one
     * @param container the <code>Scene</code> that contains this <code>Layer</code>
     * @param tag       the id-name of this <code>Layer</code>
     * @param index     the {@link #index} of this layers
     */
    public Layer(final Layer layer, final Scene container, final String tag, final int index) {
        this(container, tag, index);
        setTransform(layer.getTransform());

        gameObjects.addAll(layer.getGameObjects());
    }

    @Override
    public final void initialize() {
    }

    @Override
    public void onCollision(final CollisionEvent event) {
    }

    @Override
    public void onFixedTick() {

        for (int i = 0; i < gameObjects.size(); i++) {
            final GameObject gameObject = gameObjects.get(i);

            if (gameObject.isClearCollisions()) {
                gameObject.getCollisions().clear();
                gameObject.setClearCollisions(false);
            }

            if (!gameObject.isInitialized()) {
                gameObject.initialize();
                gameObject.setInitialized(true);
            }


            for (int i2 = i + 1; i2 < gameObjects.size(); i2++) {
                final GameObject gameObject2 = gameObjects.get(i2);
                if (gameObject2.isClearCollisions()) {
                    gameObject2.getCollisions().clear();
                    gameObject2.setClearCollisions(false);
                }

                final CollisionDetectionResult collisionDetectionResult = container.getSceneCollider().checkCollision(gameObject, gameObject2);

                if (collisionDetectionResult.isCollision()) {
                    final CollisionEvent collision = new CollisionEvent(gameObject2, collisionDetectionResult.getRootCollisionDirection());
                    final CollisionEvent collision2 = new CollisionEvent(gameObject, Directions.mirrorDirection(collisionDetectionResult.getRootCollisionDirection()));

                    gameObject.getCollisions().add(collision);
                    gameObject.onCollision(collision);
                    gameObject.getComponents().forEach(component -> component.onCollision(collision));

                    gameObject2.onCollision(collision2);
                    gameObject2.getCollisions().add(collision2);
                    gameObject2.getComponents().forEach(component -> component.onCollision(collision2));
                    Game.forEachGameListener(gameListener -> gameListener.onCollision(gameObject, collision));
                }
            }

            gameObject.getComponents().forEach(component -> component.onCollisionDetectionFinish(gameObject.getCollisions()));
            gameObject.onCollisionDetectionFinish(gameObject.getCollisions());
            gameObject.doComponentOnFixedTick();
            gameObject.doFixedTick();
            gameObject.setClearCollisions(true);
        }
        doComponentOnFixedTick();
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        //TODO: use added() method from physics branche instead of this tmp
        final Vector2f tmpVector = new Vector2f(getPosition());
        saltyGraphics.setClip(new Transform(tmpVector.add(Game.getCamera().getPosition()), getDimensions()));
        saltyGraphics.setTransform(affineTransform);

        for (int i = 0; i < gameObjects.size(); i++) {
            final GameObject gameObject = gameObjects.get(i);

            final AffineTransform before = saltyGraphics.getGraphics2D().getTransform();
            final Vector2f rotationCentre = gameObject.getTransform().getRotationCentreAbsolute();
            saltyGraphics.setRotation(gameObject.getRotationDegrees(), rotationCentre);

            gameObject.draw(saltyGraphics.copy());
            gameObject.doComponentDrawing(saltyGraphics.copy());

            saltyGraphics.setTransform(before);
        }
        doComponentDrawing(saltyGraphics.copy());
    }

    /**
     * Gets {@link #gameObjects}.
     *
     * @return the value of {@link #gameObjects}
     */
    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    /**
     * Gets {@link #container}.
     *
     * @return the value of {@link #container}
     */
    public Scene getContainer() {
        return container;
    }

    /**
     * Gets {@link #index}.
     *
     * @return the value of {@link #index}
     */
    public int getIndex() {
        return index;
    }

    /**
     * Sets {@link #index}.
     *
     * @param index the new value of {@link #index}
     */
    public void setIndex(final int index) {
        this.index = index;
    }

    /**
     * Gets {@link #affineTransform}.
     *
     * @return the value of {@link #affineTransform}
     */
    public AffineTransform getAffineTransform() {
        return affineTransform;
    }

    /**
     * Sets {@link #affineTransform}.
     *
     * @param transform the new value of {@link #affineTransform}
     */
    public void setAffineTransform(final AffineTransform transform) {
        this.affineTransform = transform;
    }

    public int size() {
        return gameObjects.size();
    }

    public boolean isEmpty() {
        return gameObjects.isEmpty();
    }

    public boolean contains(final Object o) {
        return gameObjects.contains(o);
    }

    public boolean add(final GameObject gameObject) {
        return gameObjects.add(gameObject);
    }

    public void add(final int index, final GameObject gameObject) {
        gameObjects.add(index, gameObject);
    }

    public boolean remove(final Object o) {
        return gameObjects.remove(o);
    }

    public void sort(final Comparator<? super GameObject> c) {
        gameObjects.sort(c);
    }

    public void clear() {
        gameObjects.clear();
    }
}
