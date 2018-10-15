/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.components.gfx;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.Components;

/**
 * This abstract {@link Component} describes a
 * graphical effect for a {@link ComponentParent}.
 * These {@link Component}s are disabled by default
 * and need to be enabled using the {@link #startGFX()} method for
 * the graphical effect to take effect.
 */
public abstract class GFXComponent extends Component {

    public GFXComponent(ComponentParent parent, String name) {
        super(parent, name, Components.GFX_COMPONENT);

        disable();
    }

    /**
     * Won't need that method often within a GFXComponent
     * @param e the collision event
     */
    @Override
    public void onCollision(CollisionEvent e) {

    }

    public void startGFX() {
        enable();
    }

    public void endGFX() {
        disable();
    }
}
