package de.me.edgelord.sjgl.factory;

import de.me.edgelord.sjgl.cosmetic.Frame;
import de.me.edgelord.sjgl.resource.Resource;

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
