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

import de.edgelord.saltyengine.transform.Transform;

public class Directions {

    private int directions;

    /**
     * Appends the relation between the two given {@link Transform}s to the given
     * Directions.
     * That'll only work proper if the two Transforms intersect.
     *
     * @param root       the {@link Transform} from whose perspective to view
     * @param other      the {@link Transform} whose relation to the other to return
     * @param directions the Directions to append the relation to
     */
    public static void appendRelation(final Transform root, final Transform other, Directions directions) {

        float rootBottom = root.getY() + root.getHeight();
        float otherBottom = other.getY() + other.getHeight();
        float rootRight = root.getX() + root.getWidth();
        float otherRight = other.getX() + other.getWidth();

        float bottomCollision = otherBottom - root.getY();
        float topCollision = rootBottom - other.getY();
        float leftCollision = rootRight - other.getX();
        float rightCollision = otherRight - root.getX();

        if (topCollision < bottomCollision && topCollision < leftCollision && topCollision < rightCollision) {
            directions.setDirection(Direction.DOWN);
        }

        if (bottomCollision < topCollision && bottomCollision < leftCollision && bottomCollision < rightCollision) {
            directions.setDirection(Direction.UP);
        }

        if (rightCollision < leftCollision && rightCollision < topCollision && rightCollision < bottomCollision) {
            directions.setDirection(Direction.LEFT);
        }

        if (leftCollision < rightCollision && leftCollision < bottomCollision && leftCollision < topCollision) {
            directions.setDirection(Direction.RIGHT);
        }
    }

    /**
     * This method returns the relation between two {@link Transform}s as a Direction.
     * That'll only work proper if the two Transforms intersect.
     *
     * @param root  the <code>GameObject</code> from which perspective to view
     * @param other the <code>gameObject</code> from which the relation to
     *              <code>root</code> will be returned
     * @return the relation between the two {@link Transform}s as a Direction from
     * the perspective of <code>root</code>.
     */
    public static Direction getRelation(final Transform root, final Transform other) {

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
            return Direction.DOWN;
        }

        if (bottomCollision < topCollision && bottomCollision < leftCollision && bottomCollision < rightCollision) {
            return Direction.UP;
        }

        if (rightCollision < leftCollision && rightCollision < topCollision && rightCollision < bottomCollision) {
            return Direction.LEFT;
        }

        if (leftCollision < rightCollision && leftCollision < bottomCollision && leftCollision < topCollision) {
            return Direction.RIGHT;
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

        return Direction.EMPTY;
    }

    public static Direction getFreeRelationX(Transform root, Transform other) {

        if (root.getX() + root.getWidth() < other.getX()) {
            return Direction.LEFT;
        } else if (root.getX() > other.getX() + other.getWidth()) {
            return Direction.RIGHT;
        }

        return Direction.EMPTY;
    }

    public static Direction getFreeRelationY(Transform root, Transform other) {

        if (root.getY() + root.getHeight() < other.getY()) {
            return Direction.UP;
        } else if (root.getY() > other.getY() + other.getHeight()) {
            return Direction.DOWN;
        }

        return Direction.EMPTY;
    }

    /**
     * This method is used for mirroring Directions, for example for lastInput Direction.RIGHT,
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