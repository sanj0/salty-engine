/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.saltyengine.location.Coordinates;
import de.edgelord.saltyengine.scene.Scene;

public class DummySceneForTestingZoom extends Scene {

    public DummySceneForTestingZoom() {

        addGameObject(new DummyBlock(new Coordinates(500, 350), 100, 100));
        addGameObject(new DummyBlock(new Coordinates(500, 450), 100, 100));
        addGameObject(new DummyBlock(new Coordinates(600, 350), 100, 100));
        addGameObject(new DummyBlock(new Coordinates(600, 450), 100, 100));
    }
}
