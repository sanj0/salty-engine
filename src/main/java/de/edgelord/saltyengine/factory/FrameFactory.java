/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.factory;

import de.edgelord.saltyengine.cosmetic.Frame;
import de.edgelord.saltyengine.resource.Resource;

public class FrameFactory extends Factory {

    public FrameFactory(Resource resource) {
        super(resource);
    }

    public FrameFactory(ImageFactory imageFactory) {
        super(imageFactory.getResource());
    }

    public Frame getImageFrame(String relativePath) {

        return new Frame(getResource().getImageResource(relativePath));
    }
}
