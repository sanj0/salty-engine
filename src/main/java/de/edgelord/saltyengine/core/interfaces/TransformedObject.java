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

package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.transform.*;
import de.edgelord.saltyengine.utils.Directions;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public interface TransformedObject {

    Transform getTransform();

    void setTransform(Transform transform);

    Directions getLockedDirections();

    void setLockedDirections(Directions directions);

    default Dimensions getDimensions() {
        return getTransform().getDimensions();
    }

    default void setDimensions(final Dimensions dimensions) {
        getTransform().setDimensions(dimensions);
    }

    default Vector2f getPosition() {
        return getTransform().getPosition();
    }

    default void setPosition(final Vector2f position) {
        getTransform().setPosition(position);
    }

    default Coordinates getCoordinates() {
        return getPosition().convertToCoordinates();
    }

    default Rotation getRotation() {
        return getTransform().getRotation();
    }

    default void setRotation(final Rotation rotation) {
        getTransform().setRotation(rotation);
    }

    default float getRotationDegrees() {
        return getRotation().getRotationDegrees();
    }

    default void setRotationDegrees(final float rotationDegrees) {
        getTransform().setRotationDegrees(rotationDegrees);
    }

    default float getWidth() {
        return getDimensions().getWidth();
    }

    default void setWidth(final float width) {
        getTransform().setWidth(width);
    }

    default float getHeight() {
        return getDimensions().getHeight();
    }

    default void setHeight(final float height) {
        getTransform().setHeight(height);
    }

    default float getX() {
        return getPosition().getX();
    }

    default void setX(final float x) {
        getTransform().setX(x);
    }

    default float getY() {
        return getPosition().getY();
    }

    default void setY(final float y) {
        getTransform().setY(y);
    }

    default void positionByCentre(final Vector2f centre) {
        getTransform().positionByCentre(centre);
    }

    /**
     * Moves the object by the give amount in the direction it is facing according to the {@link #getRotation()}
     *
     * @param delta the distance for the movement
     */
    default void moveToFacedDirection(final float delta) {
        moveInAngle(getRotationDegrees(), delta);
    }

    default void moveInAngle(final float degrees, final float delta) {
        final double radians = Math.toRadians(degrees);

        basicMove(delta * (float) Math.cos(radians), Directions.BasicDirection.x);
        basicMove(delta * (float) Math.sin(radians), Directions.BasicDirection.y);
    }

    /**
     * **EXPERIMENTAL**
     *
     * @return the cartesian coordinates for the current rotation
     */
    default Vector2f getCartesianCoordinates2f() {
        return new Vector2f((float) Math.cos(getRotationDegrees()), (float) Math.sin(getRotationDegrees()));
    }

    default void basicMove(final float delta, final Directions.BasicDirection direction) {

        if (direction == Directions.BasicDirection.x) {
            moveX(delta);
        } else {
            moveY(delta);
        }
    }

    default void move(float delta, final Directions.Direction direction) {
        if (getLockedDirections().hasDirection(direction)) {
            return;
        }

        // Check if delta is negative and if so, mirror its value
        if (delta < 0f) {
            delta = delta * (-1);
        }

        switch (direction) {

            case RIGHT:
                basicMove(delta, Directions.BasicDirection.x);
                break;
            case LEFT:
                basicMove(-delta, Directions.BasicDirection.x);
                break;
            case UP:
                basicMove(-delta, Directions.BasicDirection.y);
                break;
            case DOWN:
                basicMove(delta, Directions.BasicDirection.y);
                break;
        }
    }

    default void moveY(final float delta) {

        if (delta > 0f) {
            if (getLockedDirections().hasDirection(Directions.Direction.DOWN)) {
                return;
            }
        } else {
            if (getLockedDirections().hasDirection(Directions.Direction.UP)) {
                return;
            }
        }

        getTransform().setY(getY() + delta);
    }

    default void moveX(final float delta) {

        if (delta > 0f) {
            if (getLockedDirections().hasDirection(Directions.Direction.RIGHT)) {
                return;
            }
        } else {
            if (getLockedDirections().hasDirection(Directions.Direction.LEFT)) {
                return;
            }
        }

        getTransform().setX(getX() + delta);
    }
}
