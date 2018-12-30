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
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.utils.Directions;

/**
 * A {@link Component} that does the collision detection for its parent.
 * <p>
 * {@link #requestCollision(GameObject)} returns whether the parent of the component and the given {@link GameObject} collide,
 * {@link #getCollisionDirection(GameObject)} returns the direction in which the two <code>GameObject</code>s collide.
 */
public abstract class ColliderComponent extends Component<GameObject> {

    /**
     * The type of this collider, intentionally not an enum due to easier 3rd-party extensibility.
     */
    private String type;

    /**
     * The internal type of a collider that checks if the hitboxes of the two {@link GameObject}s intersect.
     *
     * @see HitboxCollider
     */
    public static final String TYPE_HITBOX_COLLIDER = "de.edgelord.saltyengine.collider.hitboxCollider";

    /**
     * {@inheritDoc}
     *
     * @param type the type of collider.
     */
    public ColliderComponent(GameObject parent, String name, String type) {
        super(parent, name, Components.COLLIDER_COMPONENT);

        this.type = type;
    }

    /**
     * Empty implementation.
     */
    @Override
    public void onFixedTick() {
    }

    /**
     * Empty implementation
     * @param saltyGraphics the graphics to render to
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    /**
     * Empty implementation
     * @param e the event of the collision
     */
    @Override
    public void onCollision(CollisionEvent e) {
    }

    /**
     * Returns whether this component's parent and the given {@link GameObject} collide or not, by computing it
     * implementation-specific.
     *
     * @param other the other <code>GameObject</code>
     * @return whether this component's parent and the given <code>GameObject</code> collide.
     */
    public abstract boolean requestCollision(GameObject other);

    /**
     * Returns where this component's parent and the given {@link GameObject} collide, this is only ever called internally
     * after {@link #requestCollision(GameObject)} returns true.
     *
     * Example: <br>
     *
     * |------------------------------|  __This is where the two <code>GameObject</code>s collide.
     * | The parent of this component | /
     * |______________________________|----------------------|
     *                                | The other GameObject |
     *                                |______________________|
     *
     * In the situation above, this method would return {@link de.edgelord.saltyengine.utils.Directions.Direction#RIGHT}
     *
     * @param other the other <code>GameObject</code>
     * @return where this component's parent and the given <code>GameObject</code> collide.
     */
    public abstract Directions.Direction getCollisionDirection(GameObject other);

    /**
     * @return the {@link #type} of this collider.
     */
    public String getType() {
        return type;
    }

    /**
     * Sets the {@link #type} of this collider.
     * @param type the new type
     */
    public void setType(String type) {
        this.type = type;
    }
}
