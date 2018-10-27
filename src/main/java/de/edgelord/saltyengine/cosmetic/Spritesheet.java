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

import de.edgelord.saltyengine.transform.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Spritesheet {

    private BufferedImage image = null;
    private int spriteWidth, spriteHeight;

    private SpritePattern spritePattern;

    public Spritesheet(BufferedImage image, int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        this.image = image;
    }

    public Spritesheet(File spritePatternFile) {

        this.spritePattern = new SpritePattern(spritePatternFile);
    }

    public List<Frame> getManualFrames(Coordinates... coordinates) {

        List<Frame> frames = new LinkedList<>();

        for (Coordinates currentCoordinates : coordinates) {

            frames.add(new Frame(getManualSprite(currentCoordinates.getX(), currentCoordinates.getY())));
        }

        return frames;
    }

    public BufferedImage getSprite(int id) {

        Rectangle rectangle = spritePattern.getRectangle(id);

        return image.getSubimage(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public BufferedImage getManualSprite(int x, int y) {

        return this.image.getSubimage(--x * getSpriteWidth(), --y * getSpriteHeight(), getSpriteWidth(), getSpriteHeight());
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Spritesheet that = (Spritesheet) o;
        return spriteWidth == that.spriteWidth &&
                spriteHeight == that.spriteHeight &&
                Objects.equals(image, that.image) &&
                Objects.equals(spritePattern, that.spritePattern);
    }

    @Override
    public int hashCode() {

        return Objects.hash(image, spriteWidth, spriteHeight, spritePattern);
    }
}
