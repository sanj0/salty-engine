/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.cosmetic;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Frame implements Cosmetic {

    public enum Mode {

        image,
        advancedCosmetics
    }

    private BufferedImage image = null;
    //private AdvancedCosmetics advancedCosmetics = null;

    public Frame(BufferedImage image) {

        this.image = image;
    }

    /*
    public Frame(AdvancedCosmetics advancedCosmetics){

        this.advancedCosmetics = advancedCosmetics;
    }
    */

    public Mode getAutomaticMode() {

        return Mode.image;

        /*
        if (image != null){

            return Mode.image;
        } else {

            return Mode.advancedCosmetics;
        }
        */
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, Vector2f position, float width, float height) {
        if (getAutomaticMode() == Mode.image) {

            saltyGraphics.drawImage(image, position.getX(), position.getY(), width, height);
        } else {

            //advancedCosmetics.draw(graphics, coordinates);
            return;
        }
    }

    public void draw(Graphics2D graphics, AffineTransform transform) {

        if (getAutomaticMode() == Mode.image) {

            graphics.drawImage(image, transform, null);
        } else {

            //advancedCosmetics.draw(graphics, coordinates);
            return;
        }
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}
