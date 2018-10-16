/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
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
