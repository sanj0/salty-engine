package de.edgelord.saltyengine.core;

import java.awt.*;

public class PaneledGame extends Game {

    public PaneledGame(Container container, int x, int y, int width, int height, long fixedTicksMillis, String gameName) {
        super(new PaneledGameHost(container, x, y, width, height, fixedTicksMillis), gameName);
    }
}
