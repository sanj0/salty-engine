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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.*;

/**
 * And implementation of {@link SceneGFXComponent} that makes the current {@link de.edgelord.saltyengine.scene.Scene} fade in
 * or out from/to a {@link #targetColor} in a specified {@link #duration}. To start this, you have to call {@link #fadeInit()} and {@link #startGFX()} <br>
 * As soon as the fade is finished, this <code>SceneFade</code> removes its used {@link DrawingRoutine} and itself from the current <code>Scene</code> and the {@link de.edgelord.saltyengine.core.graphics.GFXController}
 * and the method {@link #onFadeFinish()} is called. This way, you could chain a fade-out and a fade-in together.
 */
public class SceneFade extends SceneGFXComponent {

    /**
     * The target color of the fade. This is either the starting color (for an in-fade) or the ending color (for an out-fade)
     */
    private Color targetColor;

    /**
     * The current color of the fade.
     */
    private Color currentColor;

    /**
     * The {@link Mode} of the fade.
     */
    private Mode mode;

    /**
     * The {@link LinearKeyframeAnimation} used for the fade.
     */
    private LinearKeyframeAnimation fadeFX;

    /**
     * The duration. This is {@code 1500} by default. For a different value, you have to call {@link #setDuration(int)} before calling {@link #fadeInit()}.
     */
    private int duration = 1500;

    /**
     * The current alpha of the fade color.
     */
    private float currentAlpha;

    /**
     * The {@link DrawingRoutine} used for drawing the fade to make sure that the fade is above any {@link de.edgelord.saltyengine.gameobject.GameObject}s.
     */
    private DrawingRoutine fadeDraw;

    /**
     * The mode of the fade.
     */
    public enum Mode {

        /**
         * Fade in animation.
         */
        FADE_IN,
        /**
         * Fade out animation.
         */
        FADE_OUT
    }

    /**
     * The constructor.
     *
     * @param name        the name of this {@link de.edgelord.saltyengine.components.gfx.GFXComponent}
     * @param mode        the mode of the fade
     * @param targetColor the {@link #targetColor} of the fade
     */
    public SceneFade(String name, Mode mode, Color targetColor) {
        super(name);

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

    /**
     * A constructor that sets {@link #targetColor} to plain black if the given {@link Mode} is {@link Mode#FADE_OUT} and
     * plain white if it is {@link Mode#FADE_IN}.
     *
     * @param name the name of this {@link de.edgelord.saltyengine.components.gfx.GFXComponent}
     * @param mode the mode of the fade
     */
    public SceneFade(String name, Mode mode) {
        this(name, mode, Color.BLACK);

        if (mode == Mode.FADE_IN) {
            this.targetColor = Color.WHITE;
        }
    }

    /**
     * This method is supposed to get overridden when it's used like a trigger but it's not necessary
     */
    public void onFadeFinish() {
    }

    /**
     * This is called when the fade is finished.
     */
    private void end() {
        SceneManager.getCurrentScene().removeDrawingRoutine(fadeDraw);
        remove();
    }

    /**
     * Initializes the fade. You have to call this before you cal {@link #startGFX()}.
     */
    public void fadeInit() {

        if (mode == Mode.FADE_OUT) {
            currentColor = new Color(targetColor.getRed(), targetColor.getGreen(), targetColor.getBlue(), 0);
            currentAlpha = 0;
        } else {
            currentColor = new Color(targetColor.getRed(), targetColor.getGreen(), targetColor.getBlue(), 255);
            currentAlpha = 255;
        }

        fadeFX = new LinearKeyframeAnimation();

        fadeFX.add(duration, 255);
        fadeFX.calculateAnimation();
    }

    /**
     * This is used by the {@link #fadeDraw} to draw the fade.
     *
     * @param saltyGraphics the graphics to render to
     */
    private void drawFade(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(currentColor);
        saltyGraphics.drawRect(0, 0, Game.getGameWidth(), Game.getGameHeight());
    }

    /**
     * Fades the fade.
     */
    @Override
    public void onFixedTick() {

        if (fadeFX != null) {

            float alphaDelta = fadeFX.nextDelta();

            if (fadeFX.animationEnded()) {
                onFadeFinish();
                end();
                return;
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

    /**
     * An empty implementation because the drawing is done by the {@link #fadeDraw DrawingRoutine}.
     *
     * @param saltyGraphics teh graphics to render to
     */
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
