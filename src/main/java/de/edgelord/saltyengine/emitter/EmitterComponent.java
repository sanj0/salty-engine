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
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.emitter.prc.PlainColorParticleRenderContext;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.GeneralUtil;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A {@link Component} that emits {@link Particle}s from its {@link de.edgelord.saltyengine.gameobject.GameObject} parent.
 * It emits instances of {@link #particle}, whose dimensions can be manipulated with {@link #fixedParticleDimensions} or
 * {@link #fixedMinParticleDimensions} and {@link #fixedMaxParticleDimensions}.
 * The speed of every {@link Particle} that is created by this emitter is the current value of {@link #speed}.
 *
 * <p>
 * You can also spawn a new wave on demand by calling {@link #impact()}.
 *
 * <p>
 * If {@link #impactMode} is set to <code>true</code>, the emitter will only emit a single wave every time {@link #impact()}
 * is called. This is e.g. useful for an explosion.
 *
 * <p>
 * The {@link #modifierStack} is a list of {@link ParticleModifier}s that all modify the particles over time with e.g. speed or size.
 */
@DefaultPlacement(method = DefaultPlacement.Method.PARENT)
public abstract class EmitterComponent extends Component<ComponentContainer> {

    /**
     * If this is set to <code>true</code>, the emitter will only emit a single wave every time {@link #impact()}is called.
     */
    private boolean impactMode = false;

    /**
     * The amount of particles to spawn in one wave
     */
    private float amount;

    /**
     * The amount of ticks after which a new wave of Particles should be emitted
     */
    private int waveInterval;

    /**
     * Determines after how many fixed ticks the particles of one wave should disappear again.
     */
    private int lifetime = 1000;

    /**
     * The number of the current wave. This value is incremented after spawning each wave, so the first spawned wave has the number 0.
     */
    private int currentWave = 0;

    /**
     * The speed of the particles.
     */
    private float speed;

    /**
     * The modifier stack.
     */
    private List<ParticleModifier> modifierStack = new ArrayList<>();

    /**
     * A fixed min dimensions for new {@link Particle}s, being set after the initialization of each particle.
     * <p>
     * Special cases:
     * If {@link #fixedParticleDimensions} is not <code>null</code>, {@link #fixedParticleDimensions} will be set as the particle's dimensions
     * If either this or {@link #fixedMaxParticleDimensions} are <code>null</code>, the particles dimensions stays untouched.
     */
    private Dimensions fixedMinParticleDimensions = null;

    /**
     * A fixed max dimensions for new {@link Particle}s, being set after the initialization of each particle.
     * <p>
     * Special cases:
     * If {@link #fixedParticleDimensions} is not <code>null</code>, {@link #fixedParticleDimensions} will be set as the particle's dimensions
     * If either this or {@link #fixedMinParticleDimensions} are <code>null</code>, the particles dimensions stays untouched.
     */
    private Dimensions fixedMaxParticleDimensions = null;

    /**
     * If this is not <code>null</code>, every {@link Particle} that is emitted by this {@link EmitterComponent} will
     * have that exact size.
     */
    private Dimensions fixedParticleDimensions = null;

    private int ticks = 0;
    private int ticks2 = 0;
    private int nextWaveToRemove = 0;
    private int wavesToRemove = 0;
    private boolean impactOnNextTick = false;

    /**
     * The {@link ParticleRenderContext} that is used to render the particles.
     * By default, it is a {@link PlainColorParticleRenderContext} with a {@link ColorUtil#BLACK} color.
     */
    private ParticleRenderContext renderContext = new PlainColorParticleRenderContext(ColorUtil.BLACK);

    /**
     * A thread-safe {@link List} containing all the current {@link Particle}s
     */
    private List<Particle> currentParticles = Collections.synchronizedList(new ArrayList<>());

    /**
     * The {@link Class} object of the particle to be emitted.
     */
    private Class<? extends Particle> particle;

    /**
     * The constructor initializing an emitter that emits a wave of the given amount of particles every given duration
     * of ticks.
     *
     * @param parent       the {@link de.edgelord.saltyengine.gameobject.GameObject} that owns this {@link Component}
     * @param name         the id-name of the component
     * @param particle     the particle to be emitted. obtained via {@link Object#getClass()}
     * @param speed        the speed of the particles spawned by this emitter.
     * @param amount       the amount of emitted particles per wave
     * @param waveInterval the time to be passed between each wave
     */
    public EmitterComponent(ComponentContainer parent, String name, Class<? extends Particle> particle, float speed, float amount, int waveInterval) {
        super(parent, name, Components.EMITTER_COMPONENT);

        this.particle = particle;
        this.amount = amount;
        this.waveInterval = waveInterval;
        this.speed = speed;
    }

    /**
     * The constructor initializing an emitter with {@link #impactMode} set to <code>true</code>, meaning that it will
     * only emit a single wave of particles every time {@link #impact()} is called.
     * {@link #waveInterval} is overloaded with <code>1</code>.
     *
     * @param parent   the {@link de.edgelord.saltyengine.gameobject.GameObject} that owns this {@link Component}
     * @param name     the id-name of the component
     * @param particle the particle to be emitted. obtained via {@link Object#getClass()}
     * @param speed    the speed of the particles spawned by this emitter.
     * @param amount   the amount of emitted particles per wave
     */
    public EmitterComponent(ComponentContainer parent, String name, Class<? extends Particle> particle, float speed, float amount) {
        this(parent, name, particle, speed, amount, 1);

        impactMode = true;
    }

    @Override
    public final void initialize() {
        initializeEmitter();
    }

    /**
     * Use to initialize the emitter after the constructor. This method is called within {@link #initialize()}.
     */
    public abstract void initializeEmitter();

    /**
     * Spawns a single new particle with the following steps:
     * <p>
     * 1. Create a {@link Particle} using {@link #createParticle()}
     * 2. Set a emitter-specific position using {@link Particle#setPosition(Coordinates2f)}
     * 3. return it
     *
     * @return the spawned particle to be added to the list
     */
    public abstract Particle spawnParticle();

    /**
     * Moves each particle separately. This is called every fixed tick for every particle within {@link #currentParticles}.
     *
     * @param particle the <code>Particle</code> to be moved
     */
    public abstract void moveParticle(Particle particle);

    @Override
    public void onCollision(CollisionEvent event) {
    }

    /**
     * Calls {@link #spawnParticle()} every {@link #waveInterval} fixed ticks for {@link #amount} times and calls {@link #moveParticle(Particle)} every fixed tick for every entry in {@link #currentParticles}.
     */
    @Override
    public final void onFixedTick() {

        // fixed ticks of the particle to remove them
        for (int i = 0; i < currentParticles.size(); i++) {
            Particle particle = currentParticles.get(i);
            particle.onFixedTick();

        }

        // spawn a new wave after the specified duration
        if (!impactMode) {
            if (ticks >= waveInterval) {
                ticks = 0;
                spawnWave();
            } else {
                ticks++;
            }
        }

        if (impactOnNextTick) {
            spawnWave();
            impactOnNextTick = false;
        }

        // move all particles
        for (int i = 0; i < currentParticles.size(); i++) {
            moveParticle(currentParticles.get(i));
        }

        // apply the modifier stack
        for (ParticleModifier modifier : modifierStack) {
            modifier.modifyParticles(currentParticles);
        }
    }

    private void spawnWave() {
        for (int i = 0; i < amount; i++) {
            addParticle(spawnParticle());
        }
        currentWave++;
        wavesToRemove++;
    }

    /**
     * Draws all {@link Particle}s within {@link #currentParticles} by calling {@link Particle#draw(SaltyGraphics)}.
     *
     * @param saltyGraphics the graphics to render the particles, this is internally passed in.
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.resetObjectRotation(getParent());
        for (int i = 0; i < currentParticles.size(); i++) {
            Particle particle = currentParticles.get(i);
            renderContext.nextParticleRenderConfig(saltyGraphics, particle);
            particle.draw(saltyGraphics);
        }
    }

    /**
     * Returns a new instance of {@link #particle} with the {@link #currentWave} and {@link #speed}.
     *
     * @return a new particle
     */
    public Particle createParticle() {
        try {

            Particle particle = this.particle.getConstructor(Integer.class, Integer.class, Float.class, EmitterComponent.class).newInstance(currentWave, lifetime, speed, this);

            if (fixedParticleDimensions != null) {
                particle.setDimensions(fixedParticleDimensions);
            } else if (fixedMinParticleDimensions != null && fixedMaxParticleDimensions != null) {
                particle.setDimensions(GeneralUtil.randomDimensions(fixedMinParticleDimensions.getWidth(), fixedMaxParticleDimensions.getWidth(), fixedMinParticleDimensions.getHeight(), fixedMaxParticleDimensions.getHeight()));
            }

            return particle;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Removes the given particle from the list.
     *
     * @param particle the particle to be removed from the list.
     */
    public void removeParticle(Particle particle) {
        currentParticles.remove(particle);
    }

    /**
     * This method will cause exactly one wave of {@link Particle}s to be emitted.
     */
    public void impact() {
        impactOnNextTick = true;
    }

    /**
     * The only way to add a {@link Particle} to the {@link #currentParticles}.
     *
     * @param particle the <code>Particle</code> to be added.
     */
    private void addParticle(Particle particle) {
        currentParticles.add(particle);
    }

    /**
     * Adds the given {@link ParticleModifier} to the {@link #modifierStack}.
     *
     * @param modifier the modifier to be added to the stack.
     */
    public void addModifier(ParticleModifier modifier) {
        modifierStack.add(modifier);
    }

    /**
     * Removes the given {@link ParticleModifier} from the {@link #modifierStack}.
     *
     * @param modifier the modifier to be removed from the stack.
     */
    public void removeModifier(ParticleModifier modifier) {
        modifierStack.remove(modifier);
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getWaveInterval() {
        return waveInterval;
    }

    public void setWaveInterval(int waveInterval) {
        this.waveInterval = waveInterval;
    }

    public void setRenderContext(ParticleRenderContext renderContext) {
        this.renderContext = renderContext;
    }

    public boolean isImpactMode() {
        return impactMode;
    }

    public void setImpactMode(boolean impactMode) {
        this.impactMode = impactMode;
    }

    public int getLifetime() {
        return lifetime;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public List<ParticleModifier> getModifierStack() {
        return modifierStack;
    }

    public void setModifierStack(List<ParticleModifier> modifierStack) {
        this.modifierStack = modifierStack;
    }

    public Class<? extends Particle> getParticle() {
        return particle;
    }

    public void setParticle(Class<? extends Particle> particle) {
        this.particle = particle;
    }

    public Dimensions getFixedMinParticleDimensions() {
        return fixedMinParticleDimensions;
    }

    public void setFixedMinParticleDimensions(Dimensions fixedMinParticleDimensions) {
        this.fixedMinParticleDimensions = fixedMinParticleDimensions;
    }

    public Dimensions getFixedMaxParticleDimensions() {
        return fixedMaxParticleDimensions;
    }

    public void setFixedMaxParticleDimensions(Dimensions fixedMaxParticleDimensions) {
        this.fixedMaxParticleDimensions = fixedMaxParticleDimensions;
    }

    public Dimensions getFixedParticleDimensions() {
        return fixedParticleDimensions;
    }

    public void setFixedParticleDimensions(Dimensions fixedParticleDimensions) {
        this.fixedParticleDimensions = fixedParticleDimensions;
    }
}
