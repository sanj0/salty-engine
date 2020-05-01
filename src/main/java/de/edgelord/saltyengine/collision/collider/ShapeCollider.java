/*
 * Copyright 2019 Malte Dostal
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
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

/**
 * This implementation determines the {@link CollisionDetectionResult} with a {@link #shape Shape}.
 */
public class ShapeCollider extends Collider {

    /**
     * The {@link Shape} used for collision detection.
     */
    private Shape shape;

    /**
     * The constructor.
     *
     * @param shape the shape
     */
    public ShapeCollider(final Shape shape) {
        super(2, SHAPE_COLLIDER);

        this.shape = shape;
    }

    @Override
    public CollisionDetectionResult checkCollision(final GameObject object1, final GameObject object2) {

        final Collider otherCollider = getOtherCollider(object1.getCollider(), object2.getCollider());
        final GameObject otherGameObject = object1.getCollider() == otherCollider ? object1 : object2;

        final Area collisionArea = new Area(shape);

        switch (otherCollider.getType()) {
            case HITBOX_COLLIDER:
                collisionArea.intersect(new Area(otherGameObject.getHitbox().getTransform().getRect()));
                return new CollisionDetectionResult(!collisionArea.isEmpty(), new Transform(shape.getBounds()).getRelation(otherGameObject.getHitbox().getTransform()));

            case CIRCLE_COLLIDER:
                final CircleCollider collider = (CircleCollider) otherCollider;
                final Transform hitbox = collider.getHitbox();
                collisionArea.intersect(new Area(new Ellipse2D.Float(hitbox.getX(), hitbox.getY(), hitbox.getWidth(), hitbox.getHeight())));
                return new CollisionDetectionResult(!collisionArea.isEmpty(), new Transform(shape.getBounds()).getRelation(otherGameObject.getHitbox().getTransform()));

            case SHAPE_COLLIDER:
                final ShapeCollider shapeCollider = (ShapeCollider) otherCollider;
                collisionArea.intersect(new Area(shapeCollider.shape));
                return new CollisionDetectionResult(!collisionArea.isEmpty(), new Transform(shape.getBounds()).getRelation(new Transform(shapeCollider.shape.getBounds())));
        }

        return new CollisionDetectionResult(false, Directions.Direction.EMPTY);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(final Shape shape) {
        this.shape = shape;
    }
}
