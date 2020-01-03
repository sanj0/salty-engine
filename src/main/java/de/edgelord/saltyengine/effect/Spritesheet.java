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

import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Coordinates;

import java.io.Flushable;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Spritesheet implements Flushable {

    private SaltyImage image;
    private int spriteWidth, spriteHeight;


    public Spritesheet(SaltyImage image, float spriteWidth, float spriteHeight) {
        this.spriteWidth = Math.round(spriteWidth);
        this.spriteHeight = Math.round(spriteHeight);

        this.image = image;
    }

    public SpritesheetAnimation getAnimation(Coordinates... coordinates) {
        return new SpritesheetAnimation(getFrames(coordinates));
    }

    public List<Frame> getFrames(Coordinates... coordinates) {

        List<Frame> frames = new LinkedList<>();

        for (Coordinates currentCoordinates : coordinates) {
            frames.add(getFrame(currentCoordinates.getX(), currentCoordinates.getY()));
        }

        return frames;
    }

    public Frame getFrame(int x, int y) {
        return new Frame(image.getSubImage(--x * getSpriteWidth(), --y * getSpriteHeight(), getSpriteWidth(), getSpriteHeight()));
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
