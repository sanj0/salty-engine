/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.RepaintAble;
import de.edgelord.saltyengine.input.DisplayKeyHandler;
import de.edgelord.saltyengine.input.DisplayListener;
import de.edgelord.saltyengine.input.DisplayMouseHandler;
import de.edgelord.saltyengine.stage.Stage;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DisplayManager implements RepaintAble {

    private final Display display;
    private final Stage stage;
    private DisplayListener displayListener;
    private KeyListener nativeKeyListener;
    private DisplayKeyHandler displayKeyHandler = null;
    private DisplayMouseHandler displayMouseHandler = null;

    // The char of the current pressed key. PLease note that this only gets on key at a time as an lastInput.
    private char currentKey;

    // The following four booleans are for standard lastInput (e.g. inputUp would be true if 'w' or the UP arrow is pressed)
    // this supports multi-lastInput!
    private boolean inputUp = false;
    private boolean inputDown = false;
    private boolean inputRight = false;
    private boolean inputLeft = false;

    private final int width;
    private final int height;

    public DisplayManager(final int width, final int height, final String gameName, final Engine engine) {

        display = new Display(width, height, gameName, this);
        stage = new Stage(display, engine);
        displayListener = new DisplayListener(display);

        this.width = width;
        this.height = height;
        StaticSystem.gameName = gameName;
    }

    public void create() {

        display.create();
        createKeyListener();
    }

    public void createKeyListener() {

        nativeKeyListener = new KeyListener() {

            @Override
            public void keyTyped(final KeyEvent e) {

                if (displayKeyHandler != null) {
                    displayKeyHandler.keyTyped(e);
                }

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().keyTyped(e);
                }

                if (e.getKeyCode() == KeyEvent.VK_P) {
                    if (StaticSystem.isPaused()) {
                        StaticSystem.setPaused(false);
                    } else {
                        StaticSystem.setPaused(true);
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

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().keyPressed(e);
                }

                StaticSystem.keyboardInput.handleKeyPressed(e);
                StaticSystem.lastInputKey = currentKey;
                StaticSystem.inputUp = inputUp;
                StaticSystem.inputDown = inputDown;
                StaticSystem.inputRight = inputRight;
                StaticSystem.inputLeft = inputLeft;
                StaticSystem.lastInput = e;
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

                if (StaticSystem.currentScene.getUI() != null) {
                    StaticSystem.currentScene.getUI().keyReleased(e);
                }

                StaticSystem.keyboardInput.handleKeyReleased(e);
                StaticSystem.lastInputKey = currentKey;
                StaticSystem.inputUp = inputUp;
                StaticSystem.inputDown = inputDown;
                StaticSystem.inputRight = inputRight;
                StaticSystem.inputLeft = inputLeft;
                StaticSystem.lastInput = null;
            }
        };

        display.addKeyListener(nativeKeyListener);
        currentKey = '*';
    }

    public void scale(final double zoomX, final double zoomY) {

        stage.scale(zoomX, zoomY);
    }

    public float getHorizontalCenter(final float width) {
        return ((float) getWidth() / 2) - (width / 2);
    }

    public float getVerticalCenter(final float height) {
        return ((float) getHeight() / 2) - (height / 2);
    }

    public Vector2f getCenter(final float width, final float height) {
        return new Vector2f(getHorizontalCenter(width), getVerticalCenter(height));
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
    public void repaint() {

        //System.out.println("saltyengine 0.3 Zeus > DisplayManager > \"The stage were repainted\"");

        stage.repaint();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
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
}
