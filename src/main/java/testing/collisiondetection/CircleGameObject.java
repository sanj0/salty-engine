/*
 * Copyright 2020 Malte Dostal
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

package testing.collisiondetection;

import de.edgelord.saltyengine.collision.collider.CircleCollider;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.TransformRelationMode;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.TransformRelationUtil;

public class CircleGameObject extends GameObject {

    public CircleGameObject() {
        super(0, 0, 200, 200, "circle-game-object");

        setCollider(new CircleCollider(getTransform()));
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {
        TransformRelationUtil.positionRelativeTo(TransformRelationMode.CENTRE, Input.getCursor(), getTransform());
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(ColorUtil.PURPLE_COLOR);
        saltyGraphics.drawOval(this);
    }
}
