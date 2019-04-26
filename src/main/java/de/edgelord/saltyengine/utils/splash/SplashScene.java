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

import de.edgelord.saltyengine.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link de.edgelord.saltyengine.scene.Scene} with a collection of {@link Splash}es linked together with transitions in between.
 */
public abstract class SplashScene extends Scene {

    private List<SplashPlayer> splashPlayers = new ArrayList<>();


    public SplashScene(Splash... splashes) {

        if (splashes.length == 0) {
            throw new IllegalArgumentException("You must at least give 1 Splash!");
        }

        for (int i = 0; i < splashes.length; i++) {
            Splash splash = splashes[i];

            if (i != splashes.length - 1) {
                final int currI = i;

                splashPlayers.add(new SplashPlayer(splash) {

                    @Override
                    public void onSplashFinish() {
                        addGameObject(splashes[currI + 1]);
                    }
                });
            } else {
                splashPlayers.add(new SplashPlayer(splash) {

                    @Override
                    public void onSplashFinish() {
                        SplashScene.this.onSplashFinish();
                    }
                });
            }
        }

        addGameObject(splashes[0]);
    }

    public abstract void onSplashFinish();
}
