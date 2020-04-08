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

    public static boolean up = false;
    public static boolean down = false;
    public static boolean right = false;
    public static boolean left = false;
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

    private static List<KeyboardInputHandler> keyboardHandlers = new CopyOnWriteArrayList<>();
    private static List<MouseInputHandler> mouseHandlers = new CopyOnWriteArrayList<>();

    /**
     * Converts the current keyboard directional input to
     * a unit vector.
     *
     * @return a unit vector representing the current directional keyboard input
     * (WASD and the arrows)
     */
    public static Vector2f inputVector() {
        return new Vector2f(up ? -1 : down ? 1 : 0, right ? 1 : left ? -1 : 0).normalize();
    }

    public static Directions getInput() {

        Directions input = new Directions();

        if (up) {
            input.addDirection(Directions.Direction.UP);
        }

        if (down) {
            input.addDirection(Directions.Direction.DOWN);
        }

        if (right) {
            input.addDirection(Directions.Direction.RIGHT);
        }

        if (left) {
            input.addDirection(Directions.Direction.LEFT);
        }

        return input;
    }

    public static boolean isUp() {
        return up;
    }

    public static boolean isDown() {
        return down;
    }

    public static boolean isRight() {
        return right;
    }

    public static boolean isLeft() {
        return left;
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
