package de.edgelord.saltyengine.gameobject.components;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

/**
 * Note: It is obviously planned to make more complicated Collision detection with complex Shapes possible.
 */
public class ColliderComponent extends Component<GameObject> {

    private Type type;

    public enum Type {
        HITBOX
    }

    public ColliderComponent(GameObject parent, String name, Type type) {
        super(parent, name, Components.COLLIDER_COMPONENT);

        this.type = type;
    }

    @Override
    public void onFixedTick() {
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    @Override
    public void onCollision(CollisionEvent e) {
    }

    public boolean requestCollision(GameObject other) {

        ColliderComponent otherCollider = other.requestCollider();

        switch (otherCollider.type) {

            case HITBOX:

                if (type == Type.HITBOX) {
                    return getParent().getHitbox().collides(other);
                }
                break;
        }

        return false;
    }
}
