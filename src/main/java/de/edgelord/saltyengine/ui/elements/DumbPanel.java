package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.RoundRectangle2D;

public class DumbPanel extends Container {

    private float arc = 15;

    public DumbPanel(Vector2f position, float width, float height) {
        super(position, width, height);
    }

    public DumbPanel(Transform transform) {
        this(transform.getPosition(), transform.getWidth(), transform.getHeight());
    }

    public DumbPanel(float x, float y, float width, float height) {
        this(new Vector2f(x, y), width, height);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        // Suppress the clipping of all child elements
        supressAllClipping();

        // Set clipping area
        saltyGraphics.setClip(new RoundRectangle2D.Float(getX(), getY(), getWidth(), getHeight(), arc, arc));

        // Draw background
        saltyGraphics.setColor(getBackgroundColor());
        saltyGraphics.fillRoundRect(this, arc);

        // Draw the child elements
        getChildElements().forEach(uiElement -> uiElement.draw(saltyGraphics));

        // Reset Clipping area
        saltyGraphics.resetClip();
    }

    @Override
    public void onFixedTick() {
        getChildElements().forEach(uiElement -> uiElement.onFixedTick());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        getChildElements().forEach(uiElement -> mousePressed(e));
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseReleased(e));
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseClicked(e));
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseMoved(e));
    }

    @Override
    public void keyPressed(KeyEvent e) {
        getChildElements().forEach(uiElement -> uiElement.keyPressed(e));
    }

    @Override
    public void keyReleased(KeyEvent e) {
        getChildElements().forEach(uiElement -> uiElement.keyReleased(e));
    }

    @Override
    public void keyTyped(KeyEvent e) {
        getChildElements().forEach(uiElement -> uiElement.keyTyped(e));
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        getChildElements().forEach(uiElement -> uiElement.mouseDragged(e));
    }

    public float getArc() {
        return arc;
    }

    public void setArc(float arc) {
        this.arc = arc;
    }
}
