package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.location.Vector2f;

import java.awt.*;

public class RecalculateMiddleComponent extends GameObjectComponent {

    private Vector2f exactMiddle = new Vector2f(0, 0);
    private Coordinates middle = new Coordinates(0, 0);


    public RecalculateMiddleComponent(GameObject parent, String name) {
        super(parent, name);
    }

    @Override
    public void onFixedTick() {

        calculateMiddle();
        getParent().setMiddle(middle);
    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    private void calculateMiddle() {

        exactMiddle.setX(getParent().getX() + (getParent().getWidth() / 2));
        exactMiddle.setY(getParent().getY() + (getParent().getHeight() / 2));

        middle.parseCoordinates(exactMiddle);
    }

    public Vector2f getExactMiddle() {
        return exactMiddle;
    }
}
