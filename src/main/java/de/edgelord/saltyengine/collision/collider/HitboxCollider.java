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
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.hitbox.Hitbox;
import de.edgelord.saltyengine.transform.Transform;

/**
 * The default implementation of {@link Collider}, which any {@link GameObject} has by default. <br>
 * This <code>Collider</code> has a <code>priority</code> of <code>0</code>.
 */
public class HitboxCollider extends Collider {

    /**
     * The constructor.
     */
    public HitboxCollider() {
        super(0, HITBOX_COLLIDER);
    }

    /**
     * This implementation gets the {@link GameObject#getHitbox() hitbox} of the
     * two given <code>GameObject</code>s and then calls {@link Hitbox#collides(GameObject)} from the first hitbox and for
     * the collision direction, it calls {@link de.edgelord.saltyengine.transform.Transform#getRelation(Transform)}.
     *
     * @param object1 the first <code>GameObject</code>
     * @param object2 the second {@link GameObject}
     * @return the result of the collision detection between the two given <code>GameObject</code>s
     */
    @Override
    public CollisionDetectionResult checkCollision(final GameObject object1, final GameObject object2) {

        final Hitbox hitbox1 = object1.getHitbox();
        final Hitbox hitbox2 = object2.getHitbox();

        return new CollisionDetectionResult(hitbox1.collides(object2), hitbox1.getTransform().getRelation(hitbox2.getTransform()));
    }
}
