/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
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
