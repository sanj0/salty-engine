/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.gameobject;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToListConverter;
import de.me.edgelord.sjgl.StaticVars.Quadrant;
import de.me.edgelord.sjgl.StaticVars.StaticSystem;
import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.hitbox.SimpleHitbox;
import de.me.edgelord.sjgl.location.Coordinates;
import de.me.edgelord.sjgl.location.Vector2f;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/*
    TODO: Everything having to do with zooming (e.g. zoom() and resetZoom()) and Properties + real position (subpixel position) with Vector2f
 */

public abstract class GameObject {

    private Coordinates coordinates;
    private Vector2f vector2f = new Vector2f(0, 0);
    private Coordinates middle;
    private int width, height;
    private int startWidth, startHeight;
    private Quadrant.Quadrants quadrant;
    private HashMap<String, String> properties = new HashMap<>();
    private File propertiesFile;
    private SimpleHitbox hitbox;

    public GameObject(Coordinates coordinates, int width, int height) {
        this.coordinates = coordinates;
        this.vector2f.parseVector2f(coordinates);
        this.width = width;
        this.startWidth = width;
        this.height = height;
        this.startHeight = height;
        this.hitbox = new SimpleHitbox(this, getWidth(), getHeight(), 0, 0);


        middle = new Coordinates(getX() + (width / 2), getY() + (height / 2));
    }

    public abstract void initialize();

    public abstract void onCollision(GameObject other);

    public abstract void onFixedTick();

    public abstract void onTick();

    public abstract void draw(Graphics2D graphics);

    public void initPropertiesFile(String relativePath) {

        this.propertiesFile = StaticSystem.getOuterResource().getFile(relativePath);
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

    public Coordinates getCoordinates() {
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

    public void zoom(int zoom, DisplayManager displayManager) {

        int zoomX = zoom;
        int zoomY = zoom;

        calculateQuadrant(displayManager);

        this.setWidth(getWidth() + zoomX);
        this.setHeight(getHeight() + zoomY);

        if (quadrant == Quadrant.Quadrants.topLeft) {

            setX(getX() - (zoomX / 2));
            setY(getY() - (zoomY / 2));

            return;
        }

        if (quadrant == Quadrant.Quadrants.topRight) {

            setX(getX() + (zoomX / 2));
            setY(getY() - (zoomY / 2));

            return;
        }

        if (quadrant == Quadrant.Quadrants.bottomLeft) {

            setX(getX() - (zoomX / 2));
            setY(getY() + (zoomY / 2));

            return;
        }

        if (quadrant == Quadrant.Quadrants.bottomRight) {

            setX(getX() + (zoomX / 2));
            setY(getY() + (zoomY / 2));

            return;
        }

        return;
    }

    public Vector2f getVector2f() {
        return vector2f;
    }

    public void setVector2f(Vector2f vector2f) {
        this.vector2f = vector2f;
    }

    public int getX() {

        return getCoordinates().getX();
    }

    public void setX(int x) {

        getCoordinates().setX(x);
    }

    public int getY() {

        return getCoordinates().getY();
    }

    public void setY(int y) {

        getCoordinates().setY(y);
    }

    public Coordinates getMiddle() {
        return middle;
    }

    public Quadrant.Quadrants getQuadrant() {
        return quadrant;
    }

    public void calculateQuadrant(DisplayManager displayManager) {

        recalculateMiddle();

        if (this.getMiddle().getX() < (displayManager.getWidth() / 2)) {

            if (this.getMiddle().getY() < (displayManager.getHeight() / 2)) {
                quadrant = Quadrant.Quadrants.topLeft;
            } else {
                quadrant = Quadrant.Quadrants.bottomLeft;
            }

        } else {

            if (this.getMiddle().getY() < (displayManager.getHeight() / 2)) {
                quadrant = Quadrant.Quadrants.topRight;
            } else {
                quadrant = Quadrant.Quadrants.bottomRight;
            }
        }
    }

    public void recalculateMiddle() {

        middle.setX(getCoordinates().getX() + (width / 2));
        middle.setY(getCoordinates().getY() + (height / 2));
    }
}
