package de.me.edgelord.sjgl.hitbox;

import de.me.edgelord.sjgl.gameobject.GameObject;

public interface Hitbox {

    boolean collides(GameObject other);
}
