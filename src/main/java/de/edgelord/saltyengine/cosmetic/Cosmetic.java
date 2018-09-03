/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.cosmetic;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

public interface Cosmetic {

    void draw(SaltyGraphics saltyGraphics, Vector2f position, float width, float height);
}
