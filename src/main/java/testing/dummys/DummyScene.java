/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Vector2f;

public class DummyScene extends Scene {

    public DummyScene() {

        DummyGameObject dummyGameObject = new DummyGameObject(new Vector2f(550, 425));
        dummyGameObject.setMakeMove(false);

        //addGameObject(dummyGameObject);
    }
}
