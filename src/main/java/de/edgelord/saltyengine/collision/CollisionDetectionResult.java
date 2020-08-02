/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.collision;

import de.edgelord.saltyengine.utils.Directions;

/**
 * This class stores the result of a collision detection between two {@link
 * de.edgelord.saltyengine.gameobject.GameObject}s.
 */
public class CollisionDetectionResult {

    /**
     * This is <code>true</code> when the two {@link de.edgelord.saltyengine.gameobject.GameObject}s
     * collides and <code>false</code> when not.
     */
    private final boolean collision;

    /**
     * The {@link de.edgelord.saltyengine.utils.Directions.Direction} in which
     * the first of the two (the "root") collided with the second one.
     */
    private final Directions.Direction rootCollisionDirection;

    /**
     * The constructor.
     *
     * @param collision              <code>true</code>
     *                               when the two {@link de.edgelord.saltyengine.gameobject.GameObject}s
     *                               collides and
     *                               <code>false</code>
     *                               when not
     * @param rootCollisionDirection the {@link de.edgelord.saltyengine.utils.Directions.Direction}
     *                               in which the first of the two (the "root")
     *                               collided with the second one
     */
    public CollisionDetectionResult(final boolean collision, final Directions.Direction rootCollisionDirection) {
        this.collision = collision;
        this.rootCollisionDirection = rootCollisionDirection;
    }

    public boolean isCollision() {
        return collision;
    }

    public Directions.Direction getRootCollisionDirection() {
        return rootCollisionDirection;
    }
}
