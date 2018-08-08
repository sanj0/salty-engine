/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.components.Accelerator;
import de.edgelord.sjgl.gameobject.components.RecalculateHitboxComponent;
import de.edgelord.sjgl.gameobject.components.RecalculateMiddleComponent;
import de.edgelord.sjgl.gameobject.components.SimplePhysicsComponent;
import de.edgelord.sjgl.hitbox.SimpleHitbox;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.location.Vector2f;
import de.edgelord.sjgl.utils.Directions;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToListConverter;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class GameObject {

    private Coordinates coordinates;
    private Coordinates middle;
    private Vector2f position = new Vector2f(0, 0);
    private String tag;
    private int width, height;
    private HashMap<String, String> properties = new HashMap<>();
    private final List<GameObject> touchingGameObjects = new LinkedList<>();
    private List<GameObjectComponent> components = new LinkedList<>();
    private File propertiesFile;
    private SimpleHitbox hitbox;

    private float mass = 1f;

    private SimplePhysicsComponent physicsComponent;
    private RecalculateHitboxComponent recalculateHitboxComponent;
    private RecalculateMiddleComponent recalculateMiddleComponent;
    private Accelerator defaultAccelerator;

    public static final String DEFAULT_PHYSICS_NAME = "de.edgelord.sjgl.coreComponents.physics";
    public static final String DEFAULT_RECALCULATE_HITBOX_NAME = "de.edgelord.sjgl.coreComponents.recalculateHitbox";
    public static final String DEFAULT_RECALCULATE_MIDDLE_NAME = "de.edgelord.sjgl.coreComponents.recalculateMiddle";
    public static final String DEFAULT_ACCELERATOR_NAME = "de.edgelord.sjgl.coreComponents.accelerator";

    public GameObject(Coordinates coordinates, int width, int height, String tag) {
        this.coordinates = coordinates;
        this.position.parseVector2f(coordinates);
        this.width = width;
        this.height = height;
        this.hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);
        this.tag = tag;

        this.middle = new Coordinates(getCoordinates().getX() + (getWidth() / 2), getCoordinates().getY() + (getHeight() / 2));

        physicsComponent = new SimplePhysicsComponent(this, DEFAULT_PHYSICS_NAME);
        recalculateHitboxComponent = new RecalculateHitboxComponent(this, DEFAULT_RECALCULATE_HITBOX_NAME);
        recalculateMiddleComponent = new RecalculateMiddleComponent(this, DEFAULT_RECALCULATE_MIDDLE_NAME);
        defaultAccelerator = new Accelerator(this, DEFAULT_ACCELERATOR_NAME);

        components.add(physicsComponent);
        components.add(recalculateHitboxComponent);
        components.add(recalculateMiddleComponent);
        components.add(defaultAccelerator);
    }

    public abstract void initialize();

    public abstract void onCollision(CollisionEvent event);

    public abstract void onFixedTick();

    public abstract void onTick();

    public abstract void draw(Graphics2D graphics);

    public void addComponent(GameObjectComponent gameObjectComponent) {

        this.components.add(gameObjectComponent);
    }

    public void doComponentOnFixedTick() {

        for (GameObjectComponent gameObjectComponent : components) {

            if (gameObjectComponent.isEnabled()) {
                gameObjectComponent.onFixedTick();
            }
        }
    }

    public void doComponentDrawing(Graphics2D graphics) {

        for (GameObjectComponent gameObjectComponent : components) {

            if (gameObjectComponent.isEnabled()) {
                gameObjectComponent.draw(graphics);
            }
        }
    }

    public void doCollisionDetection(List<GameObject> gameObjects) {

        touchingGameObjects.clear();

        for (GameObject other : gameObjects) {

            if (other == this){
                break;
            }

            if (this.getHitbox().collides(other)) {

                CollisionEvent e = new CollisionEvent(this);
                CollisionEvent eSelf = new CollisionEvent(other);

                addCollisionDirections(e, other, false);
                addCollisionDirections(eSelf, this, true);

                other.onCollision(e);
                this.onCollision(eSelf);

                getTouchingGameObjects().add(other);

                for (GameObjectComponent component : getComponents()) {
                    component.onCollision(e);
                }

                for (GameObjectComponent component : other.getComponents()) {
                    component.onCollision(eSelf);
                }
            }

        }
    }

    private void addCollisionDirections(CollisionEvent e, GameObject other, boolean mirror) {

        List<Directions.Direction> directionsList = new LinkedList<>();
        Directions.Direction[] collisionDirections;

        if (e.getRoot().getMiddle().isRight(other.getMiddle())) {
            directionsList.add(Directions.Direction.right);
        } else if (e.getRoot().getMiddle().isLeft(other.getMiddle())) {
            directionsList.add(Directions.Direction.left);
        }

        if (e.getRoot().getMiddle().isAbove(other.getMiddle())) {
            directionsList.add(Directions.Direction.up);
        } else if (e.getRoot().getMiddle().isBelow(other.getMiddle())) {
            directionsList.add(Directions.Direction.down);
        }

        collisionDirections = new Directions.Direction[directionsList.size()];
        int currentIndex = 0;

        for (Directions.Direction d : directionsList) {
            if (mirror) {
                collisionDirections[currentIndex] = Directions.mirrorDirection(d);
            } else {
                collisionDirections[currentIndex] = d;
            }
        }

        e.setCollisionDirections(collisionDirections);
    }

    public void removeComponent(String name){
        for (int i = 0; i < components.size(); i++) {
            if (components.get(i).getName().equals(name)){
                components.remove(i);
            }
        }
    }

    public void initPropertiesFile(File file) {

        this.propertiesFile = file;
    }

    public void addProperty(String key, String value) {

        this.getProperties().put(key, value);
    }

    public void changeProperty(String key, String newValue) {

        this.getProperties().replace(key, this.getProperties().get(key), newValue);
    }

    public String getLocalProperty(String key) {

        return this.getProperties().get(key);
    }

    public int getPropertyAsInteger(String key) {

        return Integer.valueOf(getLocalProperty(key));
    }

    public String readProperty(String property) throws IOException {

        DataReader propertiesReader = new DataReader(propertiesFile);

        return propertiesReader.getTagValue(property);
    }

    public void syncPropertiesToFile() {

    }

    public void readKeyProperties() throws IOException {

        DataReader propertiesReader = new DataReader(propertiesFile);
        Species keyProperties = propertiesReader.getSpecies("keyProperties");

        if (keyProperties.getContent().contains("location")) {

            List<Integer> readenCoordinates = ValueToListConverter.convertToIntegerList(keyProperties, "location", ",");
            this.setCoordinates(new Coordinates(readenCoordinates.get(0), readenCoordinates.get(1)));
        }
    }

    public void basicMove(float delta, Directions.BasicDirection direction) {

        if (direction == Directions.BasicDirection.x){
            position.setX(getX() + delta);
        } else {
            position.setY(getY() + delta);
        }
    }

    public void move(float delta, Directions.Direction direction) {

        // Check if delta is negative and if so, mirror its value
        if (delta < 0f) {
            delta = delta * (-1);
        }

        switch (direction) {

            case right:
                basicMove(delta, Directions.BasicDirection.x);
                break;
            case left:
                basicMove(-delta, Directions.BasicDirection.x);
                break;
            case up:
                basicMove(-delta, Directions.BasicDirection.y);
                break;
            case down:
                basicMove(delta, Directions.BasicDirection.y);
                break;
        }
    }

    public void moveY(float delta){
        position.setY(getY() + delta);
    }

    public void moveX(float delta){
        position.setX(getX() + delta);
    }

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public void updateCoordinates() {

        coordinates.parseCoordinates(position);
    }

    public Coordinates getCoordinates() {
        updateCoordinates();

        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public SimpleHitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(SimpleHitbox hitbox) {
        this.hitbox = hitbox;
    }

    public Vector2f getPosition() {
        return position;
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public float getX() {

        return getPosition().getX();
    }

    public void setX(float x) {

        getPosition().setX(x);
    }

    public float getY() {

        return getPosition().getY();
    }

    public void setY(float y) {

        getPosition().setY(y);
    }

    public List<GameObject> getTouchingGameObjects() {
        return touchingGameObjects;
    }

    public List<GameObjectComponent> getComponents() {
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Coordinates getMiddle() {
        return middle;
    }

    public void setMiddle(Coordinates middle) {
        this.middle = middle;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }
}
