/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.utils;

import de.edgelord.sjgl.gameobject.GameObject;

public class Directions {

    /**
     * This method returns the relation between two GameObject as a Direction.
     * When <code>root</code> is truly right to <code>other</code>, then this method
     * would return <code>Direction.right</code>.
     * <code>root</code> is truly right to <code>other</code> when it's middle
     * is right of it and at least one floating number of it's sides is in the dimension
     * of the sides of <code>other</code>.
     *
     * @param root  the <code>GameObject</code> from which perspective to view
     * @param other the <code>gameObject</code> from which the relation to
     *              <code>root</code> will be returned
     * @return the relation between the two <code>GameObject</code>s as a Direction from
     * the perspective of <code>root</code>.
     */
    public static Direction getGameObjectRelation(final GameObject root, final GameObject other) {

        if (root.getX() + root.getWidth() > other.getX() && root.getX() < other.getX() + other.getWidth()) {

            if (root.getMiddle().isAbove(other.getMiddle())) {
                return Direction.up;
            } else {
                return Direction.down;
            }
        } else if (root.getY() + root.getHeight() > other.getY() && root.getY() < other.getY() + other.getHeight()) {

            if (root.getMiddle().isRight(other.getMiddle())) {
                return Direction.right;
            } else {
                return Direction.left;
            }
        }

        return null;
    }

    /**
     * This method is used for mirroring Directions, for example for input Direction.right,
     * it would return Direction.left.
     *
     * @param direction the Direction to mirror
     * @return the mirrored Direction
     */
    public static Direction mirrorDirection(final Direction direction) {

        switch (direction) {

            case right:
                return Direction.left;
            case left:
                return Direction.right;
            case up:
                return Direction.down;
            case down:
                return Direction.up;
        }

        // If the switch statement above fails (impossible) return up
        return Direction.up;
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

    public enum Direction {
        right, left, up, down
    }

    public enum BasicDirection {
        x, y
    }
}
