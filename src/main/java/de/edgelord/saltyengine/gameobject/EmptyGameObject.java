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

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

/**
 * A GameObject with default empty implementations of:
 * {@link GameObject#onCollision(CollisionEvent)} <br>
 * {@link GameObject#onFixedTick()} <br>
 * {@link GameObject#draw(SaltyGraphics)}
 * <p>
 * Inside the {@link #initialize()}, this GameObject is set to ignore the gravity using
 * {@link de.edgelord.saltyengine.components.SimplePhysicsComponent#setGravityEnabled(boolean)}
 */
@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class EmptyGameObject extends GameObject {

    public EmptyGameObject(final float xPos, final float yPos, final float width, final float height, final String tag) {
        super(xPos, yPos, width, height, tag);
    }

    public EmptyGameObject(final Transform transform, final String tag) {
        super(transform, tag);
    }

    public EmptyGameObject(final Coordinates coordinates, final Dimensions dimensions, final String tag) {
        super(coordinates, dimensions, tag);
    }

    public EmptyGameObject(final Vector2f position, final Dimensions dimensions, final String tag) {
        super(position, dimensions, tag);
    }

    public EmptyGameObject(final Vector2f position, final float width, final float height, final String tag) {
        super(position, width, height, tag);
    }

    public EmptyGameObject(final float xPos, final float yPos, final Dimensions dimensions, final String tag) {
        super(xPos, yPos, dimensions, tag);
    }

    @Override
    public void initialize() {
        getPhysics().setGravityEnabled(false);
    }

    @Override
    public void onCollision(final CollisionEvent event) {
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
    }
}
