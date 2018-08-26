/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.hitbox;

import de.edgelord.saltyengine.gameobject.GameObject;

public interface Hitbox {

    boolean collides(GameObject other);
}
