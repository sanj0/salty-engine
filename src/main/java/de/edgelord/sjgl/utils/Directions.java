/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.utils;

import de.edgelord.sjgl.gameobject.GameObject;

public class Directions {

    private int directions;

    /**
     * This method returns the relation between two GameObject as a Direction.
     * When <code>root</code> is truly RIGHT to <code>other</code>, then this method
     * would return <code>Direction.RIGHT</code>.
     * <code>root</code> is truly RIGHT to <code>other</code> when it's middle
     * is RIGHT of it and at least one floating number of it's sides is in the dimension
     * of the sides of <code>other</code>.
     *
     * @param root  the <code>GameObject</code> from which perspective to view
     * @param other the <code>gameObject</code> from which the relation to
     *              <code>root</code> will be returned
     * @return the relation between the two <code>GameObject</code>s as a Direction from
     * the perspective of <code>root</code>.
     */
    public static Directions getGameObjectRelation(final GameObject root, final GameObject other) {

        /*if (root.getY() + root.getHeight() < other.getY()) {
            direction.setDirection(Direction.UP);
        }

        if (root.getY() > other.getY() + other.getHeight()) {
            direction.setDirection(Direction.DOWN);
        }

        if (root.getX() + root.getWidth() < other.getX()) {
            direction.setDirection(Direction.LEFT);
        }

        if (root.getX() > other.getX() + other.getWidth()) {
            direction.setDirection(Direction.RIGHT);
        }

        return direction;*/


        Directions returnDirections = new Directions();

        /*if (root.getX() + root.getWidth() > other.getX() && root.getX() < other.getX() + other.getWidth()) {

            if (root.getMiddle().isAbove(other.getMiddle())) {
                return Direction.UP;
            } else {
                return Direction.DOWN;
            }
        } else {

            if (root.getMiddle().isRight(other.getMiddle())) {
                return Direction.RIGHT;
            } else {
                return Direction.LEFT;
            }
        }*/

        /*float deltaX;
        float deltaY;
        Direction directionX;
        Direction directionY;



        if (root.getMiddle().isRight(other.getMiddle())) {
            deltaX = root.getX() + root.getWidth() - other.getX();
            directionX = Direction.RIGHT;
        } else {
            deltaX = other.getX() + other.getWidth() - root.getX();
            directionX = Direction.LEFT;
        }

        if (root.getMiddle().isAbove(other.getMiddle())) {
            deltaY = root.getY() + root.getHeight() - other.getY();
            directionY = Direction.UP;
        } else {
            deltaY = other.getY() + other.getHeight() - root.getY();
            directionY = Direction.DOWN;
        }

        if (deltaX > deltaY){
            returnDirections.setDirection(directionX);
        } else {
            returnDirections.setDirection(directionY);
        }*/

        float rootBottom = root.getY() + root.getHeight();
        float otherBottom = other.getY() + other.getHeight();
        float rootRight = root.getX() + root.getWidth();
        float otherRight = other.getX() + other.getWidth();

        float bottomCollision = otherBottom - root.getY();
        float topCollision = rootBottom - other.getY();
        float leftCollision = rootRight - other.getX();
        float rightCollision = otherRight - root.getX();

        if (topCollision < bottomCollision && topCollision < leftCollision && topCollision < rightCollision) {
            returnDirections.setDirection(Direction.DOWN);
        }

        if (bottomCollision < topCollision && bottomCollision < leftCollision && bottomCollision < rightCollision) {
            returnDirections.setDirection(Direction.UP);
        }

        if (rightCollision < leftCollision && rightCollision < topCollision && rightCollision < bottomCollision) {
            returnDirections.setDirection(Direction.LEFT);
        }

        if (leftCollision < rightCollision && leftCollision < bottomCollision && leftCollision < topCollision) {
            returnDirections.setDirection(Direction.RIGHT);
        }

        /*if (root.getMiddle().isAbove(other.getMiddle())) {
            returnDirections.setDirection(Direction.UP);
        } else {
            returnDirections.setDirection(Direction.DOWN);
        }

        if (root.getMiddle().isRight(other.getMiddle())) {
            returnDirections.setDirection(Direction.RIGHT);
        } else {
            returnDirections.setDirection(Direction.LEFT);
        }*/

        return returnDirections;
    }

    /**
     * This method is used for mirroring Directions, for example for input Direction.RIGHT,
     * it would return Direction.LEFT.
     *
     * @param direction the Direction to mirror
     * @return the mirrored Direction
     */
    public static Directions mirrorDirection(final Direction direction) {

        Directions returnDirections = new Directions();

        if (returnDirections.hasDirection(Direction.RIGHT)) {
            returnDirections.removeDirection(Direction.RIGHT);
            returnDirections.setDirection(Direction.LEFT);
        }

        if (returnDirections.hasDirection(Direction.LEFT)) {
            returnDirections.removeDirection(Direction.LEFT);
            returnDirections.setDirection(Direction.RIGHT);
        }

        if (returnDirections.hasDirection(Direction.UP)) {
            returnDirections.removeDirection(Direction.UP);
            returnDirections.setDirection(Direction.DOWN);
        }

        if (returnDirections.hasDirection(Direction.DOWN)) {
            returnDirections.removeDirection(Direction.DOWN);
            returnDirections.setDirection(Direction.UP);
        }

        return returnDirections;
    }

    /**
     * This method is used for mirroring BasicDirections, for example for input BasicDirection.y,
     * it would return BasicDirection.x.
     *
     * @param direction The BasicDirection to mirror
     * @return The mirrored BasicDirection
     */
    public static BasicDirection mirrorBasicDirection(final BasicDirection direction) {

        switch (direction) {

            case x:
                return BasicDirection.y;
            case y:
                return BasicDirection.x;
        }

        // If the switch statement above fails (impossible) return x
        return BasicDirection.x;
    }

    public enum BasicDirection {
        x, y
    }

    public enum Direction {
        RIGHT, /* 1 */
        LEFT,  /* 2 */
        UP,    /* 4 */
        DOWN   /* 8 */
    }

    public void setDirection(Direction direction) {
        int dir = getDirNumber(direction);
        this.directions |= dir;
    }

    public void removeDirection(Direction direction) {
        int dir = getDirNumber(direction);
        this.directions &= ~dir;
    }

    public boolean hasDirection(Direction direction) {
        int dir = getDirNumber(direction);
        return (this.directions & dir) == dir;
    }

    private int getDirNumber(Direction direction) {
        switch (direction) {

            case RIGHT:
                return 1;
            case LEFT:
                return 2;
            case UP:
                return 4;
            case DOWN:
                return 8;
        }
        return 0;
    }
}