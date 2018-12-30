/*
 * Copyright 2018 Malte Dostal
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

package de.edgelord.saltyengine.components.collider;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;

/**
 * A implementation of {@link ColliderComponent} that checks for collisions by calling {@link de.edgelord.saltyengine.hitbox.Hitbox#collides(GameObject)}.
 */
public class HitboxCollider extends ColliderComponent {

    /**
     * {@inheritDoc}
     */
    public HitboxCollider(GameObject parent, String name) {
        super(parent, name, TYPE_HITBOX_COLLIDER);
    }

    /**
     * Returns {@link de.edgelord.saltyengine.hitbox.Hitbox#collides(GameObject)},
     * specifically: <code>getParent().getHitbox().collides(other)</code>
     *
     * @param other the other <code>GameObject</code>
     * @return whether this component's parent and the given <code>GameObject</code> collide
     *
     * @see ColliderComponent#requestCollision(GameObject)
     */
    @Override
    public boolean requestCollision(GameObject other) {

        if (getParent().getHitbox() != null) {
            return getParent().getHitbox().collides(other);
        } else {
            return false;
        }
    }

    /**
     * Returns {@link Transform#getRelation(Transform)},
     * specifically <code>getParent().getHitbox().getTransform().getRelation(other.getHitbox().getTransform())</code>.
     *
     * @param other the other <code>GameObject</code>
     * @return the direction of the collision between this component's parent and the given <code>GameObject</code>.
     */
    @Override
    public Directions.Direction getCollisionDirection(GameObject other) {
        return getParent().getHitbox().getTransform().getRelation(other.getHitbox().getTransform());
    }
}
