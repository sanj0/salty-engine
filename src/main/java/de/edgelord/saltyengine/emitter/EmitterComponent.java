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

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class EmitterComponent extends Component<ComponentContainer> {

    /**
     * The amount of particles to spawn in one wave
     */
    private float amount;

    /**
     * The amount of ticks after which a new wave of Particles should be emitted
     */
    private int waveDuration;

    /**
     * The current tick count, used for starting new waves.
     */
    private int ticks = 0;

    /**
     * A thread-safe {@link List} containing all the current {@link Particle}s
     */
    private List<Particle> currentParticles = Collections.synchronizedList(new ArrayList<>());

    public EmitterComponent(ComponentContainer parent, String name, String tag, float amount, int waveDuration) {
        super(parent, name, tag);
        this.amount = amount;
        this.waveDuration = waveDuration;
    }

    @Override
    public final void initialize() {
    }

    /**
     * Use to initialize the emitter after the constructor. This method is called within {@link #initialize()}.
     */
    public abstract void initializeEmitter();

    /**
     * Starts a new wave. This is called every {@link #waveDuration} fixed ticks.
     */
    public abstract void startWave();

    /**
     * Moves the particles. This is called every fixed tick. The given {@link List} is actually just {@link #currentParticles},
     * which can also be obtained using {@link #getCurrentParticles()}
     *
     * @param particles the {@link List} of {@link Particle}s to be moved.
     */
    public abstract void fixedParticleMove(List<Particle> particles);

    @Override
    public void onCollision(CollisionEvent event) {
    }

    /**
     * Calls {@link #startWave()} every {@link #waveDuration} fixed ticks and calles {@link #fixedParticleMove(List)} every fixed tick.
     */
    @Override
    public final void onFixedTick() {

        if (ticks >= waveDuration) {
            ticks = 0;
            startWave();
        } else {
            ticks++;
        }

        fixedParticleMove(currentParticles);
    }

    /**
     * Draws all {@link Particle}s within {@link #currentParticles} by calling {@link Particle#draw(SaltyGraphics)}.
     *
     * @param saltyGraphics the graphics to render the particles, this is internally passed in.
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        for (int i = 0; i < currentParticles.size(); i++) {
            Particle particle = currentParticles.get(i);

            particle.draw(saltyGraphics);
        }
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getWaveDuration() {
        return waveDuration;
    }

    public void setWaveDuration(int waveDuration) {
        this.waveDuration = waveDuration;
    }

    public List<Particle> getCurrentParticles() {
        return currentParticles;
    }
}
