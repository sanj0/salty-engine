/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.core.event;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.components.SimplePhysicsComponent;
import de.edgelord.sjgl.utils.Directions;

/**
 * This class is used for handling collisions between GameObjects. It has all necessary parameters for physics etc.
 */
public class CollisionEvent {

    private GameObject root;
    private SimplePhysicsComponent rootPhysics;
    private Directions.Direction collisionDirection;
    private float rootMass;

    public CollisionEvent(final GameObject root) {
        this(root, null);
    }

    public CollisionEvent(final GameObject root, final Directions.Direction collisionDirection) {

        this.root = root;
        this.rootPhysics = root.getPhysics();
        this.collisionDirection = collisionDirection;
        this.rootMass = root.getMass();
    }

    public GameObject getRoot() {
        return root;
    }

    public SimplePhysicsComponent getRootPhysics() {
        return rootPhysics;
    }

    public Directions.Direction getCollisionDirection() {
        return collisionDirection;
    }

    public void setCollisionDirection(final Directions.Direction collisionDirection) {
        this.collisionDirection = collisionDirection;
    }

    public float getRootMass() {
        return rootMass;
    }
}
