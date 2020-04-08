/*
 * Copyright 2020 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.core.physics;

import de.edgelord.saltyengine.transform.Vector2f;

import java.util.*;

/**
 * The <code>World</code> is the virtual scenery in which
 * the physics-part of the engine takes place.
 * <p>
 * It provides other <code>PhysicsBody</code>s to interact with,
 * as well as global parameters like gravity and friction as well as
 * unit conversion.
 */
public class World {

    /**
     * A constant factor that converts
     * imaginary meters to pixels.
     * Used for a more human-imaginable scale for
     * the physics engine.
     */
    public static final float METERS_TO_PIXELS = .000314f;

    /**
     * Determines whether this World has gravity or not.
     */
    private static boolean gravityEnabled = false;

    /**
     * The acceleration of gravity within this World.
     * Default is <code>9.81</code>
     */
    private static float g = 9.81f;

    /**
     * The unit-vector (direction-vector) of gravity.
     */
    private static Vector2f gravityDirection = new Vector2f(0, 1);

    /**
     * The global drag that counters every acceleration within this world.
     * Default is <code>5.12</code>
     */
    private static float drag = 5.12f;

    /**
     * The list of <code>PhysicsBody</code>s that exist in this World,
     * are effect by the latter and effect each other.
     */
    private static List<PhysicsBody> bodies = Collections.synchronizedList(new ArrayList<>());

    /**
     * Maps every <code>PhysicsBody</code> within this World to
     * a {@link Force gravity force}.
     */
    private static Map<PhysicsBody, Force> gravityForces = new HashMap<>();

    /**
     * Ticks the world.
     *
     * @param dt the time that passed after the last tick
     */
    public static void tick(long dt) {
        for (int i = 0; i < bodies.size(); i++) {
            PhysicsBody body = bodies.get(i);
            body.tick(dt);

            if (gravityEnabled) {
                float dg = gravityForces.get(body).deltaPixels(dt);
                body.getParent().getPosition().add(0, dg);
            }
        }
    }

    public static boolean add(PhysicsBody physicsBody) {
        gravityForces.put(physicsBody, new Force(gravityDirection, g));
        return bodies.add(physicsBody);
    }

    public static boolean remove(Object o) {
        gravityForces.remove(o);
        return bodies.remove(o);
    }

    public static void clear() {
        gravityForces.clear();
        bodies.clear();
    }

    /**
     * Gets {@link #gravityEnabled}.
     *
     * @return the value of {@link #gravityEnabled}
     */
    public static boolean isGravityEnabled() {
        return gravityEnabled;
    }

    /**
     * Sets {@link #gravityEnabled}.
     *
     * @param gravityEnabled the new value of {@link #gravityEnabled}
     */
    public static void setGravityEnabled(boolean gravityEnabled) {
        World.gravityEnabled = gravityEnabled;
    }

    /**
     * Gets {@link #g}.
     *
     * @return the value of {@link #g}
     */
    public static float getG() {
        return g;
    }

    /**
     * Sets {@link #g}.
     *
     * @param g the new value of {@link #g}
     */
    public static void setG(float g) {
        World.g = g;
    }

    /**
     * Gets {@link #gravityDirection}.
     *
     * @return the value of {@link #gravityDirection}
     */
    public static Vector2f getGravityDirection() {
        return gravityDirection;
    }

    /**
     * Sets {@link #gravityDirection}.
     *
     * @param gravityDirection the new value of {@link #gravityDirection}
     */
    public static void setGravityDirection(Vector2f gravityDirection) {
        World.gravityDirection = gravityDirection;
    }

    /**
     * Gets {@link #drag}.
     *
     * @return the value of {@link #drag}
     */
    public static float getDrag() {
        return drag;
    }

    /**
     * Sets {@link #drag}.
     *
     * @param drag the new value of {@link #drag}
     */
    public static void setDrag(float drag) {
        World.drag = drag;
    }

    /**
     * Gets {@link #bodies}.
     *
     * @return the value of {@link #bodies}
     */
    public static List<PhysicsBody> getBodies() {
        return bodies;
    }

    /**
     * Sets {@link #bodies}.
     *
     * @param bodies the new value of {@link #bodies}
     */
    public static void setBodies(List<PhysicsBody> bodies) {
        World.bodies = bodies;
    }
}
