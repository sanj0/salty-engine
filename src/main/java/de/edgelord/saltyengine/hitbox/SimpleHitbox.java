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

package de.edgelord.saltyengine.hitbox;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

public class SimpleHitbox implements Hitbox {

    private final GameObject parent;
    private Transform transform;
    private float offsetX, offsetY;

    public SimpleHitbox(final GameObject parent, final float width, final float height, final float offsetX, final float offsetY) {

        this.parent = parent;
        this.offsetX = offsetX;
        this.offsetY = offsetY;

        this.transform = new Transform(new Vector2f(parent.getX() + offsetX, parent.getY() + offsetY), new Dimensions(width, height));
    }

    public void recalculate() {

        transform.setPosition(new Vector2f(parent.getPosition().getX() + offsetX, parent.getPosition().getY() + offsetY));
    }

    @Override
    public boolean collides(final GameObject other) {

        return transform.intersects(other.getHitbox().getTransform());
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(final float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(final float offsetY) {
        this.offsetY = offsetY;
    }

    public Vector2f getPosition() {
        return transform.getPosition();
    }

    public void setPosition(final Vector2f position) {
        this.transform.setPosition(position);
    }

    public float getWidthExact() {
        return transform.getWidth();
    }

    public int getWidth() {
        return transform.getWidthAsInt();
    }

    public void setWidth(final float width) {
        this.transform.setWidth(width);
    }

    public float getHeightExact() {
        return transform.getHeight();
    }

    public int getHeight() {
        return transform.getHeightAsInt();
    }

    public void setHeight(final float height) {
        this.transform.setHeight(height);
    }

    public GameObject getParent() {
        return parent;
    }
}
