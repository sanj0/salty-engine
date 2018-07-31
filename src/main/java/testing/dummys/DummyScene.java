/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, 2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.scene.Scene;

public class DummyScene extends Scene {

    public DummyScene() {

        DummyGameObject dummyGameObject = new DummyGameObject(new Coordinates(550, 425));
        dummyGameObject.setMakeMove(false);

        //addGameObject(dummyGameObject);
    }
}
