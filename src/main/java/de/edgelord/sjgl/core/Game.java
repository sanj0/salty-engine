/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.core;

import de.edgelord.sjgl.display.DisplayManager;

public class Game {

    private static DisplayManager displayManager;
    private static Engine engine;

    public Game(int windowWidth, int windowHeight, String gameName, long fixedTickMillis) {

        engine = new Engine(fixedTickMillis);

        displayManager = new DisplayManager(windowWidth, windowHeight, gameName, engine);
    }

    public static void start() {

        displayManager.create();

        engine.start(displayManager);
    }

    public static void start(long fixedFPS) {

        displayManager.create();

        engine.start(displayManager, fixedFPS);
    }
    public static DisplayManager getDisplayManager() {
        return displayManager;
    }

    public static Engine getEngine() {
        return engine;
    }
}
