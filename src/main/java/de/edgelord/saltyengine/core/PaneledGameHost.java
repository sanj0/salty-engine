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
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.SaltySystem;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;

@Deprecated
public class PaneledGameHost extends Host {

    private final Dimensions dimensions;
    private final Container container;
    private final Engine engine;
    private final Stage stage;

    public PaneledGameHost(final Container container, final int x, final int y, final int width, final int height, final long fixedTickMillis) {
        dimensions = new Dimensions(width, height);

        engine = new Engine(fixedTickMillis);

        SaltySystem.fixedTickMillis = fixedTickMillis;
        Game.setEngine(engine);

        stage = new Stage(container, x, y, width, height);
        this.container = container;
    }

    @Override
    public float getHorizontalCentrePosition(final float width) {
        return (dimensions.getWidth()) - (width / 2);
    }

    @Override
    public float getVerticalCentrePosition(final float height) {
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
    public void setBackgroundColor(final Color color) {
        stage.setBackground(color);
    }

    @Override
    public Color getBackgroundColor() {
        return stage.getBackground();
    }

    @Override
    public boolean showConfirmDialog(final String message) {
        return JOptionPane.showConfirmDialog(null, message, "Confirm Deletion", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    @Override
    public ImageObserver getImageObserver() {
        return stage;
    }

    @Override
    public void setDimensions(final Dimensions dimensions) {
        container.setSize(Math.round(dimensions.getWidth()), Math.round(dimensions.getHeight()));
    }

    @Override
    public String takeScreenshot() {
        return stage.newScreenshot();
    }

    @Override
    public SaltyImage getScreenshot() {
        return stage.renderToImage();
    }

    @Override
    public void addComponent(final JComponent component) throws UnsupportedOperationException {
        stage.add(component);
    }

    @Override
    public void removeComponent(final JComponent component) throws UnsupportedOperationException {
        stage.remove(component);
    }

    @Override
    public void toggleFullscreen() {
        throw new UnsupportedOperationException("Cannot toggle fullscreen from a PaneledGame!");
    }
}
