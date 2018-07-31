package de.edgelord.sjgl.core.physics;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.components.SimplePhysicsComponent;
import de.edgelord.sjgl.utils.Directions;

/**
 * This class represents a kinetic force for SimplePhysicsComponents.
 * The main idea is, that it tries to reach the <code>targetVelocity</code> and then raises straight with it.
 * If <code>accelerate</code> is set to <code>false</code> <code>targetVelocity</code> is reached right at the first step.
 * If <code>infinitePower</code> is set to <code>true</code> the force will accelerate over and over again until
 * reaching <code>targetVelocity</code> and will accelerate again if it got slowed down because of friction or interrupting.
 *
 * @see SimplePhysicsComponent
 */
public class SimpleForce {

    private GameObject parent;
    private float targetVelocity;
    private Directions.Direction direction;
    // A id-name for editing or removing Forces from a List
    private String name;

    private float airFrcition = SimplePhysicsComponent.DEFAULT_AIRFRICTION;

    private boolean enabled;
    // If this is true, the Force will not have the target velocity right at the beginning but will accelerate towards it
    private boolean accelerate;
    private boolean reachedTargetVelocity = false;
    // The steps until reaching targetVelocity. If it is higher, the force will accelerate slower, and if it is smaller,
    // the force will accelerate faster!
    private float acceleration = 2;
    // If this is true, the Force will accelerate in every step, no matter if it was stopped or slowed down before
    private boolean infinitePower;
    private float currentVelocity = 0f;

    /**
     * The standard constructor which takes in all necessary parameters for the force to work properly.
     *
     * @param parent         the parent GameObject of this force, this will be affected by it
     * @param name           the id-name of this force
     * @param targetVelocity the target velocity of this force, it will never accelerate further
     * @param direction      the direction in which this force should move the <code>parent</code>
     */
    public SimpleForce(GameObject parent, String name, float targetVelocity, Directions.Direction direction) {
        this.parent = parent;
        this.name = name;
        this.targetVelocity = targetVelocity;
        this.direction = direction;
    }

    /**
     * This constructor takes in everything from the first constructor as well as the acceleration.
     * It sets <code>accelerate</code> to true.
     *
     * @param parent         the parent of the force
     * @param name           the id-name of this force
     * @param targetVelocity the target velocity
     * @param acceleration   the steps until reaching <code>targetVelocity</code>
     * @param direction      the direction in which the force should move the <code>parent</code>
     */
    public SimpleForce(GameObject parent, String name, float targetVelocity, float acceleration, Directions.Direction direction) {
        this.parent = parent;
        this.name = name;
        this.targetVelocity = targetVelocity;
        this.acceleration = acceleration;
        this.direction = direction;

        this.accelerate = true;
    }

    /**
     * Calculate the next step for this force
     */
    public void nextStep() {

        doAcceleration();

        // Check if  currentVelocity if negative, and if so, make it 0
        if (currentVelocity < 0f) {
            currentVelocity = 0f;
        }

        // Remove the friction between all touching gameObject but make sure
        // that the targetVelocity cannot get negative

        for (GameObject gameObject : parent.getTouchingGameObjects()) {
            if (currentVelocity >= gameObject.getFriction()) {
                currentVelocity -= gameObject.getFriction();
            } else {
                currentVelocity = 0f;
                break;
            }
        }

        // Remove the air friction but make sure
        // that the targetVelocity cannot get negative

        if (currentVelocity >= airFrcition) {
            currentVelocity -= airFrcition;
        } else {
            currentVelocity = 0f;
        }

        // Finally, move the GameObject

        parent.move(targetVelocity, direction);

    }

    /**
     * This is used for mirroring the direction in which this force should move the <code>parent</code>
     *
     * @see Directions#mirrorDirection(Directions.Direction)
     */
    public void mirrorDirection() {

        direction = Directions.mirrorDirection(direction);
    }

    /**
     * This method gets called every step and it checks if the force should accelerate and if it has infinite power
     * and not yet reached the <code>targetVelocity</code>
     */
    private void doAcceleration() {

        float accelerationStep = targetVelocity / acceleration;

        if (accelerate) {
            if (infinitePower) {
                accelerate(accelerationStep);
            } else if (!reachedTargetVelocity) {
                accelerate(accelerationStep);
            }
        } else {
            currentVelocity = targetVelocity;
            reachedTargetVelocity = true;
        }

        if (currentVelocity == targetVelocity) {
            reachedTargetVelocity = true;
        }
    }

    private void accelerate(float accelerationStep) {

        if ((currentVelocity + accelerationStep) > targetVelocity) {
            currentVelocity = targetVelocity;
            reachedTargetVelocity = true;
        } else {
            currentVelocity += accelerationStep;
        }
    }

    /**
     * When this method gets called, the current velocity gets set to 0.
     * This method gets called when e.g. the parent GameObject collides with another
     */
    public void interrupt() {

        currentVelocity = 0f;
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public float getTargetVelocity() {
        return targetVelocity;
    }

    public void setTargetVelocity(float targetVelocity) {
        this.targetVelocity = targetVelocity;
    }

    public Directions.Direction getDirection() {
        return direction;
    }

    public void setDirection(Directions.Direction direction) {
        this.direction = direction;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean isAccelerate() {
        return accelerate;
    }

    public void setAccelerate(boolean accelerate) {
        this.accelerate = accelerate;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(float acceleration) {
        this.acceleration = acceleration;
    }

    public boolean isInfinitePower() {
        return infinitePower;
    }

    public void setInfinitePower(boolean infinitePower) {
        this.infinitePower = infinitePower;
    }
}
