/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.camera;

import de.me.edgelord.sjgl.main.MainLoops;
import de.me.edgelord.sjgl.utils.Directions;
import de.me.edgelord.sjgl.utils.StaticSystem;

public class Camera {

    private MainLoops mainLoops;

    public Camera(MainLoops mainLoops) {

        this.mainLoops = mainLoops;
    }

    public void resetPosition() {

        if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

            StaticSystem.currentScene.resetPosition();
            return;
        }

        if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

            StaticSystem.currentLayerCollection.resetPositionOfAllLayers();
        }
    }

    public void moveCamera(Directions.BasicDirection direction, int delta) {

        if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

            StaticSystem.currentScene.moveCamera(direction, delta);
            return;
        }

        if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

            StaticSystem.currentLayerCollection.moveCamera(direction, delta);
            return;
        }
    }
}
