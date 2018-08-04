package de.edgelord.sjgl.gameobject.components;

import de.edgelord.sjgl.gameobject.GameObject;
import de.edgelord.sjgl.gameobject.GameObjectComponent;

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
