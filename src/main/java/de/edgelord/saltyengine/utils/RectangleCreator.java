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
import de.edgelord.saltyengine.io.FileWriter;
import de.edgelord.saltyengine.io.serialization.DataWriter;
import de.edgelord.saltyengine.io.serialization.Species;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

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
    private static final StringBuilder simpleRepresentation = new StringBuilder();
    public static Color SAVED_TRANSFORMS_COLOR = ColorUtil.withAlpha(Color.RED, .25f);
    public static Color CURRENT_TRANSFORMS_COLOR = ColorUtil.withAlpha(Color.BLUE, .25f);
    /**
     * A format string for the simple representation of the logged rects <p>
     * <pre>
     * %x - x
     * %y - y
     * %w - width
     * %h - height
     * </pre>
     */
    public static String format = "new Transform(%x, %y, %w, %h)";
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
    private boolean rectangleMove = false;
    private Vector2f dragStart = Vector2f.zero();

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
                new FileWriter(SaltySystem.defaultOuterResource.getFileResource("rects_formatted.txt")).writeThrough(simpleRepresentation.toString());
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
            origin.setPosition(dragStart.subtracted(Input.getCursorPosition()));
            upRight.setY(origin.getY());
            downLeft.setX(origin.getX());
        } else if (upRightMove) {
            upRight.setPosition(dragStart.subtracted(Input.getCursorPosition()));
            origin.setY(upRight.getY());
            downRight.setX(upRight.getX());
        } else if (downLeftMove) {
            downLeft.setPosition(dragStart.subtracted(Input.getCursorPosition()));
            origin.setX(downLeft.getX());
            downRight.setY(downLeft.getY());
        } else if (downRightMove) {
            downRight.setPosition(dragStart.subtracted(Input.getCursorPosition()));
            upRight.setX(downRight.getX());
            downLeft.setY(downRight.getY());
        } else if (rectangleMove) {
            currentTransform.setPosition(dragStart.subtracted(Input.getCursorPosition()));
            origin.positionByCentre(currentTransform.getPosition());
            upRight.positionByCentre(new Vector2f(currentTransform.getMaxX(), currentTransform.getY()));
            downLeft.positionByCentre(new Vector2f(currentTransform.getX(), currentTransform.getMaxY()));
            downRight.positionByCentre(new Vector2f(currentTransform.getMaxX(), currentTransform.getMaxY()));
        }

        calculateCurrentTransform();
    }

    @Override
    public void mousePressed(final MouseEvent e) {

        if (currentTransform != null) {
            final Transform cursor = Input.getCursor();

            if (origin.contains(cursor)) {
                originMove = true;
                dragStart = cursor.getPosition().subtracted(origin.getPosition());
            } else if (upRight.contains(cursor)) {
                upRightMove = true;
                dragStart = cursor.getPosition().subtracted(upRight.getPosition());
            } else if (downLeft.contains(cursor)) {
                downLeftMove = true;
                dragStart = cursor.getPosition().subtracted(downLeft.getPosition());
            } else if (downRight.contains(cursor)) {
                downRightMove = true;
                dragStart = cursor.getPosition().subtracted(downRight.getPosition());
            } else if (currentTransform.contains(cursor)) {
                rectangleMove = true;
                dragStart = cursor.getPosition().subtracted(currentTransform.getPosition());
            }
        }
    }

    @Override
    public void mouseReleased(final MouseEvent e) {
        originMove = false;
        upRightMove = false;
        downLeftMove = false;
        downRightMove = false;
        rectangleMove = false;
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
            simpleRepresentation.append(format()).append("\n");
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

    private String format() {
        return format.replaceAll("%x", currentTransform.getX() + "f")
                .replaceAll("%y", currentTransform.getY() + "f")
                .replaceAll("%w", currentTransform.getWidth() + "f")
                .replaceAll("%h", currentTransform.getHeight() + "f");
    }
}
