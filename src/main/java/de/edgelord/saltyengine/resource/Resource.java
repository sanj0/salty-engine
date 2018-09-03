/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.resource;

import javax.sound.sampled.Clip;
import java.awt.image.BufferedImage;
import java.io.File;

public interface Resource {

    BufferedImage getImageResource(String relativePath);

    Clip getAudioResource(String relativePath);

    File getFileResource(String relativePath);
}
