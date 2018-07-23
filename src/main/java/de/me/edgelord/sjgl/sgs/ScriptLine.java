package de.me.edgelord.sjgl.sgs;

/**
 * This class represents a line of a sgs script, with its 3 different types:
 * first type: the <code>command</code>
 * second type: the <code>minorArg</code>
 * third type: the <code>majorArg</code>
 */
public class ScriptLine {

    // The command (1. part of a script line - e.g. "move")
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
        With the examples in the comments above, the line would look like "move x 1.75" in the script
        and "move:_:x:_:1.75" as interpreted, and it would move the parent GameObject by 1.75f on the x-axis
     */

    /**
     * The standard constructor with all the necessary parameters
     *
     * @param command  the 1. part of the script line (e.g. "move")
     * @param minorArg the 2. part of the script line (e.g. "x")
     * @param majorArg the 3. part of the script line (e.g. "1.75")
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

        command = lines[0];
        minorArg = lines[1];
        majorArg = lines[2];
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
