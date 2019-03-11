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
import de.edgelord.saltyengine.hitbox.Hitbox;

public class HitboxCollider extends Collider {

    public HitboxCollider() {
        super(0, HITBOX_COLLIDER);
    }

    @Override
    public CollisionDetectionResult checkCollision(GameObject object1, GameObject object2) {

        Hitbox hitbox1 = object1.getHitbox();
        hitbox1.recalculate();
        Hitbox hitbox2 = object2.getHitbox();
        hitbox2.recalculate();

        return new CollisionDetectionResult(hitbox1.collides(object2), hitbox1.getTransform().getRelation(hitbox2.getTransform()));
    }
}
