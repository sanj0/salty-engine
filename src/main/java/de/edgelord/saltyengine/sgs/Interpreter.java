package de.edgelord.saltyengine.sgs;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.components.DebugLogGameObjectStat;
import de.edgelord.saltyengine.components.rendering.OvalRender;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.Resource;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Interpreter {

    private static List<Var> varPool = new ArrayList<>();
    private static Resource resource = new InnerResource();

    public static final String WRITE_COMMAND = "write";
    public static final String DRAW_COMMAND = "draw";
    public static final String ADD_COMP_COMMAND = "addComponent";
    public static final String ADD_COMP_COMMAND$1 = "addComp";
    public static final String PHYSICS_CONTROL_COMMAND = "physicsControl";
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

    public static void physicsControl(ScriptLine scriptLine, GameObject parent) {

        if (scriptLine.getCommand().equals(PHYSICS_CONTROL_COMMAND)) {

            switch (scriptLine.getMajorArg()) {

                case "removeForce":
                    if (scriptLine.getMinorArg().equals("gravity")) {
                        parent.getPhysics().removeGravity();
                    } else {
                        parent.getPhysics().removeForce(scriptLine.getMinorArg());
                    }
            }
        }
    }

    public static void draw(ScriptLine scriptLine, SaltyGraphics graphics, GameObject parent) throws VarNotFoundException, CannotParseVarException {

        if (scriptLine.getCommand().equals(DRAW_COMMAND)) {

            String[] opts = getOpts(scriptLine.getMinorArg());

            switch (scriptLine.getMajorArg()) {

                case "image":

                    if (scriptLine.getMinorArg().equals("parent")) {
                        graphics.drawImage((BufferedImage) getVar(scriptLine.getMinorArg()).getValue(), parent);
                    } else {
                        // syntax: draw image myImage;position=314,628;width=100;height=200
                        graphics.drawImage((BufferedImage) getVar(opts[0]).getValue(), new Transform(getVector2FFromOpt(opts[1]), getDimensionsFromOP(opts[2], opts[4])));
                    }

                    break;

                case "oval":

                    if (scriptLine.getMinorArg().equals("parent")) {
                        // syntax: draw oval parent
                        graphics.drawOval(parent);
                    } else {
                        // syntax: draw oval position=314,682;width=100;height=200
                        graphics.drawOval(new Transform(getVector2FFromOpt(opts[0]), getDimensionsFromOP(opts[1], opts[2])));
                    }

                    break;

                case "rect":
                    if (scriptLine.getMinorArg().equals("parent")) {
                        // syntax: draw rect parent
                        graphics.drawRect(parent);
                    } else {
                        // syntax: draw rect position=314,682;width=100;height=200
                        graphics.drawRect(new Transform(getVector2FFromOpt(opts[0]), getDimensionsFromOP(opts[1], opts[2])));
                    }
                    break;

                // syntax: draw text text=Hello#_#World
                // Or: draw text var=myVar
                case "text":
                    String text = "";
                    Vector2f position = getVector2FFromOpt(opts[1]);

                    if (opts[0].startsWith("var")) {
                        text = getVar(opts[0].replaceAll("var=", "")).getValue().toString();
                    } else if (opts[0].startsWith("text")) {
                        text = opts[0].replaceAll("text=", "");
                    }

                    graphics.drawText(text, position);
                    break;

                case "roundRect":

                    if (scriptLine.getMinorArg().startsWith("parent")) {
                        // syntax: draw roundRect parent;arc=15
                        graphics.drawRoundRect(parent, Float.valueOf(opts[1].replaceAll("arc=", "")));
                    } else {
                        // syntax: draw roundRect position=314,682;width=100;height=200;arc=15
                        graphics.drawRoundRect(new Transform(getVector2FFromOpt(opts[0]), getDimensionsFromOP(opts[1], opts[2])), Float.valueOf(getOpts(scriptLine.getMinorArg())[3].replaceAll("arc=", "")));
                    }
                    break;
            }
        }
    }

    public static void addComp(ScriptLine scriptLine, GameObject parent) {

        if (scriptLine.getCommand().equals(ADD_COMP_COMMAND) || scriptLine.getCommand().equals(ADD_COMP_COMMAND$1)) {

            switch (scriptLine.getMajorArg()) {

                case "ovalRender":
                    parent.addComponent(new OvalRender(parent, scriptLine.getMinorArg().replaceAll("name=", "")));
                    break;

                case "debugLog":
                    parent.addComponent(new DebugLogGameObjectStat(parent, scriptLine.getMinorArg().replaceAll("name=", "")));
                    break;
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
                        try {
                            var.setValue(getVector2F(scriptLine.getMinorArg()));
                        } catch (CannotParseVarException e) {
                            e.printStackTrace();
                        }
                        break;

                    // Syntax set image path=res/pictures/image.png
                    case Image:
                        try {
                            var.setValue(getImage(scriptLine.getMinorArg(), resource));
                        } catch (CannotParseVarException e) {
                            e.printStackTrace();
                        }
                        break;
                }
            }
        }
    }

    public static Vector2f getVector2FFromOpt(String opt) throws CannotParseVarException, VarNotFoundException {
        if (opt.startsWith("position")) {
            try {
                return getVector2F(opt.replaceAll("position=", ""));
            } catch (CannotParseVarException e) {
                throw new CannotParseVarException("Vector2F", opt.replaceAll("var=", ""));
            }
        } else if (opt.startsWith("var")) {
            try {
                return (Vector2f) getVar(opt.replaceAll("var=", "")).getValue();
            } catch (Exception e) {
                throw new VarNotFoundException(opt.replaceAll("var=", ""));
            }
        }

        return new Vector2f(314, 628);
    }

    public static Dimensions getDimensionsFromOP(String widthOpt, String heightOpt) {

        float width = Float.valueOf(widthOpt.replaceAll("width=", ""));
        float height = Float.valueOf(heightOpt.replaceAll("height=", ""));

        return new Dimensions(width, height);
    }

    public static String[] getOpts(String minorArg) {

        return minorArg.split(";");
    }

    public static Vector2f getVector2F(String arg) throws CannotParseVarException {

        try {
            return new Vector2f(Float.valueOf(arg.split(",")[0]), Float.valueOf(arg.split(",")[1]));
        } catch (Exception e) {
            throw new CannotParseVarException("Vector2F", arg);
        }
    }

    public static BufferedImage getImage(String arg, Resource resource) throws CannotParseVarException {

        try {
            return resource.getImageResource(arg.replaceAll("path=", ""));
        } catch (Exception e) {
            throw new CannotParseVarException("Image", arg);
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
