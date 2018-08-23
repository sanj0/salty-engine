/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;

import java.awt.*;

public class FixedRate extends GameObjectComponent {

    private int gate;
    private int ticks = 0;

    private boolean now = false;

    public FixedRate(GameObject parent, String name, int gate) {
        super(parent, name, TIMING_COMPONENT);
        this.gate = gate;
    }

    public boolean now() {
        return now;
    }

    @Override
    public void onFixedTick() {

        if (ticks == gate) {
            now = true;
            ticks = 0;
        } else {
            now = false;
            ticks++;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
