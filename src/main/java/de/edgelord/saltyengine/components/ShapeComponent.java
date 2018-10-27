/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class ShapeComponent extends Component {

    private Shape shape;
    private float arcWidth = 15f, arcHeight = 15f;

    private boolean drawing = true;

    public enum Shape {
        RECT,
        OVAL,
        ROUND_RECT
    }

    public ShapeComponent(ComponentParent parent, String name, Shape shape) {
        super(parent, name, Components.SIMPLE_RENDER_COMPONENT);

        this.shape = shape;
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (drawing) {
            switch (shape) {

                case RECT:
                    saltyGraphics.drawRect(getParent());
                    break;
                case OVAL:
                    saltyGraphics.drawOval(getParent());
                    break;
                case ROUND_RECT:
                    saltyGraphics.drawRoundRect(getParent(), arcWidth, arcHeight);
                    break;
            }
        }
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void disableDrawing() {
        drawing = false;
    }

    public void enableDrawing() {
        drawing = true;
    }

    public void setDrawing(boolean drawing) {
        this.drawing = drawing;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public float getArcWidth() {
        return arcWidth;
    }

    public void setArcWidth(float arcWidth) {
        this.arcWidth = arcWidth;
    }

    public float getArcHeight() {
        return arcHeight;
    }

    public void setArcHeight(float arcHeight) {
        this.arcHeight = arcHeight;
    }

    public void setArc(float arc) {
        this.arcWidth = arc;
        this.arcHeight = arc;
    }
}
