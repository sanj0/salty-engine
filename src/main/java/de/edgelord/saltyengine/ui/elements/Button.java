/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Button extends UIElement {

    private String text;
    private int textWidth, textHeight;
    private Color backgroundColor = Color.white;
    private Color foregroundColor = Color.black;
    private int arc = 15;

    public Button(String text, Vector2f position, int width, int height) {
        super(position, width, height);

        this.text = text;

        this.setTag("button");
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        drawButton(saltyGraphics);
        drawText(saltyGraphics);
    }

    public void drawText(SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(getFont());

        textWidth = saltyGraphics.getFontMetrics().stringWidth(text);
        textHeight = saltyGraphics.getFontMetrics().getAscent();

        saltyGraphics.setColor(foregroundColor);

        saltyGraphics.drawText(text, getX() + ((getWidth() - textWidth) / 2), getY() + ((getHeight() + textHeight) / 2));
    }

    public void drawButton(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(backgroundColor);

        saltyGraphics.fillRoundRect(getCoordinates().getX(), getCoordinates().getY(), getWidthAsInt(), getHeightAsInt(), arc, arc);
    }

    public abstract void onClick(MouseEvent e);

    @Override
    public void mousePressed(MouseEvent e) {

        backgroundColor = backgroundColor.darker();
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        backgroundColor = backgroundColor.brighter();
        if ((e.getX() > getCoordinates().getX() && e.getX() < getCoordinates().getX() + getWidth()) && (e.getY() > getCoordinates().getY() && e.getY() < getCoordinates().getY() + getHeight())) {
            onClick(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
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

    public int getArc() {
        return arc;
    }

    public void setArc(int arc) {
        this.arc = arc;
    }
}
