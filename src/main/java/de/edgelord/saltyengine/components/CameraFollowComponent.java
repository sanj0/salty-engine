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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;

/**
 * This implementation of {@link Component} makes the {@link
 * de.edgelord.saltyengine.graphics.camera.Camera} of the game follow its
 * parent.
 * <p>
 * The {@link Transform} {@link #whiteZone} describes the area in which the
 * {@link #getParent() parent} of this Component can be located without the
 * camera to move. When ever the parent is outside this rectangle on a fixed
 * tick, the camera moves towards it by the specific amount of pixels stored in
 * {@link #speed}.
 * <p>
 * By default, {@link #whiteZone} is twice as wide and high as the {@link
 * #getParent()} of this Components and in the centre of the screen. It can be
 * changed/edited at any time.
 */
public class CameraFollowComponent extends Component<GameObject> {

    private Transform whiteZone;
    private float speed = 0.75f;

    public CameraFollowComponent(final GameObject parent, final String name) {
        super(parent, name, Components.CAMERA_COMPONENT);

        final float defaultWhiteZoneWidth = getParent().getWidth() * 2;
        final float defaultWhiteZoneHeight = getParent().getHeight() * 2;
        final float defaultWhiteZoneX = Game.getHost().getHorizontalCentrePosition(defaultWhiteZoneWidth);
        final float defaultWhiteZoneY = Game.getHost().getVerticalCentrePosition(defaultWhiteZoneHeight);

        whiteZone = new Transform(defaultWhiteZoneX, defaultWhiteZoneY, defaultWhiteZoneWidth, defaultWhiteZoneHeight);
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        // nothing to draw
    }

    @Override
    public void onFixedTick() {

        final Transform renderedPlayerTransform = new Transform(getParent().getX() - Game.getCamera().getX() * Game.getCamera().getScale(),
                getParent().getY() - Game.getCamera().getY() * Game.getCamera().getScale(),
                getParent().getWidth(), getParent().getHeight());

        if (!whiteZone.intersects(getParent().getTransform())) {
            Game.getCamera().move(whiteZone.getFreeRelationX(renderedPlayerTransform), -speed);
            Game.getCamera().move(whiteZone.getFreeRelationY(renderedPlayerTransform), -speed);
        }
    }

    @Override
    public void onCollision(final CollisionEvent e) {
        // nothing to do
    }

    public Transform getWhiteZone() {
        return whiteZone;
    }

    public void setWhiteZone(final Transform whiteZone) {
        this.whiteZone = whiteZone;
    }

    public void setWhiteZoneX(final float x) {
        whiteZone.setX(x);
    }

    public void setWhiteZoneY(final float y) {
        whiteZone.setY(y);
    }

    public void setWhiteZoneWidth(final float width) {
        whiteZone.setWidth(width);
    }

    public void setWhiteZoneHeight(final float height) {
        whiteZone.setHeight(height);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(final float speed) {
        this.speed = speed;
    }
}
