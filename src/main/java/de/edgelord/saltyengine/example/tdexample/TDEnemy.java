/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.example.tdexample;

import de.edgelord.saltyengine.components.DrawHitboxComponent;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;

public class TDEnemy extends GameObject {

    private int targetIndex = 0;
    private Vector2f currentTarget;
    private Transform targetTransform;

    public TDEnemy() {
        super(0, 0, 50, 50, "tdenemy");

        updateTarget();

        addComponent(new DrawHitboxComponent(this, "draw-hitbox"));
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {

        getTransform().rotateToPoint(currentTarget);
        moveToFacedDirection(.5f);

        if (getTransform().contains(targetTransform)) {
            nextTarget();
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.PURPLE_COLOR);
        saltyGraphics.drawRect(this);
    }

    /**
     * Sets {@link #currentTarget}.
     *
     * @param currentTarget the new value of {@link #currentTarget}
     */
    public void setCurrentTarget(Vector2f currentTarget) {
        this.targetTransform = new Transform(currentTarget, Dimensions.one());
        this.currentTarget = targetTransform.getCentre();
    }

    private void updateTarget() {
        setCurrentTarget(TDTrack.getPoints().get(targetIndex));
    }

    public void nextTarget() {
        targetIndex++;
        updateTarget();
    }
}
