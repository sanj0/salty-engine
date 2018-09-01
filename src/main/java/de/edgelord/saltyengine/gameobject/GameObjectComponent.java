/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;

/**
 * GameObjectComponents are a way of modifying what happens every fixed tick to a GameObject, or how to draw a GameObject
 * The methods which can be used for that are #onFixedTick() #draw(Graphics2D) and
 * #onCollision(GameObject) (for example used for Physics)
 */
public abstract class GameObjectComponent {

    public static final String PUSH_OUT_ON_COLLISION = "de.edgelord.saltyengine.components.pushOutOnCollision";
    public static final String SIMPLE_RENDER_COMPONENT = "de.edgelord.saltyengine.components.simpleRenderComponent";
    public static final String ACCELERATOR_COMPONENT = "de.edgelord.saltyengine.components.accelerator";
    public static final String TECHNICAL_DRAW_COMPONENT = "de.edgelord.saltyengine.components.technicalDrawComponent";
    public static final String CORE_COMPONENT = "de.edgelord.saltyengine.components.core";
    public static final String PHYSICS_COMPONENT = "de.edgelord.saltyengine.components.physics";
    public static final String SGS_COMPONENT = "de.edgelord.saltyengine.components.sgs";
    public static final String GFX_COMPONENT = "de.edgelord.saltyengine.components.gfx";
    public static final String ANIMATION_COMPONENT = "de.edgelord.saltyengine.components.animation";
    public static final String TIMING_COMPONENT = "de.edgelord.saltyengine.components.timing";

    private GameObject parent;
    private String name;
    private boolean enabled = true;
    private String tag;

    public GameObjectComponent(GameObject parent, String name, String tag) {
        this.parent = parent;
        this.name = name;
        this.tag = tag;
    }

    public GameObject getParent() {
        return parent;
    }

    public abstract void onFixedTick();

    public abstract void draw(SaltyGraphics saltyGraphics);

    public abstract void onCollision(CollisionEvent e);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void remove() {
        getParent().removeComponent(getName());
    }

    public boolean isEnabled() {
        return enabled;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
