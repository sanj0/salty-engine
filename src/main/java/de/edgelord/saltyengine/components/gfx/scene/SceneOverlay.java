/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

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
                saltyGraphics.drawRect(-Game.camera.getX(), -Game.camera.getY(), Game.getHost().getWidth(), Game.getHost().getHeight());
            }
        };
    }

    public SceneOverlay(ComponentParent parent, String name, Color overlayColor) {
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
