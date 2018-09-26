package de.edgelord.saltyengine.core;

import java.awt.event.KeyEvent;

public interface KeyboardInputHandler {

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);

    void keyTyped(KeyEvent e);
}
