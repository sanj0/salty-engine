/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.location.Coordinates;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Button extends UIElement {

    private String text;
    private int textWidth, textHeight;
    private Color backgroundColor = Color.white;
    private Color foregroundColor = Color.black;
    // private MouseEvent currentMouseEvent = null;

    public Button(String text, Coordinates coordinates, int width, int height) {
        super(coordinates, width, height);

        this.text = text;

        this.setTag("button");
    }

    @Override
    public void draw(Graphics2D graphics) {

        drawButton(graphics);
        drawText(graphics);

        /*
        if (currentMouseEvent != null) {
            graphics.drawOval(currentMouseEvent.getX() - 157, currentMouseEvent.getY() - 157, 314, 314);
            graphics.drawRect(currentMouseEvent.getX(), currentMouseEvent.getY(), 314, 314);
        }
        */
    }

    public void drawText(Graphics2D graphics) {

        graphics.setFont(getFont());

        textWidth = graphics.getFontMetrics(getFont()).stringWidth(text);
        textHeight = graphics.getFontMetrics(getFont()).getAscent();

        graphics.setColor(foregroundColor);

        graphics.drawString(text, getCoordinates().getX() + ((getWidth() - textWidth) / 2), getCoordinates().getY() + ((getHeight() + textHeight) / 2));
    }

    public void drawButton(Graphics2D graphics) {

        graphics.setColor(backgroundColor);

        graphics.fillRect(getCoordinates().getX(), getCoordinates().getY(), getWidth(), getHeight());
    }

    public abstract void onClick(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // this.currentMouseEvent = e;

        if ((e.getX() > getCoordinates().getX() && e.getX() < getCoordinates().getX() + getWidth()) && (e.getY() > getCoordinates().getY() && e.getY() < getCoordinates().getY() + getHeight())) {
            onClick(e);
        }
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public Color getForegroundColor() {
        return foregroundColor;
    }

    public void setForegroundColor(Color foregroundColor) {
        this.foregroundColor = foregroundColor;
    }
}
