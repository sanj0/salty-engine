/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.saltyengine.layer.LayerCollection;

public class DummyLayerCollection extends LayerCollection {

    public DummyLayerCollection() {

        addLayer(new DummyLayer(1f));
        addLayer(new DummyLayer(-0.5f));
        addLayer(new DummyLayer(2f));

    }
}
