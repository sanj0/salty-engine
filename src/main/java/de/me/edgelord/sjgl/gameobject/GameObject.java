/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.gameobject;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToListConverter;
import de.me.edgelord.sjgl.gameobject.components.RecalculateHitboxComponent;
import de.me.edgelord.sjgl.gameobject.components.SimplePhysicsComponent;
import de.me.edgelord.sjgl.hitbox.SimpleHitbox;
import de.me.edgelord.sjgl.location.Coordinates;
import de.me.edgelord.sjgl.location.Vector2f;
import de.me.edgelord.sjgl.utils.Directions;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public abstract class GameObject {

    private Coordinates coordinates;
    private Vector2f position = new Vector2f(0, 0);
    private String tag;
    private float friction = 0.2f;
    private float gravity = 0.981f;
    private float airFriction = 0.1f;
    private int width, height;
    private HashMap<String, String> properties = new HashMap<>();
    private List<GameObject> touchingGameObjects = new LinkedList<>();
    private List<GameObjectComponent> components = new LinkedList<>();
    private File propertiesFile;
    private SimpleHitbox hitbox;

    private SimplePhysicsComponent physicsComponent;
    private RecalculateHitboxComponent recalculateHitboxComponent;

    public GameObject(Coordinates coordinates, int width, int height, String tag) {
        this.coordinates = coordinates;
        this.position.parseVector2f(coordinates);
        this.width = width;
        this.height = height;
        this.hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);
        this.tag = tag;

        physicsComponent = new SimplePhysicsComponent(this, "default_physics", 0, airFriction);
        recalculateHitboxComponent = new RecalculateHitboxComponent(this, "default_recalculateHitbox");

        components.add(physicsComponent);
        components.add(recalculateHitboxComponent);
    }

    public abstract void initialize();

    public abstract void onCollision(GameObject other);

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

        setTouchingGameObjects(new LinkedList<>());

        for (GameObject other : gameObjects) {

            if (other == this){
                break;
            }

            if (this.getHitbox().collides(other)) {

                this.onCollision(other);
                other.onCollision(this);
                getTouchingGameObjects().add(other);
            }

        }
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

    public void move(float delta, Directions.BasicDirection direction){

        if (direction == Directions.BasicDirection.x){
            position.setX(getX() + delta);
        } else {
            position.setY(getY() + delta);
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

    public float getFriction() {
        return friction;
    }

    public void setFriction(float friction) {
        this.friction = friction;
    }

    public float getAirFriction() {
        return airFriction;
    }

    public void setAirFriction(float airFriction) {
        this.airFriction = airFriction;
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

    public List<GameObjectComponent> getComponents() {
        return components;
    }

    public SimplePhysicsComponent getPhysics() {
        return physicsComponent;
    }

    public RecalculateHitboxComponent getRecalculateHitboxComponent() {
        return recalculateHitboxComponent;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
