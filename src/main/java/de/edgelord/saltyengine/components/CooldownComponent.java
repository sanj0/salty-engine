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

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;

import java.util.function.BooleanSupplier;

/**
 * Activates a cooldown for what happens in {@link
 * #run()}. Whenever the cooldown is done after
 * {@link #cooldownTime} ticks, {@link #shouldRun}
 * is tested and if it returns true, {@link
 * #run()} is called and the cooldown restarts.
 */
public abstract class CooldownComponent extends Component implements Runnable {

    private final BooleanSupplier shouldRun;
    private int cooldownTime;
    private boolean coolingDown = false;
    private int ticks = 0;

    /**
     * The default constructor.
     *
     * @param parent       the parent of this
     *                     component
     * @param name         the id-name of this
     *                     component
     * @param cooldownTime the time that the
     *                     cooldown takes.
     * @param shouldRun    the test for if {@link
     *                     #run()} should be
     *                     called. This is only
     *                     tested if the cooldown
     *                     was performed
     */
    public CooldownComponent(final ComponentContainer parent, final String name, final int cooldownTime, final BooleanSupplier shouldRun) {
        super(parent, name, Components.TIMING_COMPONENT);

        this.cooldownTime = cooldownTime;
        this.shouldRun = shouldRun;
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onFixedTick() {

        if (coolingDown) {
            ticks++;
        }

        if (ticks >= cooldownTime) {
            coolingDown = false;
            ticks = 0;
        }

        if (!coolingDown) {
            if (shouldRun.getAsBoolean()) {
                run();
                coolingDown = true;
            }
        }
    }


    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public abstract void run();

    public int getCooldownTime() {
        return cooldownTime;
    }

    public void setCooldownTime(final int cooldownTime) {
        this.cooldownTime = cooldownTime;
    }
}
