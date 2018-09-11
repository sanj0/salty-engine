/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.hitbox;

import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Transform;

public interface Hitbox {

    boolean collides(GameObject other);

    Transform getTransform();

    void recalculate();
}
