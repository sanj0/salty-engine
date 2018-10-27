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

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;

public abstract class SimpleRenderComponent extends Component {

    // The color with which the Component should render
    private Color color = Color.black;

    // Whether the Component should only DRAW (e.g. graphics.drawRectangle()) or FILL it (e.g. graphics.fillRectangle())
    private boolean fill = true;

    // The stroke (like a pen) with which the component should DRAW the primitives
    private Stroke stroke = new BasicStroke();

    /**
     * The default super constructor for gameObjectComponent, which takes in the parent GameObject and the
     * name, used as an id, for fishing specific Components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the Coordinate info from
     * @param name   the id-name for this Component
     * @see Component
     */
    public SimpleRenderComponent(final ComponentParent parent, final String name) {
        super(parent, name, Components.SIMPLE_RENDER_COMPONENT);
    }

    @Override
    public void onFixedTick() {

        // Is not needed for renderComponents, so prevent unnecessary Code
    }

    /**
     * Any classes extending SimpleRenderComponent has to override this method
     * for drawing e.g. a primitives like a Rectangle in RectangleRender
     *
     * @param saltyGraphics the SaltyGraphics to which the component should DRAW
     * @see Component
     */
    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    /**
     * Sets the necessary clipping to the given graphics
     *
     * @param saltyGraphics the graphics to set the clipping to
     */
    public void setClipping(SaltyGraphics saltyGraphics) {
        saltyGraphics.setClip(getParent().getTransform());
    }

    @Override
    public void onCollision(final CollisionEvent e) {

        // Is not needed for renderComponents, so prevent unnecessary Code
    }


    /**
     * Sets the color and stroke of the given Graphics2D to the ones set UP in this class
     * So the user can always control how the component should render the primitives.
     *
     * @param saltyGraphics the SaltyGraphics which should be prepared
     * @see Graphics2D
     */
    protected void setUpGraphics(final SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(getColor());
        saltyGraphics.setStroke(getStroke());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(final boolean fill) {
        this.fill = fill;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(final Stroke stroke) {
        this.stroke = stroke;
    }
}
