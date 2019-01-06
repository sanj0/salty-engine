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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class DumbPanel extends Container {

    private float arc = 15;

    public DumbPanel(Coordinates2f position, float width, float height) {
        super(position, width, height);
    }

    public DumbPanel(Transform transform) {
        this(transform.getPosition(), transform.getWidth(), transform.getHeight());
    }

    public DumbPanel(float x, float y, float width, float height) {
        this(new Coordinates2f(x, y), width, height);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        // Suppress the clipping of all child elements
        suppressAllClipping();

        // Set clipping area
        saltyGraphics.setClip(new RoundRectangle2D.Float(getX(), getY(), getWidth(), getHeight(), arc, arc));

        // Draw background
        saltyGraphics.setColor(getBackgroundColor());
        saltyGraphics.drawRoundRect(this, arc);

        // Draw the child elements
        getChildElements().forEach(uiElement -> uiElement.draw(saltyGraphics));

        // Reset Clipping area
        saltyGraphics.resetClip();
    }

    @Override
    public void onFixedTick() {
        getChildElements().forEach(UIElement::onFixedTick);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        getChildElements().forEach(uiElement -> mousePressed(e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseReleased(e));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseClicked(e));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseMoved(e));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        getChildElements().forEach(uiElement -> uiElement.keyPressed(e));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        getChildElements().forEach(uiElement -> uiElement.keyReleased(e));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        getChildElements().forEach(uiElement -> uiElement.keyTyped(e));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseDragged(e));
    }

    @Override
    public void mouseExitedScreen(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseExitedScreen(e));
    }

    @Override
    public void mouseEnteredScreen(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseEnteredScreen(e));
    }

    @Override
    public void mouseWheelMoved(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseWheelMoved(e));
    }

    public float getArc() {
        return arc;
    }

    public void setArc(float arc) {
        this.arc = arc;
    }
}
