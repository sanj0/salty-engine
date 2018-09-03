package de.edgelord.saltyengine.sgs.segments;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.sgs.ScriptSegment;

public class ScriptInitSegment extends ScriptSegment {

    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public ScriptInitSegment(String content) {
        super(content, Type.SCRIPT_INIT);
    }

    @Override
    public void interpret(GameObject parent) {

    }
}
