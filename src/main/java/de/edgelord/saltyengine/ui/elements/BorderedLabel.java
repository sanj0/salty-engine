package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 * This is the standard implementation of a {@link Label}, with the given text only
 * be drawn within the bounds and no line breaks, meaning this Label is one-line.
 */
public class BorderedLabel extends Label {

    public BorderedLabel(String text, Vector2f position, float width, float height) {
        super(text, position, width, height);
    }

    public BorderedLabel(String text, float x, float y, float width, float height) {
        this(text, new Vector2f(x, y), width, height);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        prepareGraphics(saltyGraphics);

        float textXPos = 0f;
        float textYPos = 0f;
        FontMetrics fontMetrics = saltyGraphics.getFontMetrics();

        saltyGraphics.setClip(getTransform());

        switch (getHorizontalAlignment()) {

            case right:
                textXPos = getHorizontalRightAlignmentPosition(fontMetrics);
                break;
            case left:
                textXPos = getHorizontalLeftAlignmentPosition();
                break;
            case centered:
                textXPos = getHorizontalCenteredAlignmentPosition(fontMetrics);
                break;
        }

        switch (getVerticalAlignment()) {

            case top:
                textYPos = getVerticalTopAlignmentPosition(fontMetrics);
                break;
            case bottom:
                textYPos = getVerticalBottomAlignmentPosition(fontMetrics);
                break;
            case centered:
                textYPos = getVerticalCenteredAlignmentPosition(fontMetrics);
                break;
        }

        saltyGraphics.drawText(getText(), getX() + textXPos, getY() + textYPos);

        saltyGraphics.resetClip();
    }
    
    private float getVerticalTopAlignmentPosition(FontMetrics fontMetrics) {
        return fontMetrics.getMaxAscent();
    }
    
    private float getVerticalBottomAlignmentPosition(FontMetrics fontMetrics) {
        return getHeight() - fontMetrics.getMaxDescent();
    }
    
    private float getVerticalCenteredAlignmentPosition(FontMetrics fontMetrics) {
        return (getHeight() + fontMetrics.getMaxAscent()) / 2;
    }
    
    private float getHorizontalLeftAlignmentPosition() {
        return 0;
    }
    
    private float getHorizontalRightAlignmentPosition(FontMetrics fontMetrics) {
        return getWidth() - fontMetrics.stringWidth(getText());
    }
    
    private float getHorizontalCenteredAlignmentPosition(FontMetrics fontMetrics) {
        return (getWidth() - fontMetrics.stringWidth(getText())) / 2;
    }
}
