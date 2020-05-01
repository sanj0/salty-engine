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

import de.edgelord.saltyengine.collision.collider.Collider;
import de.edgelord.saltyengine.gameobject.GameObject;

/**
 * This implementation of {@link SceneCollider} takes the resut from the {@link Collider} with the highest {@code priority}.
 */
public class PrioritySceneCollider implements SceneCollider {

    /**
     * Returns the return value of {@link Collider#checkCollision(GameObject, GameObject)} from the <code>Collider</code>
     * with the highest priority (which most likely means the highest accuracy).
     *
     * @param object1 the first <code>GameObject</code>
     * @param object2 the second <code>GameObject</code>
     * @return the result of the collision detection between the two given {@link GameObject}s.
     */
    @Override
    public CollisionDetectionResult checkCollision(final GameObject object1, final GameObject object2) {

        final Collider collider1 = object1.getCollider();
        final Collider collider2 = object2.getCollider();
        final Collider collider = collider1.getPriority() >= collider2.getPriority() ? collider1 : collider2;

        return collider.checkCollision(object1, object2);
    }
}
