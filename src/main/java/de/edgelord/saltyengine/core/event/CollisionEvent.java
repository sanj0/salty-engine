/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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

package de.edgelord.saltyengine.core.event;

import de.edgelord.saltyengine.components.SimplePhysicsComponent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.utils.Directions;

/**
 * This class is used for handling collisions between GameObjects. It has all necessary parameters for physics etc.
 */
public class CollisionEvent {

    private final GameObject root;
    private final SimplePhysicsComponent rootPhysics;
    private Directions collisionDirections;
    private final float rootMass;

    public CollisionEvent(final GameObject root) {
        this(root, null);
    }

    public CollisionEvent(final GameObject root, final Directions collisionDirections) {

        this.root = root;
        rootPhysics = root.getPhysics();
        this.collisionDirections = collisionDirections;
        rootMass = root.getMass();
    }

    public GameObject getRoot() {
        return root;
    }

    public SimplePhysicsComponent getRootPhysics() {
        return rootPhysics;
    }

    public Directions getCollisionDirections() {
        return collisionDirections;
    }

    public void setCollisionDirections(final Directions collisionDirections) {
        this.collisionDirections = collisionDirections;
    }

    public float getRootMass() {
        return rootMass;
    }
}
