package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;
import de.edgelord.sjgl.utils.Directions;

import java.awt.*;

public class PushInTheOppositeDirectionOnCollision extends GameObjectComponent {

    public PushInTheOppositeDirectionOnCollision(GameObject parent, String name) {
        super(parent, name, PUSH_OUT_ON_COLLISION);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

        if (getParent().getLastDirection() != null) {
            switch (getParent().getLastDirection()) {

                case right:
                    getParent().move(e.getRoot().getX() + e.getRoot().getWidth() - getParent().getX(), Directions.Direction.right);
                    break;
                case left:
                    getParent().move(getParent().getX() + getParent().getWidth() - e.getRoot().getX(), Directions.Direction.left);
                    break;
                case up:
                    getParent().move(getParent().getY() + getParent().getHeight() - e.getRoot().getY(), Directions.Direction.up);
                    break;
                case down:
                    getParent().move(e.getRoot().getY() + e.getRoot().getHeight() - getParent().getY(), Directions.Direction.down);
                    break;
            }

        }
    }
}
