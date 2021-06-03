/*
 * Copyright 2021 Malte Dostal
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
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.GameObject;

/**
 * When GameObjects move with a high enough pixel per time velocity and they
 * collide with other GameObjects, they sometimes move enough pixels per tick so
 * that they overlap with the GameObject they collide with. This components is
 * for pushing out its parent from any collision caused overlap.
 * <p>
 * Effectively, this means that after a collision is detected, the parent of
 * this component will be moved in the opposite direction so that it just
 * touches the other gameobject but doesn't overlap with it.
 */
public class CollisionOverlapFixer extends CollisionWatcher {

    public CollisionOverlapFixer(final GameObject parent, final String name) {
        super(parent, name);
    }

    @Override
    public void onCollisionStart(final CollisionEvent e) {
        final ComponentContainer parent = getParent();
        switch (e.getCollisionDirection()) {
            case RIGHT:
                parent.setX(e.getOtherGameObject().getX() - parent.getWidth() + .1f);
                break;
            case LEFT:
                parent.setX(e.getOtherGameObject().getX() + e.getOtherGameObject().getWidth() - .1f);
                break;
            case UP:
                parent.setY(e.getOtherGameObject().getY() + e.getOtherGameObject().getHeight() - .1f);
                break;
            case DOWN:
                parent.setY(e.getOtherGameObject().getY() - parent.getHeight() + .1f);
                break;
            case EMPTY:
                // ignore
                break;
        }
    }

    @Override
    public void onCollisionEnd(final GameObject gameObject) {
        // nothing to do
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        // nothing to do!
    }

    @Override
    public void onFixedTick() {
        // nothing to do!
    }

    @Override
    public void onCollision(final CollisionEvent e) {
    }
}
