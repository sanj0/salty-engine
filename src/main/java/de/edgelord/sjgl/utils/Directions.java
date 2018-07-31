/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.edgelord.sjgl.utils;

public class Directions {

    public enum Direction {
        right, left, up, down
    }

    public enum BasicDirection {
        x, y
    }

    /**
     * This method is used for mirroring Directions, for example for input Direction.right,
     * it would return Direction.left.
     *
     * @param direction the Direction to mirror
     * @return the mirrored Direction
     */
    public static Direction mirrorDirection(Direction direction) {

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
    public static BasicDirection mirrorBasicDirection(BasicDirection direction) {

        switch (direction) {

            case x:
                return BasicDirection.y;
            case y:
                return BasicDirection.x;
        }

        // If the switch statement above fails (impossible) return x
        return BasicDirection.x;
    }
}
