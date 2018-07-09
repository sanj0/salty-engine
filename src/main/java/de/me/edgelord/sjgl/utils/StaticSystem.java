/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.utils;

import de.me.edgelord.sjgl.layer.LayerCollection;
import de.me.edgelord.sjgl.output.Output;
import de.me.edgelord.sjgl.scene.Scene;
import testing.dummys.DummyLayerCollection;
import testing.dummys.DummyScene;

public class StaticSystem {

    public enum Mode {
        layerCollection, scene
    }

    public static Mode currentMode = Mode.scene;
    public static Output systemOutput = new Output(System.out);

    public static Scene currentScene = new DummyScene();
    public static LayerCollection currentLayerCollection = new DummyLayerCollection();
}
