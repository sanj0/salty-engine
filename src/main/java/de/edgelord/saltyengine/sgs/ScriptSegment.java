/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.sgs;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.Resource;

import java.util.ArrayList;
import java.util.List;

/**
 * A segment of a sgs script (e.g. -vars or -draw)
 */
public abstract class ScriptSegment {

    // The complete content of the segment
    private String content;

    private Resource resource;

    // The different lines of the segment
    private List<ScriptLine> lines = new ArrayList<>();

    // The type of the segment
    private Type type;

    // The different types of the segments
    public enum Type {
        SCRIPT_INIT, VARS, INIT, ON_FIXED_TICK, DRAW, ON_COLLISION
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
        this.resource = new InnerResource();

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

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public List<ScriptLine> getLines() {
        return lines;
    }

    public Type getType() {
        return type;
    }
}
