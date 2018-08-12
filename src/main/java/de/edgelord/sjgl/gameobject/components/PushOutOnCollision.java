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

public class PushOutOnCollision extends GameObjectComponent {

    public PushOutOnCollision(final GameObject parent, final String name) {
        super(parent, name, PUSH_OUT_ON_COLLISION);
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(final Graphics2D graphics) {

    }

    @Override
    public void onCollision(final CollisionEvent e) {

        // System.out.println(e.getCollisionDirection());

        if (e.getCollisionDirection() != null) {

            switch (e.getCollisionDirection()) {

                case right:
                    getParent().move(e.getRoot().getX() + e.getRoot().getWidth() - getParent().getX(), Directions.Direction.right);
                    break;
                case left:
                    getParent().move(getParent().getX() + getParent().getWidth() - e.getRoot().getX(), Directions.Direction.left);
                    break;
                case  up:
                    getParent().move(getParent().getY() + getParent().getHeight() - e.getRoot().getY(), Directions.Direction.up);
                    break;
                case down:
                    getParent().move(e.getRoot().getY() + e.getRoot().getHeight() - getParent().getY(), Directions.Direction.down);
                    break;
            }
        }
    }
}
