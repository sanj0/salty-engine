package testing;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.PaneledGame;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;

import javax.swing.*;
import java.awt.*;

public class PaneledGameTester extends JFrame {

    public PaneledGameTester() {

        setSize(1920, 1080);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        PaneledGame paneledGame = new PaneledGame(this, 100, 100, 1080, 720, 1, "PaneledGame Test");

        Game.start();
    }

    public static void main(String[] args) {

        new PaneledGameTester();

        SceneManager.getCurrentScene().addDrawingRoutine(new DrawingRoutine(DrawingRoutine.DrawingPosition.AFTER_GAMEOBJECTS) {
            @Override
            public void draw(SaltyGraphics saltyGraphics) {
                saltyGraphics.drawOval(0, 0, 1920, 1080);
            }
        });
    }
}
