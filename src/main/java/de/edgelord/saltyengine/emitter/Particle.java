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

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.FixedTickRoutine;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;

/**
 * A {@link Particle} is a physics-independent drawable object that has a transform.
 * <code>Particle</code>s can be emitted by {@link EmitterComponent}s.
 * As with the only constructor there is no {@link Transform} argument, the transform is {@link Transform#zero()} by default to avoid a {@link NullPointerException}.
 *
 * <p>
 * All instances of {@link EmitterComponent} are recommended to using this particles {@link #speed}.
 *
 * <p>
 * Usually, the particle itself has a default size, but the {@link EmitterComponent} can be configured to set a specific
 * size for its emitted particles.
 */
@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class Particle implements TransformedObject, Drawable, FixedTickRoutine {

    /**
     * The transform of this particle.
     */
    private Transform transform;

    /**
     * The <code>Directions</code> in which this particle can't move.
     */
    private Directions lockedDirections = new Directions();

    /**
     * The number of the wave that this particle is spawned in.
     */
    private final Integer waveNumber;

    /**
     * The speed of this particle.
     */
    private float speed;

    /**
     * The rest of this particle's lifetime measured in fixed ticks.
     */
    private int restLifetime;

    /**
     * The emitter that spawned this particle.
     */
    private EmitterComponent parent;

    private int ticks = 0;

    /**
     * The constructor.
     *
     * @param waveNumber the number of the wave that this particle is spawned in.
     * @param restLifetime the rest of this particles lifetime.
     * @param speed the speed of this particle.
     * @param parent the emitter that spawned this particle.
     */
    public Particle(Integer waveNumber, Integer restLifetime, Float speed, EmitterComponent parent) {
        this.waveNumber = waveNumber;
        this.restLifetime = restLifetime;
        this.parent = parent;
        this.speed = speed;
        transform = Transform.zero();
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public void onFixedTick() {

        if (ticks >= restLifetime) {
            parent.removeParticle(this);
        } else {
            ticks++;
        }
    }

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setLockedDirections(Directions directions) {
        this.lockedDirections = directions;
    }

    @Override
    public Directions getLockedDirections() {
        return lockedDirections;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public int getWaveNumber() {
        return waveNumber;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public int getRestLifetime() {
        return restLifetime;
    }

    public void setRestLifetime(int restLifetime) {
        this.restLifetime = restLifetime;
    }

    public EmitterComponent getParent() {
        return parent;
    }

    public void setParent(EmitterComponent parent) {
        this.parent = parent;
    }
}
