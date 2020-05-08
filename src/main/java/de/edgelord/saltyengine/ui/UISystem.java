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
import de.edgelord.saltyengine.transform.Transform;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class UISystem {

    private final List<UIElement> elements = new CopyOnWriteArrayList<>();
    private UIElement currentlyFocused = null;

    public void drawUI(final SaltyGraphics saltyGraphics) {
        for (final UIElement element : elements) {
            element.draw(saltyGraphics.copy());
            element.doComponentDrawing(saltyGraphics);
        }
    }

    public void onFixedTick() {
        final Transform cursor = Input.getAbsoluteCursor();
        for (final UIElement element : elements) {

            if (element.getTransform().contains(cursor)) {

                if (!element.mouseHoversOver()) {
                    element.mouseEntered(cursor);
                    element.doComponentCursorEntersParent();
                }

                element.mouseHover(cursor);
                element.setMouseHoversOver(true);
            } else if (element.mouseHoversOver()) {
                element.setMouseHoversOver(false);
                element.mouseExited(cursor);
                element.doComponentCursorExitsParent();
            }

            element.onFixedTick();
            element.doComponentOnFixedTick();
        }
    }

    public void addElement(final UIElement element) {
        this.elements.add(element);
    }

    public void removeElement(final UIElement element) {

        elements.remove(element);
    }

    public void keyPressed(final KeyEvent e) {

        for (final UIElement element : elements) {
            element.keyPressed(e);
        }
    }

    public void keyReleased(final KeyEvent e) {

        for (final UIElement element : elements) {
            element.keyReleased(e);
        }
    }

    public void keyTyped(final KeyEvent e) {

        for (final UIElement element : elements) {
            element.keyTyped(e);
        }
    }

    public void mouseClicked(final MouseEvent e) {

        for (final UIElement element : elements) {
            element.mouseClicked(e);
        }
    }

    public void mousePressed(final MouseEvent e) {

        if (currentlyFocused != null) {
            currentlyFocused.setFocused(false);
        }

        for (final UIElement element : elements) {

            if (element.getTransform().contains(Input.getAbsoluteCursor())) {
                element.setFocused(true);
                currentlyFocused = element;
            }
            element.mousePressed(e);
        }
    }

    public void mouseReleased(final MouseEvent e) {

        for (final UIElement element : elements) {
            element.mouseReleased(e);
        }
    }

    public void mouseDragged(final MouseEvent e) {

        for (final UIElement element : elements) {
            element.mouseDragged(e);
        }
    }

    public void mouseExitedScreen(final MouseEvent e) {

        for (final UIElement element : elements) {
            element.mouseExitedScreen(e);
        }
    }

    public void mouseEnteredScreen(final MouseEvent e) {

        for (final UIElement element : elements) {
            element.mouseEnteredScreen(e);
        }
    }

    public void mouseWheelMoved(final MouseEvent e) {

        for (final UIElement element : elements) {
            element.mouseWheelMoved(e);
        }
    }

    public void mouseMoved(final MouseEvent e) {
        for (final UIElement element : elements) {
            element.mouseMoved(e);
        }
    }

    public List<UIElement> getElements() {
        return elements;
    }
}
