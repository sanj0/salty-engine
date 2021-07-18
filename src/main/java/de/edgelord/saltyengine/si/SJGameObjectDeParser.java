package de.edgelord.saltyengine.si;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.ImageObject;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.sanjo.SJClass;
import de.edgelord.sanjo.SJValue;

import java.awt.*;
import java.util.Map;

public interface SJGameObjectDeParser {
    static SJClass defaultDeparsing(final GameObject object, final String className) {
        final SJClass clazz = new SJClass(className);
        switch (object.getTag()) {
            case ImageObject.TAG:
                clazz.addValue(SJFormatKeys.KEY_ID, ImageObject.TAG);
                clazz.addValue(SJFormatKeys.KEY_TRANSFORM, deparseTransform(object.getTransform()));
                clazz.addValue(SJFormatKeys.KEY_IMAGE, ((ImageObject) object).getImgPath());
                return clazz;
            default:
                return null;
        }
    }

    static SJValue deparseTransform(final Transform t) {
        return new SJValue(SJFormatKeys.KEY_TRANSFORM, t.getX() + ", " + t.getY() + ", " + t.getWidth() + ", " + t.getHeight());
    }

    /**
     * Deparses the given color into a {@link SJValue}. When {@code tryName} is
     * true, this method will try to match the given Color with one from the
     * {@link de.edgelord.saltyengine.utils.ColorUtil} class and deparse it as
     * the name of that color instead.
     *
     * @param c       a color
     * @param tryName try to match it to a {@link de.edgelord.saltyengine.utils.ColorUtil}
     *                color and deparse it as the name of it?
     *
     * @return the deparsed color
     */
    static SJValue deparseColor(final Color c, final boolean tryName) {
        if (tryName) {
            for (final Map.Entry<String, Color> e : ColorUtil.colors.entrySet()) {
                if (e.getValue().equals(c)) {
                    return new SJValue(SJFormatKeys.KEY_COLOR, "\"" + e.getKey() + "\"");
                }
            }
        }
        return new SJValue(SJFormatKeys.KEY_COLOR, c.getRed() + ", " + c.getGreen() + ", " + c.getBlue());
    }

    /**
     * Deparses the given GameObject into an SJClass used to re-parse it via
     * {@link SJGameObjectParser}
     *
     * @param object an object
     *
     * @return the SJClass representation of the object
     */
    SJClass deparse(final GameObject object);
}
