/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;
import de.edgelord.sjgl.utils.Directions;

import java.awt.*;

public class PushInTheOppositeDirectionOnCollision extends GameObjectComponent {

    public PushInTheOppositeDirectionOnCollision(final GameObject parent, final String name) {
        super(parent, name, GameObjectComponent.PUSH_OUT_ON_COLLISION);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(final Graphics2D graphics) {

    }

    @Override
    public void onCollision(final CollisionEvent e) {

        if (getParent().getLastDirection() != null) {
            switch (getParent().getLastDirection()) {

                case RIGHT:
                    getParent().move(e.getRoot().getX() + e.getRoot().getWidth() - getParent().getX(), Directions.Direction.RIGHT);
                    break;
                case LEFT:
                    getParent().move(getParent().getX() + getParent().getWidth() - e.getRoot().getX(), Directions.Direction.LEFT);
                    break;
                case UP:
                    getParent().move(getParent().getY() + getParent().getHeight() - e.getRoot().getY(), Directions.Direction.UP);
                    break;
                case DOWN:
                    getParent().move(e.getRoot().getY() + e.getRoot().getHeight() - getParent().getY(), Directions.Direction.DOWN);
                    break;
            }

        }
    }
}
