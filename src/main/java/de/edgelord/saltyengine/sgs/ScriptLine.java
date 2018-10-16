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

/**
 * This class represents a line of a sgs script, with its 3 different types:
 * first type: the <code>command</code>
 * second type: the <code>minorArg</code>
 * third type: the <code>majorArg</code>
 */
public class ScriptLine {

    // The command (1. part of a script line - e.g. "basicMove")
    private String command;

    // The minor argument (2. part of a script line - e.g. "x")
    private String minorArg;

    // The major argument (3. part of a script line - e.g. "1.75")
    private String majorArg;

    // This String is used for converting system-wanted spaces to non-spaces, e.g. as the separator of the 3 parts of a line
    private final String SYSTEMSPACE = ":_:";

    // This String is used for converting user-wanted spaces to actual spaces, e.g. in text
    private final String USERSPACE = "#_#";

    /*
        With the examples in the comments above, the line would look like "basicMove x 1.75" in the script
        and "basicMove:_:x:_:1.75" as interpreted, and it would basicMove the parent GameObject by 1.75f on the x-axis
     */

    /**
     * The standard constructor with all the necessary parameters
     *
     * @param command  the 1. part of the script line (e.g. "basicMove")
     * @param majorArg the 2. part of the script line (e.g. "x")
     * @param minorArg the 3. part of the script line (e.g. "1.75")
     */
    public ScriptLine(String command, String minorArg, String majorArg) {
        this.command = command;
        this.minorArg = minorArg;
        this.majorArg = majorArg;
    }

    /**
     * A constructor, which extracts all the necessary parameters itself, from a given line of a script
     *
     * @param line the script-line which this class should be representing
     */
    public ScriptLine(String line) {

        String preparedLine;

        // First, replace all the spaces which separate the parts of the line by the pre-defined SYSTEMSPACE
        preparedLine = line.replaceAll(" ", SYSTEMSPACE);

        // Then, replace all #_# with spaces (they are wanted, e.g. in text)
        preparedLine = preparedLine.replaceAll(USERSPACE, " ");

        //Now, split the prepared line into its three parts, by splitting at SYSTEMSPACE
        String[] lines = preparedLine.split(SYSTEMSPACE);

        if (lines.length == 1) {
            command = "";
            minorArg = "";
            majorArg = "";
        } else {
            command = lines[0];
            majorArg = lines[1];
            minorArg = lines[2];
        }
    }

    public String foo() {
        return "bar";
    }

    public String getCommand() {
        return command;
    }

    public String getMinorArg() {
        return minorArg;
    }

    public String getMajorArg() {
        return majorArg;
    }
}
