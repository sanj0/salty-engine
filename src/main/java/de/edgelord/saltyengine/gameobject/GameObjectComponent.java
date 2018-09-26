/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.core.Component;

/**
 * GameObjectComponents are a way of modifying what happens every fixed tick to a GameObject, or how to DRAW a GameObject
 * The methods which can be used for that are #ON_FIXED_TICK() #DRAW(Graphics2D) and
 * #ON_COLLISION(GameObject) (for example used for Physics)
 */
public abstract class GameObjectComponent extends Component<GameObject> {

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
    public static final String COLLIDER_COMPONENT = "de.edgelord.saltyengine.components.collider";

    public GameObjectComponent(GameObject parent, String name, String tag) {
        super(parent, name, tag);
    }
}
