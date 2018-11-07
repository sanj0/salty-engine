/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;

import java.awt.*;

/**
 * Locks its parent to the given {@link Transform} by using whether its {@link de.edgelord.saltyengine.hitbox.Hitbox} or its {@link Transform}.
 * What to use is defined in {@link #mode}. The default is {@link #MODE_TRANSFORM}
 */
public class LockToBounds extends Component<GameObject> {
    /**
     * The rectangular bounds to which the {@link #parent} is locked
     */
    private Transform bounds;

    private int mode = MODE_TRANSFORM;

    public static final int MODE_HITBOX = 0;
    public static final int MODE_TRANSFORM = 1;

    public LockToBounds(GameObject parent, Transform bounds, String name) {
        super(parent, name, Components.GAME_COMPONENTS);

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
                    getParent().getLockedDirections().setDirection(bounds.getRelation(getParent().getTransform()));
                }
                break;

            case MODE_HITBOX:
                if (!bounds.contains(getParent().getHitbox().getTransform())) {
                    getParent().getLockedDirections().setDirection(bounds.getRelation(getParent().getHitbox().getTransform()));
                }
                break;
        }
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
