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

/**
 * This implementation determines the {@link CollisionDetectionResult} with a {@link #shape Shape}.
 * WARNING: this is not tested yet!
 */
public class ShapeCollider extends Collider {

    private Shape shape;

    /**
     * The constructor.
     *
     * @param shape the shape
     */
    public ShapeCollider(Shape shape) {
        super(2, SHAPE_COLLIDER);

        this.shape = shape;
    }

    @Override
    public CollisionDetectionResult checkCollision(GameObject object1, GameObject object2) {

        Collider otherCollider = getOtherCollider(object1.getCollider(), object2.getCollider());
        GameObject otherGameObject = object1.getCollider() == otherCollider ? object1 : object2;

        Area collisionArea = new Area(shape);

        switch (otherCollider.getName()) {
            case HITBOX_COLLIDER :
                collisionArea.intersect(new Area(otherGameObject.getHitbox().getTransform().getRect()));
                return new CollisionDetectionResult(!collisionArea.isEmpty(), new Transform(shape.getBounds()).getRelation(otherGameObject.getHitbox().getTransform()));

            case SHAPE_COLLIDER :
                ShapeCollider shapeCollider = (ShapeCollider) otherCollider;
                collisionArea.intersect(new Area(shapeCollider.shape));
                return new CollisionDetectionResult(!collisionArea.isEmpty(), new Transform(shape.getBounds()).getRelation(new Transform(shapeCollider.shape.getBounds())));
        }

        return new CollisionDetectionResult(false, Directions.Direction.EMPTY);
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }
}
