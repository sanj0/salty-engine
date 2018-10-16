package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;

public class LockToBounds extends Component<GameObject> {
    /**
     * The rectangular bounds to which the {@link #parent} is locked
     */
    private Transform bounds;

    public LockToBounds(GameObject parent, Transform bounds, String name) {
        super(parent, name, Components.GAME_COMPONENTS);

        this.bounds = bounds;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onFixedTick() {

        // Calculate difference and move
    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
