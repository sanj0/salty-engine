package de.me.edgelord.sjgl.gameobject.components;

import de.me.edgelord.sjgl.gameobject.GameObjectComponent;
import de.me.edgelord.sjgl.gameobject.GameObject;

import java.awt.*;

public class RecalculateHitboxComponent extends GameObjectComponent {

    public RecalculateHitboxComponent(GameObject parent, String name) {
        super(parent, name);
    }

    @Override
    public void onFixedTick() {

        getParent().getHitbox().recalculate();
    }

    @Override
    public void draw(Graphics2D graphics) {

    }

    @Override
    public void onCollision(GameObject other) {

    }
}
