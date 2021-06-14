/*
 * Copyright 2021 Malte Dostal
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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.MouseInputHandler;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.event.MouseEvent;

public class DraggableSceneMouseInputHandler implements MouseInputHandler {

    private Vector2f lastMousePosition = null;

    @Override
    public void mouseMoved(final MouseEvent e) {

    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            final Vector2f mousePos = Input.getCursorPosition();
            Game.getCamera().getPosition().subtract(mousePos.subtracted(lastMousePosition));
            lastMousePosition = mousePos;
        }
    }

    @Override
    public void mousePressed(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            lastMousePosition = Input.getCursorPosition();
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3) {
            lastMousePosition = null;
        }
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

    }

    @Override
    public void mouseExitedScreen(final MouseEvent e) {

    }

    @Override
    public void mouseEnteredScreen(final MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(final MouseEvent e) {

    }
}
