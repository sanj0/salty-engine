/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.WindowClosingHooks;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.input.MouseInputHandler;
import de.edgelord.saltyengine.io.serialization.DataWriter;
import de.edgelord.saltyengine.io.serialization.Species;
import de.edgelord.saltyengine.transform.Transform;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Usage: <br>
 * <code>RectangleCreator rc = RectangleCreator.init()</code>
 */
public class RectangleCreator extends DrawingRoutine implements MouseInputHandler {

    private static final Species rects = new Species("points");
    public static Color SAVED_TRANSFORMS_COLOR = ColorUtil.withAlpha(Color.RED, .25f);
    public static Color CURRENT_TRANSFORMS_COLOR = ColorUtil.withAlpha(Color.BLUE, .25f);
    private static DataWriter writer;
    private final List<Transform> savedTransforms = new CopyOnWriteArrayList<>();
    private final Transform origin = new Transform(0, 0, 30, 30);
    private final Transform upRight = new Transform(0, 0, 30, 30);
    private final Transform downLeft = new Transform(0, 0, 30, 30);
    private final Transform downRight = new Transform(0, 0, 30, 30);
    private Transform currentTransform = null;
    private boolean originMove = false;
    private boolean upRightMove = false;
    private boolean downLeftMove = false;
    private boolean downRightMove = false;

    RectangleCreator() {
        super(DrawingPosition.AFTER_GAMEOBJECTS);
    }

    public static RectangleCreator init() {

        final RectangleCreator rectangleCreator = new RectangleCreator();

        SceneManager.getCurrentScene().addDrawingRoutine(rectangleCreator);
        Input.addMouseInputHandler(rectangleCreator);

        try {
            writer = new DataWriter(SaltySystem.defaultOuterResource.getFileResource("rects.sdb"));
        } catch (final IOException e) {
            e.printStackTrace();
        }

        WindowClosingHooks.addShutdownHook(() -> {
            writer.addSpecies(rects);
            try {
                writer.syncFile();
                System.out.println("Rects have been written to " + writer.getFile().getAbsolutePath());
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        return rectangleCreator;
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(SAVED_TRANSFORMS_COLOR);

        for (final Transform transform : savedTransforms) {
            saltyGraphics.drawRect(transform);
        }

        saltyGraphics.setColor(CURRENT_TRANSFORMS_COLOR);

        if (currentTransform != null) {
            saltyGraphics.drawRect(currentTransform);
        }

        saltyGraphics.drawRect(origin);
        saltyGraphics.drawRect(upRight);
        saltyGraphics.drawRect(downLeft);
        saltyGraphics.drawRect(downRight);
    }

    public void calculateCurrentTransform() {
        this.currentTransform = new Transform(origin.getCentre().getX(), origin.getCentre().getY(), upRight.getCentre().getX() - origin.getCentre().getX(), downLeft.getCentre().getY() - origin.getCentre().getY());
    }

    @Override
    public void mouseMoved(final MouseEvent e) {
    }

    @Override
    public void mouseDragged(final MouseEvent e) {

        if (originMove) {
            origin.positionByCentre(Input.getCursorPosition());
            upRight.setY(origin.getY());
            downLeft.setX(origin.getX());
        } else if (upRightMove) {
            upRight.positionByCentre(Input.getCursorPosition());
            origin.setY(upRight.getY());
            downRight.setX(upRight.getX());
        } else if (downLeftMove) {
            downLeft.positionByCentre(Input.getCursorPosition());
            origin.setX(downLeft.getX());
            downRight.setY(downLeft.getY());
        } else if (downRightMove) {
            downRight.positionByCentre(Input.getCursorPosition());
            upRight.setX(downRight.getX());
            downLeft.setY(downRight.getY());
        }

        calculateCurrentTransform();
    }

    @Override
    public void mousePressed(final MouseEvent e) {

        final Transform cursor = Input.getCursor();

        if (origin.contains(cursor)) {
            originMove = true;
        } else if (upRight.contains(cursor)) {
            upRightMove = true;
        } else if (downLeft.contains(cursor)) {
            downLeftMove = true;
        } else if (downRight.contains(cursor)) {
            downRightMove = true;
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {

        originMove = false;
        upRightMove = false;
        downLeftMove = false;
        downRightMove = false;
    }

    @Override
    public void mouseClicked(final MouseEvent e) {

        if (currentTransform == null || Input.getKeyboardInput().isSpace()) {
            final float x = Input.getCursorPosition().getX();
            final float y = Input.getCursorPosition().getY();

            origin.setX(x);
            origin.setY(y);

            upRight.setX(x + 100);
            upRight.setY(y);

            downRight.setX(x + 100);
            downRight.setY(y + 100);

            downLeft.setX(x);
            downLeft.setY(y + 100);
        } else if (e.isShiftDown() || e.isAltDown()) {
            savedTransforms.add(currentTransform);
            rects.addTag("rect" + savedTransforms.size(), currentTransform.getX() + "f, " + currentTransform.getY() + "f, " + currentTransform.getWidth() + "f, " + currentTransform.getHeight() + "f");
            currentTransform = null;
        }
    }

    @Override
    public void mouseExitedScreen(final MouseEvent e) {

    }

    @Override
    public void mouseEnteredScreen(final MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(final MouseEvent e) {

    }
}
