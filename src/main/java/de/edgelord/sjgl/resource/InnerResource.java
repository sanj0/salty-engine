/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.resource;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

public class InnerResource implements Resource {

    private final ClassLoader classLoader = InnerResource.class.getClassLoader();

    @Override
    public BufferedImage getImageResource(String relativePath) {

        String arrangedPath = arrangePath(relativePath);

        try (InputStream inputStream = classLoader.getResourceAsStream(arrangedPath)) {

            return ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If the return statement above fails, return a new empty BufferedImage with 314 x 314 pixels and ImageType 1

        return new BufferedImage(314, 314, 1);
    }

    @Override
    public Clip getAudioResource(String relativePath) {

        AudioInputStream audioInput = null;

        String arrangedPath = arrangePath(relativePath);

        try {

            InputStream inputStream = classLoader.getResourceAsStream(arrangedPath);
            audioInput = AudioSystem.getAudioInputStream(inputStream);
        } catch (IOException | UnsupportedAudioFileException e) {
            e.printStackTrace();
        }

        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInput);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }

        return clip;
    }

    private String arrangePath(String path){
        if (path.startsWith("/")) {

            return path.replaceFirst("/", "");
        }

        return path;
    }
}
