package de.edgelord.saltyengine.gameobject.components;

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
