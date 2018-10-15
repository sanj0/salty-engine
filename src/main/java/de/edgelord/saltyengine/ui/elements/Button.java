/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.cosmetic.ColorUtil;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Button extends UIElement {

    private String text;
    private int textWidth, textHeight;
    private Color backgroundColor = Color.black;
    private Color hoverColor = ColorUtil.changeBrightness(backgroundColor, -0.15f);
    private Color clickColor = ColorUtil.changeBrightness(backgroundColor, 0.15f);
    private Color currentBackgroundColor = backgroundColor;
    private Color foregroundColor = Color.white;
    private int arc = 15;

    public Button(String text, Vector2f position, int width, int height) {
        super(position, width, height, BUTTON);

        this.text = text;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (!isSuppressClipping()) {
            saltyGraphics.setClip(getTransform());
        }
        drawButton(saltyGraphics);
        drawText(saltyGraphics);
        if (!isSuppressClipping()) {
            saltyGraphics.resetClip();
        }
    }

    public void drawText(SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(getFont());

        textWidth = saltyGraphics.getFontMetrics().stringWidth(text);
        textHeight = saltyGraphics.getFontMetrics().getAscent();

        saltyGraphics.setColor(foregroundColor);

        saltyGraphics.drawText(text, getX() + ((getWidth() - textWidth) / 2), getY() + ((getHeight() + textHeight) / 2));
    }

    public void drawButton(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(currentBackgroundColor);

        saltyGraphics.drawRoundRect(getX(), getY(), getWidth(), getHeight(), arc);
    }

    public boolean touchesButton(MouseEvent e) {
        return (e.getX() > getCoordinates().getX() && e.getX() < getCoordinates().getX() + getWidth()) && (e.getY() > getCoordinates().getY() && e.getY() < getCoordinates().getY() + getHeight());
    }

    public abstract void onClick(MouseEvent e);

    @Override
    public void onFixedTick() {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

        if (touchesButton(e)) {

            currentBackgroundColor = hoverColor;
        } else {
            currentBackgroundColor = backgroundColor;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (touchesButton(e)) {
            currentBackgroundColor = clickColor;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

        currentBackgroundColor = backgroundColor;

        if (touchesButton(e)) {
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
        this.currentBackgroundColor = backgroundColor;
        this.hoverColor = ColorUtil.changeBrightness(backgroundColor, -0.15f);
        this.clickColor = ColorUtil.changeBrightness(backgroundColor, 0.15f);
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
