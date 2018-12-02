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

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * A GameObject with defautl empty implementations of:
 * {@link GameObject#onCollision(CollisionEvent)} <br>
 * {@link GameObject#onFixedTick()} <br>
 * {@link GameObject#draw(SaltyGraphics)} <p>
 * <p>
 * Inside the {@link #initialize()}, this GameObject is set to be {@link GameObject#setStationary(boolean)} and
 * to ignore the gravity using {@link de.edgelord.saltyengine.components.SimplePhysicsComponent#setGravityEnabled(boolean)}
 */
public class NullGameObject extends GameObject {

    public NullGameObject(float xPos, float yPos, float width, float height, String tag) {
        super(xPos, yPos, width, height, tag);
    }

    public NullGameObject(Transform transform, String tag) {
        super(transform, tag);
    }

    public NullGameObject(Coordinates coordinates, Dimensions dimensions, String tag) {
        super(coordinates, dimensions, tag);
    }

    public NullGameObject(Vector2f position, Dimensions dimensions, String tag) {
        super(position, dimensions, tag);
    }

    public NullGameObject(Vector2f position, float width, float height, String tag) {
        super(position, width, height, tag);
    }

    public NullGameObject(float xPos, float yPos, Dimensions dimensions, String tag) {
        super(xPos, yPos, dimensions, tag);
    }

    @Override
    public void initialize() {
        setStationary(true);
        getPhysics().setGravityEnabled(false);
    }

    @Override
    public void onCollision(CollisionEvent event) {
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }
}
