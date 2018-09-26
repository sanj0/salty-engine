/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.sgs;

import de.edgelord.saltyengine.core.Component;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.Components;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.resource.Resource;
import de.edgelord.saltyengine.sgs.segments.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.stream.Collectors;

/**
 * This class interprets salty gameObject scripting (sgs) scripts by doing whatever the next script-line says, e.g. basicMove
 * It is designed as an GameObjectComponent, and it may not be perfect when it comes to performances, because it
 * interprets e.g. the "ON_FIXED_TICK" segment every fixed tick again.
 */
public class ScriptInterpreter extends Component<GameObject> {

    // All of the different segments of a sgs script
    private ScriptInitSegment scriptInitSegment;
    private VarsSegment varsSegment;
    private DrawSegment drawSegment;
    private InitSegment initSegment;
    private FixedTickSegment fixedTickSegment;
    private CollisionSegment collisionSegment;

    private boolean initRun = true;

    public static final String SCRIPTINIT_IN = "-scriptInit";
    public static final String SCRIPTINIT_OUT = "--scriptInit";

    public static final String VARSSEGMENT_IN = "-vars";
    public static final String VARSSEGMENT_OUT = "--vars";

    public static final String DRAWSEGMENT_IN = "-draw";
    public static final String DRAWSEGMENT_OUT = "--draw";

    public static final String INITSEGMENT_IN = "-init";
    public static final String INITSEGMENT_OUT = "--init";

    public static final String FIXEDTICKSEGMENT_IN = "-onFixedTick";
    public static final String FIXEDTICKSEGMENT_OUT = "--onFixedTick";

    public static final String COLLISIONSEGMENT_IN = "-onCollision";
    public static final String COLLISIONSEGMENT_OUT = "--onCollision";

    /**
     * The only constructor, having all the necessary parameters, but I recommend to never use it,
     * use loadScript instead
     *
     * @param varsSegment      the VarsSegment of the script
     * @param drawSegment      the DrawSegment of the script
     * @param initSegment      the InitSegment of the script
     * @param fixedTickSegment the FixedTickSegment of the script
     * @param collisionSegment the CollisionSegment of the script
     * @param parent           the parent GameObject of this Components
     * @param name             the id-name of this component
     * @see #loadScript(String, Resource, GameObject, String)
     */
    public ScriptInterpreter(ScriptInitSegment scriptInitSegment, VarsSegment varsSegment, DrawSegment drawSegment, InitSegment initSegment, FixedTickSegment fixedTickSegment, CollisionSegment collisionSegment, GameObject parent, String name) {
        super(parent, name, Components.SGS_COMPONENT);
        this.scriptInitSegment = scriptInitSegment;
        this.varsSegment = varsSegment;
        this.drawSegment = drawSegment;
        this.initSegment = initSegment;
        this.fixedTickSegment = fixedTickSegment;
        this.collisionSegment = collisionSegment;
    }

    /**
     * Returns a new ScriptInterpreter based off of the given script-text, the parent GameObject and the id-name,
     * by getting subStrings out of the script, beginning with the IN and ending with the OUT (e.g. -VARS and --VARS)
     *
     * @param relativePath the relative path to the script within the given Resource
     * @param resource     the Resource manager from which to load e.g. images that are requested by the script
     * @param parent       the parent GameObject for this Component
     * @param name         the id-name for this Component
     * @return the new ScriptInterpreter
     */
    public static ScriptInterpreter loadScript(String relativePath, Resource resource, GameObject parent, String name) {

        String script = null;
        try {
            script = Files.lines(resource.getFileResource(relativePath).toPath(), StandardCharsets.UTF_8).collect(Collectors.joining(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        ScriptInitSegment scriptInitSegment = new ScriptInitSegment(subString(script, SCRIPTINIT_IN, SCRIPTINIT_OUT));
        VarsSegment varsSegment = new VarsSegment(subString(script, VARSSEGMENT_IN, VARSSEGMENT_OUT));
        DrawSegment drawSegment = new DrawSegment(subString(script, DRAWSEGMENT_IN, DRAWSEGMENT_OUT));
        InitSegment initSegment = new InitSegment(subString(script, INITSEGMENT_IN, INITSEGMENT_OUT));
        FixedTickSegment fixedTickSegment = new FixedTickSegment(subString(script, FIXEDTICKSEGMENT_IN, FIXEDTICKSEGMENT_OUT));
        CollisionSegment collisionSegment = new CollisionSegment(subString(script, COLLISIONSEGMENT_IN, COLLISIONSEGMENT_OUT));

        return new ScriptInterpreter(scriptInitSegment, varsSegment, drawSegment, initSegment, fixedTickSegment, collisionSegment, parent, name);
    }

    @Override
    public void onFixedTick() {

        if (initRun) {
            scriptInitSegment.interpret(getParent());
            varsSegment.interpret(getParent());
            initSegment.interpret(getParent());
            initRun = false;
        }

        fixedTickSegment.interpret(getParent());
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        drawSegment.setLastGraphics(saltyGraphics);
        drawSegment.interpret(getParent());
    }

    @Override
    public void onCollision(CollisionEvent e) {

        collisionSegment.interpret(getParent());
    }

    private static String subString(String base, String start, String end) {

        return base.substring(base.indexOf(start) + start.length(), base.indexOf(end));
    }
}
