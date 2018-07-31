package de.me.edgelord.sjgl.sgs.segments;

import de.me.edgelord.sjgl.gameobject.GameObject;
import de.me.edgelord.sjgl.location.Vector2f;
import de.me.edgelord.sjgl.resource.Resource;
import de.me.edgelord.sjgl.sgs.ScriptLine;
import de.me.edgelord.sjgl.sgs.ScriptSegment;
import de.me.edgelord.sjgl.sgs.Var;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class VarsSegment extends ScriptSegment {

    private List<Var> varPool = new LinkedList<>();
    private Resource resource;

    public static final String VAR_COMMAND = "var";
    public static final String SET_COMMAND = "set";

    public static final String TEXT_VAR = "Text";
    public static final String NUMBER_VAR = "Number";
    public static final String FLOAT_VAR = "Float";
    public static final String IMAGE_VAR = "Image";
    public static final String VECTOR_VAR = "Vector2F";

    /**
     * The standard constructor with all necessary parameters
     *
     * @param content the complete content of the segment
     */
    public VarsSegment(String content, Resource resource) {
        super(content, Type.vars);

        this.resource = resource;
    }

    @Override
    public void interpret(GameObject parent) {

        for (ScriptLine scriptLine : getLines()) {

            // First command available in the vars segment: "var" which creates a new Variable

            if (scriptLine.getCommand().equals(VAR_COMMAND)) {

                switch (scriptLine.getMajorArg()) {

                    // Syntax: var Text myText
                    case TEXT_VAR:
                        varPool.add(new Var<String>(scriptLine.getMinorArg(), "", Var.Type.Text));
                        break;

                    // Syntax: var Number myNumber
                    case NUMBER_VAR:
                        varPool.add(new Var<Integer>(scriptLine.getMinorArg(), 0, Var.Type.Number));
                        break;

                    // Syntax: var Float myFloat
                    case FLOAT_VAR:
                        varPool.add(new Var<Float>(scriptLine.getMinorArg(), 0f, Var.Type.Float));
                        break;

                    // Syntax: var Image myImage
                    case IMAGE_VAR:
                        varPool.add(new Var<BufferedImage>(scriptLine.getMinorArg(), null, Var.Type.Image));
                        break;

                    // Syntax: var Vector2F myVector2F
                    case VECTOR_VAR:
                        varPool.add(new Var<Vector2f>(scriptLine.getMinorArg(), null, Var.Type.Vector2F));
                        break;
                }
            }

            // The second command possible in this segment, "set" which sets the value of a var

            if (scriptLine.getCommand().equals(SET_COMMAND)) {

                Var var = getVar(scriptLine.getMajorArg());

                switch (var.getType()) {

                    // Syntax: set myText Hello World!
                    case Text:
                        var.setValue(scriptLine.getMinorArg());
                        break;

                    // Syntax: set myNumber 314
                    case Number:
                        var.setValue(Integer.valueOf(scriptLine.getMinorArg()));
                        break;

                    // Syntax: set myFloat 3.14
                    case Float:
                        var.setValue(Float.valueOf(scriptLine.getMinorArg()));
                        break;


                    // Syntax: set myVec2F 3.14 , 6.26
                    // NOTICE the spaces before and after the comma!
                    case Vector2F:
                        var.setValue(new Vector2f(Float.valueOf(scriptLine.getMinorArg().split(" ,")[0]), Float.valueOf(scriptLine.getMinorArg().split(", ")[1])));

                        // Syntax set image path=res/pictures/image.png
                    case Image:
                        var.setValue(resource.getImageResource(scriptLine.getMinorArg().replaceAll("path=", "")));
                }
            }
        }
    }

    private Var getVar(String name) {

        for (Var var : varPool) {

            if (var.getName().equals(name)) {
                return var;
            }
        }

        return null;
    }
}