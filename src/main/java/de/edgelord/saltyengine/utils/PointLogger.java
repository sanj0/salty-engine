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
import de.edgelord.saltyengine.io.serialization.DataReader;
import de.edgelord.saltyengine.io.serialization.DataWriter;
import de.edgelord.saltyengine.io.serialization.Species;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * A simple static util class that allows the programmer to log points from the scene into a file to make placement
 * easier.
 * You click once to temporally save a point and click while shift or alt is down to safe the point.
 * When the game is quit, all saved points are written into a file.
 */
public class PointLogger extends DrawingRoutine implements MouseInputHandler {

    private static final List<Vector2f> savedPoints = new ArrayList<>();
    private static final Species points = new Species("points");
    private static final Scanner scanner = new Scanner(System.in);
    public static Dimensions POINTS_VISUALIZING_DIMENSIONS = new Dimensions(20, 20);
    public static Color SAVED_POINTS_VISUALIZING_COLOR = ColorUtil.withAlpha(ColorUtil.BLUE, 0.5f);
    public static Color POINT_VISUALIZING_COLOR = ColorUtil.withAlpha(ColorUtil.RED, 0.35f);
    private static Vector2f lastPoint = Vector2f.zero();
    private static DataWriter writer;

    private PointLogger(final String fileName) throws IOException {
        super(DrawingPosition.AFTER_GAMEOBJECTS);

        writer = new DataWriter(SaltySystem.defaultOuterResource.getFileResource(fileName));
    }

    /**
     * Creates a new instance of this class, adds it to the current {@link de.edgelord.saltyengine.scene.Scene} as a
     * {@link DrawingRoutine}, assigns it as an input handler to {@link Input} and returns it.
     *
     * @param fileName the name of the save file, relative to {@link SaltySystem#defaultOuterResource}.
     * @return the created <code>PointLogger</code>.
     * @throws IOException if something does wrong when creating the {@link DataWriter}.
     */
    public static PointLogger init(final String fileName) throws IOException {

        final PointLogger logger = new PointLogger(fileName + DataReader.SDB_FILE_EXTENSION);
        SceneManager.getCurrentScene().addDrawingRoutine(logger);
        Input.addMouseInputHandler(logger);
        WindowClosingHooks.addShutdownHook(() -> {
            writer.addSpecies(points);
            try {
                writer.syncFile();
                System.out.println("Points have been written to " + writer.getFile().getAbsolutePath());
            } catch (final IOException e) {
                e.printStackTrace();
            }
        });

        return logger;
    }

    private static Transform visualizingTransform(final Vector2f point) {
        return new Transform(point.getX() - (POINTS_VISUALIZING_DIMENSIONS.getWidth() / 2f), point.getY() - (POINTS_VISUALIZING_DIMENSIONS.getHeight() / 2f), POINTS_VISUALIZING_DIMENSIONS.getWidth(), POINTS_VISUALIZING_DIMENSIONS.getHeight());
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        if (lastPoint.getX() != 0 || lastPoint.getY() != 0) {
            saltyGraphics.setColor(POINT_VISUALIZING_COLOR);
            saltyGraphics.drawOval(visualizingTransform(lastPoint));
        }

        saltyGraphics.setColor(SAVED_POINTS_VISUALIZING_COLOR);
        for (final Vector2f vector2F : savedPoints) {
            saltyGraphics.drawOval(visualizingTransform(vector2F));
        }
    }

    @Override
    public void mouseMoved(final MouseEvent e) {

    }

    @Override
    public void mouseDragged(final MouseEvent e) {

    }

    @Override
    public void mousePressed(final MouseEvent e) {

    }

    @Override
    public void mouseReleased(final MouseEvent e) {

    }

    @Override
    public void mouseClicked(final MouseEvent e) {

        if (e.isAltDown() || e.isShiftDown()) {
            System.out.println("Enter a name for point " + getPointIndicator(lastPoint) + " and press [ENTER]");
            final String name = scanner.nextLine();
            points.addTag(name, lastPoint.getX() + ", " + lastPoint.getY());
            savedPoints.add(lastPoint);
            System.out.println("Successfully added the point " + name + " to the save-queue. Exiting the game will automatically save them all.");
        } else {
            lastPoint = Input.getCursorPosition();
            System.out.println("Temporally saved " + getPointIndicator(lastPoint) + " as the last point. Click while holding shift or alt to save it.");
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

    private String getPointIndicator(final Vector2f point) {
        return "(" + (int) point.getX() + " | " + (int) point.getY() + ")";
    }
}
