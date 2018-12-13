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

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.Directions;

/**
 * Note: It is obviously planned to make more complicated Collision detection with complex Shapes possible.
 */
public abstract class ColliderComponent extends Component<GameObject> {

    private String type;

    public static final String TYPE_HITBOX_COLLIDER = "de.edgelord.saltyengine.collider.hitboxCollider";

    public ColliderComponent(GameObject parent, String name, String type) {
        super(parent, name, Components.COLLIDER_COMPONENT);

        this.type = type;
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    @Override
    public void onCollision(CollisionEvent e) {
    }

    public abstract boolean requestCollision(GameObject other);

    public abstract Directions.Direction getCollisionDirection(GameObject other);

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
