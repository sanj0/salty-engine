/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.utils;

import de.edgelord.sjgl.layer.LayerCollection;
import de.edgelord.sjgl.output.Output;
import de.edgelord.sjgl.scene.Scene;
import testing.dummys.DummyLayerCollection;
import testing.dummys.DummyScene;

import java.awt.*;

public class StaticSystem {

    public static String versionTag = "0.3-SNAPSHOT";
    public static double version = 0.3;
    public static String versionMode = "SNAPSHOT";
    public static String versionName = "Zeus";
    public static String gameName = "My name is Nym. Arno Nym.";

    public static long fixedTicksMillis = 1;
    public static boolean paused = false;
    public static boolean withExperimentalFeatures = false;

    public static boolean inputUp = false;
    public static boolean inputDown = false;
    public static boolean inputRight = false;
    public static boolean inputLeft = false;
    public static char inputKey;

    public static boolean isPaused() {
        return paused;
    }

    public static void setPaused(boolean paused) {
        StaticSystem.paused = paused;
    }

    public enum Mode {
        layerCollection, scene
    }

    public static Mode currentMode = Mode.scene;
    public static Output systemOutput = new Output(System.out);
    public static boolean drawFPS = true;

    public static Font font = new Font(Font.SERIF, 0, 15);

    public static Scene currentScene = new DummyScene();
    public static LayerCollection currentLayerCollection = new DummyLayerCollection();
}
