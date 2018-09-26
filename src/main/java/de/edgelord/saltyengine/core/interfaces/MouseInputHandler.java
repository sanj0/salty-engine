package de.edgelord.saltyengine.core.interfaces;

import java.awt.event.MouseEvent;

public interface MouseInputHandler {

    void mouseMoved(MouseEvent e);

    void mouseDragged(MouseEvent e);

    void mousePressed(MouseEvent e);

    void mouseReleased(MouseEvent e);

    void mouseClicked(MouseEvent e);
}
