/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.gameobject.FixedTask;
import de.edgelord.saltyengine.input.DisplayKeyHandler;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.event.KeyEvent;

public class DummyDisplayKeyHandler implements DisplayKeyHandler {

    public DummyDisplayKeyHandler() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_P) {

            if (Game.isPaused()) {
                Game.setPaused(false);
            } else {
                Game.setPaused(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
