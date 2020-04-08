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

import java.util.ArrayList;
import java.util.List;

/**
 * The <code>MotionState</code> defines
 * the state of motion of a {@link PhysicsBody}.
 */
public class MotionState {

    /**
     * The list of {@link Force}s that
     * effect the motion state.
     */
    List<Force> forces = new ArrayList<>();

    /**
     * Sums the {@link Force#deltaPixels(long) delta distances} of
     * every <code>Force</code> of this <code>MotionState</code>
     * to a single {@link Vector2f vector}.
     *
     * @param dt the delta time that passed after the last tick
     * @return a {@link Vector2f vector} that resembles all <code>Force</code>s
     * of this motion state after the given time delta
     */
    public Vector2f sumForces(long dt) {
        Vector2f result = Vector2f.zero();
        for (Force force : forces) {
            float dp = force.deltaPixels(dt);
            result.add(force.getDirection().multiplied(new Vector2f(dp, dp)));
        }

        return result;
    }

    /**
     * Delegated from {@link #forces}, adds the given
     * {@link Force} to this <code>MotionState</code>.
     *
     * @param force the <code>Force</code> to add to this <code>MotionState</code>
     * @return <code>true</code> (as specified by {@link java.util.Collection#add(Object)})
     */
    public boolean add(Force force) {
        return forces.add(force);
    }

    /**
     * Delegated from {@link #forces}, removes the given
     * {@link Force} from this <code>MotionState</code>.
     *
     * @param force the <code>Force</code> to remove from this <code>MotionState</code>
     * @return <code>true</code> if this <code>MotionState</code> contained the given <code>Force</code>
     */
    public boolean remove(Force force) {
        return forces.remove(force);
    }

    /**
     * Gets {@link #forces}.
     *
     * @return the value of {@link #forces}
     */
    public List<Force> getForces() {
        return forces;
    }
}
