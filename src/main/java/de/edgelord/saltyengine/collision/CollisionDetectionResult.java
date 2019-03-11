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

package de.edgelord.saltyengine.collision;

import de.edgelord.saltyengine.utils.Directions;

public class CollisionDetectionResult {

    private boolean collision;
    private Directions.Direction rootCollisionDirection;

    public CollisionDetectionResult(boolean collision, Directions.Direction rootCollisionDirection) {
        this.collision = collision;
        this.rootCollisionDirection = rootCollisionDirection;
    }

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    public Directions.Direction getRootCollisionDirection() {
        return rootCollisionDirection;
    }

    public void setRootCollisionDirection(Directions.Direction rootCollisionDirection) {
        this.rootCollisionDirection = rootCollisionDirection;
    }
}
