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
import java.util.Collection;
import java.util.List;

/**
 * The <code>MotionState</code> defines
 * the state of motion of a {@link PhysicsObject}.
 */
public class MotionState {

    List<Force> forces = new ArrayList<>();

    public Vector2f sumForces(long dt) {
        Vector2f result = Vector2f.zero();
        for (Force force : forces) {
            result.add(force.getDirection().withMagnitude(force.deltaDistance(dt)));
        }

        return result;
    }


    public int size() {
        return forces.size();
    }

    public boolean contains(Object o) {
        return forces.contains(o);
    }

    public boolean add(Force force) {
        return forces.add(force);
    }

    public boolean remove(Object o) {
        return forces.remove(o);
    }

    public boolean addAll(Collection<? extends Force> c) {
        return forces.addAll(c);
    }

    public void clear() {
        forces.clear();
    }

    public Force get(int index) {
        return forces.get(index);
    }

    public Force remove(int index) {
        return forces.remove(index);
    }
}
