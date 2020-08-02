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

import de.edgelord.saltyengine.gameobject.GameObject;

/**
 * This is the class highest in the hierarchy of collision detection classes.
 * Any {@link de.edgelord.saltyengine.scene.Scene} has an instance of an
 * implementation, by default {@link PrioritySceneCollider}.
 */
public interface SceneCollider {

    /**
     * Checks the collision state between the two given {@link GameObject}s by
     * loading it into an instance of {@link CollisionDetectionResult}.
     *
     * @param object1 the first <code>GameObject</code>
     * @param object2 the second <code>GameObject</code>
     *
     * @return the result of the check
     */
    CollisionDetectionResult checkCollision(GameObject object1, GameObject object2);
}
