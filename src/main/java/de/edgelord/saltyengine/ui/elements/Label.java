package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 * This class is a non-abstract label-like implementation of the class {@link TextElement}
 * In general, these elements are text container with no writing feature
 * nor a caret showing up.
 */
public abstract class Label extends TextElement {

    public Label(String text, Vector2f position, float width, float height) {
        super(text, position, width, height);
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public void onFixedTick() {

    }

    @Override
    public final void mouseDragged(MouseEvent e) {

    }

    @Override
    public final void mousePressed(MouseEvent e) {

    }

    @Override
    public final void mouseReleased(MouseEvent e) {

    }

    @Override
    public final void mouseClicked(MouseEvent e) {

    }

    @Override
    public final void mouseMoved(MouseEvent e) {

    }

    @Override
    public final void keyPressed(KeyEvent e) {

    }

    @Override
    public final void keyReleased(KeyEvent e) {

    }

    @Override
    public final void keyTyped(KeyEvent e) {

    }
}
