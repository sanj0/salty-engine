/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.camera.Camera;
import de.edgelord.saltyengine.display.DisplayManager;
import de.edgelord.saltyengine.graphics.GFXController;
import de.edgelord.saltyengine.input.Keyboard;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Time;

import java.awt.event.KeyEvent;

public class Game {

    private static GFXController defaultGFXController = new GFXController();

    public static String gameName = "My name is Nym. Arno Nym.";
    public static boolean paused = false;
    public static Camera camera = new Camera();
    public static boolean inputUp = false;
    public static boolean inputDown = false;
    public static boolean inputRight = false;
    public static boolean inputLeft = false;
    public static char lastInputKey;
    /**
     * You have to add a ifn check if you use this!
     */
    public static KeyEvent lastInput;
    /**
     * For input that is not meant for typing use this!
     * Please use this for every input that has to do with controls etc etc
     */
    public static Keyboard keyboardInput = new Keyboard();

    public static Vector2f cursorPosition = new Vector2f(0, 0);
    public static Transform cursor = new Transform(0, 0, 0, 0);
    public static boolean mouseDrags = false;
    public static boolean mousePresses = false;

    /**
     * Proposes the <code>Host</code> to draw the FPS {@link Time#getFPS()} or not.
     * All <code>Host</code>s included in this library will accept that. 3rd party
     * <code>Host</code>s may not and do not have to.
     */
    private static boolean drawFPS = true;

    private static Host host;
    private static Engine engine;

    public Game(int windowWidth, int windowHeight, String gameName, long fixedTickMillis) {

        System.setProperty("sun.java2d.opengl", "True");

        engine = new Engine(fixedTickMillis);

        host = new DisplayManager(windowWidth, windowHeight, gameName, engine);
    }

    public Game(Host host, String gameName, long fixedTickMillis) {

        System.setProperty("sun.java2d.opengl", "True");

        engine = new Engine(fixedTickMillis);

        Game.host = host;
    }

    /**
     * When using this constructor you should set the static Engine immediately after calling this constructor
     *
     * @param host     the host of the Game
     * @param gameName the name of the game
     */
    public Game(Host host, String gameName) {

        System.setProperty("sun.java2d.opengl", "true");

        Game.host = host;
    }

    public static void start() {

        host.create();

        engine.start();
    }

    public static void start(long fixedFPS) {

        host.create();

        engine.start(fixedFPS);
    }

    protected static void setEngine(Engine engine) {
        Game.engine = engine;
    }

    public static Host getHost() {
        return host;
    }

    public static DisplayManager getHostAsDisplayManager() {
        return (DisplayManager) host;
    }

    public static Engine getEngine() {
        return engine;
    }

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        Game.paused = paused;
    }

    public static GFXController getDefaultGFXController() {
        return defaultGFXController;
    }

    public static void setDefaultGFXController(GFXController defaultGFXController) {
        Game.defaultGFXController = defaultGFXController;
    }

    public static boolean isDrawFPS() {
        return drawFPS;
    }

    public static void setDrawFPS(boolean drawFPS) {
        Game.drawFPS = drawFPS;
    }
}
