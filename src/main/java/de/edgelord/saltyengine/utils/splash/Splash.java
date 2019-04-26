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

package de.edgelord.saltyengine.utils.splash;

import de.edgelord.saltyengine.components.gfx.scene.SceneFade;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.gameobject.EmptyGameObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;

public class Splash extends EmptyGameObject {

    private SaltyImage image;
    private float summandWidth;
    private float summandHeight;
    private int time = 0;
    private int duration;

    private boolean fadeInFinished = false;
    private SceneFade fadeIn;
    private SceneFade fadeOut;

    private SplashPlayer supervisor = null;

    public Splash(SaltyImage image, Dimensions startDimensions, int duration, float delta, Color fadeColor) {
        super(0, 0, startDimensions.getWidth(), startDimensions.getHeight(), "de.edgelord.saltyengine.utils.splash");

        this.image = image;
        this.duration = (int) ((duration * 1000) / SaltySystem.fixedTickMillis);

        summandWidth = (delta / this.duration) * getWidth();
        summandHeight = (delta / this.duration) * getHeight();

        fadeIn = new SceneFade("splash-fade-in", SceneFade.Mode.FADE_IN, fadeColor) {
            @Override
            public void onFadeFinish() {
                fadeInFinished = true;
            }
        };
        fadeIn.fadeInit();
        fadeOut = new SceneFade("splash-fade-out", SceneFade.Mode.FADE_OUT, fadeColor) {
            @Override
            public void onFadeFinish() {
                if (supervisor != null) {
                    supervisor.onSplashFinish();
                }
            }
        };
        fadeOut.fadeInit();

        Game.getDefaultGFXController().addGFX(fadeIn);
        Game.getDefaultGFXController().addGFX(fadeOut);

        fadeIn.startGFX();
    }

    public Splash(SaltyImage image, int duration, float delta, Color fadeColor) {
        this(image, image.getDimensions(), duration, delta, fadeColor);
    }

    @Override
    public void onFixedTick() {

        if (fadeInFinished) {
            if (time < duration) {
                time++;

                setWidth(getWidth() + summandWidth);
                setHeight(getHeight() + summandHeight);

                positionByCentre(Game.getCentre());
            } else {
                fadeOut.startGFX();
                removeFromCurrentScene();
            }
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(image, getTransform());
    }

    /**
     * Sets {@link #supervisor}.
     *
     * @param supervisor the new value of {@link #supervisor}
     */
    public void setSupervisor(SplashPlayer supervisor) {
        this.supervisor = supervisor;
    }
}
