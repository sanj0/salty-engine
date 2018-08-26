/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components.rendering;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.GameObjectComponent;

import java.awt.*;

public abstract class SimpleRenderComponent extends GameObjectComponent {

    // The color with which the Component should render
    private Color color = Color.black;

    // Whether the Component should only draw (e.g. graphics.drawRectangle()) or fill it (e.g. graphics.fillRectangle())
    private boolean fill = true;

    // The stroke (like a pen) with which the component should draw the primitives
    private Stroke stroke = new BasicStroke();

    /**
     * The default super constructor for gameObjectComponent, which takes in the parent GameObject and the
     * name, used as an id, for fishing specific Components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the Coordinate info from
     * @param name   the id-name for this Component
     * @see GameObjectComponent
     */
    public SimpleRenderComponent(final GameObject parent, final String name) {
        super(parent, name, GameObjectComponent.SIMPLE_RENDER_COMPONENT);
    }

    @Override
    public void onFixedTick() {

        // Is not needed for renderComponents, so prevent unnecessary Code
    }

    /**
     * Any classes extending SimpleRenderComponent has to override this method
     * for drawing e.g. a primitives like a Rectangle in RectangleRender
     *
     * @param graphics the Graphics2D to which the component should draw
     * @see GameObjectComponent
     */
    @Override
    public abstract void draw(Graphics2D graphics);

    @Override
    public void onCollision(final CollisionEvent e) {

        // Is not needed for renderComponents, so prevent unnecessary Code
    }


    /**
     * Sets the color and stroke of the given Graphics2D to the ones set UP in this class
     * So the user can always control how the component should render the primitives.
     *
     * @param graphics the Graphics2D which should be given the values
     * @see Graphics2D
     */
    protected void setUpGraphics(final Graphics2D graphics) {

        graphics.setColor(getColor());
        graphics.setStroke(getStroke());
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
