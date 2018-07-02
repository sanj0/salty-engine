/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.resource;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class OuterResource {

    public enum OperatingSystems {

        windows,
        macOS,
        linux
    }

    private String gameTitle;
    private boolean hidden;
    private OperatingSystems os;
    private String user;
    private File sourceDirectory;

    public OuterResource(String gameTitle, boolean hidden) {

        this.gameTitle = gameTitle;
        this.hidden = hidden;

        prepareSourceDirectory();

    }

    private void prepareSourceDirectory() {

        readProperties();

        if (os == OperatingSystems.linux) {

            if (hidden) {

                sourceDirectory = new File("/home/" + user + "/." + gameTitle);
            } else {

                sourceDirectory = new File("/home/" + user + "/" + gameTitle);
            }
        } else if (os == OperatingSystems.macOS) {

            if (hidden) {

                sourceDirectory = new File("/Users/" + user + "/." + gameTitle);
            } else {

                sourceDirectory = new File("/Users/" + user + "/" + gameTitle);
            }
        }

        if (!sourceDirectory.exists()) {

            sourceDirectory.mkdir();
        }
    }

    public File getFile(String relativePath) {

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

    @Deprecated
    public AudioClip getAudioClip(String relativePath) throws MalformedURLException {

        if (relativePath.startsWith("/")) {

            return Applet.newAudioClip(new File(sourceDirectory.getAbsolutePath() + relativePath).toURI().toURL());
        } else {

            return Applet.newAudioClip(new File(sourceDirectory.getAbsolutePath() + "/" + relativePath).toURI().toURL());
        }
    }

    public Clip getClip(String relativePath) throws IOException, UnsupportedAudioFileException, LineUnavailableException {

        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(getFile(relativePath));
        Clip clip = AudioSystem.getClip();

        clip.open(audioInputStream);

        return clip;
    }

    private void readProperties() {

        System.out.println("INFO: Current Operating System's name is: " + System.getProperty("os.name"));

        if (System.getProperty("os.name").toLowerCase().contains("windows")) {

            os = OperatingSystems.windows;
        } else if (System.getProperty("os.name").toLowerCase().contains("linux")) {

            os = OperatingSystems.linux;
        } else if (System.getProperty("os.name").toLowerCase().contains("mac")) {

            os = OperatingSystems.macOS;
        }

        System.out.println("INFO: sjgl detected your Operating System as: " + os.toString() + ". If this is wrong, please contact the developer with the output of the Operating System's name.");

        user = System.getProperty("user.name");

        System.out.println("INFO: The current user of sjgl is: " + user);
    }
}
