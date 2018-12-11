/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.stage.Stage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
    public Dimensions getDimensions() {
        return new Dimensions(getWidth(), getHeight());
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
    public void setDimensions(Dimensions dimensions) {
        container.setSize(Math.round(dimensions.getWidth()), Math.round(dimensions.getHeight()));
    }

    @Override
    public String takeScreenshot() {
        String name = "screenshot_";
        name += LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

        try {
            ImageUtils.saveImage(stage.renderToImage(), ImageUtils.IMAGE_FORMAT_PNG, name, SaltySystem.defaultOuterResource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return name;
    }

    @Override
    public Dimensions getOriginalResolution() {
        return stage.getResolution();
    }

    @Override
    public void toggleFullscreen() {
        throw new UnsupportedOperationException("Cannot toggle fullscreen from a PaneledGame!");
    }
}
