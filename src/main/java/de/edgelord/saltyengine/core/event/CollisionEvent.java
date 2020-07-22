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

package de.edgelord.saltyengine.core.event;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.utils.Directions;

/**
 * This class is used for handling collisions
 * between GameObjects. It has all necessary
 * parameters for physics etc.
 */
public class CollisionEvent {

    private final GameObject otherGameObject;
    private Directions.Direction collisionDirection;

    public CollisionEvent(final GameObject otherGameObject, final Directions.Direction collisionDirection) {

        this.otherGameObject = otherGameObject;
        this.collisionDirection = collisionDirection;
    }

    public GameObject getOtherGameObject() {
        return otherGameObject;
    }

    public Directions.Direction getCollisionDirection() {
        return collisionDirection;
    }

    public void setCollisionDirection(final Directions.Direction collisionDirection) {
        this.collisionDirection = collisionDirection;
    }
}
