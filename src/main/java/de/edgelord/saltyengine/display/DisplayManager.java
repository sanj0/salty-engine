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

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.Host;
import de.edgelord.saltyengine.input.DisplayKeyHandler;
import de.edgelord.saltyengine.input.DisplayListener;
import de.edgelord.saltyengine.input.DisplayMouseHandler;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.stage.Stage;
import de.edgelord.saltyengine.transform.Dimensions;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DisplayManager extends Host {

    private final Display display;
    private final Stage stage;
    private DisplayListener displayListener;
    private KeyListener nativeKeyListener;
    private DisplayKeyHandler displayKeyHandler = null;
    private DisplayMouseHandler displayMouseHandler = null;

    // The char of the current pressed key. Please note that this only gets on key at a time as an lastInput.
    private char currentKey;

    // The following four booleans are for standard lastInput (e.g. inputUp would be true if 'w' or the UP arrow is pressed)
    // this supports multi-lastInput!
    private boolean inputUp = false;
    private boolean inputDown = false;
    private boolean inputRight = false;
    private boolean inputLeft = false;

    public DisplayManager(final int width, final int height, final String gameName, final Engine engine) {

        display = new Display(width, height, gameName);
        stage = new Stage(display, engine);
        displayListener = new DisplayListener(display);

        Game.gameName = gameName;
    }

    @Override
    public void create() {

        display.create();
        createKeyListener();
    }

    @Override
    public float getHorizontalCentrePosition(final float width) {
        return (getWidth() / 2) - (width / 2);
    }

    @Override
    public float getVerticalCentrePosition(final float height) {
        return (getHeight() / 2) - (height / 2);
    }

    @Override
    public void repaint() {

        //System.out.println("saltyengine 0.3 Zeus > DisplayManager > \"The stage were repainted\"");

        stage.repaint();
    }

    public void createKeyListener() {

        nativeKeyListener = new KeyListener() {

            @Override
            public void keyTyped(final KeyEvent e) {

                if (displayKeyHandler != null) {
                    displayKeyHandler.keyTyped(e);
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

                if (displayKeyHandler != null) {
                    displayKeyHandler.keyPressed(e);
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

                if (displayKeyHandler != null) {
                    displayKeyHandler.keyReleased(e);
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
        };

        display.addKeyListener(nativeKeyListener);
        currentKey = '*';
    }

    public void setDisplayKeyHandler(final DisplayKeyHandler displayKeyHandler) {
        this.displayKeyHandler = displayKeyHandler;
    }

    public void setDisplayMouseHandler(final DisplayMouseHandler displayMouseHandler) {
        this.displayMouseHandler = displayMouseHandler;

        stage.setMouseHandler(displayMouseHandler);
    }

    public DisplayListener getDisplayListener() {
        return displayListener;
    }

    public void setDisplayListener(final DisplayListener displayListener) {
        this.displayListener = displayListener;
    }

    public char getCurrentKey() {
        return currentKey;
    }

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(display.getContentPane().getWidth(), display.getContentPane().getHeight());
    }

    public boolean isInputUp() {
        return inputUp;
    }

    public boolean isInputDown() {
        return inputDown;
    }

    public boolean isInputRight() {
        return inputRight;
    }

    public boolean isInputLeft() {
        return inputLeft;
    }

    public boolean isCloseRequested() {
        return display.isCloseRequested();
    }

    public Display getDisplay() {
        return display;
    }

    public Stage getStage() {
        return stage;
    }

    @Override
    public void setBackgroundColor(Color color) {
        stage.setBackground(color);
    }
}
