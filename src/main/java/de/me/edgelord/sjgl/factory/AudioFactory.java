package de.me.edgelord.sjgl.factory;

import de.me.edgelord.sjgl.resource.Resource;

import javax.sound.sampled.Clip;

public class AudioFactory extends Factory {

    public AudioFactory(Resource resource) {
        super(resource);
    }

    public Clip getClip(String relativePath) {

        return getResource().getAudioResource(relativePath);
    }
}
