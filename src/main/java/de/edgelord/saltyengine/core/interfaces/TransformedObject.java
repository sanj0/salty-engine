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

import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Rotation;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import javax.naming.OperationNotSupportedException;

public interface TransformedObject {

    Transform getTransform();

    void setTransform(Transform transform);

    default Dimensions getDimensions() {
        return getTransform().getDimensions();
    }

    default Vector2f getPosition() {
        return getTransform().getPosition();
    }

    default Rotation getRotation() {
        return getTransform().getRotation();
    }

    default void setRotation(Rotation rotation) {
        getTransform().setRotation(rotation);
    }

    default float getRotationDegrees() {
        return getRotation().getRotationDegrees();
    }

    default void setRotationDegrees(float rotationDegrees) {
        getTransform().setRotationDegrees(rotationDegrees);
    }

    default float getWidth() {
        return getDimensions().getWidth();
    }

    default float getHeight() {
        return getDimensions().getHeight();
    }

    default float getX() {
        return getPosition().getX();
    }

    default float getY() {
        return getPosition().getY();
    }

    default void setDimensions(Dimensions dimensions) {
        getTransform().setDimensions(dimensions);
    }

    default void setPosition(Vector2f position) {
        getTransform().setPosition(position);
    }

    default void setWidth(float width) {
        getTransform().setWidth(width);
    }

    default void setHeight(float height) {
        getTransform().setHeight(height);
    }

    default void setX(float x) {
        getTransform().setX(x);
    }

    default void setY(float y) {
        getTransform().setY(y);
    }

    default void positionByCentre(Vector2f centre) {
        getTransform().positionByCentre(centre);
    }

    /**
     * **NOT YET SUPPORTED**
     * Moves the object by the give amount in the direction it is facing according to the {@link #getRotation()}
     *
     * @param delta the distance for the movement
     */
    default void moveToFacedDirection(float delta) {
        return;
        /*
        Vector2f cartesian = getCartesianVector();

        setX(getX() + cartesian.getX() * delta);
        setY(getY() + cartesian.getY() * delta);
        */
    }

    /**
     * **EXPERIMENTAL**
     *
     * @return the cartesian coordinates for the current rotation
     */
    default Vector2f getCartesianVector() {
        return new Vector2f((float) Math.cos(getRotationDegrees()), (float) Math.sin(getRotationDegrees()));
    }

    default void basicMove(final float delta, final Directions.BasicDirection direction) {

        if (direction == Directions.BasicDirection.x) {
            setX(getX() + delta);
        } else {
            setY(getY() + delta);
        }
    }

    default void move(float delta, final Directions.Direction direction) {

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
        getTransform().setY(getY() + delta);
    }

    default void moveX(final float delta) {
        getTransform().setX(getX() + delta);
    }
}
