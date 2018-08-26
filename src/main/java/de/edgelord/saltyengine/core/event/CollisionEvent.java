/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.core.event;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.components.SimplePhysicsComponent;
import de.edgelord.saltyengine.utils.Directions;

/**
 * This class is used for handling collisions between GameObjects. It has all necessary parameters for physics etc.
 */
public class CollisionEvent {

    private final GameObject root;
    private final SimplePhysicsComponent rootPhysics;
    private Directions collisionDirections;
    private final float rootMass;

    public CollisionEvent(final GameObject root) {
        this(root, null);
    }

    public CollisionEvent(final GameObject root, final Directions collisionDirections) {

        this.root = root;
        rootPhysics = root.getPhysics();
        this.collisionDirections = collisionDirections;
        rootMass = root.getMass();
    }

    public GameObject getRoot() {
        return root;
    }

    public SimplePhysicsComponent getRootPhysics() {
        return rootPhysics;
    }

    public Directions getCollisionDirections() {
        return collisionDirections;
    }

    public void setCollisionDirections(final Directions collisionDirections) {
        this.collisionDirections = collisionDirections;
    }

    public float getRootMass() {
        return rootMass;
    }
}
