/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class DumbPanel extends Container {

    private float arc = 15;

    public DumbPanel(Vector2f position, float width, float height) {
        super(position, width, height);
    }

    public DumbPanel(Transform transform) {
        this(transform.getPosition(), transform.getWidth(), transform.getHeight());
    }

    public DumbPanel(float x, float y, float width, float height) {
        this(new Vector2f(x, y), width, height);
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
        getChildElements().forEach(uiElement -> uiElement.onFixedTick());
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

    public float getArc() {
        return arc;
    }

    public void setArc(float arc) {
        this.arc = arc;
    }
}
