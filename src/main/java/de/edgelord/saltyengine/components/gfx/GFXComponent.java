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

package de.edgelord.saltyengine.components.gfx;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.Components;

/**
 * This abstract {@link Component} describes a
 * graphical effect for a {@link ComponentContainer}.
 * These {@link Component}s are disabled by default
 * and need to be enabled using the {@link #startGFX()} method for
 * the graphical effect to start.
 */
public abstract class GFXComponent extends Component {

    public GFXComponent(ComponentContainer parent, String name) {
        super(parent, name, Components.GFX_COMPONENT);

        disable();
    }

    /**
     * Won't need that method often within a GFXComponent, so an empty implementation.
     *
     * @param e the collision event
     */
    @Override
    public void onCollision(CollisionEvent e) {

    }

    /**
     * "Starts" the GFX by calling {@link #enable()}.
     */
    public void startGFX() {
        enable();
    }

    /**
     * "Ends" the GFX by calling {@link #disable()}.
     */
    public void endGFX() {
        disable();
    }
}
