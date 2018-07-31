package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.core.physics.SimpleForce;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;
import de.edgelord.sjgl.utils.Directions;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;

public class SimplePhysicsComponent extends GameObjectComponent {

    private List<SimpleForce> forces = new LinkedList<>();
    private float gravityVelocity = DEFAULT_GRAVITY_VELOCITY;
    private float gravityAcceleration = DEFAULT_GRAVITY_ACCELERATION;
    private boolean gravityHasInfinitePower = DEFAULT_GRAVITY_HAS_INFINITE_POWER;
    private boolean affectByGravity = true;

    // This are the standard values (will maybe by changed after various testing), they are that low because it affects
    // parent every fixed tick
    public static final float DEFAULT_GRAVITY_VELOCITY = 0.5f;
    public static final float DEFAULT_GRAVITY_ACCELERATION = 100f;
    public static final boolean DEFAULT_GRAVITY_HAS_INFINITE_POWER = true;
    public static final float DEFAULT_AIRFRICTION = 0.1f;
    public static final String DEFAULT_GRAVITY_NAME = "de.edgelord.sjgl.core.physics.default_gravityForce";

    public SimplePhysicsComponent(GameObject parent, String name, float gravityVelocity) {
        super(parent, name);

        this.gravityVelocity = gravityVelocity;
        addGravityForce();
    }

    public SimplePhysicsComponent(GameObject parent, String name) {
        super(parent, name);

        addGravityForce();
    }

    @Override
    public void onFixedTick() {

        // Currently, first the forces and then the gravity affect the parent, may change this after various testing

        for (SimpleForce force : forces) {
            force.nextStep();
        }
    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(GameObject other) {

    }

    private void addGravityForce() {

        addForce(new SimpleForce(getParent(), DEFAULT_GRAVITY_NAME, gravityVelocity, gravityAcceleration, Directions.Direction.down));
    }

    public void addForce(SimpleForce force) {

        forces.add(force);
    }

    public SimpleForce getForce(String name) {
        for (SimpleForce force : forces) {
            if (force.getName().equals(name)) {
                return force;
            }
        }

        return null;
    }

    public void removeForce(String name) {

        int indexToRemove = -1;

        for (int index = 0; index < forces.size() - 1; index++) {

            if (forces.get(index).getName().equals(name)) {
                indexToRemove = index;
                break;
            }
        }

        if (indexToRemove != -1) {
            forces.remove(indexToRemove);
        }
    }

    public List<SimpleForce> getForces() {
        return forces;
    }

    public void setForces(List<SimpleForce> forces) {
        this.forces = forces;
    }

    public float getGravityVelocity() {
        return gravityVelocity;
    }

    public void setGravityVelocity(float gravityVelocity) {
        this.gravityVelocity = gravityVelocity;
        getForce(DEFAULT_GRAVITY_NAME).setTargetVelocity(gravityVelocity);
    }

    public float getGravityAcceleration() {
        return gravityAcceleration;
    }

    public void setGravityAcceleration(float gravityAcceleration) {
        this.gravityAcceleration = gravityAcceleration;
        getForce(DEFAULT_GRAVITY_NAME).setAcceleration(gravityAcceleration);
    }

    public boolean isGravityHasInfinitePower() {
        return gravityHasInfinitePower;
    }

    public void setGravityHasInfinitePower(boolean gravityHasInfinitePower) {
        this.gravityHasInfinitePower = gravityHasInfinitePower;
        getForce(DEFAULT_GRAVITY_NAME).setInfinitePower(gravityHasInfinitePower);
    }

    public boolean isAffectByGravity() {
        return affectByGravity;
    }

    public void setAffectByGravity(boolean affectByGravity) {
        this.affectByGravity = affectByGravity;
    }
}
