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
import de.edgelord.saltyengine.utils.Directions;

public class HitboxCollider extends ColliderComponent {

    public HitboxCollider(GameObject parent, String name) {
        super(parent, name, TYPE_HITBOX_COLLIDER);
    }

    @Override
    public boolean requestCollision(GameObject other) {

        ColliderComponent otherCollider = other.requestCollider();

        switch (otherCollider.getType()) {

            case TYPE_HITBOX_COLLIDER:
                return getParent().getHitbox().collides(other);
        }

        return false;
    }

    @Override
    public Directions.Direction getCollisionDirection(GameObject other) {
        return getParent().getHitbox().getTransform().getRelation(other.getHitbox().getTransform());
    }
}
