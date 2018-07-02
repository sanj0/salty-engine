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
    private DisplayKeyHandler displayKeyHandler;

    private KeyEvent currentKey;

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

                displayKeyHandler.keyTyped(e);
            }

            @Override
            public void keyPressed(KeyEvent e) {

                displayKeyHandler.keyPressed(e);

                currentKey = e;
            }

            @Override
            public void keyReleased(KeyEvent e) {

                displayKeyHandler.keyReleased(e);

                currentKey = null;
            }
        };

        display.addKeyListener(nativeKeyListener);
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

    public KeyEvent getCurrentKey() {
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

    public boolean isCloseRequested() {
        return display.isCloseRequested();
    }
}
