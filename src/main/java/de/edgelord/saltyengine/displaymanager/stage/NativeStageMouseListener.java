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

package de.edgelord.saltyengine.displaymanager.stage;

import de.edgelord.saltyengine.core.interfaces.MouseInputHandler;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NativeStageMouseListener extends MouseInputHandlerListener implements MouseListener {

    public NativeStageMouseListener(MouseInputHandler mouseHandler) {
        super(mouseHandler);
    }

    @Override
    public void mouseClicked(final MouseEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mouseClicked(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mouseClicked(e);
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mousePressed(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mousePressed(e);
        }

        Input.mouseDown = true;
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mouseReleased(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mouseReleased(e);
        }

        Input.mouseDrags = false;
        Input.mouseDown = false;
    }

    @Override
    public void mouseEntered(final MouseEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mouseEnteredScreen(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mouseEnteredScreen(e);
        }
    }

    @Override
    public void mouseExited(final MouseEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mouseExitedScreen(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mouseExitedScreen(e);
        }
    }
}
