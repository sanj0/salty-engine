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

package testing;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.PaneledGame;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
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
