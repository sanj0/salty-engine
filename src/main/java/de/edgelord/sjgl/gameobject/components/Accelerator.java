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

public class Accelerator extends GameObjectComponent {

    private long ticks;
    private long duration;
    private String forceName;

    public Accelerator(GameObject parent, String name) {
        super(parent, name);
    }

    @Override
    public void onFixedTick() {

        ticks++;

        if (ticks == duration) {
            getParent().getPhysics().getForce(forceName).setAcceleration(0f);
            ticks = 0;
            duration = 0;
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void accelerate(String forceName, float acceleration, long duration) {
        getParent().getPhysics().getForce(forceName).setAcceleration(acceleration);

        this.forceName = forceName;
        this.duration = duration;
    }
}
