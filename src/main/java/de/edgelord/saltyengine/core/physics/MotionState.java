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
 * the state of motion of a {@link PhysicsObject}.
 */
public class MotionState {

    List<Force> forces = new ArrayList<>();

    private Vector2f sumForces() {
        Vector2f result = Vector2f.zero();
        for (Force force : forces) {
            result.add(force.getDirection().normalize(force.getVelocity()));
        }

        return result;
    }
}
