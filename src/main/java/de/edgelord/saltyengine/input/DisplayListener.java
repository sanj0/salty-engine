/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.input;

import de.edgelord.saltyengine.display.Display;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class DisplayListener implements WindowListener {

    private Display host;

    public DisplayListener(Display host) {

        host.addWindowListener(this);

        this.host = host;
    }

    @Override
    public void windowOpened(WindowEvent e) {
    }

    @Override
    public void windowClosing(WindowEvent e) {

        /*
            All that saving and stuff like that
         */

        host.setCloseRequested(true);

        System.exit(1);
    }

    @Override
    public void windowClosed(WindowEvent e) {
    }

    @Override
    public void windowIconified(WindowEvent e) {
    }

    @Override
    public void windowDeiconified(WindowEvent e) {
    }

    @Override
    public void windowActivated(WindowEvent e) {
    }

    @Override
    public void windowDeactivated(WindowEvent e) {
    }
}
