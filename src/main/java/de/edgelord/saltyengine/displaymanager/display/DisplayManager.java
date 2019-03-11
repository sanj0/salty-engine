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

package de.edgelord.saltyengine.displaymanager.display;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.Host;
import de.edgelord.saltyengine.displaymanager.stage.Stage;
import de.edgelord.saltyengine.input.KeyboardInputHandler;
import de.edgelord.saltyengine.input.MouseInputHandler;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class DisplayManager extends Host {

    private final Display display;
    private final Stage stage;
    private NativeDisplayKeyListener nativeKeyListener;

    public DisplayManager(DisplayRatio displayRatio, final String gameName, final Engine engine) {

        display = new Display(displayRatio, gameName);
        stage = new Stage(display.getContentPane(), engine);

        Game.gameName = gameName;
    }

    @Override
    public void create() {

        display.create();
        createKeyListener();
    }

    @Override
    public float getHorizontalCentrePosition(final float width) {
        return (Game.getGameWidth() / 2) - (width / 2);
    }

    @Override
    public float getVerticalCentrePosition(final float height) {
        return (Game.getGameHeight() / 2) - (height / 2);
    }

    @Override
    public void repaint() {

        stage.repaint();
        //setDimensions(display.getDisplayRatio().getCurrentDimensions());
    }

    @Override
    public Dimensions getCurrentDimensions() {
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
    public ImageObserver getImageObserver() {
        return stage;
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        display.setSize(Math.round(dimensions.getWidth()), Math.round(dimensions.getHeight()));
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
        display.setFullscreen(!display.isFullscreen());
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
