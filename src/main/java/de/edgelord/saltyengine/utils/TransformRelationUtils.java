package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.RelationMode;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.util.List;

/**
 * An implementation of the modes from {@link de.edgelord.saltyengine.transform.RelationMode} to position
 * one or more {@link Transform}(s) relative to a "super"-Transform
 */
public class TransformRelationUtils {

    public static void positionRelativeTo(RelationMode mode, Transform superTransform, List<Transform> transforms) {
        positionRelativeTo(mode, superTransform, (Transform[]) transforms.toArray());
    }

    public static void positionRelativeTo(RelationMode mode, GameObject superTransform, List<GameObject> transforms) {

        positionRelativeTo(mode, superTransform, (GameObject[]) transforms.toArray());
    }

    public static void positionRelativeTo(RelationMode mode, GameObject superTransform, GameObject... transforms) {

        Transform[] transformsFromGameObjects = new Transform[transforms.length];

        for (int i = 0; i < transforms.length; i++) {
            transformsFromGameObjects[i] = transforms[i].getTransform();
        }

        positionRelativeTo(mode, superTransform.getTransform(), transformsFromGameObjects);
    }

    public static void positionRelativeTo(RelationMode mode, Transform superTransform, Transform... transforms) {

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
                    transform.positionByCentre(new Vector2f(centreX, transform.getY()));
                }

                break;
            case CENTRE_Y:

                float centreY = superTransform.getCentre().getY();

                for (Transform transform : transforms) {
                    transform.positionByCentre(new Vector2f(transform.getX(), centreY));
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
