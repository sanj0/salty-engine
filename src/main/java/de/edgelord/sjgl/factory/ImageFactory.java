/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.factory;

import de.edgelord.sjgl.resource.Resource;

import java.awt.image.BufferedImage;

public class ImageFactory extends Factory {

    public ImageFactory(Resource resource) {
        super(resource);
    }

    public BufferedImage getImageResource(String relativePath) {

        return getResource().getImageResource(relativePath);
    }
}
