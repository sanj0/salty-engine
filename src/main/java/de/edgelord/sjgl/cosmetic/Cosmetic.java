/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.cosmetic;

import de.edgelord.sjgl.location.Coordinates;

import java.awt.*;

public interface Cosmetic {

    void draw(Graphics2D graphics, Coordinates coordinates, int width, int height);
}
