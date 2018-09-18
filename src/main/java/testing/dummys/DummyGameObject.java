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

    private Random random = new Random();
    private boolean makeMove = true;

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

        if (makeMove)
            makeMeMove();
    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        saltyGraphics.drawOval(getX(), getCoordinates().getY(), getWidth(), getHeight());
    }

    private void makeMeMove() {

        int direction = random.nextInt(4);

        System.out.println("saltyengine 1.0.devel > DummyGameObject > \"Random number for direction is: " + direction + ", we will basicMove the GameObject " + makeMove + "\"");

        if (makeMove) {
            if (direction == 0 && getCoordinates().getX() != 1100)
                getCoordinates().changeX(1);
            if (direction == 1 && getCoordinates().getY() != 850)
                getCoordinates().changeY(1);
            if (direction == 2 && getCoordinates().getX() != 0)
                getCoordinates().changeX(-1);
            if (direction == 3 && getCoordinates().getY() != 0)
                getCoordinates().changeY(-1);
        }
    }

    public void setMakeMove(boolean makeMove) {
        this.makeMove = makeMove;
    }
}
