package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.utils.StaticSystem;

public class ScreenExampleMain extends Game {

    public ScreenExampleMain(int windowWidth, int windowHeight, String gameName, long fixedTickMillis) {
        super(windowWidth, windowHeight, gameName, fixedTickMillis);
    }

    public static void main(String[] args) {
        new ScreenExampleMain(920, 720, "Example of a simple Screen", 10);

        Game.getHostAsDisplayManager().getStage().setHighQuality(true);

        Game.start();

        StaticSystem.currentScene.addGameObject(new Screen());
    }
}
