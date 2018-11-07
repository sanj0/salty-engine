/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.utils;

public class Directions {

    private int directions;

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
     * This method is used for mirroring BasicDirections, for example for lastInput BasicDirection.y,
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
        DOWN,   /* 8 */
        EMPTY /* 16 */
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
            case EMPTY:
                return 16;
        }
        return 0;
    }
}