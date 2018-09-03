/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.resource;

import de.edgelord.saltyengine.utils.StaticSystem;
import de.edgelord.systemdependentfiles.SystemDependentFiles;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OuterResource implements Resource {

    @Override
    public BufferedImage getImageResource(String relativePath) {

        if (relativePath.startsWith("/")) {

            try {
                return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + relativePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {

            try {
                return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + "/" + relativePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // If the return statement above fails, return a new empty BufferedImage with 314 x 314 pixels and ImageType 1

        return new BufferedImage(314, 314, 1);
    }

    @Override
    public Clip getAudioResource(String relativePath) {
        AudioInputStream audioInputStream = null;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(getFile(relativePath));
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Clip clip = null;
        try {
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return clip;
    }

    @Override
    public File getFileResource(String relativePath) {
        return getFile(relativePath);
    }

    private String gameTitle;
    private boolean hidden;
    private File sourceDirectory;

    public OuterResource(boolean hidden) {

        this.gameTitle = StaticSystem.gameName;
        this.hidden = hidden;

        prepareSourceDirectory();

    }

    private void prepareSourceDirectory() {

        if (hidden) {

            sourceDirectory = SystemDependentFiles.getUserFile("." + gameTitle);
        } else {

            sourceDirectory = SystemDependentFiles.getSystemFile(gameTitle);
        }
    }

    private File getFile(String relativePath) {

        if (relativePath.startsWith("/")) {

            return new File(sourceDirectory.getAbsolutePath() + relativePath);
        } else {

            return new File(sourceDirectory.getAbsolutePath() + "/" + relativePath);
        }
    }

    public BufferedImage getImage(String relativePath) throws IOException {

        if (relativePath.startsWith("/")) {

            return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + relativePath));
        } else {

            return ImageIO.read(new File(sourceDirectory.getAbsolutePath() + "/" + relativePath));
        }
    }
}
