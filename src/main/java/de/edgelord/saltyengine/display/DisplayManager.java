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

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.Host;
import de.edgelord.saltyengine.core.interfaces.KeyboardInputHandler;
import de.edgelord.saltyengine.core.interfaces.MouseInputHandler;
import de.edgelord.saltyengine.stage.Stage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.SimpleFormatter;

public class DisplayManager extends Host {

    private final Display display;
    private final Stage stage;
    private NativeDisplayKeyListener nativeKeyListener;

    public DisplayManager(DisplayRatio displayRatio, final String gameName, final Engine engine) {

        display = new Display(displayRatio, gameName);
        stage = new Stage(display, engine);

        Game.gameName = gameName;
    }

    @Override
    public void create() {

        display.create();
        createKeyListener();
    }

    @Override
    public float getHorizontalCentrePosition(final float width) {
        return (getWidth() / 2) - (width / 2);
    }

    @Override
    public float getVerticalCentrePosition(final float height) {
        return (getHeight() / 2) - (height / 2);
    }

    @Override
    public void repaint() {

        stage.repaint();
        setDimensions(display.getDisplayRatio().getCurrentDimensions());
        stage.scaleTo(display.getDisplayRatio().getScale());
    }

    @Override
    public Dimensions getDimensions() {
        return new Dimensions(display.getContentPane().getWidth(), display.getContentPane().getHeight());
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
        display.setSize(Math.round(dimensions.getWidth()), Math.round(dimensions.getHeight()));
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

    public void createKeyListener() {

        nativeKeyListener = new NativeDisplayKeyListener(null);

        display.addKeyListener(nativeKeyListener);
    }

    public void setDisplayKeyHandler(final KeyboardInputHandler displayKeyHandler) {
        this.nativeKeyListener.setKeyboardHandler(displayKeyHandler);
    }

    public void setDisplayMouseHandler(final MouseInputHandler displayMouseHandler) {
        stage.setMouseHandler(displayMouseHandler);
    }

    public char getCurrentKey() {
        return nativeKeyListener.getCurrentKey();
    }

    public boolean isInputUp() {
        return nativeKeyListener.isInputUp();
    }

    public boolean isInputDown() {
        return nativeKeyListener.isInputDown();
    }

    public boolean isInputRight() {
        return nativeKeyListener.isInputRight();
    }

    public boolean isInputLeft() {
        return nativeKeyListener.isInputLeft();
    }

    public boolean isCloseRequested() {
        return display.isCloseRequested();
    }

    public Display getDisplay() {
        return display;
    }

    public Stage getStage() {
        return stage;
    }

    public void setResizeable(boolean resizeable) {
        display.setResizable(resizeable);
    }
}
