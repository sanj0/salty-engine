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

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.components.*;
import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.interfaces.CollideAble;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.core.interfaces.InitializeAble;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.hitbox.Hitbox;
import de.edgelord.saltyengine.hitbox.SimpleHitbox;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToListConverter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import static de.edgelord.saltyengine.scene.Scene.concurrentBlock;

public abstract class GameObject extends ComponentParent implements Drawable, FixedTickRoutine, CollideAble, InitializeAble {

    public static final String DEFAULT_PHYSICS_NAME = "de.edgelord.saltyengine.coreComponents.physics";
    public static final String DEFAULT_RECALCULATE_HITBOX_NAME = "de.edgelord.saltyengine.coreComponents.recalculateHitbox";
    public static final String DEFAULT_ACCELERATOR_NAME = "de.edgelord.saltyengine.coreComponents.accelerator";
    public static final String DEFAULT_COLLIDER_COMPONENT_NAME = "de.edgelord.saltyengine.coreComponents.collider";
    public static final String DEFAULT_SHAPE_COMPONENT_NAME = "de.edgelord.saltyengine.coreComponents.shape";

    private final List<Component> components = new CopyOnWriteArrayList<>();

    private final SimplePhysicsComponent physicsComponent;
    private final RecalculateHitboxComponent recalculateHitboxComponent;
    private final Accelerator defaultAccelerator;
    private final ColliderComponent collider;
    private final ShapeComponent shapeComponent;

    /**
     * If this is set to true, this GameObject will not have a collision detection. Use this for
     * stationary objects like obstacles, houses and generally all kind of GameObjects that
     * aren't moving due to heavy performance improvements.
     * When ever the {@link #onCollision(CollisionEvent)} implementation is empty, set this to true
     * to improve the performance of the game a lot. Other GameObjects still collide with this one then,
     * but this one will never collide with others which is redundant when the {@link #onCollision(CollisionEvent)}
     * implementation is empty.
     * Please note that gravity and other forces won't take effect on stationary GameObjects
     */
    private boolean stationary = false;

    private String colliderComponent = DEFAULT_COLLIDER_COMPONENT_NAME;

    private Transform transform;
    private HashMap<String, String> properties = new HashMap<>();
    private File propertiesFile;
    private Hitbox hitbox;
    private float mass = 1f;

    public GameObject(final float xPos, final float yPos, final float width, final float height, final String tag) {
        super(tag);

        transform = new Transform(new Vector2f(xPos, yPos), new Dimensions(width, height));
        hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);

        physicsComponent = new SimplePhysicsComponent(this, GameObject.DEFAULT_PHYSICS_NAME);
        recalculateHitboxComponent = new RecalculateHitboxComponent(this, GameObject.DEFAULT_RECALCULATE_HITBOX_NAME);
        defaultAccelerator = new Accelerator(this, GameObject.DEFAULT_ACCELERATOR_NAME);
        collider = new ColliderComponent(this, DEFAULT_COLLIDER_COMPONENT_NAME, ColliderComponent.Type.HITBOX);
        shapeComponent = new ShapeComponent(this, DEFAULT_SHAPE_COMPONENT_NAME, ShapeComponent.Shape.RECT);
        shapeComponent.disableDrawing();

