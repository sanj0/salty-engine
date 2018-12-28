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
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;

/**
 * A {@link Particle} is a physics-independent drawable object that has a transform.
 * Those get emitted by {@link EmitterComponent}s.
 * As with the only constructor, there is no {@link Transform} argument, the transform is {@link Transform#zero()} by default.
 * The particle itself may set its size and the emitter may set its position.
 */
public abstract class Particle implements TransformedObject, Drawable {

    private Transform transform;
    private final Integer waveNumber;

    public Particle(Integer waveNumber) {
        this.waveNumber = waveNumber;
        transform = Transform.zero();
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
