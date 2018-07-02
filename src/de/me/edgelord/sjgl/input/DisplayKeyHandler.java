/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.input;

import java.awt.event.KeyEvent;

public interface DisplayKeyHandler {

    void keyTyped(KeyEvent e);

    void keyPressed(KeyEvent e);

    void keyReleased(KeyEvent e);
}
