/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.layer;

import de.edgelord.saltyengine.scene.Scene;

public class Layer extends Scene {

    private float speed;

    public Layer(float speed) {

        this.speed = speed;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}