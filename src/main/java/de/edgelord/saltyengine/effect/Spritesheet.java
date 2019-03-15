/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.effect;

import de.edgelord.saltyengine.core.interfaces.Disposable;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Spritesheet implements Disposable {

    private SaltyImage image = null;
    private int spriteWidth, spriteHeight;

    private SpritePattern spritePattern;

    public Spritesheet(SaltyImage image, int spriteWidth, int spriteHeight) {
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

    public VolatileImage getSprite(int id) {

        Rectangle rectangle = spritePattern.getRectangle(id);

        return image.getSubImage(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }

    public SaltyImage getManualSprite(int x, int y) {

        return new SaltyImage(image.getSubImage(--x * getSpriteWidth(), --y * getSpriteHeight(), getSpriteWidth(), getSpriteHeight()));
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

    @Override
    public void dispose() {
        image.dispose();
    }
}
