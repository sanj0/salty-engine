/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.sgs.segments;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.sgs.Interpreter;
import de.edgelord.saltyengine.sgs.ScriptLine;
import de.edgelord.saltyengine.sgs.ScriptSegment;
import de.edgelord.saltyengine.sgs.VarNotFoundException;

public class DrawSegment extends ScriptSegment {
    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public DrawSegment(String content) {
        super(content, Type.DRAW);
    }

    @Override
    public void interpret(GameObject parent) {

        for (ScriptLine scriptLine : getLines()) {

            Interpreter.write(scriptLine);
            Interpreter.set(scriptLine);
            try {
                Interpreter.draw(scriptLine, getLastGraphics(), parent);
            } catch (VarNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private SaltyGraphics lastGraphics = null;

    public SaltyGraphics getLastGraphics() {
        return lastGraphics;
    }

    public void setLastGraphics(SaltyGraphics lastGraphics) {
        this.lastGraphics = lastGraphics;
    }
}
