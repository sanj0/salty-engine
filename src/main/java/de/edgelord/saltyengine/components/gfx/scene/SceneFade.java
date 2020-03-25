/*
 * Copyright 2019 Malte Dostal
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
import de.edgelord.saltyengine.core.animation.KeyframeAnimation;
import de.edgelord.saltyengine.core.animation.LinearKeyframeAnimation;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;

/**
 * A {@link SceneGFXComponent} that draws a fade.
 * The fade is either {@link Fade#IN} or {@link Fade#OUT}.
 * <p>
 * <br>
 * Usage: <br>
 *
 * <code>
 * SceneFade fade = new SceneFade("myFade", ColorUtil.BLACK, 2000, SceneFade.Fade.OUT, myScene); <br>
 * fade.startGFX();
 * </code>
 */
public abstract class SceneFade extends SceneGFXComponent {

    /**
     * The <code>DrawingRoutine</code> that  is used to draw the fade.
     */
    private DrawingRoutine drawingRoutine;

    /**
     * The current alpha value of the fade.
     */
    private float currentAlpha;

    /**
     * The <code>KeyframeAnimation</code> used to animated the fade.
     */
    private KeyframeAnimation animation;

    /**
     * The <code>Scene</code> that contains the fade.
     */
    private Scene container;

    /**
     * The mode of the fade.
     */
    public enum Fade {

        /**
         * Fade in, meaning that the fade is fully visible at th beginning and fully invisible at the end.
         */
        IN,

        /**
         * Fade out, meaning that the fade is fully invisible at the beginning and fully visible at the end.
         */
        OUT
    }

    /**
     * The constructor.
     *
     * @param name      the name of the component
     * @param color     the <code>Color</code> of the fade
     * @param duration  the duration of the fade in amount of fixed ticks
     * @param mode      the mode of the fade
     * @param container the container of the fade
     */
    public SceneFade(String name, Color color, int duration, Fade mode, Scene container) {
        super(name);

        this.container = container;

        animation = new LinearKeyframeAnimation();

        switch (mode) {

            case IN:
                currentAlpha = 1f;
                animation.add(0, 1f);
                animation.add(duration, 0f);
                animation.calculateAnimation();
                break;
            case OUT:
                currentAlpha = 0f;
                animation.add(0, 0f);
                animation.add(duration, 1f);
                animation.calculateAnimation();
                break;
        }

        drawingRoutine = new DrawingRoutine(DrawingRoutine.DrawingPosition.LAST) {
            @Override
            public void draw(SaltyGraphics saltyGraphics) {

                if (isEnabled()) {
                    saltyGraphics.setColor(ColorUtil.withAlpha(color, currentAlpha));
                    saltyGraphics.drawRect(Vector2f.zero(), Game.getGameDimensions());
                }
            }
        };

        container.addDrawingRoutine(drawingRoutine);
    }

    /**
     * This method is called when the fade is finished, to e.g. load the next Scene or activate the content.
     */
    public abstract void onFadeFinish();

    /**
     * Empty implementation because the drawing is done with the {@link #drawingRoutine}.
     *
     * @param saltyGraphics the graphics to draw to
     */
    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    /**
     * Updates the {@link #currentAlpha} or calls {@link #onFadeFinish()} and cleans up when the fade ended.
     */
    @Override
    public void onFixedTick() {

        if (animation.animationEnded()) {
            onFadeFinish();
            container.removeDrawingRoutine(drawingRoutine);
            remove();
        } else {
            currentAlpha += animation.nextDelta();
        }
    }
}
