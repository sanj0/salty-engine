/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.collision.collider;

import de.edgelord.saltyengine.collision.CollisionDetectionResult;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.GeneralUtil;

/**
 * An implementation of {@link Collider} that uses
 * a circle as the "hitbox".
 * Using CircleColliders only would make the collision detection very fast. <p>
 * <p>
 * Note that for the circle, the width of {@link de.edgelord.saltyengine.hitbox.Hitbox}
 * is being used and that the height is therefore ignored, but as the centre
 * of the circle is needed, the user should always ensure that width and height
 * are equal.
 */
public class CircleCollider extends Collider implements TransformedObject {

    private Transform hitbox;
    private Directions lockedDirections = new Directions();


    /**
     * The constructor.
     */
    public CircleCollider(Transform hitbox) {
        super(1, CIRCLE_COLLIDER);
        this.hitbox = hitbox;
    }

    public CircleCollider(Vector2f position, Dimensions dimensions) {
        this(new Transform(position, dimensions));
    }

    @Override
    public CollisionDetectionResult checkCollision(GameObject object1, GameObject object2) {
        Collider otherCollider = getOtherCollider(object1.getCollider(), object2.getCollider());
        GameObject otherGameObject = object1.getCollider() == otherCollider ? object1 : object2;

        switch (otherCollider.getType()) {
            case HITBOX_COLLIDER:
                Vector2f centre = hitbox.getCentre();
                Transform otherHitbox = otherGameObject.getHitbox().getTransform();
                float closestX = GeneralUtil.clamp(centre.getX(), otherHitbox.getX(), otherHitbox.getX() + otherHitbox.getWidth());
                float closestY = GeneralUtil.clamp(centre.getY(), otherHitbox.getY(), otherHitbox.getY() + otherHitbox.getHeight());

                return new CollisionDetectionResult(new Vector2f(closestX, closestY).distance(centre) < getWidth() / 2f, hitbox.getRelation(otherHitbox));

            case CIRCLE_COLLIDER:
                CircleCollider otherCircleCollider = (CircleCollider) otherCollider;
                Vector2f centre1 = getHitbox().getCentre();
                Vector2f centre2 = otherCircleCollider.getHitbox().getCentre();
                boolean collision = centre1.distance(centre2) <= hitbox.getWidth() / 2f + otherCircleCollider.hitbox.getWidth() / 2f;

                return new CollisionDetectionResult(collision, hitbox.getRelation(otherCircleCollider.hitbox));
        }

        return new CollisionDetectionResult(false, Directions.Direction.EMPTY);
    }

    /**
     * Gets {@link #hitbox}.
     *
     * @return the value of {@link #hitbox}
     */
    public Transform getHitbox() {
        return hitbox;
    }

    /**
     * Sets {@link #hitbox}.
     *
     * @param hitbox the new value of {@link #hitbox}
     */
    public void setHitbox(Transform hitbox) {
        this.hitbox = hitbox;
    }

    @Override
    public Transform getTransform() {
        return hitbox;
    }

    @Override
    public void setTransform(Transform transform) {
        this.hitbox = transform;
    }

    @Override
    public void setLockedDirections(Directions directions) {
        this.lockedDirections = directions;
    }

    @Override
    public Directions getLockedDirections() {
        return lockedDirections;
    }
}
