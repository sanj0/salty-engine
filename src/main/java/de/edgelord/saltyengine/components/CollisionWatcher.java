/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * A utility component that adds functionality to collision response via the two
 * methods {@link #onCollisionStart(CollisionEvent)} and {@link
 * #onCollisionEnd(GameObject)}.
 */
public abstract class CollisionWatcher extends Component<GameObject> {

    private final Set<GameObject> currentlyTouching = new HashSet<>();

    public CollisionWatcher(final GameObject parent, final String name) {
        super(parent, name, Components.UTILITY_COMPONENT);
    }

    /**
     * Called when this component's parent collides with a
     * <code>GameObject</code> that it did not touch on the previous fixed
     * tick.
     *
     * @param event the <code>CollisionEvent</code> of the collision
     */
    public abstract void onCollisionStart(final CollisionEvent event);

    /**
     * Called when this component's parent does no longer touch a
     * <code>GameObject</code> it touched in the previous fixdd tick.
     *
     * @param gameObject the <code>GameObject</code> who stopped touching this
     *                   component's parent
     */
    public abstract void onCollisionEnd(final GameObject gameObject);

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        // nothing to do
    }

    @Override
    public void onFixedTick() {
        // nothing to do here
    }

    @Override
    public void onCollision(final CollisionEvent e) {
        if (currentlyTouching.contains(e.getOtherGameObject())) {
            currentlyTouching.add(e.getOtherGameObject());
            onCollisionStart(e);
        }
    }

    @Override
    public void onCollisionDetectionFinish(final List<CollisionEvent> collisions) {
        for (final GameObject gameObject : currentlyTouching) {
            if (!containsCollisionWith(gameObject, collisions)) {
                currentlyTouching.remove(gameObject);
                onCollisionEnd(gameObject);
            }
        }
    }

    private boolean containsCollisionWith(final GameObject gameObject, final List<CollisionEvent> collisions) {
        for (final CollisionEvent e : collisions) {
            if (e.getOtherGameObject() == gameObject) {
                return true;
            }
        }

        return false;
    }
}
