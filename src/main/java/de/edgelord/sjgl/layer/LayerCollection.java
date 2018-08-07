/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.layer;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.utils.Directions;

import java.awt.*;
import java.util.LinkedList;

public class LayerCollection {

    private LinkedList<Layer> layers = new LinkedList<>();

    public LayerCollection() {


    }

    public void addLayer(Layer layer) {

        layers.add(layer);
    }

    public void onFixedTick() {

        for (Layer layer : layers) {

            for (GameObject gameObject : layer.getGameObjects()) {
                gameObject.onFixedTick();
            }
        }
    }

    public void onTick() {

        for (Layer layer : layers) {

            for (GameObject gameObject : layer.getGameObjects()) {
                gameObject.onTick();
            }
        }
    }

    public void initGameObjects() {

        for (Layer layer : layers) {

            for (GameObject gameObject : layer.getGameObjects()) {
                gameObject.initialize();
            }
        }
    }

    public void moveCamera(Directions.BasicDirection direction, float delta) {

        if (direction == Directions.BasicDirection.y) {
            for (Layer layer : layers) {
                for (GameObject gameObject : layer.getGameObjects()) {
                    gameObject.moveY(delta * layer.getSpeed());
                }
            }
        } else {
            for (Layer layer : layers) {
                for (GameObject gameObject : layer.getGameObjects()) {
                    gameObject.moveX(delta * layer.getSpeed());
                }
            }
        }
    }

    public void draw(Graphics2D graphics) {

        for (Layer layer : layers)
            layer.draw(graphics);
    }

    public void resetPositionOfAllLayers() {

        for (Layer layer : layers) {

            layer.resetPosition();
        }
    }
}
