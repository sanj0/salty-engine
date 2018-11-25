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
                Vector2f centre = superTransform.getCentre();

                for (Transform transform : transforms) {
                    transform.positionByCentre(centre);
                }

                break;
            case CENTRE_X:
                float centreX = superTransform.getCentre().getX();

                for (Transform transform : transforms) {
                    transform.positionByCentre(new Vector2f(centreX, transform.getCentre().getY()));
                }

                break;
            case CENTRE_Y:

                float centreY = superTransform.getCentre().getY();

                for (Transform transform : transforms) {
                    transform.positionByCentre(new Vector2f(transform.getCentre().getX(), centreY));
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

                Vector2f topLeftCorner = superTransform.getPosition();

                for (Transform transform : transforms) {
                    transform.setPosition(topLeftCorner);
                }

                break;
            case TOP_RIGHT_CORNER:

                Vector2f topRightCorner = new Vector2f(superTransform.getMaxX(), superTransform.getY());

                for (Transform transform : transforms) {
                    transform.setX(topRightCorner.getX() - transform.getWidth());
                    transform.setY(topRightCorner.getY());
                }

                break;
            case BOTTOM_LEFT_CORNER:

                Vector2f bottomLeftCorner = new Vector2f(superTransform.getX(), superTransform.getMaxY());

                for (Transform transform : transforms) {
                    transform.setX(bottomLeftCorner.getX());
                    transform.setY(bottomLeftCorner.getY() - transform.getHeight());
                }

                break;
            case BOTTOM_RIGHT_CORNER:

                Vector2f bottomRightCorner = new Vector2f(superTransform.getMaxX(), superTransform.getMaxY());

                for (Transform transform : transforms) {
                    transform.setX(bottomRightCorner.getX() - transform.getWidth());
                    transform.setY(bottomRightCorner.getY() - transform.getHeight());
                }

                break;
        }
    }
}
