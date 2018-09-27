/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.interfaces.*;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.components.*;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.hitbox.Hitbox;
import de.edgelord.saltyengine.hitbox.SimpleHitbox;
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

public abstract class GameObject extends ComponentParent implements Drawable, FixedTickRoutine, CollideAble, InitializeAble {

    public static final String DEFAULT_PHYSICS_NAME = "de.edgelord.saltyengine.coreComponents.physics";
    public static final String DEFAULT_RECALCULATE_HITBOX_NAME = "de.edgelord.saltyengine.coreComponents.recalculateHitbox";
    public static final String DEFAULT_RECALCULATE_MIDDLE_NAME = "de.edgelord.saltyengine.coreComponents.recalculateMiddle";
    public static final String DEFAULT_ACCELERATOR_NAME = "de.edgelord.saltyengine.coreComponents.accelerator";
    public static final String DEFAULT_COLLIDER_COMPONENT_NAME = "de.edgelord.saltyengine.coreComponents.collider";
    public static final String DEFAULT_SHAPE_COMPONENT_NAME = "de.edgelord.saltyengine.coreComponents.shape";

    private final List<Component> components = new CopyOnWriteArrayList<>();

    private final SimplePhysicsComponent physicsComponent;
    private final RecalculateHitboxComponent recalculateHitboxComponent;
    private final RecalculateMiddleComponent recalculateMiddleComponent;
    private final Accelerator defaultAccelerator;
    private final ColliderComponent collider;
    private final ShapeComponent shapeComponent;

    private String colliderComponent = DEFAULT_COLLIDER_COMPONENT_NAME;

    private Transform transform;
    private Vector2f middle;
    private String tag;
    private HashMap<String, String> properties = new HashMap<>();
    private File propertiesFile;
    private Hitbox hitbox;
    private float mass = 1f;

    public GameObject(final float xPos, final float yPos, final float width, final float height, final String tag) {

        transform = new Transform(new Vector2f(xPos, yPos), new Dimensions(width, height));
        hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);
        this.tag = tag;

        middle = new Vector2f(getCoordinates().getX() + getWidth() / 2, getCoordinates().getY() + getHeight() / 2);

        physicsComponent = new SimplePhysicsComponent(this, GameObject.DEFAULT_PHYSICS_NAME);
        recalculateHitboxComponent = new RecalculateHitboxComponent(this, GameObject.DEFAULT_RECALCULATE_HITBOX_NAME);
        recalculateMiddleComponent = new RecalculateMiddleComponent(this, GameObject.DEFAULT_RECALCULATE_MIDDLE_NAME);
        defaultAccelerator = new Accelerator(this, GameObject.DEFAULT_ACCELERATOR_NAME);
        collider = new ColliderComponent(this, DEFAULT_COLLIDER_COMPONENT_NAME, ColliderComponent.Type.HITBOX);
        shapeComponent = new ShapeComponent(this, DEFAULT_SHAPE_COMPONENT_NAME, ShapeComponent.Shape.RECT);
        shapeComponent.disableDrawing();

        components.add(physicsComponent);
        components.add(recalculateHitboxComponent);
        components.add(recalculateMiddleComponent);
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

        for (final GameObject other : gameObjects) {

            if (other == this) {
                continue;
            }

            if (requestCollider().requestCollision(other)) {

                Directions.appendGameObjectRelation(this, other, collisionDirections);

                // final CollisionEvent e = new CollisionEvent(other, collisionDirections);
                final CollisionEvent eSelf = new CollisionEvent(other, collisionDirections);


                collisions.add(eSelf);
                // other.ON_COLLISION(e);
                onCollision(eSelf);

                components.forEach(component -> component.onCollision(eSelf));

                /*
                for (final GameObjectComponent component : other.getComponents()) {
                    if (!component.getTag().equals(GameObjectComponent.PUSH_OUT_ON_COLLISION)) {
                        component.ON_COLLISION(e);
                    }
                }
                */
            }
        }

        components.forEach(component -> component.onCollisionDetectionFinish(collisions));
        onCollisionDetectionFinish(collisions);
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

    public String getTag() {
        return tag;
    }

    public void setTag(final String tag) {
        this.tag = tag;
    }

    public Vector2f getMiddle() {
        return middle;
    }

    /**
     * WARNING!! THIS METHOD WON'T PLACE THE GAMEOBJECT BY ITS MIDDLE BUT SET THE MIDDLE WITHOUT CHANGING THE POSITION!
     * Usually, you should not call this method manually!
     *
     * @param middle the new middle
     */
    public void setMiddle(final Vector2f middle) {
        this.middle = middle;
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
}
