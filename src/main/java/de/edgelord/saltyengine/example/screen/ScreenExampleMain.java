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

package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.ui.elements.BorderedLabel;

public class ScreenExampleMain extends Game {

    public static void main(final String[] args) {
        Game.init(GameConfig.config(920, 720, "Example of a simple Screen", 5));
        Game.start();

        SceneManager.getCurrentScene().addGameObject(new Screen());

        final BorderedLabel usage = new BorderedLabel("Use WASD or the arrow keys to move the content of the screen!", 0, 25, Game.getGameWidth(), 35);

        SceneManager.getCurrentScene().getUI().addElement(usage);
    }
}
