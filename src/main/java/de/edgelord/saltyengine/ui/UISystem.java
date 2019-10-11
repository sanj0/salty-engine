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

package de.edgelord.saltyengine.ui;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.Input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UISystem {

    private List<UIElement> elements = new CopyOnWriteArrayList<>();
    private UIElement currentlyFocused = null;

    public void drawUI(SaltyGraphics saltyGraphics) {
        for (UIElement element : elements) {
            element.draw(saltyGraphics);
            element.doComponentDrawing(saltyGraphics);
        }
    }

    public void onFixedTick() {
        for (UIElement element : elements) {

            if (element.getTransform().contains(Input.getAbsoluteCursor())) {

                if (!element.mouseHoversOver()) {
                    element.mouseEntered(Input.cursor);
                    element.doComponentCursorEntersParent();
                }

                element.mouseHover(Input.cursor);
                element.setMouseHoversOver(true);
            } else if (element.mouseHoversOver()) {
                element.setMouseHoversOver(false);
                element.mouseExited(Input.cursor);
                element.doComponentCursorExitsParent();
            }

            element.onFixedTick();
            element.doComponentOnFixedTick();
        }
    }

    public void addElement(UIElement element) {
        this.elements.add(element);
    }

    public void removeElement(UIElement element) {

        elements.remove(element);
    }

    public void keyPressed(KeyEvent e) {

        for (UIElement element : elements) {
            element.keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e) {

        for (UIElement element : elements) {
            element.keyReleased(e);
        }
    }

    public void keyTyped(KeyEvent e) {

        for (UIElement element : elements) {
            element.keyTyped(e);
        }
    }

    public void mouseClicked(MouseEvent e) {

        for (UIElement element : elements) {
            element.mouseClicked(e);
        }
    }

    public void mousePressed(MouseEvent e) {

        if (currentlyFocused != null) {
            currentlyFocused.setFocused(false);
        }

        for (UIElement element : elements) {

            if (element.getTransform().contains(Input.getAbsoluteCursor())) {
                element.setFocused(true);
                currentlyFocused = element;
            }
            element.mousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e) {

        for (UIElement element : elements) {
            element.mouseReleased(e);
        }
    }

    public void mouseDragged(MouseEvent e) {

        for (UIElement element : elements) {
            element.mouseDragged(e);
        }
    }

    public void mouseExitedScreen(MouseEvent e) {

        for (UIElement element : elements) {
            element.mouseExitedScreen(e);
        }
    }

    public void mouseEnteredScreen(MouseEvent e) {

        for (UIElement element : elements) {
            element.mouseEnteredScreen(e);
        }
    }

    public void mouseWheelMoved(MouseEvent e) {

        for (UIElement element : elements) {
            element.mouseWheelMoved(e);
        }
    }

    public void mouseMoved(MouseEvent e) {
        for (UIElement element : elements) {
            element.mouseMoved(e);
        }
    }

    public List<UIElement> getElements() {
        return elements;
    }
}
