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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.camera.Camera;
import de.edgelord.saltyengine.display.DisplayManager;
import de.edgelord.saltyengine.display.DisplayRatio;
import de.edgelord.saltyengine.display.SplashWindow;
import de.edgelord.saltyengine.graphics.GFXController;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.Time;

public class Game {

    private static GFXController defaultGFXController = new GFXController();

    public static String gameName = "My name is Nym. Arno Nym.";
    public static boolean paused = false;
    public static Camera camera = new Camera();

    /**
     * Proposes the <code>Host</code> to draw the FPS {@link Time#getFPS()} or not.
     * All <code>Host</code>s included in this library will accept that. 3rd party
     * <code>Host</code>s may not and do not have to.
     */
    private static boolean drawFPS = true;

    private static Host host;
    private static Engine engine;

    public Game(float resolutionWidth, float resolutionHeight, String gameName, long fixedTickMillis) {

        System.setProperty("sun.java2d.opengl", "True");

        engine = new Engine(fixedTickMillis);

        host = new DisplayManager(new DisplayRatio(new Dimensions(resolutionWidth, resolutionHeight)), gameName, engine);
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

        System.setProperty("sun.java2d.opengl", "True");

        Game.host = host;
    }

    public static void start(SplashWindow.Splash splash) {

        GameStarter.startGame(-1, splash);
    }

    public static void start(long fixedFPS, SplashWindow.Splash splash) {

        GameStarter.startGame(fixedFPS, splash);
    }

    public static void start() {

        GameStarter.startGame(-1, SplashWindow.Splash.DEFAULT_SPLASH);
    }

    public static void start(long fixedFPS) {

        GameStarter.startGame(fixedFPS, SplashWindow.Splash.DEFAULT_SPLASH);
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
