/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package testing.dummys;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;

public class DummyBlock extends GameObject {

    public DummyBlock(Coordinates coordinates, int width, int height) {
        super(coordinates, width, height);
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(GameObject other) {

    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

        graphics.drawRect(getCoordinates().getX(), getCoordinates().getY(), getWidth(), getHeight());
    }
}
