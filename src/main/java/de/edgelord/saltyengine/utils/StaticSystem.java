/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.output.Output;
import de.edgelord.saltyengine.resource.InnerResource;

import java.awt.*;

public class StaticSystem {

    public static String versionTag = "0.11.1-SNAPSHOT";
    public static String version = "0.11.1";
    public static VersionMode versionMode = VersionMode.SNAPSHOT;

    public static long fixedTickMillis = 1;

    public static ImageFactory defaultImageFactory = new ImageFactory(new InnerResource());
    public static InnerResource defaultResource = new InnerResource();

    public static Output systemOutput = new Output(System.out);

    public static Font font = new Font(Font.SERIF, Font.PLAIN, 15);

    public enum VersionMode {
        SNAPSHOT,
        ALPHA,
        BETA,
        RELEASE
    }
}
