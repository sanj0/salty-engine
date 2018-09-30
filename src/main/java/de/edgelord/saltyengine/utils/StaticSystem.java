/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.layer.LayerCollection;
import de.edgelord.saltyengine.output.Output;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import testing.dummys.DummyLayerCollection;
import testing.dummys.DummyScene;

import java.awt.*;

public class StaticSystem {

    public static String versionTag = "0.10.0-SNAPSHOT";
    public static String version = "0.10.0";
    public static VersionMode versionMode = VersionMode.SNAPSHOT;

    public static long fixedTickMillis = 1;

    public static ImageFactory defaultImageFactory = new ImageFactory(new InnerResource());

    public enum Mode {
        layerCollection, scene
    }

    public static Mode currentMode = Mode.scene;
    public static Output systemOutput = new Output(System.out);
    public static boolean drawFPS = false;

    public static Font font = new Font(Font.SERIF, Font.PLAIN, 15);

    public static Scene currentScene = new DummyScene();
    public static LayerCollection currentLayerCollection = new DummyLayerCollection();

    public enum VersionMode {
        SNAPSHOT,
        ALPHA,
        BETA,
        RELEASE
    }
}
