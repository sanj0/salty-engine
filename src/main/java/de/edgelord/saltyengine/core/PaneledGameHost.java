/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.displaymanager.stage.Stage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class PaneledGameHost extends Host {

    private Dimensions dimensions;
    private Container container;
    private Engine engine;
    private Stage stage;

    public PaneledGameHost(Container container, int x, int y, int width, int height, long fixedTickMillis) {
        dimensions = new Dimensions(width, height);

        engine = new Engine(fixedTickMillis);

        SaltySystem.fixedTickMillis = fixedTickMillis;
        Game.setEngine(engine);

        stage = new Stage(container, engine, x, y, width, height);
        this.container = container;
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
    public Dimensions getCurrentDimensions() {
        return new Dimensions(stage.getWidth(), stage.getHeight());
    }

    @Override
    public void setBackgroundColor(Color color) {
        stage.setBackground(color);
    }

    @Override
    public RenderingHints getRenderHints() {
        return stage.getRenderHints();
    }

    @Override
    public ImageObserver getImageObserver() {
        return stage;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        container.setSize(Math.round(dimensions.getWidth()), Math.round(dimensions.getHeight()));
    }

    @Override
    public String takeScreenshot() {
        return stage.newScreenshot();
    }

    @Override
    public BufferedImage getScreenshot() {
        return ImageUtils.toBufferedImage(stage.renderToImage());
    }

    @Override
    public void toggleFullscreen() {
        throw new UnsupportedOperationException("Cannot toggle fullscreen from a PaneledGame!");
    }
}
