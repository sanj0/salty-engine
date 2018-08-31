package de.edgelord.saltyengine.example;

import de.edgelord.saltyengine.core.Game;

/**
 * This is an example for the main class of a game.
 */
public class ExampleMain extends Game {

    public ExampleMain(int windowWidth, int windowHeight, String gameName, long fixedTickMillis) {
        super(windowWidth, windowHeight, gameName, fixedTickMillis);
    }

    public static void main(String[] args) {
        new ExampleMain(1920, 1080, "Salty Engine Example Game", 1);

        Game.start();
    }
}
