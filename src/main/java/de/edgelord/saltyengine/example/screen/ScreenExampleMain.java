package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UISystem;
import de.edgelord.saltyengine.ui.elements.BorderedLabel;
import de.edgelord.saltyengine.ui.elements.TextElement;
import de.edgelord.saltyengine.utils.StaticSystem;

public class ScreenExampleMain extends Game {

    public ScreenExampleMain(int windowWidth, int windowHeight, String gameName, long fixedTickMillis) {
        super(windowWidth, windowHeight, gameName, fixedTickMillis);
    }

    public static void main(String[] args) {
        new ScreenExampleMain(920, 720, "Example of a simple Screen", 10);

        Game.getHostAsDisplayManager().getStage().setHighQuality(true);

        Game.start();

        StaticSystem.currentScene.setUI(new UISystem());

        StaticSystem.currentScene.addGameObject(new Screen());

        BorderedLabel usage = new BorderedLabel("Use WASD or the arrow keys to move the content of the screen!", 0, 25, Game.getHost().getWidth(), 35);

        StaticSystem.currentScene.getUI().addElement(usage);
    }
}
