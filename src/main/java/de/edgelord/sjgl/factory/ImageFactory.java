/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.factory;

import de.edgelord.sjgl.resource.Resource;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ImageFactory extends Factory {

    public ImageFactory(Resource resource) {
        super(resource);
    }

    public BufferedImage getImageResource(String relativePath) {

        return getResource().getImageResource(relativePath);
    }

    public BufferedImage getOptimizedImageResource(String relativePath){
        return createCompatibleImage(getImageResource(relativePath));
    }

    private BufferedImage createCompatibleImage(BufferedImage image) {
        GraphicsConfiguration gfxConfig = GraphicsEnvironment.
                getLocalGraphicsEnvironment().getDefaultScreenDevice().
                getDefaultConfiguration();

        if (image.getColorModel().equals(gfxConfig.getColorModel()))
            return image;

        BufferedImage newImage = gfxConfig.createCompatibleImage(
                image.getWidth(), image.getHeight(), image.getTransparency());

        Graphics2D g2d = newImage.createGraphics();

        g2d.drawImage(image, 0, 0, null);
        g2d.dispose();

        return newImage;
    }
}
