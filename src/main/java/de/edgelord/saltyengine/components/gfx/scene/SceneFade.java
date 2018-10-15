package de.edgelord.saltyengine.components.gfx.scene;

import de.edgelord.saltyengine.components.gfx.GFXComponent;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.*;

public class SceneFade extends GFXComponent {

    private Color targetColor;
    private Color currentColor;
    private Mode mode;
    private KeyframeAnimation fadeFX;
    private int duration = 1500;
    private float currentAlpha;

    private DrawingRoutine fadeDraw;

    public enum Mode {FADE_IN, FADE_OUT}

    public SceneFade(ComponentParent parent, String name, Mode mode, Color targetColor) {
        super(parent, name);

        this.mode = mode;
        this.targetColor = targetColor;

        fadeDraw = new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
            @Override
            public void draw(SaltyGraphics saltyGraphics) {
                drawFade(saltyGraphics);
            }
        };
        SceneManager.getCurrentScene().addDrawingRoutine(fadeDraw);
    }

    public SceneFade(ComponentParent parent, String name, Mode mode) {
        this(parent, name, mode, Color.BLACK);

        if (mode == Mode.FADE_IN) {
            this.targetColor = Color.WHITE;
        }
    }

    /**
     * This method is supposed to get overridden when it's used like a trigger but it's not necessary
     */
    public void onFadeFinish() {
    }

    private void end() {
        SceneManager.getCurrentScene().removeDrawingRoutine(fadeDraw);
        remove();
    }

    public void fadeInit() {

        if (mode == Mode.FADE_OUT) {
            currentColor = new Color(targetColor.getRed(), targetColor.getGreen(), targetColor.getBlue(), 0);
            currentAlpha = 0;
        } else {
            currentColor = new Color(targetColor.getRed(), targetColor.getGreen(), targetColor.getBlue(), 255);
            currentAlpha = 255;
        }

        fadeFX = new KeyframeAnimation();

        fadeFX.add(duration, 255);
        fadeFX.calculateAnimation();

        startGFX();
    }

    private void drawFade(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(currentColor);
        saltyGraphics.drawRect(0, 0, Game.getHostAsDisplayManager().getWidth(), Game.getHostAsDisplayManager().getHeight());
    }

    @Override
    public void onFixedTick() {

        if (fadeFX != null) {

            float alphaDelta = fadeFX.nextDelta();

            if (alphaDelta <= 0.00f) {
                onFadeFinish();
                end();
            }

            switch (mode) {

                case FADE_IN:
                    currentAlpha -= alphaDelta;
                    break;
                case FADE_OUT:
                    currentAlpha += alphaDelta;
                    break;
            }
            currentColor = new Color(targetColor.getRed(), targetColor.getGreen(), targetColor.getBlue(), (int) (currentAlpha));
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}
