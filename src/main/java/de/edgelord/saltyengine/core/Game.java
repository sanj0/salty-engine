/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.display.DisplayManager;

public class Game {

    private static Host host;
    private static Engine engine;

    public Game(int windowWidth, int windowHeight, String gameName, long fixedTickMillis) {

        System.setProperty("sun.java2d.opengl", "true");

        engine = new Engine(fixedTickMillis);

        host = new DisplayManager(windowWidth, windowHeight, gameName, engine);
    }

    public Game(Host host, String gameName, long fixedTickMillis) {

        System.setProperty("sun.java2d.opengl", "true");

        engine = new Engine(fixedTickMillis);

        Game.host = host;
    }

    public static void start() {

        host.create();

        engine.start(host);
    }

    public static void start(long fixedFPS) {

        host.create();

        engine.start(host, fixedFPS);
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
}
