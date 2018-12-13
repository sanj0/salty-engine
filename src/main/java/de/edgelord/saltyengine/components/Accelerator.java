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
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class Accelerator extends Component<GameObject> {

    private long ticks;
    private long duration;
    private String forceName;
    private boolean accelerationFinished = true;

    public Accelerator(GameObject parent, String name) {
        super(parent, name, Components.ACCELERATOR_COMPONENT);
    }

    @Override
    public void onFixedTick() {

        if (!accelerationFinished) {
            ticks++;

            if (ticks >= duration) {

                getParent().getPhysics().getForce(forceName).setAcceleration(0f);
                ticks = 0;
                duration = 0;
                accelerationFinished = true;
            }
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void accelerate(String forceName, float acceleration, long duration) {
        getParent().getPhysics().getForce(forceName).setAcceleration(acceleration);

        this.accelerationFinished = false;
        this.forceName = forceName;
        this.duration = duration;
    }
}
