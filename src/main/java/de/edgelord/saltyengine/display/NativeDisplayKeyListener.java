/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
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

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.KeyboardInputHandler;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class NativeDisplayKeyListener implements KeyListener {

    private KeyboardInputHandler keyboardHandler;

    private char currentKey = '*';

    private boolean inputUp = false;
    private boolean inputDown = false;
    private boolean inputLeft = false;
    private boolean inputRight = false;

    public NativeDisplayKeyListener(KeyboardInputHandler keyboardHandler) {
        this.keyboardHandler = keyboardHandler;
    }

    @Override
    public void keyTyped(final KeyEvent e) {

        if (keyboardHandler != null) {
            keyboardHandler.keyTyped(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().keyTyped(e);
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            if (Game.isPaused()) {
                Game.setPaused(false);
            } else {
                Game.setPaused(true);
            }
        }
    }

    @Override
    public void keyPressed(final KeyEvent e) {

        if (keyboardHandler != null) {
            keyboardHandler.keyPressed(e);
        }

        currentKey = e.getKeyChar();

        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {

            inputUp = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {

            inputDown = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {

            inputLeft = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {

            inputRight = true;
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().keyPressed(e);
        }

        Game.keyboardInput.handleKeyPressed(e);
        Game.lastInputKey = currentKey;
        Game.inputUp = inputUp;
        Game.inputDown = inputDown;
        Game.inputRight = inputRight;
        Game.inputLeft = inputLeft;
        Game.lastInput = e;
    }

    @Override
    public void keyReleased(final KeyEvent e) {

        if (keyboardHandler != null) {
            keyboardHandler.keyReleased(e);
        }

        currentKey = '*';

        if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {

            inputUp = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {

            inputDown = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {

            inputLeft = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {

            inputRight = false;
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().keyReleased(e);
        }

        Game.keyboardInput.handleKeyReleased(e);
        Game.lastInputKey = currentKey;
        Game.inputUp = inputUp;
        Game.inputDown = inputDown;
        Game.inputRight = inputRight;
        Game.inputLeft = inputLeft;
        Game.lastInput = null;
    }

    public KeyboardInputHandler getKeyboardHandler() {
        return keyboardHandler;
    }

    public void setKeyboardHandler(KeyboardInputHandler keyboardHandler) {
        this.keyboardHandler = keyboardHandler;
    }

    public char getCurrentKey() {
        return currentKey;
    }

    public boolean isInputUp() {
        return inputUp;
    }

    public boolean isInputDown() {
        return inputDown;
    }

    public boolean isInputLeft() {
        return inputLeft;
    }

    public boolean isInputRight() {
        return inputRight;
    }
}
