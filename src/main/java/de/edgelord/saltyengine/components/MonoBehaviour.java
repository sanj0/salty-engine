package de.edgelord.saltyengine.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

public class MonoBehaviour extends Component<GameObject> {

    public MonoBehaviour(GameObject parent, String name) {
        super(parent, name, Components.MONO_BEHAVIOUR);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }
}
