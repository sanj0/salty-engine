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

package de.edgelord.saltyengine.collision.collider;

import de.edgelord.saltyengine.collision.CollisionDetectionResult;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.utils.Directions;

/**
 * A collider that always returns {@code new
 * CollisionDetectionResult(false, Directions.Direction.EMPTY)},
 * hence making its parent "ghostly" by means that
 * it won't ever be involved a collision. The
 * priority of the collider is {@link
 * Integer#MAX_VALUE}
 */
public class GhostCollider extends Collider {

    /**
     * The constructor.
     */
    public GhostCollider() {
        super(Integer.MAX_VALUE, GHOST_COLLIDER);
    }

    @Override
    public CollisionDetectionResult checkCollision(final GameObject object1, final GameObject object2) {
        return new CollisionDetectionResult(false, Directions.Direction.EMPTY);
    }
}
