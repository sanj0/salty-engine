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

package testing;

import de.edgelord.saltyengine.audio.AudioSystem;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.display.SplashWindow;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Tester extends Game {

    private static AudioSystem audioSystem;

    private static void initGame() {

        Game.init(GameConfig.config(1200f, 900f, "testing", 1L));

        System.out.println("Welcome to Salty Engine v" + SaltySystem.versionTag);

        Tester.audioSystem = new AudioSystem(new AudioFactory(new InnerResource()));

        Tester.audioSystem.loadNewAudio("joy_sticky", "res/audio/music/Joy Sticky.wav");
        Tester.audioSystem.loadNewAudio("bird_flap", "res/audio/sound/flap.wav");

        Tester.audioSystem.loop("joy_sticky");

        audioSystem.setClipVolume("joy_sticky", 0.75f);

        getHost().setBackgroundColor(Color.lightGray);
    }

    public static void main(final String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        initGame();

        SceneManager.addScene("testingScene", TestingScene.class);

        start(SplashWindow.Splash.NO_SPLASH);
        serializeOnExit();

        SceneManager.setCurrentScene("testingScene", "foo", 842);

        //SceneFade fadeIn = new SceneFade("fadeIn", SceneFade.Mode.FADE_IN, Color.BLACK);

        //fadeIn.setDuration(3500);
        //fadeIn.fadeInit();
        // Game.getDefaultGFXController().addGFX(fadeIn);
        // Game.getDefaultGFXController().startAll();
    }

    public static AudioSystem getAudioSystem() {
        return audioSystem;
    }
}
