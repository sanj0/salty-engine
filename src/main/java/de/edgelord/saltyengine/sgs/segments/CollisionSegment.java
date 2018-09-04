/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.sgs.segments;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.sgs.Interpreter;
import de.edgelord.saltyengine.sgs.ScriptLine;
import de.edgelord.saltyengine.sgs.ScriptSegment;

public class CollisionSegment extends ScriptSegment {
    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public CollisionSegment(String content) {
        super(content, Type.ON_COLLISION);
    }

    @Override
    public void interpret(GameObject parent) {

        for (ScriptLine scriptLine : getLines()) {

            Interpreter.write(scriptLine);
            Interpreter.set(scriptLine);
            Interpreter.physicsControl(scriptLine, parent);
            Interpreter.addComp(scriptLine, parent);
        }
    }

    private CollisionEvent lastCollisionEvent = null;

    public CollisionEvent getLastCollisionEvent() {
        return lastCollisionEvent;
    }

    public void setLastCollisionEvent(CollisionEvent lastCollisionEvent) {
        this.lastCollisionEvent = lastCollisionEvent;
    }
}
