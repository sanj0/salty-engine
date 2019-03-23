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
 * A class that defines when a {@link GameObject} collides with another.
 * The default one ({@link GameObject#getCollider() collider}) is a {@link HitboxCollider}. <p>
 *
 * This class is used by the {@link SceneCollider} of the {@link de.edgelord.saltyengine.scene.Scene}.
 * With the default one being {@link PrioritySceneCollider}, when checking the collision between two <code>GameObject</code>s,
 * the <code>Collider</code> with the higher {@link #priority} is taken.
 */
public abstract class Collider {

    /**
     * The priority of this collider.
     */
    private int priority;

    /**
     * The name of this collider.
     */
    private String name;

    /**
     * The name of the default {@link HitboxCollider}.
     */
    public static final String HITBOX_COLLIDER = "de.edgelord.saltyengine.collision.HitboxCollider";

    /**
     * The constructor.
     *
     * @param priority the priority of this collider
     * @param name the name
     */
    public Collider(int priority, String name) {
        this.priority = priority;
        this.name = name;
    }

    /**
     * Checks whether the two given {@link GameObject}s collide or not.
     * Returns the result as {@link CollisionDetectionResult}.
     *
     * @param object1 the first <code>GameObject</code>
     * @param object2 the second {@link GameObject}
     * @return the result of the collision detection
     */
    public abstract CollisionDetectionResult checkCollision(GameObject object1, GameObject object2);

    public int getPriority() {
        return priority;
    }

    public String getName() {
        return name;
    }
}
