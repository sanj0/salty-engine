/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, 2018
 * All rights reserved
 */

package testing.dummys;

import de.me.edgelord.sjgl.layer.Layer;
import de.me.edgelord.sjgl.location.Coordinates;

public class DummyLayer extends Layer {

    DummyGameObject dummyGameObject = new DummyGameObject(new Coordinates(500, 500));

    public DummyLayer(float speed) {
        super(speed);

        dummyGameObject.setMakeMove(false);

        addGameObject(dummyGameObject);
    }
}
