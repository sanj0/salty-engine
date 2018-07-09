package de.me.edgelord.sjgl.factory;

import de.me.edgelord.sjgl.cosmetic.Spritesheet;
import de.me.edgelord.sjgl.resource.Resource;

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
