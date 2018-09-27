/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.components.rendering;

import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

/**
 * This component is used for simplifying the process of rendering an oval.
 * <p>
 * The only thing that is LEFT for the user to call is
 * <code>GameObject.addComponent(new OvalRender(this, "some_id_name"));</code>
 */
public class OvalRender extends SimpleRenderComponent {

    public OvalRender(final ComponentParent parent, final String name) {
        super(parent, name);
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        setUpGraphics(saltyGraphics);

        if (isFill()) {
            saltyGraphics.fillOval(getParent());
        } else {
            saltyGraphics.drawOval(getParent());
        }
    }
}
