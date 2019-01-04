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
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.TransformRelationMode;

import java.util.List;

/**
 * An implementation of the modes from {@link TransformRelationMode} to position
 * one or more {@link Transform}(s) relative to a "super"-Transform
 */
public class TransformRelationUtil {

    public static void positionRelativeTo(TransformRelationMode mode, Transform superTransform, List<Transform> transforms) {
        positionRelativeTo(mode, superTransform, (Transform[]) transforms.toArray());
    }

    public static void positionRelativeTo(TransformRelationMode mode, GameObject superTransform, List<GameObject> transforms) {

        positionRelativeTo(mode, superTransform, (GameObject[]) transforms.toArray());
    }

    public static void positionRelativeTo(TransformRelationMode mode, GameObject superTransform, GameObject... transforms) {

        Transform[] transformsFromGameObjects = new Transform[transforms.length];

        for (int i = 0; i < transforms.length; i++) {
            transformsFromGameObjects[i] = transforms[i].getTransform();
        }

        positionRelativeTo(mode, superTransform.getTransform(), transformsFromGameObjects);
    }

    public static void positionRelativeTo(TransformRelationMode mode, Transform superTransform, Transform... transforms) {

        switch (mode) {

            case CENTRE:
                Coordinates2f centre = superTransform.getCentre();

                for (Transform transform : transforms) {
                    transform.positionByCentre(centre);
                }

                break;
            case CENTRE_X:
                float centreX = superTransform.getCentre().getX();

                for (Transform transform : transforms) {
                    transform.positionByCentre(new Coordinates2f(centreX, transform.getCentre().getY()));
                }

                break;
            case CENTRE_Y:

                float centreY = superTransform.getCentre().getY();

                for (Transform transform : transforms) {
                    transform.positionByCentre(new Coordinates2f(transform.getCentre().getX(), centreY));
                }

                break;
            case LEFT_EDGE:

                float leftEdge = superTransform.getX();

                for (Transform transform : transforms) {
                    transform.setX(leftEdge);
                }

                break;
            case RIGHT_EDGE:

                float rightEdge = superTransform.getMaxX();

                for (Transform transform : transforms) {
                    transform.setX(rightEdge - transform.getWidth());
                }

                break;
            case TOP_EDGE:

                float topEdge = superTransform.getY();

                for (Transform transform : transforms) {
                    transform.setY(topEdge);
                }

                break;
            case BOTTOM_EDGE:

                float bottomEdge = superTransform.getMaxY();

                for (Transform transform : transforms) {
                    transform.setY(bottomEdge - transform.getHeight());
                }

                break;
            case TOP_LEFT_CORNER:

                Coordinates2f topLeftCorner = superTransform.getPosition();

                for (Transform transform : transforms) {
                    transform.setPosition(topLeftCorner);
                }

                break;
            case TOP_RIGHT_CORNER:

                Coordinates2f topRightCorner = new Coordinates2f(superTransform.getMaxX(), superTransform.getY());

                for (Transform transform : transforms) {
                    transform.setX(topRightCorner.getX() - transform.getWidth());
                    transform.setY(topRightCorner.getY());
                }

                break;
            case BOTTOM_LEFT_CORNER:

                Coordinates2f bottomLeftCorner = new Coordinates2f(superTransform.getX(), superTransform.getMaxY());

                for (Transform transform : transforms) {
                    transform.setX(bottomLeftCorner.getX());
                    transform.setY(bottomLeftCorner.getY() - transform.getHeight());
                }

                break;
            case BOTTOM_RIGHT_CORNER:

                Coordinates2f bottomRightCorner = new Coordinates2f(superTransform.getMaxX(), superTransform.getMaxY());

                for (Transform transform : transforms) {
                    transform.setX(bottomRightCorner.getX() - transform.getWidth());
                    transform.setY(bottomRightCorner.getY() - transform.getHeight());
                }

                break;
        }
    }
}
