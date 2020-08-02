/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.input;

import java.awt.event.MouseEvent;

/**
 * An abstract adapter class for receiving keyboard events. The methods in this
 * class are empty. This class exists as convenience for creating {@link
 * MouseInputHandler} objects.
 */
public abstract class MouseInputAdapter implements MouseInputHandler {

    @Override
    public void mouseMoved(final MouseEvent e) {
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
    }

    @Override
    public void mousePressed(final MouseEvent e) {
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
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
