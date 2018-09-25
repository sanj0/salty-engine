package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public abstract class TextElement extends UIElement {

    private String text;

    private Color textColor = Color.black;
    private Font font = new Font(Font.SANS_SERIF, Font.PLAIN, 15);

    private HorizontalAlignment horizontalAlignment = HorizontalAlignment.centered;
    private VerticalAlignment verticalAlignment = VerticalAlignment.centered;

    /**
     * The horizontal alignments for the text within the bounds of the label.
     * An implementation of TextElement should take this into account.
     * The default is {@link HorizontalAlignment#centered}
     */
    public enum HorizontalAlignment {
        right,
        left,
        centered
    }

    /**
     * The vertical alignments for the text within the bounds of the label.
     * An implementation of TextElement should take this into account.
     * The default is {@link VerticalAlignment#centered}
     */
    public enum VerticalAlignment {
        top,
        bottom,
        centered
    }

    public TextElement(String text, Vector2f position, float width, float height) {
        super(position, width, height);

        this.text = text;
    }

    /**
     * This mehtods prepares the given graphics for rendering the text with the values
     * font and color. This should be called before drawing the text.
     *
     * @param graphics the graphics to prepare
     */
    public void prepareGraphics(SaltyGraphics graphics) {
        graphics.setColor(textColor);
        graphics.setFont(font);
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }

    public HorizontalAlignment getHorizontalAlignment() {
        return horizontalAlignment;
    }

    public void setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
        this.horizontalAlignment = horizontalAlignment;
    }

    public VerticalAlignment getVerticalAlignment() {
        return verticalAlignment;
    }

    public void setVerticalAlignment(VerticalAlignment verticalAlignment) {
        this.verticalAlignment = verticalAlignment;
    }

    @Override
    public Font getFont() {
        return font;
    }

    @Override
    public void setFont(Font font) {
        this.font = font;
    }

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
}
