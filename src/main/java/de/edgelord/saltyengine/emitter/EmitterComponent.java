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

public abstract class EmitterComponent extends Component<ComponentContainer> {

    /**
     * The amount of particles to spawn in one wave
     */
    private float amount;

    /**
     * The amount of ticks after which a new wave of Particles should be emitted
     */
    private int waveDuration;

    public EmitterComponent(ComponentContainer parent, String name, String tag, float amount, int waveDuration) {
        super(parent, name, tag);
        this.amount = amount;
        this.waveDuration = waveDuration;
    }

    @Override
    public final void initialize() {
    }

    /**
     * Use to initialize the emitter after the constructor. This method is called within {@link #initialize()}
     */
    public abstract void initializeEmitter();

    public abstract void startWave();

    @Override
    public void onCollision(CollisionEvent event) {
    }

    @Override
    public abstract void onFixedTick();

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

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
}
