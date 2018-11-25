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
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;

public class DebugLogGameObjectStat extends Component {

    private FixedRate fixedRate = new FixedRate(getParent(), "de.edgelord.saltyengine.DebugLogGameObjectStat$1<tmp>", 100);

    public DebugLogGameObjectStat(ComponentContainer parent, String name) {
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

        for (Component component : getParent().getComponents()) {
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
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(Color.RED);
        saltyGraphics.drawOval(getParent().getX() - 20, getParent().getY() - 20, 20, 20);
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void setGate(int gate) {
        fixedRate.setGate(gate);
    }
}
