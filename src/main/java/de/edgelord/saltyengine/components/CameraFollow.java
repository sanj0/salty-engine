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
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;

/**
 * This implementation of {@link Component} makes the {@link de.edgelord.saltyengine.camera.Camera}
 * of the game follow its parent.
 * <p>
 * The {@link Transform} {@link #whiteZone} describes the area in which the {@link #parent} of this
 * Component can be located without the camera to move. When ever the parent is outside this rectangle
 * on a fixed tick, the camera moves towards it by the specific amount of pixels stored in {@link #speed}.
 * This can also be a floating-point number, the floating-points will stack and eventually turn into an integer.
 * <p>
 * By default, {@link #whiteZone} is twice as wide and high as the {@link #parent} of this Components
 * and in the middle of the screen. It can be changed/edited at any time.
 */
public class CameraFollow extends Component<GameObject> {

    private Transform whiteZone;
    private float speed = 1f;

    public CameraFollow(GameObject parent, String name) {
        super(parent, name, Components.CAMERA_COMPONENT);

        float defaultWhiteZoneWidth = getParent().getWidth() * 2;
        float defaultWhiteZoneHeight = getParent().getHeight() * 2;
        float defaultWhiteZoneX = Game.getHost().getHorizontalCentrePosition(defaultWhiteZoneWidth);
        float defaultWhiteZoneY = Game.getHost().getVerticalCentrePosition(defaultWhiteZoneHeight);

        whiteZone = new Transform(defaultWhiteZoneX, defaultWhiteZoneY, defaultWhiteZoneWidth, defaultWhiteZoneHeight);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    @Override
    public void onFixedTick() {

        Transform renderedPlayerTransform = new Transform(getParent().getX() + Game.camera.getX(), getParent().getY() + Game.camera.getY(),
                getParent().getWidth(), getParent().getHeight());

        if (!whiteZone.intersects(getParent().getTransform())) {
            Game.camera.move(Directions.getFreeRelationX(whiteZone, renderedPlayerTransform), speed);
            Game.camera.move(Directions.getFreeRelationY(whiteZone, renderedPlayerTransform), speed);
        }
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public Transform getWhiteZone() {
        return whiteZone;
    }

    public void setWhiteZone(Transform whiteZone) {
        this.whiteZone = whiteZone;
    }

    public void setWhiteZoneX(float x) {
        whiteZone.setX(x);
    }

    public void setWhiteZoneY(float y) {
        whiteZone.setY(y);
    }

    public void setWhiteZoneWidth(float width) {
        whiteZone.setWidth(width);
    }

    public void setWhiteZoneHeight(float height) {
        whiteZone.setHeight(height);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }
}
