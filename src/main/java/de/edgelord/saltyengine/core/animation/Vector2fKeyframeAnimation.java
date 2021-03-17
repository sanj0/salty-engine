package de.edgelord.saltyengine.core.animation;

import de.edgelord.saltyengine.transform.Vector2f;

/**
 * Packages two {@link KeyframeAnimation}s
 * into one for animating positions based on
 * {@link de.edgelord.saltyengine.transform.Vector2f}s.
 */
public class Vector2fKeyframeAnimation {

    /**
     * The KeyframeAnimation for the x direction
     */
    private final KeyframeAnimation animationX;
    /**
     * The KeyframeAnimation for the y direction
     */
    private final KeyframeAnimation animationY;

    /**
     * Constructs a new instance by setting
     * {@link #animationX} and {@link #animationY}
     * to the two given ones, respectively.
     *
     * @param animationX the x animation
     * @param animationY the y animation
     */
    public Vector2fKeyframeAnimation(KeyframeAnimation animationX, KeyframeAnimation animationY) {
        this.animationX = animationX;
        this.animationY = animationY;
    }

    /**
     * Returns the {@link KeyframeAnimation#nextDelta()}
     * of both x and y animations stored in a
     * {@link Vector2f} instance.
     * For animating, one would want to add
     * the returned vector to the object's position
     * {@code object.getPosition().add(animation.nextDelta())}
     *
     * @return the next delta of the 2D movement stored in a
     * {@link Vector2f}
     */
    public Vector2f nextDelta() {
        return new Vector2f(animationX.nextDelta(), animationY.nextDelta());
    }

    /**
     * Adds a new Keyframe to the animation
     * at the given timecode with the given
     * x and y values, respectively.
     *
     * @param timecode the timecode of the new Keyframe
     * @param valueX   the x value for the new Keyframe
     * @param valueY   the y value for the new Keyframe
     */
    public void add(final int timecode, final float valueX, final float valueY) {
        animationX.add(timecode, valueX);
        animationY.add(timecode, valueY);
    }

    /**
     * Adds a new Keyframe to the animation
     * at the given timecode with the same, given
     * value for both x and y components of this
     * animation.
     *
     * @param timecode the timecode of the new Keyframe
     * @param value    the value for both x and y for the new Keyframe
     */
    public void add(final int timecode, final float value) {
        animationX.add(timecode, value);
        animationY.add(timecode, value);
    }

    /**
     * Calculates the animation in its
     * current state by calling the according method
     * for both individual KeyframeAnimations.
     *
     * @see KeyframeAnimation#calculateAnimation()
     */
    public void calculateAnimation() {
        animationX.calculateAnimation();
        animationY.calculateAnimation();
    }

    /**
     * Checks whether or not this animation
     * has reached its end, which is the case
     * when both individual animations have ended.
     *
     * @return has this animation ended yet?
     * @see KeyframeAnimation#animationEnded()
     */
    public boolean animationEnded() {
        return animationX.animationEnded() && animationY.animationEnded();
    }

    /**
     * Adds the given Keyframe to both
     * individual animations
     *
     * @param keyframe the Keyframe to be added to the animation
     * @see KeyframeAnimation#add(Keyframe)
     */
    public void add(Keyframe keyframe) {
        animationX.add(keyframe);
        animationY.add(keyframe);
    }

    /**
     * Removes the given Keyframe from
     * both individual animations
     *
     * @param keyframe the keyframe to remove from the animations
     * @see KeyframeAnimation#remove(Keyframe)
     */
    public void remove(Keyframe keyframe) {
        animationX.remove(keyframe);
        animationY.remove(keyframe);
    }

    /**
     * Removes the Keyframe at the given
     * timecode from both individual animations.
     *
     * @param timing the timecode at which to remove a Keyframe
     * @see KeyframeAnimation#removeByTimecode(int)
     */
    public void removeByTimecode(int timing) {
        animationX.removeByTimecode(timing);
        animationY.removeByTimecode(timing);
    }

    /**
     * Removes all Keyframes in both
     * animations that have the given value.
     *
     * @param key the value by which to remove all Keyframes
     * @see KeyframeAnimation#removeByKey(float)
     */
    public void removeByKey(float key) {
        animationX.removeByKey(key);
        animationY.removeByKey(key);
    }

    /**
     * Restarts the animation by restarting both
     * individual animations.
     *
     * @see KeyframeAnimation#restart()
     */
    public void restart() {
        animationX.restart();
        animationY.restart();
    }

    /**
     * Sets the current frame to the given one
     * in both individual animations
     *
     * @param currentFrame the current frame to be
     * @see KeyframeAnimation#setCurrentFrame(int)
     */
    public void setCurrentFrame(int currentFrame) {
        animationX.setCurrentFrame(currentFrame);
        animationY.setCurrentFrame(currentFrame);
    }

    /**
     * Gets {@link #animationX}.
     *
     * @return {@link #animationX}
     */
    public KeyframeAnimation getXAnimation() {
        return animationX;
    }

    /**
     * Gets {@link #animationY}.
     *
     * @return {@link #animationY}
     */
    public KeyframeAnimation getYAnimation() {
        return animationY;
    }
}
