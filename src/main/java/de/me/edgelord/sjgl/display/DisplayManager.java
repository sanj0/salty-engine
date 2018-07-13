/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.display;

import de.me.edgelord.sjgl.input.DisplayKeyHandler;
import de.me.edgelord.sjgl.input.DisplayListener;
import de.me.edgelord.sjgl.main.MainLoops;
import de.me.edgelord.sjgl.stage.Stage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class DisplayManager {

    private Display display;
    private Stage stage;
    private DisplayListener displayListener;
    private KeyListener nativeKeyListener;
    private DisplayKeyHandler displayKeyHandler = null;

    // The char of the current pressed key. PLease note that this only gets on key at a time as an input.

    private char currentKey;

    // The following four booleans are for standard input (e.g. inputUp would be true if 'w' or the up arrow is pressed)
    // this supports multi-input!

    private boolean inputUp = false;
    private boolean inputDown = false;
    private boolean inputRight = false;
    private boolean inputLeft = false;

    private int width, height;

    public DisplayManager(int width, int height, MainLoops mainLoops) {

        display = new Display(width, height, this);
        stage = new Stage(display, mainLoops);
        displayListener = new DisplayListener(display);

        this.width = width;
        this.height = height;
    }

    public void create() {

        display.create();
        createKeyListener();
    }

    public void createKeyListener() {

        nativeKeyListener = new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {

                if (displayKeyHandler != null) {
                    displayKeyHandler.keyTyped(e);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (displayKeyHandler != null) {
                    displayKeyHandler.keyPressed(e);
                }

                currentKey = e.getKeyChar();

                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){

                    inputUp = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){

                    inputDown = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){

                    inputLeft = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){

                    inputRight = true;
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

                if (displayKeyHandler != null) {
                    displayKeyHandler.keyReleased(e);
                }

                currentKey = '*';

                if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP){

                    inputUp = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN){

                    inputDown = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT){

                    inputLeft = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT){

                    inputRight = false;
                }
            }
        };

        display.addKeyListener(nativeKeyListener);
        currentKey = '*';
    }

    public void scale(double zoomX, double zoomY) {

        stage.scale(zoomX, zoomY);
    }

    public DisplayKeyHandler getDisplayKeyHandler() {
        return displayKeyHandler;
    }

    public void setDisplayKeyHandler(DisplayKeyHandler displayKeyHandler) {
        this.displayKeyHandler = displayKeyHandler;
    }

    public char getCurrentKey() {
        return currentKey;
    }

    public void repaintStage() {

        //System.out.println("sjgl 0.0.2 > DisplayManager > \"The stage were repainted\"");

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
}
