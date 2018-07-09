/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.layer;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.utils.Directions;

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

    public void moveCamera(Directions.BasicDirection direction, int delta) {

        if (delta == 1 || delta == -1)
            System.err.println("sjgl 0.2 > LayerCollection > \"INFO: The camera movement of 1 or -1 will effect ALL Layers the same way!\"");

        if (direction == Directions.BasicDirection.y) {
            for (Layer layer : layers) {
                for (GameObject gameObject : layer.getGameObjects()) {
                    gameObject.getCoordinates().changeY((int) (delta * layer.getSpeed()));
                }
            }
        } else {
            for (Layer layer : layers) {
                for (GameObject gameObject : layer.getGameObjects()) {
                    gameObject.getCoordinates().changeX((int) (delta * layer.getSpeed()));
                }
            }
        }
    }

    public void draw(Graphics2D graphics) {

        for (Layer layer : layers)
            layer.draw(graphics);
    }

    public void resetZoomOfAllLayers() {

        for (Layer layer : layers) {

            layer.resetZoom();
        }
    }

    public void resetPositionOfAllLayers() {

        for (Layer layer : layers) {

            layer.resetPosition();
        }
    }
}
