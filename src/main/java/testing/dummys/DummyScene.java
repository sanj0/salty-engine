/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.saltyengine.location.Coordinates;
import de.edgelord.saltyengine.scene.Scene;

public class DummyScene extends Scene {

    public DummyScene() {

        DummyGameObject dummyGameObject = new DummyGameObject(new Coordinates(550, 425));
        dummyGameObject.setMakeMove(false);

        //addGameObject(dummyGameObject);
    }
}
