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

package testing;

import de.edgelord.saltyengine.audio.AudioSystem;
import de.edgelord.saltyengine.components.gfx.scene.SceneFade;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.lang.reflect.InvocationTargetException;

public class Tester extends Game {

    private static AudioSystem audioSystem;

    public Tester(final int windowWidth, final int windowHeight, final String gameName, final long fixedTickMillis) {
        super(windowWidth, windowHeight, gameName, fixedTickMillis);

        System.out.println("Welcome to Salty Engine v" + StaticSystem.versionTag);

        Tester.audioSystem = new AudioSystem(new AudioFactory(new InnerResource()));

        Tester.audioSystem.loadNewAudio("joy_sticky", "res/audio/music/Joy Sticky.wav");
        Tester.audioSystem.loadNewAudio("bird_flap", "res/audio/sound/flap.wav");

        Tester.audioSystem.loop("joy_sticky");

        audioSystem.setClipVolume("joy_sticky", 0.75f);

        getHost().setBackgroundColor(Color.lightGray);
    }

    public static void main(final String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        final Tester tester = new Tester(1200, 900, "testing", 1);

        SceneManager.addScene("testingScene", TestingScene.class);

        start(60);

        SceneManager.setCurrentScene("testingScene", "foo", 842);

        SceneFade fadeIn = new SceneFade("fadeIn", SceneFade.Mode.FADE_IN, Color.BLACK);

        fadeIn.setDuration(3500);
        fadeIn.fadeInit();
        Game.getDefaultGFXController().addGFX(fadeIn);
        Game.getDefaultGFXController().startAll();
    }

    public static AudioSystem getAudioSystem() {
        return audioSystem;
    }
}
