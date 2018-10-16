package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.stage.Stage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;

public class PaneledGameHost extends Host {

    private Dimensions dimensions;
    private Engine engine;
    private Stage stage;

    public PaneledGameHost(Container container, int x, int y, int width, int height, long fixedTickMillis) {
        dimensions = new Dimensions(width, height);

        engine = new Engine(fixedTickMillis);

        StaticSystem.fixedTickMillis = fixedTickMillis;
        Game.setEngine(engine);

        stage = new Stage(container, engine, x, y, width, height);
    }

    @Override
    public float getHorizontalCentrePosition(float width) {
        return (dimensions.getWidth()) - (width / 2);
    }

    @Override
    public float getVerticalCentrePosition(float height) {
        return (dimensions.getHeight()) - (height / 2);
    }

    @Override
    public void create() {
    }

    @Override
    public void repaint() {

        stage.repaint();
    }

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(getWidth(), getHeight());
    }

    @Override
    public void setBackgroundColor(Color color) {
        stage.setBackground(color);
    }
}
