/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.cosmetic;

import de.me.edgelord.sjgl.StaticVars.StaticSystem;
import de.me.edgelord.sjgl.location.Coordinates;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Spritesheet {

    private BufferedImage image = null;
    private int spriteWidth, spriteHeight;

    private SpritePattern spritePattern;

    public Spritesheet(String relativePathToImage, int spriteWidth, int spriteHeight) {
        this.spriteWidth = spriteWidth;
        this.spriteHeight = spriteHeight;

        try {
            this.image = StaticSystem.getOuterResource().getImage(relativePathToImage);
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public Spritesheet(String relativePathToSpritePattern) {

        this.spritePattern = new SpritePattern(relativePathToSpritePattern);
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
