package de.edgelord.saltyengine.sgs;

import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.Resource;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private static List<Var> varPool = new ArrayList<>();
    private static Resource resource = new InnerResource();

    public static final String WRITE_COMMAND = "write";
    public static final String VAR_COMMAND = "var";
    public static final String SET_COMMAND = "set";

    public static final String TEXT_VAR = "Text";
    public static final String NUMBER_VAR = "Number";
    public static final String FLOAT_VAR = "Float";
    public static final String IMAGE_VAR = "Image";
    public static final String VECTOR_VAR = "Vector2F";

    public static void write(ScriptLine scriptLine) {

        if (scriptLine.getCommand().equals(WRITE_COMMAND)) {

            switch (scriptLine.getMajorArg()) {

                // syntax: write text Hello#_#World
                case "text":
                    System.out.println(scriptLine.getMinorArg());
                    break;

                // syntax: write var myVar
                case "var":
                    try {
                        System.out.println(getVar(scriptLine.getMinorArg()).getValue());
                    } catch (VarNotFoundException e) {
                        e.printStackTrace();
                    }
            }
        }
    }

    public static void var(ScriptLine scriptLine) {

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
    }

    public static void set(ScriptLine scriptLine) {
        if (scriptLine.getCommand().equals(SET_COMMAND)) {

            Var var = null;
            try {
                var = getVar(scriptLine.getMajorArg());
            } catch (VarNotFoundException e) {
                e.printStackTrace();
            }

            if (var == null) {

            } else {

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
                        var.setValue(new Vector2f(Float.valueOf(scriptLine.getMinorArg().split(",")[0]), Float.valueOf(scriptLine.getMinorArg().split(",")[1])));
                        break;

                        // Syntax set image path=res/pictures/image.png
                    case Image:
                        var.setValue(resource.getImageResource(scriptLine.getMinorArg().replaceAll("path=", "")));
                        break;
                }
            }
        }
    }

    public static Var getVar(String name) throws VarNotFoundException {
        for (Var var : varPool) {
            if (var.getName().equals(name)) {
                return var;
            }
        }

        throw new VarNotFoundException(name);
    }

    public static List<Var> getVarPool() {
        return varPool;
    }

    public static void setVarPool(List<Var> varPool) {
        Interpreter.varPool = varPool;
    }

    public static Resource getResource() {
        return resource;
    }

    public static void setResource(Resource resource) {
        Interpreter.resource = resource;
    }
}
