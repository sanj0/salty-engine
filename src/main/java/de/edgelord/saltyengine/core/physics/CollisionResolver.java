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

public class CollisionResolver {

    public static void resolveCollision(CollisionEvent collision) {

        Vector2f relativeVelocity = collision.getPartner().getMotionState().getVelocity().subtracted(collision.getOwner().getMotionState().getVelocity());
        float velocityAlongNormal = relativeVelocity.dotProduct(collision.getNormal());

        if (velocityAlongNormal > 0) {
            return;
        }

        collision.getOwner().getParent().getPosition().add(collision.getNormal().multiplied(collision.getOwner().getMotionState().getVelocity()));
        collision.getPartner().getParent().getPosition().add(collision.getNormal().multiplied(Vector2f.negativeOne()).multiplied(collision.getOwner().getMotionState().getVelocity()));
    }
}
