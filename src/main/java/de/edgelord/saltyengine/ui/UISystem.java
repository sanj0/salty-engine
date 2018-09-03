/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.ui;

import de.edgelord.saltyengine.graphics.SaltyGraphics;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class UISystem {

    private List<UIElement> elements = new LinkedList<>();

    public void drawUI(SaltyGraphics saltyGraphics) {
        for (UIElement element : elements) {
            element.draw(saltyGraphics);
            element.doComponentDrawing(saltyGraphics);
        }
    }

    public void onFixedTick() {
        for (UIElement element : elements) {
            element.doComponentOnFixedTick();
            element.onFixedTick();
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

    public void mouseMoved(MouseEvent e) {
        for (UIElement element : elements) {
            element.mouseMoved(e);
        }
    }
}
