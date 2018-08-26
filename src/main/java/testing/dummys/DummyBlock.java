/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.gameobject.components.rendering.RectangleRender;
import de.edgelord.saltyengine.location.Coordinates;

import java.awt.*;

public class DummyBlock extends GameObject {

    public DummyBlock(Coordinates coordinates, int width, int height) {
        super(coordinates, width, height, "dumbest_block_ever");

        this.addComponent(new RectangleRender(this, "ract_renderDev"));
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
    public void onTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {
    }
}
