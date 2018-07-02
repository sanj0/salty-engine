/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.cosmetic;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToDataConverter;
import de.edgelord.stdf.reading.ValueToListConverter;
import de.me.edgelord.sjgl.StaticVars.StaticSystem;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class SpritePattern {

    private File spritePattern;
    private HashMap<Integer, Rectangle> pattern = new HashMap<>();

    private Rectangle[][] rectangles;

    public SpritePattern(String relativePathToSpritePattern) {

        spritePattern = StaticSystem.getOuterResource().getFile(relativePathToSpritePattern);
    }

    public void loadPattern() throws Exception {

        DataReader dataReader = new DataReader(spritePattern);
        List<Integer> currentRectangleData;

        int sprites;
        int currentIndex = 0;

        Species spritePattern = dataReader.getMainSpecies();
        Species pattern = spritePattern.getSubSpecies("pattern");

        sprites = ValueToDataConverter.convertToInteger(spritePattern, "sprites");

        while (currentIndex != sprites) {

            ++currentIndex;

            currentRectangleData = ValueToListConverter.convertToIntegerList(pattern, "rectangle" + currentIndex, ",");

            this.pattern.put(currentRectangleData.get(4), new Rectangle(currentRectangleData.get(0), currentRectangleData.get(1), currentRectangleData.get(2), currentRectangleData.get(3)));
        }

    }

    public Rectangle getRectangle(int id) {

        return pattern.get(id);
    }
}
