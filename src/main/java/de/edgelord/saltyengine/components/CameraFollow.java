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
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;

/**
 * This implementation of {@link Component} makes the {@link de.edgelord.saltyengine.core.camera.Camera}
 * of the game follow its parent.
 * <p>
 * The {@link Transform} {@link #whiteZone} describes the area in which the {@link #parent} of this
 * Component can be located without the camera to move. When ever the parent is outside this rectangle
 * on a fixed tick, the camera moves towards it by the specific amount of pixels stored in {@link #speed}.
 * This can also be a floating-point number, the floating-points will stack and eventually turn into an integer.
 * <p>
 * By default, {@link #whiteZone} is twice as wide and high as the {@link #parent} of this Components
 * and in the centre of the screen. It can be changed/edited at any time.
 */
public class CameraFollow extends Component<GameObject> {

    private Transform whiteZone;
    private float speed = 0.75f;

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

        Transform renderedPlayerTransform = new Transform(getParent().getX() + Game.getCamera().getX(), getParent().getY() + Game.getCamera().getY(),
                getParent().getWidth(), getParent().getHeight());

        if (!whiteZone.intersects(getParent().getTransform())) {
            Game.getCamera().move(whiteZone.getFreeRelationX(renderedPlayerTransform), speed);
            Game.getCamera().move(whiteZone.getFreeRelationY(renderedPlayerTransform), speed);
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
