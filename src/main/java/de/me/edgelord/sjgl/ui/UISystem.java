package de.me.edgelord.sjgl.ui;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.LinkedList;
import java.util.List;

public class UISystem {

    private List<UIElement> elements = new LinkedList<>();

    public void drawUI(Graphics2D graphics){
        for (UIElement element : elements){
            element.draw(graphics);
        }
    }

    public void addElement(UIElement element){
        this.elements.add(element);
    }

    public void removeElement(UIElement element){

        int currentIndex = 0;
        int indexToRemove = -1;

        for (UIElement elementFromList : elements){

            if (elementFromList == element){
                indexToRemove = currentIndex;
            }

            currentIndex++;
        }

        if (indexToRemove == -1){
            return;
        } else {
            elements.remove(indexToRemove);
        }
    }

    public void keyPressed(KeyEvent e){

        for (UIElement element : elements){
            element.keyPressed(e);
        }
    }

    public void keyReleased(KeyEvent e){

        for (UIElement element : elements){
            element.keyReleased(e);
        }
    }

    public void keyTyped(KeyEvent e){

        for (UIElement element : elements){
            element.keyTyped(e);
        }
    }

    public void mouseClicked(MouseEvent e){

        for (UIElement element : elements){
            element.mouseClicked(e);
        }
    }

    public void mousePressed(MouseEvent e){

        for (UIElement element : elements){
            element.mousePressed(e);
        }
    }

    public void mouseReleased(MouseEvent e){

        for (UIElement element : elements){
            element.mouseReleased(e);
        }
    }
}
