package de.edgelord.saltyengine.core.animation;

/**
 * An implementation of {@link Vector2fKeyframeAnimation} that uses two
 * instances of {@link LinearKeyframeAnimation} to animate.
 */
public class LinearVector2fKeyframeAnimation extends Vector2fKeyframeAnimation {
    public LinearVector2fKeyframeAnimation() {
        super(new LinearKeyframeAnimation(), new LinearKeyframeAnimation());
    }
}
