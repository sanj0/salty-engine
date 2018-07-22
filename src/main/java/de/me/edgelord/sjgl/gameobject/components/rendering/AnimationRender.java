package de.me.edgelord.sjgl.gameobject.components.rendering;

import de.me.edgelord.sjgl.cosmetic.Animation;
import de.me.edgelord.sjgl.gameobject.GameObject;

import java.awt.*;

public class AnimationRender extends SimpleRenderComponent {

    // The Animation which should be drawn
    private Animation animation = null;

    // The number of fixed ticks (default would be something like 1 milli; set i the MainLoops constructor)
    // after which the next frame should be drawn
    private int ticksPerFrame = 75;

    // The current number of fixed ticks; when it reaches #ticksPerFrame it will be reset to 0 again
    private int ticks = 0;

    /**
     * The default super constructor for gameObjectComponent, which takes in the parent GameObject and the
     * name, used as an id, for fishing specific components out of a list
     *
     * @param parent the parent of the Component, so where to take the e.g. the Coordinate info from
     * @param name   the id-name for this Component
     * @see de.me.edgelord.sjgl.gameobject.GameObjectComponent
     */
    public AnimationRender(GameObject parent, String name) {
        super(parent, name);
    }

    /**
     * A constructor with all necessary parameters
     *
     * @param parent        the parent of the Component, so where to take e.g. the Coordinates info from
     * @param name          the id-name of this Component
     * @param animation     the animation that should be rendered
     * @param ticksPerFrame after how many fixed ticks the next frame of the animation should be triggered
     */
    public AnimationRender(GameObject parent, String name, Animation animation, int ticksPerFrame) {
        super(parent, name);
        this.animation = animation;
        this.ticksPerFrame = ticksPerFrame;
    }

    /**
     * Draws the current frame of the animation
     *
     * @param graphics the Graphics2D to which the component should draw
     * @see #onFixedTick()
     * @see #animation
     * @see de.me.edgelord.sjgl.gameobject.GameObjectComponent
     */
    @Override
    public void draw(Graphics2D graphics) {

        if (isEnabled()) {
            if (animation != null) {
                animation.drawCurrentFrame(graphics);
            }
        }
    }

    /**
     * On every fixed tick, #ticks is incremented; when it reaches #ticksPerFrame,
     * the next frame of #animation gets triggered and ticks will be reset to 0
     */
    @Override
    public void onFixedTick() {
        if (isEnabled()) {
            if (ticks == ticksPerFrame) {
                if (animation != null) {
                    animation.nextFrame();
                    ticks = 0;
                }
            } else if (animation != null) {
                ticks++;
            }
        }
    }

    public Animation getAnimation() {
        return animation;
    }

    public void setAnimation(Animation animation) {
        this.animation = animation;
    }

    public int getTicksPerFrame() {
        return ticksPerFrame;
    }

    public void setTicksPerFrame(int ticksPerFrame) {
        this.ticksPerFrame = ticksPerFrame;
    }
}
