package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;

public interface CentrePositionProvider {

    /**
     * This method should return the centre position for the given width on the x axis of the coordinates system.
     * A simple implementation would e.g. be:
     *
     * <code>return (float) (this.width / 2) - (width / 2)</code>
     * assuming <code>this.width</code> is the width of the coordinates system.
     *
     * @param width the width for which to return the centre position on the x axis
     * @return the centre position on the x axis for the given width
     */
    float getHorizontalCentrePosition(float width);

    /**
     * This method should return the centre position for the given height on the y axis of the coordinates system.
     * A simple implementation would e.g. be:
     *
     * <code>return (float) (this.height / 2) - (height / 2)</code>
     * assuming <code>this.height</code> is the height of the coordinates system.
     *
     * @param height the width for which to return the centre position on the y axis
     * @return the centre position on the x axis for the given height
     */
    float getVerticalCentrePosition(float height);


    default Vector2f getCentrePosition(float width, float height) {
        return new Vector2f(getHorizontalCentrePosition(width), getVerticalCentrePosition(height));
    }

    default float getHorizontalCentrePosition(GameObject gameObject) {
        return getHorizontalCentrePosition(gameObject.getWidth());
    }

    default float getHorizontalCentrePosition(Dimensions dimensions) {
        return getHorizontalCentrePosition(dimensions.getWidth());
    }

    default float getVerticalCentrePosition(GameObject gameObject) {
        return getVerticalCentrePosition(gameObject.getHeight());
    }

    default float getVerticalCentrePosition(Dimensions dimensions) {
        return getVerticalCentrePosition(dimensions.getHeight());
    }

    default Vector2f getCentrePosition(GameObject gameObject) {
        return getCentrePosition(gameObject.getWidth(), gameObject.getHeight());
    }
    
    default Vector2f getCentrePosition(Dimensions dimensions) {
        return getCentrePosition(dimensions.getWidth(), dimensions.getHeight());
    }
}