        components.add(physicsComponent);
        components.add(recalculateHitboxComponent);
        components.add(defaultAccelerator);
        components.add(collider);
        components.add(shapeComponent);
    }

    public GameObject(Transform transform, String tag) {
        this(transform.getX(), transform.getY(), transform.getWidth(), transform.getHeight(), tag);
    }

    public GameObject(Coordinates coordinates, Dimensions dimensions, String tag) {
        this(coordinates.getX(), coordinates.getY(), dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    public GameObject(Vector2f position, Dimensions dimensions, String tag) {
        this(position.getX(), position.getY(), dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    public GameObject(Vector2f position, float width, float height, String tag) {
        this(position.getX(), position.getY(), width, height, tag);
    }

    public GameObject(float xPos, float yPos, Dimensions dimensions, String tag) {
        this(xPos, yPos, dimensions.getWidth(), dimensions.getHeight(), tag);
    }

    @Override
    public abstract void initialize();

    @Override
    public abstract void onCollision(CollisionEvent event);

    @Override
    public abstract void onFixedTick();

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    /**
     * This method can be overridden but It's not necessary and you won't need this nearly always, so it's not abstract
     *
     * @param collisions the detected collisions of this run
     */
    public void onCollisionDetectionFinish(List<CollisionEvent> collisions) {

    }

    public void doCollisionDetection(final List<GameObject> gameObjects) {

        Directions collisionDirections = new Directions();
        List<CollisionEvent> collisions = new ArrayList<>();

        if (!stationary) {

            synchronized (concurrentBlock) {

                for (int i = 0; i < gameObjects.size(); i++) {
                    GameObject other = gameObjects.get(i);

                    if (other == this) {
                        continue;
                    }

                    if (!stationary) {
                        if (requestCollider().requestCollision(other)) {

                            Directions.appendRelation(this.getTransform(), other.getTransform(), collisionDirections);

                            // final CollisionEvent e = new CollisionEvent(other, collisionDirections);
                            final CollisionEvent eSelf = new CollisionEvent(other, collisionDirections);


                            collisions.add(eSelf);
                            // other.ON_COLLISION(e);
                            onCollision(eSelf);

                            components.forEach(component -> component.onCollision(eSelf));

                        }
                    }
                }
            }

            components.forEach(component -> component.onCollisionDetectionFinish(collisions));
            onCollisionDetectionFinish(collisions);
        }
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
    }

    @Override
    public void removeComponent(Component component) {
        components.remove(component);
    }

    @Override
    public void removeComponent(final String name) {
        components.removeIf(gameObjectComponent -> gameObjectComponent.getName().equals(name));
    }

    @Override
    public Component getComponent(String name) {

        for (Component component : getComponents()) {
            if (component.getName().equals(name)) {
                return component;
            }
        }

        return null;
    }

    public void initPropertiesFile(final File file) {

        propertiesFile = file;
    }

    public void addProperty(final String key, final String value) {

        getProperties().put(key, value);
    }

    public void changeProperty(final String key, final String newValue) {

        getProperties().replace(key, getProperties().get(key), newValue);
    }

    public String getLocalProperty(final String key) {

        return getProperties().get(key);
    }

    public int getPropertyAsInteger(final String key) {

        return Integer.valueOf(getLocalProperty(key));
    }

    public String readProperty(final String property) throws IOException {

        final DataReader propertiesReader = new DataReader(propertiesFile);

        return propertiesReader.getTagValue(property);
    }

    public void syncPropertiesToFile() {

    }

    public void readKeyProperties() throws IOException {

        final DataReader propertiesReader = new DataReader(propertiesFile);
        final Species keyProperties = propertiesReader.getSpecies("keyProperties");

        if (keyProperties.getContent().contains("location")) {

            final List<Integer> readenCoordinates = ValueToListConverter.convertToIntegerList(keyProperties, "location", ",");
            setPosition(new Vector2f(readenCoordinates.get(0), readenCoordinates.get(1)));
        }
    }

    public void setShapeDrawing(boolean shapeDrawing) {
        shapeComponent.setDrawing(shapeDrawing);
    }

    public void setShape(ShapeComponent.Shape shape) {
        shapeComponent.setShape(shape);
    }

    public ShapeComponent.Shape getShape() {
        return shapeComponent.getShape();
    }

    public ColliderComponent requestCollider() {

        return (ColliderComponent) getComponent(colliderComponent);
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(final HashMap<String, String> properties) {
        this.properties = properties;
    }

    public Coordinates getCoordinates() {
        return transform.getCoordinates();
    }

    public int getWidthAsInt() {
        return transform.getWidthAsInt();
    }

    public int getHeightAsInt() {
        return transform.getHeightAsInt();
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public SimpleHitbox getHitboxAsSimpleHitbox() {
        return (SimpleHitbox) getHitbox();
    }

    public void setHitbox(final Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public List<Component> getComponents() {
        return components;
    }

    public SimplePhysicsComponent getPhysics() {
        return physicsComponent;
    }

    public RecalculateHitboxComponent getRecalculateHitboxComponent() {
        return recalculateHitboxComponent;
    }

    public Accelerator getDefaultAccelerator() {
        return defaultAccelerator;
    }

    public ColliderComponent getCollider() {
        return collider;
    }

    public ShapeComponent getShapeComponent() {
        return shapeComponent;
    }

    public void setColliderComponent(String colliderComponent) {
        this.colliderComponent = colliderComponent;
    }

    public boolean isStationary() {
        return stationary;
    }

    public void setStationary(boolean stationary) {
        this.stationary = stationary;
    }

    public Vector2f getMiddle() {
        return getTransform().getMiddle();
    }

    public float getMass() {
        return mass;
    }

    public void setMass(final float mass) {
        this.mass = mass;
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public void removeFromCurrentScene() {
        SceneManager.getCurrentScene().removeGameObject(this);
    }
}
