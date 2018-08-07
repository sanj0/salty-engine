package de.edgelord.sjgl.sgs;

import de.edgelord.sjgl.core.event.CollisionEvent;
import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;
import de.edgelord.sjgl.resource.Resource;
import de.edgelord.sjgl.sgs.segments.*;

import java.awt.*;

/**
 * This class interprets salty gameObject scripting (sgs) scripts by doing whatever the next script-line says, e.g. basicMove
 * It is designed as an GameObjectComponent, and it may not be very good when it comes to performances, because it
 * interprets e.g. the "onFixedTick" segment every fixed tick again.
 */
public class ScriptInterpreter extends GameObjectComponent {

    // All of the different segments of a sgs script
    private VarsSegment varsSegment;
    private DrawSegment drawSegment;
    private InitSegment initSegment;
    private FixedTickSegment fixedTickSegment;
    private CollisionSegment collisionSegment;

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
    public ScriptInterpreter(VarsSegment varsSegment, DrawSegment drawSegment, InitSegment initSegment, FixedTickSegment fixedTickSegment, CollisionSegment collisionSegment, GameObject parent, String name) {
        super(parent, name);
        this.varsSegment = varsSegment;
        this.drawSegment = drawSegment;
        this.initSegment = initSegment;
        this.fixedTickSegment = fixedTickSegment;
        this.collisionSegment = collisionSegment;
    }

    /**
     * Returns a new ScriptInterpreter based off of the given script-text, the parent GameObject and the id-name,
     * by getting subStrings out of the script, beginning with the IN and ending with the OUT (e.g. -vars and --vars)
     *
     * @param script the script which the new Interpreter should stand for
     * @param parent the parent GameObject for this Component
     * @param name   the id-name for this Component
     * @return the new ScriptInterpreter
     */
    public static ScriptInterpreter loadScript(String script, Resource resource, GameObject parent, String name) {

        VarsSegment varsSegment = new VarsSegment(subString(script, VARSSEGMENT_IN, VARSSEGMENT_OUT), resource);
        DrawSegment drawSegment = new DrawSegment(subString(script, DRAWSEGMENT_IN, DRAWSEGMENT_OUT));
        InitSegment initSegment = new InitSegment(subString(script, INITSEGMENT_IN, INITSEGMENT_OUT));
        FixedTickSegment fixedTickSegment = new FixedTickSegment(subString(script, FIXEDTICKSEGMENT_IN, FIXEDTICKSEGMENT_OUT));
        CollisionSegment collisionSegment = new CollisionSegment(subString(script, COLLISIONSEGMENT_IN, COLLISIONSEGMENT_OUT));

        return new ScriptInterpreter(varsSegment, drawSegment, initSegment, fixedTickSegment, collisionSegment, parent, name);
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(CollisionEvent e) {

    }

    private static String subString(String base, String start, String end) {

        return base.substring(base.lastIndexOf(start) + start.length(), base.lastIndexOf(end));
    }
}
