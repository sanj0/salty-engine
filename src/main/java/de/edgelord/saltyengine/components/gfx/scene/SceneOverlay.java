package de.edgelord.saltyengine.components.gfx.scene;

import de.edgelord.saltyengine.components.gfx.GFXComponent;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.stereotypes.ComponentParent;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.*;

/**
 * This {@link GFXComponent} for the whole {@link Scene} lays the {@link Color} {@link #overlayColor} with the alpha value
 * {@link #alpha} onto the scene by assigning a {@link DrawingRoutine} to the
 * current scene with the {@link DrawingRoutine#drawingPosition}
 * {@link DrawingRoutine.DrawingPosition#AFTER_GAMEOBJECTS} to ensure that the drawn color is above all
 * {@link de.edgelord.saltyengine.gameobject.GameObject}s.
 * When no longer needed, you should call {@link #endGFX()} to make sure that the DrawingRoutine is no longer in the scene.
 */
public class SceneOverlay extends GFXComponent {

    /**
     * The {@link Color} which will be rendered onto the screen
     */
    private Color overlayColor;

    /**
     * The alpha value with which the {@link #overlayColor} will be rendered.
     * This value goes from 0f (fully transparent) to 1f (no transparency)
     */
    private float alpha;

    private DrawingRoutine drawColorOverlay;

    public SceneOverlay(ComponentParent parent, String name, Color overlayColor, float alpha) {
        super(parent, name);

        this.overlayColor = overlayColor;
        this.alpha = alpha;

        drawColorOverlay = new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
            @Override
            public void draw(SaltyGraphics saltyGraphics) {

                int alphaInt = Math.round(255f * alpha);

                Color overlay = new Color(overlayColor.getRed(), overlayColor.getGreen(), overlayColor.getBlue(), alphaInt);

                saltyGraphics.setColor(overlay);
                saltyGraphics.drawRect(-Game.camera.getX(), -Game.camera.getY(), Game.getHost().getWidth(), Game.getHost().getHeight());
            }
        };
    }

    public SceneOverlay(ComponentParent parent, String name, Color overlayColor) {
        this(parent, name, overlayColor, 0.5f);
    }

    @Override
    public void startGFX() {
        super.startGFX();

        SceneManager.getCurrentScene().addDrawingRoutine(drawColorOverlay);
    }

    @Override
    public void endGFX() {
        super.endGFX();

        SceneManager.getCurrentScene().removeDrawingRoutine(drawColorOverlay);
    }

    /**
     * All of the drawing happens inside the {@link DrawingRoutine} {@link #drawColorOverlay}.
     *
     * @param saltyGraphics the graphics context to draw with
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {
    }

    @Override
    public void onFixedTick() {

    }

    public Color getOverlayColor() {
        return overlayColor;
    }

    public void setOverlayColor(Color overlayColor) {
        this.overlayColor = overlayColor;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }
}
