package de.me.edgelord.sjgl.ui;

import de.me.edgelord.sjgl.location.Coordinates;;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class Button extends UIElement {

    private String text;
    private int textWidth, textHeight;
    private Color backgroundColor = Color.white;
    private Color foregroundColor = Color.black;

    public Button(String text, Coordinates coordinates, int width, int height) {
        super(coordinates, width, height);

        this.text = text;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.setFont(getFont());

        textWidth = graphics.getFontMetrics(getFont()).stringWidth(text);
        textHeight = (int) graphics.getFontMetrics(getFont()).getStringBounds(text, graphics).getHeight();

        graphics.setColor(backgroundColor);

        graphics.fillRect(getCoordinates().getX(), getCoordinates().getY(), getWidth(), getHeight());

        graphics.setColor(foregroundColor);

        graphics.drawString(text, getCoordinates().getX() + ((getWidth() - textWidth) / 2),  getCoordinates().getY() + textHeight);
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

        if ((e.getX() > getCoordinates().getX() && e.getX() < getCoordinates().getX() + getWidth()) && (e.getY() > getCoordinates().getY() && e.getY() < getCoordinates().getY() + getHeight())){
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
