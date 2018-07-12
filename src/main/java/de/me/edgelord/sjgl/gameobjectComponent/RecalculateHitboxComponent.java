package de.me.edgelord.sjgl.gameobjectComponent;

import de.me.edgelord.sjgl.gameobject.GameObject;

import java.awt.*;

public class RecalculateHitboxComponent extends Component {

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
