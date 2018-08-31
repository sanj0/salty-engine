package de.edgelord.saltyengine.cosmetic.lighting;

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

public class LightSystem extends GameObject {

    private Color background = Color.BLACK;

    private DrawingRoutine drawBackground = new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
        @Override
        public void draw(Graphics2D graphics2D) {
            drawBackground(graphics2D);
        }
    };

    public LightSystem(Vector2f position, float width, float height, String tag) {
        super(position.getX(), position.getY(), width, height, tag);
    }

    private void drawBackground(Graphics2D graphics) {

    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(Graphics2D graphics) {

    }
}
