package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.image.BufferedImage;

public class ScreenContent {

    private float xOrigin;
    private float yOrigin;

    private BufferedImage bgImage = StaticSystem.defaultImageFactory.getOptimizedImageResource("res/pictures/bg.png");

    private Vector2f origin;

    public ScreenContent(float xOrigin, float yOrigin) {

        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;

        updateOrigin();
    }

    public void draw(SaltyGraphics graphics) {

        graphics.drawImage(bgImage, origin);
    }

    public void moveX(float delta) {
        xOrigin += delta;
        updateOrigin();
    }

    public void moveY(float delta) {
        yOrigin += delta;
        updateOrigin();
    }

    private void updateOrigin() {
        origin = new Vector2f(xOrigin, yOrigin);
    }
}
