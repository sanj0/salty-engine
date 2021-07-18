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

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.TransformRelationMode;
import de.edgelord.saltyengine.transform.Vector2f;

import java.util.List;

/**
 * An implementation of the modes from {@link TransformRelationMode} to position
 * one or more {@link Transform}(s) relative to a "super"-Transform
 */
public class TransformRelationUtil {

    private TransformRelationUtil() {
    }

    public static void positionRelativeTo(final TransformRelationMode mode, final Transform superTransform, final List<Transform> transforms) {
        positionRelativeTo(mode, superTransform, transforms.toArray(new Transform[0]));
    }

    public static void positionRelativeTo(final TransformRelationMode mode, final GameObject superTransform, final List<GameObject> transforms) {
        positionRelativeTo(mode, superTransform, transforms.toArray(new GameObject[0]));
    }

    public static void positionRelativeTo(final TransformRelationMode mode, final GameObject superTransform, final GameObject... transforms) {
        final Transform[] transformsFromGameObjects = new Transform[transforms.length];

        for (int i = 0; i < transforms.length; i++) {
            transformsFromGameObjects[i] = transforms[i].getTransform();
        }

        positionRelativeTo(mode, superTransform.getTransform(), transformsFromGameObjects);
    }

    public static void positionRelativeTo(final TransformRelationMode mode, final Transform superTransform, final Transform... transforms) {
        switch (mode) {
            case CENTRE:
                final Vector2f centre = superTransform.getCentre();

                for (final Transform transform : transforms) {
                    transform.positionByCentre(centre);
                }

                break;
            case CENTRE_X:
                final float centreX = superTransform.getCentre().getX();

                for (final Transform transform : transforms) {
                    transform.positionByCentre(new Vector2f(centreX, transform.getCentre().getY()));
                }

                break;
            case CENTRE_Y:

                final float centreY = superTransform.getCentre().getY();

                for (final Transform transform : transforms) {
                    transform.positionByCentre(new Vector2f(transform.getCentre().getX(), centreY));
                }

                break;
            case LEFT_EDGE:

                final float leftEdge = superTransform.getX();

                for (final Transform transform : transforms) {
                    transform.setX(leftEdge);
                }

                break;
            case RIGHT_EDGE:

                final float rightEdge = superTransform.getMaxX();

                for (final Transform transform : transforms) {
                    transform.setX(rightEdge - transform.getWidth());
                }

                break;
            case TOP_EDGE:

                final float topEdge = superTransform.getY();

                for (final Transform transform : transforms) {
                    transform.setY(topEdge);
                }

                break;
            case BOTTOM_EDGE:

                final float bottomEdge = superTransform.getMaxY();

                for (final Transform transform : transforms) {
                    transform.setY(bottomEdge - transform.getHeight());
                }

                break;
            case TOP_LEFT_CORNER:

                final Vector2f topLeftCorner = superTransform.getPosition();

                for (final Transform transform : transforms) {
                    transform.setPosition(topLeftCorner);
                }

                break;
            case TOP_RIGHT_CORNER:

                final Vector2f topRightCorner = new Vector2f(superTransform.getMaxX(), superTransform.getY());

                for (final Transform transform : transforms) {
                    transform.setX(topRightCorner.getX() - transform.getWidth());
                    transform.setY(topRightCorner.getY());
                }

                break;
            case BOTTOM_LEFT_CORNER:

                final Vector2f bottomLeftCorner = new Vector2f(superTransform.getX(), superTransform.getMaxY());

                for (final Transform transform : transforms) {
                    transform.setX(bottomLeftCorner.getX());
                    transform.setY(bottomLeftCorner.getY() - transform.getHeight());
                }

                break;
            case BOTTOM_RIGHT_CORNER:

                final Vector2f bottomRightCorner = new Vector2f(superTransform.getMaxX(), superTransform.getMaxY());

                for (final Transform transform : transforms) {
                    transform.setX(bottomRightCorner.getX() - transform.getWidth());
                    transform.setY(bottomRightCorner.getY() - transform.getHeight());
                }

                break;
        }
    }
}
