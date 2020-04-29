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

package de.edgelord.saltyengine.input;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.event.KeyEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class Input {

    private static final List<KeyboardInputHandler> keyboardHandlers = new CopyOnWriteArrayList<>();
    private static final List<MouseInputHandler> mouseHandlers = new CopyOnWriteArrayList<>();
    public static boolean inputUp = false;
    public static boolean inputDown = false;
    public static boolean inputRight = false;
    public static boolean inputLeft = false;
    public static char lastInputKey;
    /**
     * You have to add a ifn check if you use this!
     */
    public static KeyEvent lastInput = null;
    /**
     * For input that is not meant for typing use this!
     * Please use this for every input that has to do with controls etc etc
     */
    public static Keyboard keyboardInput = new Keyboard();
    /**
     * The absolute (meaning relative to the normal userspace)
     * position of the mouse cursor.
     */
    public static Vector2f cursorPosition = new Vector2f(0, 0);
    public static boolean mouseDrags = false;
    public static boolean mouseDown = false;

    public static Directions getInput() {

        Directions input = new Directions();

        if (inputUp) {
            input.addDirection(Directions.Direction.UP);
        }

        if (inputDown) {
            input.addDirection(Directions.Direction.DOWN);
        }

        if (inputRight) {
            input.addDirection(Directions.Direction.RIGHT);
        }

        if (inputLeft) {
            input.addDirection(Directions.Direction.LEFT);
        }

        return input;
    }

    public static boolean isInputUp() {
        return inputUp;
    }

    public static boolean isInputDown() {
        return inputDown;
    }

    public static boolean isInputRight() {
        return inputRight;
    }

    public static boolean isInputLeft() {
        return inputLeft;
    }

    public static char getLastInputKey() {
        return lastInputKey;
    }

    public static KeyEvent getLastInput() {
        return lastInput;
    }

    public static Keyboard getKeyboardInput() {
        return keyboardInput;
    }

    public static Vector2f getCursorPosition() {
        return Game.getCamera().getRelativePosition(cursorPosition);
    }

    public static Vector2f getAbsoluteCursorPosition() {
        return cursorPosition;
    }

    public static Transform getCursor() {
        return new Transform(getCursorPosition(), Dimensions.one());
    }

    public static Transform getAbsoluteCursor() {
        return new Transform(getAbsoluteCursorPosition(), Dimensions.one());
    }

    public static boolean isMouseDrags() {
        return mouseDrags;
    }

    public static boolean isMouseDown() {
        return mouseDown;
    }

    public static void addKeyboardInputHandler(KeyboardInputHandler handler) {
        keyboardHandlers.add(handler);
    }

    public static List<KeyboardInputHandler> getKeyboardHandlers() {
        return keyboardHandlers;
    }

    public static void addMouseInputHandler(MouseInputHandler handler) {
        mouseHandlers.add(handler);
    }

    public static List<MouseInputHandler> getMouseHandlers() {
        return mouseHandlers;
    }
}
