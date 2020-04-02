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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;

/**
 * Locks its parent to the given {@link Transform} by using either its {@link de.edgelord.saltyengine.hitbox.Hitbox} or its {@link Transform}.
 * What to use is defined in {@link #mode}. The default is {@link #MODE_TRANSFORM}
 */
public class LockToBounds extends Component<GameObject> {
    /**
     * The rectangular bounds to which the {@link #getParent()} is locked
     */
    private Transform bounds;

    private int mode = MODE_TRANSFORM;

    public static final int MODE_HITBOX = 0;
    public static final int MODE_TRANSFORM = 1;

    public LockToBounds(GameObject parent, Transform bounds, String name) {
        super(parent, name, Components.GAME_COMPONENT);

        this.bounds = bounds;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    @Override
    public void onFixedTick() {
        switch (mode) {
            case MODE_TRANSFORM:
                if (!bounds.contains(getParent().getTransform())) {
                    getParent().getLockedDirections().addDirection(bounds.getRelation(getParent().getTransform()));
                }
                break;

            case MODE_HITBOX:
                if (!bounds.contains(getParent().getHitbox().getTransform())) {
                    getParent().getLockedDirections().addDirection(bounds.getRelation(getParent().getHitbox().getTransform()));
                }
                break;
        }
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
