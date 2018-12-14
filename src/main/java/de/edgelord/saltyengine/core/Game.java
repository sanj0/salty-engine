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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.camera.Camera;
import de.edgelord.saltyengine.display.DisplayManager;
import de.edgelord.saltyengine.display.DisplayRatio;
import de.edgelord.saltyengine.display.SplashWindow;
import de.edgelord.saltyengine.graphics.GFXController;
import de.edgelord.saltyengine.io.serialization.Serializer;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import java.io.IOException;

public class Game {

    private static GFXController defaultGFXController = new GFXController();

    public static String gameName = "salty-engine";
    public static boolean paused = false;
    private static Camera camera = new Camera();

    private static Dimensions gameDimensions;

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
        gameDimensions = new Dimensions(resolutionWidth, resolutionHeight);

        SaltySystem.defaultHiddenOuterResource = new OuterResource(true);
        SaltySystem.defaultOuterResource = new OuterResource(false);
    }

    public Game(Host host, String gameName, long fixedTickMillis) {

        System.setProperty("sun.java2d.opengl", "True");

        engine = new Engine(fixedTickMillis);
        gameDimensions = host.getCurrentDimensions();

        Game.host = host;
        SaltySystem.defaultHiddenOuterResource = new OuterResource(true);
        SaltySystem.defaultOuterResource = new OuterResource(false);
    }

    public static Dimensions getGameDimensions() {
        return gameDimensions;
    }

    public static float getGameWidth() {
        return gameDimensions.getWidth();
    }

    public static float getGameHeight() {
        return gameDimensions.getHeight();
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

    public static Camera getCamera() {
        return camera;
    }

    public static void setCamera(Camera camera) {
        Game.camera = camera;
    }

    public static void saveOnExit(String safeFile) {
        WindowClosingHooks.addShutdownHook(() -> {
            try {
                Serializer.doSerialization(safeFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public static void saveOnExit() {
        saveOnExit(Serializer.getSaveFileName());
    }

    public static void serializeOnExit() {
        saveOnExit();
    }

    public static void serializeOnExit(String safeFile) {
        saveOnExit(safeFile);
    }
}
