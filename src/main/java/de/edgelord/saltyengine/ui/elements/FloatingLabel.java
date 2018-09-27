package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 *
 * This implementation of {@link Label} allows pixel-precise positioning of a one-line text
 * because the size of it fits the text. Also, the placing is natural and not from
 * the baseLine of the text but from its upper left corner.
 */
public class FloatingLabel extends Label {
    public FloatingLabel(String text, Vector2f position) {
        super(text, position, 0, 0);
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        prepareGraphics(saltyGraphics);

        recalculateSize(saltyGraphics.getFontMetrics());
        saltyGraphics.drawRect(this);
        saltyGraphics.setClip(getTransform());

        saltyGraphics.drawText(getText(), getX(), getY() + saltyGraphics.getFontMetrics().getMaxAscent());

        saltyGraphics.resetClip();
    }

    private void recalculateSize(FontMetrics metrics) {
        setWidth(metrics.stringWidth(getText()));
        setHeight(metrics.getMaxAscent() + metrics.getMaxDescent());
    }
}
