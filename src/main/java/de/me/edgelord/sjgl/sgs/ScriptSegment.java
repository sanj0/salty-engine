package de.me.edgelord.sjgl.sgs;

import de.me.edgelord.sjgl.gameobject.GameObject;

import java.util.List;

/**
 * A segment of a sgs script (e.g. -vars or -draw)
 */
public abstract class ScriptSegment {

    // The complete content of the segment
    private String content;

    // The different lines of the segment
    private List<ScriptLine> lines;

    // The type of the segment
    private Type type;

    // The different types of the segments
    public enum Type {
        vars, init, onFixedTick, draw, onCollision
    }

    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     * @param type    the type of the segment
     */
    public ScriptSegment(String content, Type type) {
        this.content = content;
        this.type = type;

        readLines();
    }

    private void readLines() {

        for (String line : content.split("\n")) {

            if (!line.startsWith("#")) {
                lines.add(new ScriptLine(line));
            }
        }
    }

    public abstract void interpret(GameObject parent);

    public String getContent() {
        return content;
    }

    public List<ScriptLine> getLines() {
        return lines;
    }

    public Type getType() {
        return type;
    }
}
