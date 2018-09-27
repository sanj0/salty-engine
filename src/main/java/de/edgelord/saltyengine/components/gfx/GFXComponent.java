/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.components.gfx;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.Components;

/**
 * This abstract <code>GameObjectComponent</code> describes a
 * graphical effect for a <code>GameObject</code>.
 * These <code>GameObjectComponents</code> are disabled by default
 * and need to be enabled using the <code>startGFX()</code> method for
 * the graphical effect to effect the <code>GameObject</code>.
 */
public abstract class GFXComponent extends Component {

    public GFXComponent(ComponentParent parent, String name) {
        super(parent, name, Components.GFX_COMPONENT);

        disable();
    }

    public void startGFX() {
        enable();
    }

    public void endGFX() {
        disable();
    }
}
