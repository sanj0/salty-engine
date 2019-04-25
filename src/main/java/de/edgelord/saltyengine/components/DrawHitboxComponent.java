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
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;

import java.awt.*;

public class DrawHitboxComponent extends Component<GameObject> {

    private static Color color = new Color(1, 0, 0, 0.25f);

    public DrawHitboxComponent(GameObject parent, String name) {
        super(parent, name, Components.TECHNICAL_DRAW_COMPONENT);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.resetObjectRotation(getParent());
        saltyGraphics.setColor(color);
        saltyGraphics.drawRect(getParent().getHitbox().getTransform());
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
