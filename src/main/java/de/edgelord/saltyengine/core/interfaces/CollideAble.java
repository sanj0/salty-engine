package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.core.event.CollisionEvent;

import java.util.List;

public interface CollideAble {

    void onCollision(CollisionEvent e);

    void onCollisionDetectionFinish(List<CollisionEvent> collisions);
}
