/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.hitbox;

import de.edgelord.sjgl.gameobject.GameObject;

public interface Hitbox {

    boolean collides(GameObject other);
}
