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

public class Frame implements Cosmetic {

    public enum Mode {

        image,
        advancedCosmetics
    }

    private BufferedImage image = null;
    //private AdvancedCosmetics advancedCosmetics = null;

    public Frame(String relativePath) {

        try {
            this.image = StaticSystem.getOuterResource().getImage(relativePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
    public void draw(Graphics2D graphics, Coordinates coordinates, int width, int height) {

        if (getAutomaticMode() == Mode.image) {

            graphics.drawImage(image, coordinates.getX(), coordinates.getY(), width, height, null);
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
