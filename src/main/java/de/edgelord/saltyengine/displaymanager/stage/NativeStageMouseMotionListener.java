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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.MouseInputHandler;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class NativeStageMouseMotionListener extends MouseInputHandlerListener implements MouseMotionListener {

    private Stage container;

    public NativeStageMouseMotionListener(MouseInputHandler mouseHandler, Stage stage) {
        super(mouseHandler);

        this.container = stage;
    }

    @Override
    public void mouseDragged(final MouseEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mouseDragged(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mouseDragged(e);
        }

        Input.mouseDrags = true;
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
        if (getMouseHandler() != null) {
            getMouseHandler().mouseMoved(e);
        }

        if (SceneManager.getCurrentScene().getUI() != null) {
            SceneManager.getCurrentScene().getUI().mouseMoved(e);
        }

        Coordinates2f cursorPos = new Coordinates2f(e.getX(), e.getY());
        Coordinates2f imagePos = container.getImagePosition();
        float currentScale = container.getCurrentScale();
        cursorPos.subtract(imagePos.getX(), imagePos.getY());
        cursorPos.divide(currentScale, currentScale);

        Input.cursorPosition = Game.getCamera().getRelativePosition(cursorPos);
        Input.cursor = new Transform(Input.cursorPosition, Dimensions.one());
    }
}
