/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.camera;

import de.edgelord.sjgl.utils.Directions;
import de.edgelord.sjgl.utils.StaticSystem;

public class Camera {

    public Camera() {
    }

    public static void resetPosition() {

        if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

            StaticSystem.currentScene.resetPosition();
            return;
        }

        if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

            StaticSystem.currentLayerCollection.resetPositionOfAllLayers();
        }
    }

    public static void moveCamera(Directions.BasicDirection direction, int delta) {

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
