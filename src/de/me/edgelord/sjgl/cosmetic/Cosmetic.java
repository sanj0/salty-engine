package de.me.edgelord.sjgl.cosmetic;

import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;

public interface Cosmetic {

    void draw(Graphics2D graphics, Coordinates coordinates, int width, int height);
}
