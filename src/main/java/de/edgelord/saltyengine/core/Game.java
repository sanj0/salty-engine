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
import de.edgelord.saltyengine.core.camera.Camera2D;
import de.edgelord.saltyengine.core.graphics.GFXController;
import de.edgelord.saltyengine.displaymanager.display.DisplayManager;
import de.edgelord.saltyengine.displaymanager.display.DisplayRatio;
import de.edgelord.saltyengine.displaymanager.display.SplashWindow;
import de.edgelord.saltyengine.factory.FontFactory;
import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.io.serialization.Serializer;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.resource.OuterResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * The central core class for a Salty Engine game. To start a game, call either
 * {@link #init(GameConfig)} or {@link #init(Host, String, long)} <br>
 * <p>
 * and then
 * <p>
 * {@link #start()}, {@link #start(long)}, {@link #start(long, SplashWindow.Splash)} or {@link #start(SplashWindow.Splash)}
 */
public class Game {

    /**
     * The default {@link GFXController} for the game. Any {@link Component}s added to this {@link de.edgelord.saltyengine.core.stereotypes.ComponentContainer}
     * will be applied in any {@link de.edgelord.saltyengine.scene.Scene} at any time.
     */
    private static GFXController defaultGFXController = new GFXController();

    /**
     * The name of the game. Until the game is initialized, this will be {@code salty-engine} by default.
     */
    public static String gameName = "salty-engine";

    /**
     * If this is set to true, {@link Scene#onFixedTick()} is not called until this is set to false again.
     */
    public static boolean paused = false;

    /**
     * The {@link Camera} of the game.
     */
    private static Camera2D camera;

    /**
     * The original dimensions of the game aka it's resolution. This will be it's resolution at any time,
     * a bigger window will only resist in scaling up, not in a higher resolution as this is easier in terms of logic
     * for the user of this library and in terms of rendering for this library.
     */
    private static Dimensions gameDimensions;

    /**
     * Proposes the <code>Host</code> either to draw the FPS {@link Time#getFPS()} or not.
     * All <code>Host</code>s included in this library will accept that. 3rd party
     * <code>Host</code>s may not and do not have to.
     */
    private static boolean drawFPS = true;

    /**
     * The {@link Host} of this game. The <code>Host</code> will do the rendering and has some more useful methods.
     */
    private static Host host;

    /**
     * The {@link Engine} that runs the game. It repaints the {@link #host} and calls the {@link Scene#onFixedTick()}.
     */
    private static Engine engine;

    /**
     * The list of {@link GameListener} that are assigned.
     */
    private static List<GameListener> gameListeners = new ArrayList<>();

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
        initSaltySystem(gameName);
    }

    private static void initSaltySystem(String gameName) {
        Game.gameName = gameName;
        camera = new Camera2D(new Vector2f(0, 0), getGameDimensions(), getGameDimensions(), 1f);
        SaltySystem.defaultHiddenOuterResource = new OuterResource(true);
        SaltySystem.defaultOuterResource = new OuterResource(false);
        SaltySystem.defaultResource = new InnerResource();
        SaltySystem.defaultImageFactory = new ImageFactory(SaltySystem.defaultResource);
        SaltySystem.defaultFontFactory = new FontFactory(SaltySystem.defaultResource);
        try {
            SaltySystem.defaultFont = SaltySystem.defaultFontFactory.getFont("res/fonts/OpenSans-Regular.ttf", 10f);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        WindowClosingHooks.addShutdownHook(() -> Game.forEachGameListener(GameListener::onClose));
    }

    private static void internalPreInitDisplayManager(GameConfig config) {

        enableOpenGl();

        engine = new Engine(config.getFixedTickMillis());
        host = new DisplayManager(new DisplayRatio(new Dimensions(config.getResWidth(), config.getResHeight())), config.getGameName(), engine);
        gameDimensions = new Dimensions(config.getResWidth(), config.getResHeight());

        initSaltySystem(config.getGameName());
    }

    private static void enableOpenGl() {
        System.setProperty("sun.java2d.opengl", "True");
    }

    public static Dimensions getGameDimensions() {
        return gameDimensions;
    }

    /**
     * Returns the width value of {@link #gameDimensions}.
     *
     * @return the width of the game resolution
     */
    public static float getGameWidth() {
        return gameDimensions.getWidth();
    }

    /**
     * Returns the height value of {@link #gameDimensions}.
     *
     * @return the height of the game resolution
     */
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

    /**
     * Adds the given {@link GameListener} to the list.
     *
     * @param gameListener the new <code>GameListener</code>
     */
    public static void addGameListener(GameListener gameListener) {
        gameListeners.add(gameListener);
    }

    /**
     * Removes the given {@link GameListener} from the list.
     *
     * @param gameListener a {@link GameListener}
     */
    public static void removeGameListener(GameListener gameListener) {
        gameListeners.remove(gameListener);
    }

    public static void forEachGameListener(Consumer<? super GameListener> action) {
        gameListeners.forEach(action);
    }

    public static List<GameListener> getGameListeners() {
        return gameListeners;
    }

    protected static void setEngine(Engine engine) {
        Game.engine = engine;
    }

    public static Host getHost() {
        return host;
    }

    /**
     * Casts the {@link #host} to {@link DisplayManager} and returns it. This only works if the <code>DisplayManager</code>
     * as default is used.
     *
     * @return the {@link #host} as a {@link DisplayManager}
     */
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

    public static Camera2D getCamera() {
        return camera;
    }

    public static void setCamera(Camera2D camera) {
        Game.camera = camera;
    }

    /**
     * Once this method is called, the game will call {@link Serializer#doSerialization()} when the game is exited.
     *
     * @param safeFile the name of the savefile
     */
    public static void saveOnExit(String safeFile) {
        WindowClosingHooks.addShutdownHook(() -> {
            try {
                Serializer.doSerialization(safeFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    /**
     * Once this method is called, the game will call {@link Serializer#doSerialization()} when the game is exited.
     */
    public static void saveOnExit() {
        saveOnExit(Serializer.getSaveFileName());
    }

    /**
     * Calls {@link #saveOnExit()}
     */
    public static void serializeOnExit() {
        saveOnExit();
    }

    /**
     * Calls {@link #saveOnExit(String)}.
     *
     * @param safeFile the name of the savefile
     */
    public static void serializeOnExit(String safeFile) {
        saveOnExit(safeFile);
    }
}
