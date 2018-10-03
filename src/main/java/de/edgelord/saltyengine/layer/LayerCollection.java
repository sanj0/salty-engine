/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.layer;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.Directions;

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

    public void initGameObjects() {

        for (Layer layer : layers) {

            for (GameObject gameObject : layer.getGameObjects()) {
                gameObject.initialize();
            }
        }
    }

    public void draw(SaltyGraphics saltyGraphics) {

        for (Layer layer : layers)
            layer.draw(saltyGraphics);
    }
}
