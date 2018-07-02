/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.layer;

import de.me.edgelord.sjgl.scene.Scene;

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
