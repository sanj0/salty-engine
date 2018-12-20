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

package de.edgelord.saltyengine.emitter;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

public abstract class Emitter<T extends TransformedObject & Drawable> extends GameObject {

    public Emitter(float xPos, float yPos, float width, float height, String tag) {
        super(xPos, yPos, width, height, tag);
    }

    public Emitter(Transform transform, String tag) {
        super(transform, tag);
    }

    public Emitter(Coordinates coordinates, Dimensions dimensions, String tag) {
        super(coordinates, dimensions, tag);
    }

    public Emitter(Vector2f position, Dimensions dimensions, String tag) {
        super(position, dimensions, tag);
    }

    public Emitter(Vector2f position, float width, float height, String tag) {
        super(position, width, height, tag);
    }

    public Emitter(float xPos, float yPos, Dimensions dimensions, String tag) {
        super(xPos, yPos, dimensions, tag);
    }

    /**
     * Prepares the coexistence of this emitter relative to other, real {@link GameObject}s in the scene.
     */
    private void prepareCoexistence() {
        getPhysics().setGravityEnabled(false);
        setTrigger(true);
        setStationary(true);
    }

    @Override
    public final void initialize() {

    }

    /**
     * Use to initialize the emitter after the constructor. Thsi method is called within {@link #initialize()}
     */
    public abstract void initializeEmitter();

    @Override
    public void onCollision(CollisionEvent event) {
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }
}
