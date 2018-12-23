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

import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

public abstract class Particle implements TransformedObject, Drawable {

    private Transform transform;
    private final int waveNumber;

    public Particle(Transform transform, int waveNumber) {
        this.transform = transform;
        this.waveNumber = waveNumber;
    }

    public Particle(Vector2f position, Dimensions dimensions, int waveNumber) {
        this(new Transform(position, dimensions), waveNumber);
    }

    public Particle(float x, float y, float width, float height, int waveNumber) {
        this(new Transform(x, y, width, height), waveNumber);
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public int getWaveNumber() {
        return waveNumber;
    }
}
