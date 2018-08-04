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
    private float rootVelocity;
    private float rootMass;
    private float kineticEnergy;

    public CollisionEvent(GameObject root, Directions.Direction... collisionDirections) {

        this.root = root;
        this.rootPhysics = root.getPhysics();
        this.collisionDirections = collisionDirections;
        this.rootVelocity = root.getVelocity();
        this.rootMass = root.getMass();
        this.kineticEnergy = (rootMass / 2) * (rootVelocity * rootVelocity);
    }

    public GameObject getRoot() {
        return root;
    }

    public SimplePhysicsComponent getRootPhysics() {
        return rootPhysics;
    }

    public Directions.Direction[] getCollisionDirections() {
        return collisionDirections;
    }

    public float getRootVelocity() {
        return rootVelocity;
    }

    public float getRootMass() {
        return rootMass;
    }

    public float getKineticEnergy() {
        return kineticEnergy;
    }
}
