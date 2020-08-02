/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.KeyboardInputHandler;

import java.awt.event.KeyEvent;

/**
 * This class adds a {@link KeyboardInputHandler} to {@link
 * Input#getKeyboardHandlers()} that adds every typed key to {@link
 * #currentCheatCode} and calls {@link #handleCheatCode(String)} on every key
 * type event. Whenever {@link #ATTENTION_KEY} is typed, the {@link
 * #currentCheatCode} is cleared.
 */
public abstract class CheatCodeListener {

    /**
     * The Keycode which clears the current cheat code so that the player can
     * enter one.
     */
    public static final int ATTENTION_KEY = KeyEvent.VK_ENTER;

    /**
     * The {@link KeyboardInputHandler} that listens fr input.
     */
    private final KeyboardInputHandler inputHandler;

    /**
     * The currently typed cheat code.
     */
    private String currentCheatCode = "";

    /**
     * The constructor. It creates the {@link #inputHandler} and adds it to the
     * {@link Input#getKeyboardHandlers()}.
     */
    public CheatCodeListener() {

        inputHandler = new KeyboardInputHandler() {
            @Override
            public void keyPressed(final KeyEvent e) {
                // nothing to do
            }

            @Override
            public void keyReleased(final KeyEvent e) {
                // nothing to do
            }

            @Override
            public void keyTyped(final KeyEvent e) {

                if (e.getKeyCode() == ATTENTION_KEY) {
                    currentCheatCode = "";
                } else {
                    currentCheatCode += e.getKeyChar();
                    if (handleCheatCode(currentCheatCode)) {
                        currentCheatCode = "";
                    }
                }
            }
        };

        reopen();
    }

    /**
     * Removes {@link #inputHandler} from {@link Input#getKeyboardHandlers()}.
     */
    public void remove() {
        Input.getKeyboardHandlers().remove(inputHandler);
    }

    /**
     * Reopens the listener by adding {@link #inputHandler} to the {@link
     * Input#getKeyboardHandlers()}.
     */
    public void reopen() {
        Input.addKeyboardInputHandler(inputHandler);
    }

    /**
     * Handles a typed cheat code. This method is called every time the player
     * types a key. This method returns <code>true</code> if the cheat code was
     * valid and <code>false</code> if not. If the method returns
     * <code>true</code>, the {@link
     * #currentCheatCode} is cleared.
     *
     * @param cheatcode the current cheat code
     *
     * @return whether the cheat code was valid or not.
     */
    public abstract boolean handleCheatCode(String cheatcode);
}
