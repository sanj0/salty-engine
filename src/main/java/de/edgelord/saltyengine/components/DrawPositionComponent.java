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

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;

import java.awt.*;

public class DrawPositionComponent extends Component {

    public DrawPositionComponent(final ComponentContainer parent, final String name) {
        super(parent, name, Components.TECHNICAL_DRAW_COMPONENT);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 15));
        saltyGraphics.setColor(Color.BLACK);

        saltyGraphics.drawText(getParent().getX(), getParent().getX(), getParent().getY(), SaltyGraphics.TextAnchor.BOTTOM_LEFT_CORNER);
        saltyGraphics.drawText(getParent().getY(), getParent().getX(), getParent().getY(), SaltyGraphics.TextAnchor.TOP_RIGHT_CORNER);
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }
}
