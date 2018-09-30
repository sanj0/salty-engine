package de.edgelord.saltyengine.ui.elements;

import de.edgelord.saltyengine.core.Game;
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

    private boolean centreOnXAxis = false;
    private boolean centreOnYAxis = false;

    public FloatingLabel(String text, Vector2f position) {
        super(text, position, 0, 0);
    }

    public FloatingLabel(String text, float x, float y) {
        this(text, new Vector2f(x, y));
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        prepareGraphics(saltyGraphics);

        recalculateSize(saltyGraphics.getFontMetrics());

        if (centreOnXAxis) {
            setX(Game.getHost().getHorizontalCentrePosition(getWidth()));
        }

        if (centreOnYAxis) {
            setY(Game.getHost().getVerticalCentrePosition(getHeight()));
        }

        if (!isSuppressClipping()) {
            saltyGraphics.setClip(getTransform());
        }

        saltyGraphics.drawText(getText(), getX(), getY() + saltyGraphics.getFontMetrics().getMaxAscent());

        if (!isSuppressClipping()) {
            saltyGraphics.resetClip();
        }
    }

    private void recalculateSize(FontMetrics metrics) {
        setWidth(metrics.stringWidth(getText()));
        setHeight(metrics.getMaxAscent() + metrics.getMaxDescent());
    }

    public void centreOnXAxis(boolean centre) {
        this.centreOnXAxis = centre;
    }

    public void centreOnYAxis(boolean centre) {
        this.centreOnYAxis = centre;
    }
}
