package de.me.edgelord.sjgl.sgs.segments;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.sgs.ScriptSegment;

public class DrawSegment extends ScriptSegment {
    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public DrawSegment(String content) {
        super(content, Type.draw);
    }

    @Override
    public void interpret(GameObject parent) {

    }
}
