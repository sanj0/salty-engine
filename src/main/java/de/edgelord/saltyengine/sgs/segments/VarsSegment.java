/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.sgs.segments;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.resource.Resource;
import de.edgelord.saltyengine.sgs.Interpreter;
import de.edgelord.saltyengine.sgs.ScriptLine;
import de.edgelord.saltyengine.sgs.ScriptSegment;

public class VarsSegment extends ScriptSegment {

    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public VarsSegment(String content) {
        super(content, ScriptSegment.Type.VARS);
    }

    @Override
    public void interpret(GameObject parent) {

        for (ScriptLine scriptLine : getLines()) {

            Interpreter.var(scriptLine);
            Interpreter.set(scriptLine);
        }
    }
}
