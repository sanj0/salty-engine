package de.edgelord.sjgl.gameobject.components.rendering;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;

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
    public SimpleRenderComponent(GameObject parent, String name) {
        super(parent, name);
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
    public abstract void draw(Graphics2D graphics);

    @Override
    public void onCollision(GameObject other) {

        // Is not needed for renderComponents, so prevent unnecessary Code
    }


    /**
     * Sets the color and stroke of the given Graphics2D to the ones set up in this class
     * So the user can always control how the component should render the primitives.
     *
     * @param graphics the Graphics2D which should be given the values
     * @see Graphics2D
     */
    protected void setUpGraphics(Graphics2D graphics) {

        graphics.setColor(getColor());
        graphics.setStroke(getStroke());
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }
}
