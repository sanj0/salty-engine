/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.cosmetic;

import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.reading.ValueToDataConverter;
import de.edgelord.stdf.reading.ValueToListConverter;

import java.awt.*;
import java.io.File;
import java.util.HashMap;
import java.util.List;

public class SpritePattern {

    private File spritePattern;
    private HashMap<Integer, Rectangle> pattern = new HashMap<>();

    private Rectangle[][] rectangles;

    public SpritePattern(File file) {

        spritePattern = file;
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
