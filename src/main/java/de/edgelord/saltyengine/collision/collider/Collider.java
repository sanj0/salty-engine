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

package de.edgelord.saltyengine.collision.collider;

import de.edgelord.saltyengine.collision.CollisionDetectionResult;
import de.edgelord.saltyengine.collision.PrioritySceneCollider;
import de.edgelord.saltyengine.collision.SceneCollider;
import de.edgelord.saltyengine.gameobject.GameObject;

/**
 * A class that defines when a {@link GameObject} collides with another.
 * The default one ({@link GameObject#getCollider() collider}) is a {@link HitboxCollider}. <p>
 * <p>
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
    private int type;

    /**
     * The type of the default {@link HitboxCollider}.
     */
    public static final int HITBOX_COLLIDER = 0;

    /**
     * The type of the {@link ShapeCollider}.
     */
    public static final int SHAPE_COLLIDER = 1;

    /**
     * The type of the {@link CircleCollider}.
     */
    public static final int CIRCLE_COLLIDER = 2;

    /**
     * The constructor.
     *
     * @param priority the priority of this collider
     * @param type     the name
     */
    public Collider(int priority, int type) {
        this.priority = priority;
        this.type = type;
    }

    /**
     * Returns the {@link Collider} of the given two, that isn't <code>this</code>.
     *
     * @param collider1 the first collider
     * @param collider2 the second collider
     * @return the one <code>Collider</code> from the given two that isn't <code>this</code>.
     */
    public Collider getOtherCollider(Collider collider1, Collider collider2) {
        return collider1 != this ? collider1 : collider2;
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

    public int getType() {
        return type;
    }
}
