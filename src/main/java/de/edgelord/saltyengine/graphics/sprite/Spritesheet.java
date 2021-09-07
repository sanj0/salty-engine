/*
 * Copyright 2020 Malte Dostal
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

package de.edgelord.saltyengine.graphics.sprite;

import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.transform.Coordinates;

import java.io.Flushable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Spritesheet implements Flushable {

    private final SaltyImage image;
    private int spriteWidth, spriteHeight;
    private int offsetX, offsetY;
    private boolean startsWithOffset;

    public Spritesheet(final SaltyImage image, final float spriteWidth, final float spriteHeight,
                       final int offsetX, final int offsetY, final boolean startsWithOffset) {
        this.spriteWidth = Math.round(spriteWidth);
        this.spriteHeight = Math.round(spriteHeight);
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.startsWithOffset = startsWithOffset;

        this.image = image;
    }

    public Spritesheet(final SaltyImage image, final float spriteWidth, final float spriteHeight) {
        this(image, spriteWidth, spriteHeight, 0, 0, false);
    }

    public SpritesheetAnimation getAnimation(final Coordinates... coordinates) {
        return new SpritesheetAnimation(getFrames(coordinates));
    }

    public List<Frame> getFrames(final Coordinates... coordinates) {
        final List<Frame> frames = new LinkedList<>();

        for (final Coordinates currentCoordinates : coordinates) {
            frames.add(getFrame(currentCoordinates.getX(), currentCoordinates.getY()));
        }

        return frames;
    }

    public Frame getFrame(final int x, final int y) {
        final int oX = x * offsetX + (startsWithOffset ? offsetX : 0);
        final int oY = x * offsetY + (startsWithOffset ? offsetY : 0);
        return new Frame(image.getSubImage(x * getSpriteWidth() + oX, y * getSpriteHeight() + oY, getSpriteWidth(), getSpriteHeight()));
    }

    public int getSpriteWidth() {
        return spriteWidth;
    }

    public void setSpriteWidth(final int spriteWidth) {
        this.spriteWidth = spriteWidth;
    }

    public int getSpriteHeight() {
        return spriteHeight;
    }

    public void setSpriteHeight(final int spriteHeight) {
        this.spriteHeight = spriteHeight;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass())
            return false;
        final Spritesheet that = (Spritesheet) o;
        return spriteWidth == that.spriteWidth &&
                spriteHeight == that.spriteHeight &&
                Objects.equals(image, that.image);
    }

    @Override
    public int hashCode() {

        return Objects.hash(image, spriteWidth, spriteHeight);
    }

    @Override
    public void flush() {
        image.flush();
    }
}
