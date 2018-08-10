/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.core.event;

import de.edgelord.sjgl.gameobject.GameObject;

public class TouchingEvent {

    private final CollisionEvent collisionEvent;
    private final GameObject receiver;

    public TouchingEvent(final CollisionEvent collisionEvent, final GameObject receiver) {
        this.collisionEvent = collisionEvent;
        this.receiver = receiver;
    }

    public CollisionEvent getCollisionEvent() {
        return collisionEvent;
    }

    public GameObject getReceiver() {
        return receiver;
    }
}
