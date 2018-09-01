/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.GameObjectComponent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;

@Deprecated
public class PushInTheOppositeDirectionOnCollision extends GameObjectComponent {

    public PushInTheOppositeDirectionOnCollision(final GameObject parent, final String name) {
        super(parent, name, GameObjectComponent.PUSH_OUT_ON_COLLISION);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

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
