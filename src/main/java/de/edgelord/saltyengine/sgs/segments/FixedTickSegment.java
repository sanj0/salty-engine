/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.sgs.segments;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.sgs.Interpreter;
import de.edgelord.saltyengine.sgs.ScriptLine;
import de.edgelord.saltyengine.sgs.ScriptSegment;

public class FixedTickSegment extends ScriptSegment {
    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public FixedTickSegment(String content) {
        super(content, Type.ON_FIXED_TICK);
    }

    @Override
    public void interpret(GameObject parent) {

        for (ScriptLine scriptLine : getLines()) {

            Interpreter.write(scriptLine);
            Interpreter.set(scriptLine);
            Interpreter.physicsControl(scriptLine, parent);
        }
    }
}
