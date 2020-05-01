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

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.GraphicsConfiguration;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;

public abstract class PrimitivesRenderComponent extends RenderComponent {

    // The color with which the Component should render
    private Color color = Color.black;

    // Whether the Component should only DRAW (e.g. graphics.drawRectangle()) or FILL it (e.g. graphics.fillRectangle())
    private boolean fill = true;

    // The stroke (like a pen) with which the component should DRAW the primitives
    private float lineWidth = 3f;

    private SaltyImage primitiveImage;

    private Drawable primitiveDraw;

    /**
     * The default super constructor for gameObjectComponent, which takes in the parent GameObject and the
     * name, used as an id, for fishing specific Components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the Coordinate info from
     * @param name   the id-name for this Component
     * @see Component
     */
    public PrimitivesRenderComponent(final ComponentContainer parent, final String name) {
        super(parent, name, Components.PRIMITIVES_RENDER_COMPONENT);
        updateImage();
    }

    /**
     * Any classes extending PrimitivesRenderComponent has to override this method
     * for drawing e.g. a primitives like a Rectangle in RectangleRender
     *
     * @param saltyGraphics the SaltyGraphics to which the component should DRAW
     * @see Component
     */
    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(primitiveImage, getParent().getPosition());
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
        saltyGraphics.setStroke(new BasicStroke(lineWidth));
    }

    public abstract void updateImageData();

    public void updateImage() {
        updateImageData();
        primitiveImage = ImageUtils.createPrimitiveImage(saltyGraphics -> {
            setUpGraphics(saltyGraphics);
            primitiveDraw.draw(saltyGraphics);
        }, new Dimensions(getParent().getWidth() + (lineWidth * 2), getParent().getHeight() + (lineWidth * 2)), GraphicsConfiguration.renderingHints);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(final Color color) {
        this.color = color;
        updateImage();
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(final boolean fill) {
        this.fill = fill;
        updateImage();
    }

    public float getLineWidth() {
        return lineWidth;
    }

    public void setLineWidth(final float width) {
        this.lineWidth = width;
        updateImage();
    }

    protected Drawable getPrimitiveDraw() {
        return primitiveDraw;
    }

    protected void setPrimitiveDraw(final Drawable primitiveDraw) {
        this.primitiveDraw = primitiveDraw;
    }
}
