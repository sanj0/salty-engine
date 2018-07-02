/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, 2018
 * All rights reserved
 */

package testing.dummys;

import de.me.edgelord.sjgl.location.Coordinates;
import de.me.edgelord.sjgl.scene.Scene;

public class DummyScene extends Scene {

    public DummyScene() {

        DummyGameObject dummyGameObject = new DummyGameObject(new Coordinates(550, 425));
        dummyGameObject.setMakeMove(false);

        //addGameObject(dummyGameObject);
    }
}
