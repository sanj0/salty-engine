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

package de.edgelord.saltyengine.example.splash;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.splash.Splash;
import de.edgelord.saltyengine.utils.splash.SplashScene;

import java.awt.*;

/**
 * An example main on how to use {@link de.edgelord.saltyengine.utils.splash.SplashScene}
 */
public class SplashExample extends Game {

    public static void main(String[] args) {
        init(GameConfig.config(1920, 1080, "Salty Engine Splash example", 5));
        start(60);
        getHost().toggleFullscreen();

        SplashScene splashScene = new SplashScene(new Splash(new SaltyImage("res/pictures/spritesheets/bird_spritesheet.png"), 30, 1f, Color.BLACK)) {
            @Override
            public void onSplashFinish() {
                System.out.println("Splash finished! The game would now start!");
            }
        };
        SceneManager.setCurrentScene(splashScene);
    }
}
