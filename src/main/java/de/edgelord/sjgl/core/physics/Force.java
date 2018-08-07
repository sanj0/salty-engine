package de.edgelord.sjgl.core.physics;

public interface Force {

    /**
     * This method should calculate the next step for the Force, by first calculating the velocity
     * (by e.g. taking friction into consideration) and then moving the <code>parent</code>
     */
    void nextStep();

    String getName();
}
