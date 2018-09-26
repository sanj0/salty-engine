/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.rendering;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.interfaces.ComponentParent;
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
