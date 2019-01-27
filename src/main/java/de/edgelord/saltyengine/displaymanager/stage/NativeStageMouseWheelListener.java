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

import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

public class NativeStageMouseWheelListener extends MouseInputHandlerListener implements MouseWheelListener {

    public NativeStageMouseWheelListener(MouseInputHandler mouseHandler) {
        super(mouseHandler);
    }

    @Override
    public void mouseWheelMoved(final MouseWheelEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mouseWheelMoved(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mouseWheelMoved(e);
        }

        Input.getMouseHandlers().forEach(mouseInputHandler -> mouseInputHandler.mouseWheelMoved(e));
    }
}
