/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.ui.elements.BorderedLabel;

public class ScreenExampleMain extends Game {

    public ScreenExampleMain(int windowWidth, int windowHeight, String gameName, long fixedTickMillis) {
        super(windowWidth, windowHeight, gameName, fixedTickMillis);
    }

    public static void main(String[] args) {
        new ScreenExampleMain(920, 720, "Example of a simple Screen", 10);

        Game.getHostAsDisplayManager().getStage().setHighQuality(true);

        Game.start();

        SceneManager.getCurrentScene().addGameObject(new Screen());

        BorderedLabel usage = new BorderedLabel("Use WASD or the arrow keys to move the content of the screen!", 0, 25, Game.getHost().getWidth(), 35);

        SceneManager.getCurrentScene().getUI().addElement(usage);
    }
}
