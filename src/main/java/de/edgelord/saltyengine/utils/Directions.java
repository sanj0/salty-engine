/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
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
    public static Direction mirrorDirection(final Direction direction) {

        switch (direction) {

            case RIGHT:
                return Direction.LEFT;
            case LEFT:
                return Direction.RIGHT;
            case UP:
                return Direction.DOWN;
            case DOWN:
                return Direction.UP;
            case EMPTY:
                return Direction.EMPTY;
        }

        return null;
    }

    /**
     * This method mirrors all the {@link Direction}s that the given {@link Directions} has and returns a new
     * instance of <code>Directions</code> that contains all of them.
     *
     * @param directions the directions to mirror
     * @return the mirrored directions
     */
    public static Directions mirrorDirections(Directions directions) {

        Directions mirroredDirections = new Directions();

        if (directions.hasDirection(Direction.UP)) {
            mirroredDirections.addDirection(Direction.DOWN);
        }

        if (directions.hasDirection(Direction.DOWN)) {
            mirroredDirections.addDirection(Direction.UP);
        }

        if (directions.hasDirection(Direction.LEFT)) {
            mirroredDirections.addDirection(Direction.RIGHT);
        }

        if (directions.hasDirection(Direction.RIGHT)) {
            mirroredDirections.addDirection(Direction.LEFT);
        }

        return mirroredDirections;
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

    /**
     * Mirrors this directions
     */
    public void mirror() {

        if (hasDirection(Direction.UP)) {
            removeDirection(Direction.UP);
            addDirection(Direction.DOWN);
        }

        if (hasDirection(Direction.DOWN)) {
            removeDirection(Direction.DOWN);
            addDirection(Direction.UP);
        }

        if (hasDirection(Direction.RIGHT)) {
            removeDirection(Direction.RIGHT);
            addDirection(Direction.LEFT);
        }

        if (hasDirection(Direction.LEFT)) {
            removeDirection(Direction.LEFT);
            addDirection(Direction.RIGHT);
        }
    }

    @Override
    public String toString() {
        return "Directions{" +
                "directions=" + directions +
                '}';
    }

    public void addDirection(Direction direction) {
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
}