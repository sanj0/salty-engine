package de.me.edgelord.sjgl.sgs.segments;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.sgs.ScriptSegment;

public class InitSegment extends ScriptSegment {
    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public InitSegment(String content) {
        super(content, Type.init);
    }

    @Override
    public void interpret(GameObject parent) {

    }
}
