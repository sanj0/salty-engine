package de.me.edgelord.sjgl.gameobject.components.rendering;

import de.me.edgelord.sjgl.gameobject.GameObject;

import java.awt.*;

public class RectangleRender extends SimpleRenderComponent {

    public RectangleRender(GameObject parent, String name) {
        super(parent, name);
    }

    @Override
    public void draw(Graphics2D graphics) {

        setUpGraphics(graphics);

        if (isFill()) {
            graphics.fillRect(getParent().getCoordinates().getX(), getParent().getCoordinates().getY(), getParent().getWidth(), getParent().getHeight());
        } else {
            graphics.drawRect(getParent().getCoordinates().getX(), getParent().getCoordinates().getY(), getParent().getWidth(), getParent().getHeight());
        }
    }
}
