package de.me.edgelord.sjgl.factory;

import de.me.edgelord.sjgl.resource.Resource;

import java.awt.image.BufferedImage;

public class ImageFactory extends Factory {

    public ImageFactory(Resource resource) {
        super(resource);
    }

    public BufferedImage getImageResource(String relativePath) {

        return getResource().getImageResource(relativePath);
    }
}
