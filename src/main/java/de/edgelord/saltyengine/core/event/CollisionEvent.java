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

import de.edgelord.saltyengine.core.physics.PhysicsBody;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * An event that describes the collision between two objects
 */
public class CollisionEvent {

    private Vector2f normal;
    private PhysicsBody owner;
    private PhysicsBody partner;

    public CollisionEvent(Vector2f normal, PhysicsBody owner, PhysicsBody partner) {
        this.normal = normal;
        this.owner = owner;
        this.partner = partner;
    }

    /**
     * Fires this event using
     * {@code owner.getParent().onCollision(this)}
     */
    public void fire() {
        owner.getParent().onCollision(this);
    }

    /**
     * Gets {@link #normal}.
     *
     * @return the value of {@link #normal}
     */
    public Vector2f getNormal() {
        return normal;
    }

    /**
     * Gets {@link #owner}.
     *
     * @return the value of {@link #owner}
     */
    public PhysicsBody getOwner() {
        return owner;
    }

    /**
     * Gets {@link #partner}.
     *
     * @return the value of {@link #partner}
     */
    public PhysicsBody getPartner() {
        return partner;
    }
}
