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

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.transform.Vector2f;

import java.util.LinkedList;
import java.util.List;

public class CollisionDetector {

    public static List<CollisionEvent[]> collisionDetection(List<PhysicsBody> bodies) {
        List<CollisionEvent[]> result = new LinkedList<>();

        for (int i = 0; i < bodies.size(); i++) {
            PhysicsBody body1 = bodies.get(i);

            for (int j = i + 1; j < bodies.size(); j++) {
                PhysicsBody body2 = bodies.get(j);

                if (body1.getParent().getTransform().intersects(body2.getParent().getTransform())) {
                    // collision!
                    Vector2f normal = body1.getParent().getTransform().getCentre().subtract(body2.getParent().getTransform().getCentre()).normalize();
                    result.add(new CollisionEvent[]{new CollisionEvent(normal, body1, body2), new CollisionEvent(normal, body2, body1)});
                }
            }
        }

        return result;
    }
}
