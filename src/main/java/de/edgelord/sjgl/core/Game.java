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

        // The Thread is supposed to sleep because otherwise the fixedTicks and the renderingProcess will start
        // before the window shows up
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        engine.start(displayManager);
    }
}
