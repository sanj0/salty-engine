/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.util.Random;

public class DummyGameObject extends GameObject {

    public DummyGameObject(Vector2f position) {
        super(position.getX(), position.getY(), 100, 100, "dumbest_game-object_ever");
    }

    @Override
    public void initialize() {
    }

    @Override
    public void onCollision(CollisionEvent e) {
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }
}
