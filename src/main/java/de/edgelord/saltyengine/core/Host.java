package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.interfaces.CentrePositionProvider;
import de.edgelord.saltyengine.core.interfaces.Creatable;
import de.edgelord.saltyengine.core.interfaces.DimensionsProvider;
import de.edgelord.saltyengine.core.interfaces.Repaintable;

import java.awt.*;

/**
 * This is the class to implement for the host of a game.
 * The default host is {@link de.edgelord.saltyengine.display.DisplayManager}
 * <p>
 * Every host has to be repaintable, has to provide centre position for better placing of
 * objects inside the game, it has to provide its dimensions, has to be creatable and has to implement {@link #setBackgroundColor(Color)}
 * <p>
 * Apart from that, it can be literally everything. A window, a panel, an applet...
 * <p>
 * A host should also draw the content of {@link Engine}, an example: {@link de.edgelord.saltyengine.stage.Stage#paint(Graphics)}
 */
public abstract class Host implements Repaintable, CentrePositionProvider, DimensionsProvider, Creatable {

    /**
     * This method sets the background color of the host.
     * This is the color that is seen there, where nothing is drawn over.
     *
     * @param color the new background color
     */
    public abstract void setBackgroundColor(Color color);
}
