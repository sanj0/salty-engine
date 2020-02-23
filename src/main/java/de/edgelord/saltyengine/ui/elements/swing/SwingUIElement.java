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

package de.edgelord.saltyengine.ui.elements.swing;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import javax.swing.*;

public class SwingUIElement extends UIElement {

    private JComponent component;

    public SwingUIElement(JComponent component, Vector2f position, float width, float height) {
        super(position, width, height, UIElement.SWING_ELEMENT);
        this.component = component;

        //Game.getHost().addComponent(component);
        initComponent();
    }

    public SwingUIElement(JComponent component, Transform transform) {
        super(transform, UIElement.SWING_ELEMENT);
        this.component = component;

        Game.getHost().addComponent(component);
        initComponent();
    }

    private void initComponent() {
        component.setSize(getTransform().getWidthAsInt(), getTransform().getHeightAsInt());
        component.setLocation(Math.round(getX()), Math.round(getY()));
        component.setVisible(true);
    }

    @Override
    public void onRemove() {
        Game.getHost().removeComponent(component);
    }

    /**
     * Gets {@link #component}.
     *
     * @return the value of {@link #component}
     */
    public JComponent getComponent() {
        return component;
    }

    /**
     * Sets {@link #component}.
     *
     * @param component the new value of {@link #component}
     */
    public void setComponent(JComponent component) {
        this.component = component;
    }

    @Override
    public void drawBackground(SaltyGraphics saltyGraphics) {
        component.paintAll(saltyGraphics.getGraphics2D());
    }

    @Override
    public void drawForeground(SaltyGraphics saltyGraphics) {

    }
}
