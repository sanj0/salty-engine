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
import de.edgelord.saltyengine.emitter.prc.PlainColorParticleRenderContext;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@link Component} that emits {@link Particle}s from its {@link de.edgelord.saltyengine.gameobject.GameObject} parent.
 * It emits
 */
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
     * Determines after how many fixed ticks the particles of one wave should disappear again.
     */
    private int lifespan = 1000;

    /**
     * The number of the current wave. This value is incremented after spawning each wave, so the first spawned wave has the number 0.
     */
    private int currentWave = 0;


    private int ticks = 0;
    private int ticks2 = 0;

    /**
     * The {@link ParticleRenderContext} that is used to render the particles.
     * By default, it is a {@link PlainColorParticleRenderContext} with a {@link ColorUtil#BLACK} color.
     */
    private ParticleRenderContext renderContext = new PlainColorParticleRenderContext(ColorUtil.BLACK);

    /**
     * A thread-safe {@link List} containing all the current {@link Particle}s
     */
    private List<Particle> currentParticles = Collections.synchronizedList(new ArrayList<>());

    private Class<? extends Particle> particle;

    /**
     * The constructor.
     *
     * @param parent       the {@link de.edgelord.saltyengine.gameobject.GameObject} that owns this {@link Component}
     * @param name         the id-name of the component
     * @param particle     the particle to be emitted. obtained via {@link Object#getClass()}
     * @param amount       the amount of emitted particles per wave
     * @param waveDuration the time to be passed between each wave
     */
    public EmitterComponent(ComponentContainer parent, String name, Class<? extends Particle> particle, float amount, int waveDuration) {
        super(parent, name, Components.EMITTER_COMPONENT);
        this.particle = particle;
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
     * Calls {@link #startWave()} every {@link #waveDuration} fixed ticks and calls {@link #fixedParticleMove(List)} every fixed tick.
     */
    @Override
    public final void onFixedTick() {

        if (ticks2 >= lifespan) {
            ticks2 = 0;
            currentParticles.removeIf(particle -> particle.getWaveNumber() == currentWave - 1);
        } else {
            ticks2++;
        }

        if (ticks >= waveDuration) {
            ticks = 0;
            startWave();
            currentWave++;
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
            renderContext.nextParticleRenderConfig(saltyGraphics, particle);
            particle.draw(saltyGraphics);
        }
    }

    /**
     * Returns a new instance of {@link #particle} with the {@link #currentWave}
     *
     * @return a new particle
     */
    public Particle createParticle() {
        try {
            return particle.getConstructor(Integer.class).newInstance(currentWave);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    protected void addParticle(Particle particle) {
        currentParticles.add(particle);
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

    public ParticleRenderContext getRenderContext() {
        return renderContext;
    }

    public void setRenderContext(ParticleRenderContext renderContext) {
        this.renderContext = renderContext;
    }
}
