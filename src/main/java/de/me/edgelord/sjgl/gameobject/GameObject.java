/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.gameobject;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToListConverter;
import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.gameobjectComponent.Component;
import de.me.edgelord.sjgl.gameobjectComponent.RecalculateHitbox;
import de.me.edgelord.sjgl.gameobjectComponent.SimplePhysics;
import de.me.edgelord.sjgl.hitbox.SimpleHitbox;
import de.me.edgelord.sjgl.location.Coordinates;
import de.me.edgelord.sjgl.location.Vector2f;
import de.me.edgelord.sjgl.utils.Quadrant;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
    TODO: Properties
 */

public abstract class GameObject {

    private Coordinates coordinates;
    private Vector2f vector2f = new Vector2f(0, 0);
    private float friction = 0.2f;
    private float gravity = 0.981f;
    private float airFriction = 0.1f;
    private int width, height;
    private int startWidth, startHeight;
    private Quadrant.Quadrants quadrant;
    private HashMap<String, String> properties = new HashMap<>();
    private List<GameObject> touchingGameObjects = new LinkedList<>();
    private List<Component> components = new LinkedList<>();
    private File propertiesFile;
    private SimpleHitbox hitbox;

    private SimplePhysics physics;
    private RecalculateHitbox recalculateHitbox;

    public GameObject(Coordinates coordinates, int width, int height) {
        this.coordinates = coordinates;
        this.vector2f.parseVector2f(coordinates);
        this.width = width;
        this.startWidth = width;
        this.height = height;
        this.startHeight = height;
        this.hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);

        physics = new SimplePhysics(this, 0, airFriction);
        recalculateHitbox = new RecalculateHitbox(this);

        components.add(physics);
        components.add(recalculateHitbox);
    }

    public abstract void initialize();

    public abstract void onCollision(GameObject other);

    public abstract void onFixedTick();

    public abstract void onTick();

    public abstract void draw(Graphics2D graphics);

    public void addComponent(Component component) {

        this.components.add(component);
    }

    public void doComponentOnFixedTick() {

        for (Component component : components) {

            if (component.isEnabled()) {
                component.onFixedTick();
            }
        }
    }

    public void doComponentDrawing(Graphics2D graphics) {

        for (Component component : components) {

            if (component.isEnabled()) {
                component.draw(graphics);
            }
        }
    }

    public void doCollisionDetection(List<GameObject> gameObjects) {

        setTouchingGameObjects(new LinkedList<>());

        for (GameObject other : gameObjects) {

            if (this.getHitbox().collides(other)) {

                onCollision(other);
                getTouchingGameObjects().add(other);
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

    public HashMap<String, String> getProperties() {
        return properties;
    }

    public void setProperties(HashMap<String, String> properties) {
        this.properties = properties;
    }

    public void updateCoordinates() {

        coordinates.parseCoordinates(vector2f);
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

    public void resetZoom() {

        setWidth(startWidth);
        setHeight(startHeight);
    }

    public Vector2f getVector2f() {
        return vector2f;
    }

    public void setVector2f(Vector2f vector2f) {
        this.vector2f = vector2f;
    }

    public float getX() {

        return getVector2f().getX();
    }

    public void setX(float x) {

        getVector2f().setX(x);
    }

    public float getY() {

        return getVector2f().getY();
    }

    public void setY(float y) {

        getVector2f().setY(y);
    }

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getGravity() {
        return gravity;
    }

    public void setGravity(float gravity) {
        this.gravity = gravity;
    }

    public List<GameObject> getTouchingGameObjects() {
        return touchingGameObjects;
    }

    public void setTouchingGameObjects(List<GameObject> touchingGameObjects) {
        this.touchingGameObjects = touchingGameObjects;
    }

    public Quadrant.Quadrants getQuadrant() {
        return quadrant;
    }

    public List<Component> getComponents() {
        return components;
    }

    public SimplePhysics getPhysics() {
        return physics;
    }

    public RecalculateHitbox getRecalculateHitbox() {
        return recalculateHitbox;
    }
}
