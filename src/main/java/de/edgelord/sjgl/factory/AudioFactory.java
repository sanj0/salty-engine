/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.factory;

import de.edgelord.sjgl.resource.Resource;

import javax.sound.sampled.Clip;

public class AudioFactory extends Factory {

    public AudioFactory(Resource resource) {
        super(resource);
    }

    public Clip getClip(String relativePath) {

        return getResource().getAudioResource(relativePath);
    }
}
