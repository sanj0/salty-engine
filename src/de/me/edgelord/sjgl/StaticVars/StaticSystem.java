/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.StaticVars;

import de.me.edgelord.sjgl.layer.LayerCollection;
import de.me.edgelord.sjgl.output.Output;
import de.me.edgelord.sjgl.resource.OuterResource;
import de.me.edgelord.sjgl.scene.Scene;
import testing.dummys.DummyLayerCollection;
import testing.dummys.DummyScene;

public class StaticSystem {

    static OuterResource outerResource;

    public static OuterResource getOuterResource() {
        return outerResource;
    }

    public static void setOuterResource(OuterResource outerResource) {
        StaticSystem.outerResource = outerResource;
    }

    public enum Mode {
        layerCollection, scene
    }

    public static Mode currentMode = Mode.scene;
    public static Output systemOutput = new Output(System.out);

    public static Scene currentScene = new DummyScene();
    public static LayerCollection currentLayerCollection = new DummyLayerCollection();
}
