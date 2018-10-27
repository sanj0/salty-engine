/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.ui;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class UISystem {

    private List<UIElement> elements = new LinkedList<>();

    public void drawUI(SaltyGraphics saltyGraphics) {
        Game.camera.tmpResetViewToGraphics(saltyGraphics);
        for (UIElement element : elements) {
            element.draw(saltyGraphics);
            element.doComponentDrawing(saltyGraphics);
        }
    }

    public void onFixedTick() {
        for (UIElement element : elements) {

            if (element.getTransform().subtractFromPosition(Game.camera.getPosition()).contains(Game.cursor)) {

                if (!element.mouseHoversOver()) {
                    element.mouseEntered(Game.cursor);
                }

                element.mouseHover(Game.cursor);
                element.setMouseHoversOver(true);
            } else if (element.mouseHoversOver()) {
                element.setMouseHoversOver(false);
                element.mouseExited(Game.cursor);
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

        for (UIElement element : elements) {
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
