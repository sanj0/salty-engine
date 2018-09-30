package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.cosmetic.ColorUtil;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public abstract class Container extends UIElement {

    private Color backgroundColor = ColorUtil.changeBrightness(Color.blue, -0.5f);

    private List<UIElement> childElements = new CopyOnWriteArrayList<>();

    public Container(Vector2f position, float width, float height) {
        super(position, width, height, UIElement.CONTAINER);
    }

    public Container(Transform transform) {
        this(transform.getPosition(), transform.getWidth(), transform.getHeight());
    }

    public Container(float x, float y, float width, float height) {
        this(new Vector2f(x, y), width, height);
    }

    public void add(UIElement element) {
        getChildElements().add(element);
    }

    public void remove(UIElement element) {
        getChildElements().removeIf(uiElement -> uiElement == element);
        element.setSuppressClipping(false);
    }

    public void supressAllClipping() {
        for (UIElement element : getChildElements()) {
            element.setSuppressClipping(true);
        }
    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<UIElement> getChildElements() {
        return childElements;
    }

    public void setChildElements(List<UIElement> childElements) {
        this.childElements = childElements;
    }
}
