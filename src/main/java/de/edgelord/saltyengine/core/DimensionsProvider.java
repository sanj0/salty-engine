package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.transform.Dimensions;

public interface DimensionsProvider {

    Dimensions getDimensions();

    default float getWidth() {
        return getDimensions().getWidth();
    }

    default float getHeight() {
        return getDimensions().getHeight();
    }
}
