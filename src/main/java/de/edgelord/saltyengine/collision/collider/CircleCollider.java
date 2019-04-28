package de.edgelord.saltyengine.collision.collider;

import de.edgelord.saltyengine.collision.CollisionDetectionResult;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

public class CircleCollider extends Collider {

    /**
     * The constructor.
     */
    public CircleCollider() {
        super(1, CIRCLE_COLLIDER);
    }

    @Override
    public CollisionDetectionResult checkCollision(GameObject object1, GameObject object2) {

        Collider otherCollider = getOtherCollider(object1.getCollider(), object2.getCollider());
        GameObject parent = object1.getCollider() == otherCollider ? object2 : object1;
        GameObject other = parent == object1 ? object2 : object1;

        boolean collision = false;

        switch (otherCollider.getName()) {
            case HITBOX_COLLIDER:
                collision = collidesWithRect(parent.getTransform(), other.getTransform());
                break;

            case CIRCLE_COLLIDER:
                collision = collidesWithCircle(parent.getTransform(), other.getTransform());
                break;
        }

        return new CollisionDetectionResult(collision, object1.getTransform().getRelation(object2.getTransform()));
    }

    private boolean collidesWithRect(Transform circle, Transform rect) {

        Vector2f circleCentre = circle.getCentre();

        float closestX = clamp(circleCentre.getX(), rect.getX(), rect.getX() + rect.getWidth());
        float closestY = clamp(circleCentre.getY(), rect.getY() - rect.getHeight(), rect.getY());

        float distanceX = circleCentre.getX() - closestX;
        float distanceY = circleCentre.getY() - closestY;

        return Math.pow(distanceX, 2) + Math.pow(distanceY, 2) < Math.pow(circle.getWidth() / 2f, 2);
    }

    private static boolean collidesWithCircle(Transform circle1, Transform circle2) {
        Vector2f centre1 = circle1.getCentre();
        Vector2f centre2 = circle2.getCentre();

        return Math.pow(centre1.getX() - centre2.getX(), 2) + Math.pow(centre2.getY() - centre2.getY(), 2) <= Math.pow((circle1.getWidth() / 2f) + (circle2.getWidth() / 2f), 2);
    }

    public static float clamp(float value, float min, float max) {
        float x = value;
        if (x < min) {
            x = min;
        } else if (x > max) {
            x = max;
        }
        return x;
    }
}
