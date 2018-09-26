package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.core.event.CollisionEvent;

public interface CollideAble {

    void onCollision(CollisionEvent e);
}
