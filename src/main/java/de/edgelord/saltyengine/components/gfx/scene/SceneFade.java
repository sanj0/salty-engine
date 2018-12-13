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
import de.edgelord.saltyengine.core.stereotypes.ComponentContainer;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.*;

public class SceneFade extends SceneGFXComponent {

    private Color targetColor;
    private Color currentColor;
    private Mode mode;
    private LinearKeyframeAnimation fadeFX;
    private int duration = 1500;
    private float currentAlpha;

    private DrawingRoutine fadeDraw;

    public enum Mode {FADE_IN, FADE_OUT}

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

    public SceneFade(ComponentContainer parent, String name, Mode mode) {
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

        fadeFX = new LinearKeyframeAnimation();

        fadeFX.add(duration, 255);
        fadeFX.calculateAnimation();
    }

    private void drawFade(SaltyGraphics saltyGraphics) {
        saltyGraphics.setColor(currentColor);
        saltyGraphics.drawRect(0, 0, Game.getHostAsDisplayManager().getWidth(), Game.getHostAsDisplayManager().getHeight());
    }

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
