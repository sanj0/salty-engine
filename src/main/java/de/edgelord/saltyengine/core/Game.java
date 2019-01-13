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

import de.edgelord.saltyengine.core.camera.Camera;
import de.edgelord.saltyengine.core.graphics.GFXController;
import de.edgelord.saltyengine.displaymanager.display.DisplayManager;
import de.edgelord.saltyengine.displaymanager.display.DisplayRatio;
import de.edgelord.saltyengine.displaymanager.display.SplashWindow;
import de.edgelord.saltyengine.io.serialization.Serializer;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import java.io.IOException;

/**
 * The central core class for a Salty Engine game. To start a game, call whether
 * {@link #init(GameConfig)} or {@link #init(Host, String, long)} <br>
 * <p>
 * and then
 * <p>
 * {@link #start()}, {@link #start(long)}, {@link #start(long, SplashWindow.Splash)} or {@link #start(SplashWindow.Splash)}
 */
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

    /**
     * Initialized the game with the given {@link GameConfig} and a {@link DisplayManager} as {@link Host}.
     *
     * @param config the configuration of the game
     */
    public static void init(GameConfig config) {
        internalPreInitDisplayManager(config);
    }

    /**
     * Initializes the game with the given {@link Host}, the given name and the given milliseconds for the fixed tick.
     *
     * @param host            the {@link Host} for the game
     * @param gameName        the name of the game
     * @param fixedTickMillis the milliseconds for the periodical fixed update for e.g. physics
     */
    public static void init(Host host, String gameName, long fixedTickMillis) {
        internalPreInitForForeignHost(host, gameName, fixedTickMillis);
    }

    private static void internalPreInitForForeignHost(Host host, String gameName, long fixedTickMillis) {

        enableOpenGl();

        engine = new Engine(fixedTickMillis);
        gameDimensions = host.getCurrentDimensions();

        Game.host = host;
        SaltySystem.defaultHiddenOuterResource = new OuterResource(true);
        SaltySystem.defaultOuterResource = new OuterResource(false);
    }

    private static void internalPreInitDisplayManager(GameConfig config) {

        enableOpenGl();

        engine = new Engine(config.getFixedTickMillis());
        host = new DisplayManager(new DisplayRatio(new Dimensions(config.getResWidth(), config.getResHeight())), config.getGameName(), engine);
        gameDimensions = new Dimensions(config.getResWidth(), config.getResHeight());

        SaltySystem.defaultHiddenOuterResource = new OuterResource(true);
        SaltySystem.defaultOuterResource = new OuterResource(false);
    }

    private static void enableOpenGl() {
        System.setProperty("sun.java2d.opengl", "True");
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

    /**
     * Starts the game with the given {@link de.edgelord.saltyengine.displaymanager.display.SplashWindow.Splash} before and running
     * with the maximum fps.
     * You should call {@link #init(GameConfig)} or {@link #init(Host, String, long)} first.
     *
     * @param splash the {@link de.edgelord.saltyengine.displaymanager.display.SplashWindow.Splash} to be displayed before the game
     */
    public static void start(SplashWindow.Splash splash) {

        GameStarter.startGame(-1, splash);
    }

    /**
     * Starts the game with the given fps and the given {@link de.edgelord.saltyengine.displaymanager.display.SplashWindow.Splash}.
     * You should call {@link #init(GameConfig)} or {@link #init(Host, String, long)} first.
     *
     * @param fixedFPS the fps with which the game should run
     * @param splash   the {@link de.edgelord.saltyengine.displaymanager.display.SplashWindow.Splash} to be displayed when the game starts
     */
    public static void start(long fixedFPS, SplashWindow.Splash splash) {

        GameStarter.startGame(fixedFPS, splash);
    }

    /**
     * Starts the game with the default {@link de.edgelord.saltyengine.displaymanager.display.SplashWindow.Splash} and as much fps as
     * possible.
     * You should call {@link #init(GameConfig)} or {@link #init(Host, String, long)} first.
     */
    public static void start() {

        GameStarter.startGame(-1, SplashWindow.Splash.SPOTLIGHT_SPLASH);
    }

    /**
     * Start the game with the default {@link de.edgelord.saltyengine.displaymanager.display.SplashWindow.Splash} and the given
     * fps.
     * You should call {@link #init(GameConfig)} or {@link #init(Host, String, long)} first.
     *
     * @param fixedFPS the fps with which the game should run.
     */
    public static void start(long fixedFPS) {

        GameStarter.startGame(fixedFPS, SplashWindow.Splash.SPOTLIGHT_SPLASH);
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
