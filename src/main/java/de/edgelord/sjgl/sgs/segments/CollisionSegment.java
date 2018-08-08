/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.sgs.segments;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.sgs.ScriptSegment;

public class CollisionSegment extends ScriptSegment {
    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public CollisionSegment(String content) {
        super(content, Type.onCollision);
    }

    @Override
    public void interpret(GameObject parent) {

    }
}
