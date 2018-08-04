package de.edgelord.sjgl.hitbox;

import de.edgelord.sjgl.gameobject.GameObject;

public interface Hitbox {

    boolean collides(GameObject other);
}
