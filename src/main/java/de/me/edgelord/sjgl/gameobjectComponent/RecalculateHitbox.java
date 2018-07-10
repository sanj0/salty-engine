package de.me.edgelord.sjgl.gameobjectComponent;

import de.me.edgelord.sjgl.gameobject.GameObject;

import java.awt.*;

public class RecalculateHitbox extends Component {

    public RecalculateHitbox(GameObject parent) {
        super(parent);
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
