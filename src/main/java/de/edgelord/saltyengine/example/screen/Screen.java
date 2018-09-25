package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;

public class Screen extends GameObject {

    private final Transform screen;
    private ScreenContent screenContent;

    private String usageMessage = "Use WASD or the arrow keys to move the screen content!";

    public Screen() {
        super(Game.getHost().getCentrePosition(250, 450), 250, 450, "screen");

        screen = new Transform(getX() + 10, getY() + 10, getWidth() - 20, getHeight() - 20);
        screenContent = new ScreenContent(getX(), getY());

        getPhysics().removeGravity();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {

        if (StaticSystem.inputLeft) {
            screenContent.moveX(7f);
        } else if (StaticSystem.inputRight) {
            screenContent.moveX(-7f);
        }

        if (StaticSystem.inputUp) {
            screenContent.moveY(7f);
        } else if (StaticSystem.inputDown) {
            screenContent.moveY(-7f);
        }
    }

    @Override
    public void onTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        // Draw the screen surrounding
        saltyGraphics.setStroke(new BasicStroke(20));
        saltyGraphics.drawRoundRect(this, 50);
        saltyGraphics.setStroke(new BasicStroke(20));
        saltyGraphics.drawRect(screen);

        // set the clip so that the following things only get drawn within the area of the screen
        saltyGraphics.setClip(screen);

        // Draw the screen content
        screenContent.draw(saltyGraphics);

        // Reset the clip
        saltyGraphics.resetClip();
    }
}
