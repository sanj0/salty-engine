/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.components.gfx.scene;

import de.edgelord.saltyengine.components.gfx.GFXComponent;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
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
public class SceneOverlay extends SceneGFXComponent {

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

    public SceneOverlay(String name, Color overlayColor, float alpha) {
        super(name);

        this.overlayColor = overlayColor;
        this.alpha = alpha;

        drawColorOverlay = new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
            @Override
            public void draw(SaltyGraphics saltyGraphics) {

                int alphaInt = Math.round(255f * alpha);

                Color overlay = new Color(overlayColor.getRed(), overlayColor.getGreen(), overlayColor.getBlue(), alphaInt);

                saltyGraphics.setColor(overlay);
                saltyGraphics.drawRect(-Game.getCamera().getX(), -Game.getCamera().getY(), Game.getGameWidth(), Game.getGameHeight());
            }
        };
    }

    public SceneOverlay(ComponentContainer parent, String name, Color overlayColor) {
        this(name, overlayColor, 0.5f);
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
