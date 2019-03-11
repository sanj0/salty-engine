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

package de.edgelord.saltyengine.displaymanager.display;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.KeyboardInputHandler;
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

        if (e.getKeyChar() == 'f' && Game.getHost().isFullscreenToggleF()) {
            Game.getHost().toggleFullscreen();
        }

        if (keyboardHandler != null) {
            keyboardHandler.keyTyped(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().keyTyped(e);
        }

        Input.getKeyboardHandlers().forEach(keyboardInputHandler -> keyboardInputHandler.keyTyped(e));
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

        Input.keyboardInput.handleKeyPressed(e);
        updateInputStates();
        Input.lastInput = e;
        Input.getKeyboardHandlers().forEach(keyboardInputHandler -> keyboardInputHandler.keyPressed(e));
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

        Input.keyboardInput.handleKeyReleased(e);
        updateInputStates();
        Input.lastInput = null;

        Input.getKeyboardHandlers().forEach(keyboardInputHandler -> keyboardInputHandler.keyReleased(e));
    }

    private void updateInputStates() {
        Input.lastInputKey = currentKey;
        Input.inputUp = inputUp;
        Input.inputDown = inputDown;
        Input.inputRight = inputRight;
        Input.inputLeft = inputLeft;
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
