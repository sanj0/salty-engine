/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.input;

import java.awt.event.KeyEvent;

public interface DisplayKeyHandler {

    void keyTyped(KeyEvent e);

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);
}
