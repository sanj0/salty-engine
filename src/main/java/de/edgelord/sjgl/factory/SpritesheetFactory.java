/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.factory;

import de.edgelord.sjgl.cosmetic.Spritesheet;
import de.edgelord.sjgl.resource.Resource;

public class SpritesheetFactory extends Factory {

    public SpritesheetFactory(Resource resource) {
        super(resource);
    }

    public SpritesheetFactory(ImageFactory imageFactory) {
        super(imageFactory.getResource());
    }

    public Spritesheet getSpritesheet(String relativePath, int spriteWidth, int spriteHeight) {

        return new Spritesheet(getResource().getImageResource(relativePath), spriteWidth, spriteHeight);
    }
}
