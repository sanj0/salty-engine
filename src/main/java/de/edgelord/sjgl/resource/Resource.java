/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.resource;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;

public interface Resource {

    BufferedImage getImageResource(String relativePath);

    Clip getAudioResource(String relativePath);
}
