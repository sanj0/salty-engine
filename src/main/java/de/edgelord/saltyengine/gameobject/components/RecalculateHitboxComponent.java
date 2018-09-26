/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class RecalculateHitboxComponent extends Component<GameObject> {

    public RecalculateHitboxComponent(GameObject parent, String name) {
        super(parent, name, Components.CORE_COMPONENT);
    }

    @Override
    public void onFixedTick() {

        getParent().getHitbox().recalculate();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
