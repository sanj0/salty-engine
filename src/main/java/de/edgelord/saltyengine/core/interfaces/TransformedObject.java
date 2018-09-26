package de.edgelord.saltyengine.core.interfaces;

import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

public interface TransformedObject {

    Transform getTransform();

    void setTransform(Transform transform);

    default Dimensions getDimensions() {
        return getTransform().getDimensions();
    }

    default Vector2f getPosition() {
        return getTransform().getPosition();
    }

    default float getWidth() {
        return getDimensions().getWidth();
    }

    default float getHeight() {
        return getDimensions().getHeight();
    }

    default float getX() {
        return getPosition().getX();
    }

    default float getY() {
        return getPosition().getY();
    }

    default void setDimensions(Dimensions dimensions) {
        getTransform().setDimensions(dimensions);
    }

    default void setPosition(Vector2f position) {
        getTransform().setPosition(position);
    }

    default void setWidth(float width) {
        getTransform().setWidth(width);
    }

    default void setHeight(float height) {
        getTransform().setHeight(height);
    }

    default void setX(float x) {
        getTransform().setX(x);
    }

    default void setY(float y) {
        getTransform().setY(y);
    }
}
