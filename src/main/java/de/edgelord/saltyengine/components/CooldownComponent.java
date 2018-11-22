/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.util.function.BooleanSupplier;

/**
 * Activates a cooldown for what happens in {@link #run()}.
 * Whenever the cooldown is done after {@link #cooldownTime} ticks, {@link #shouldRun} is tested and if it returns true,
 * {@link #run()} is called and the cooldown restarts.
 */
public abstract class CooldownComponent extends Component implements Runnable {

    private int cooldownTime;
    private boolean coolingDown = false;
    private int ticks = 0;

    private BooleanSupplier shouldRun;

    /**
     * The default constructor.
     *
     * @param parent the parent of this component
     * @param name the id-name of this component
     * @param cooldownTime the time that the cooldown takes.
     * @param shouldRun the test for if {@link #run()} should be called. This is only tested if the cooldown was performed
     */
    public CooldownComponent(ComponentParent parent, String name, int cooldownTime, BooleanSupplier shouldRun) {
        super(parent, name, Components.TIMING_COMPONENT);

        this.cooldownTime = cooldownTime;
        this.shouldRun = shouldRun;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onFixedTick() {

        if (coolingDown) {
            ticks++;
        }

        if (ticks == cooldownTime) {
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
    public void onCollision(CollisionEvent e) {

    }

    @Override
    public abstract void run();
}
