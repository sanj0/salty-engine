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
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;

import java.awt.*;

public class DebugLogGameObjectStat extends Component {

    private final FixedRate fixedRate = new FixedRate(getParent(), "de.edgelord.saltyengine.DebugLogGameObjectStat$1<tmp>", 100);

    public DebugLogGameObjectStat(final ComponentContainer parent, final String name) {
        super(parent, name, Components.CORE_COMPONENT);

        getParent().addComponent(fixedRate);
    }

    private void debugLog() {
        System.out.println("--------------------");
        System.out.println(getParent().getTag() + " stat:");
        System.out.println();

        System.out.println("-----");
        System.out.println("Transform:");
        System.out.println();
        System.out.println("x position = " + getParent().getX());
        System.out.println("y position = " + getParent().getY());
        System.out.println("width = " + (int) getParent().getWidth() + " <exact = " + getParent().getWidth() + ">");
        System.out.println("height = " + (int) getParent().getHeight() + " <exact = " + getParent().getHeight() + ">");

        System.out.println("-----");
        System.out.println("Components (" + getParent().getComponents().size() + "):");
        System.out.println();

        for (final Component component : getParent().getComponents()) {
            if (component.isEnabled()) {
                System.out.println("Enabled Component:");
            } else {
                System.out.println("Disabled Component:");
            }

            System.out.println("tag = " + component.getTag());
            System.out.println("name = " + component.getName());
            System.out.println();
        }

        System.out.println();
    }

    @Override
    public void onFixedTick() {

        if (fixedRate.now()) {
            debugLog();
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(Color.RED);
        saltyGraphics.drawOval(getParent().getX() - 20, getParent().getY() - 20, 20, 20);
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    public void setGate(final int gate) {
        fixedRate.setGate(gate);
    }
}
