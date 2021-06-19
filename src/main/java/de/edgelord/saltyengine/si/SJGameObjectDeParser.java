package de.edgelord.saltyengine.si;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.sanjo.SJClass;

public interface SJGameObjectDeParser {
    /**
     * Deparses the given GameObject into an SJClass
     * used to re-parse it via {@link SJGameObjectParser}
     * @param object an object
     * @return the SJClass representation of the object
     */
    SJClass deparse(final GameObject object);
}
