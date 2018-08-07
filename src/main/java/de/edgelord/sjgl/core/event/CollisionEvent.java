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
 * This class is used for handling collisions between GameObjects. It has all necessary fields for physics etc.
 */
public class CollisionEvent {

    private GameObject root;
    private SimplePhysicsComponent rootPhysics;
    private Directions.Direction[] collisionDirections;
    private float rootMass;

    public CollisionEvent(GameObject root, Directions.Direction... collisionDirections) {

        this.root = root;
        this.rootPhysics = root.getPhysics();
        this.collisionDirections = collisionDirections;
        this.rootMass = root.getMass();
    }

    public GameObject getRoot() {
        return root;
    }

    public SimplePhysicsComponent getRootPhysics() {
        return rootPhysics;
    }

    public void setCollisionDirections(Directions.Direction[] collisionDirections) {
        this.collisionDirections = collisionDirections;
    }

    public Directions.Direction[] getCollisionDirections() {
        return collisionDirections;
    }

    public float getRootMass() {
        return rootMass;
    }
}
