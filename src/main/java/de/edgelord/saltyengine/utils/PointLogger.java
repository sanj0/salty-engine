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

import de.edgelord.saltyengine.core.WindowClosingHooks;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.MouseInputHandler;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.DataReader;
import de.edgelord.stdf.scripts.ReformatFile;
import de.edgelord.stdf.writing.DataWriter;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple static util class that allows the programmer to log points from the scene into a file to make placement
 * easier.
 * You click once to temporally save a point and click while shift or alt is down to safe the point.
 * When the game is quit, all saved points are written into a file.
 *
 */
public class PointLogger extends DrawingRoutine implements MouseInputHandler {

    private static Coordinates2f lastPoint = Coordinates2f.zero();
    private static List<Coordinates2f> savedPoints = new ArrayList<>();
    private static DataWriter writer;
    private static Species points = new Species("points");
    public static Dimensions POINTS_VISUALIZING_DIMENSIONS = new Dimensions(20, 20);
    public static Color SAVED_POINTS_VISUALIZING_COLOR = ColorUtil.changeAlpha(ColorUtil.BLUE, 0.5f);
    public static Color POINT_VISUALIZING_COLOR = ColorUtil.changeAlpha(ColorUtil.RED, 0.35f);

    private PointLogger(String fileName) throws IOException {
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
    public static PointLogger init(String fileName) throws IOException {

        PointLogger logger = new PointLogger(fileName + DataReader.SDB_FILE_EXTENSION);
        SceneManager.getCurrentScene().addDrawingRoutine(logger);
        Input.addMouseInputHandler(logger);
        WindowClosingHooks.addShutdownHook(() -> {
            writer.addSpecies(points);
            try {
                writer.syncFile();
                ReformatFile.reformat(writer.getFile());
                System.out.println("Points have been written to " + writer.getFile().getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return logger;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        if (lastPoint.getX() != 0 || lastPoint.getY() != 0) {
            saltyGraphics.setColor(POINT_VISUALIZING_COLOR);
            saltyGraphics.drawOval(visualizingTransform(lastPoint));
        }

        saltyGraphics.setColor(ColorUtil.BLUE);
        for (Coordinates2f coordinates2f : savedPoints) {
            saltyGraphics.drawOval(visualizingTransform(coordinates2f));
        }
    }

    private static Transform visualizingTransform(Coordinates2f point) {
        return new Transform(point.getX() - (POINTS_VISUALIZING_DIMENSIONS.getWidth() / 2f), point.getY() - (POINTS_VISUALIZING_DIMENSIONS.getHeight() / 2f), POINTS_VISUALIZING_DIMENSIONS.getWidth(), POINTS_VISUALIZING_DIMENSIONS.getHeight());
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if (e.isAltDown() || e.isShiftDown()) {
            int number = savedPoints.size();
            System.out.println("saving points " + lastPoint.toString() + " as point" + number);
            points.addTag("point" + number, lastPoint.getX() + ", " + lastPoint.getY());
            savedPoints.add(lastPoint);
        } else {
            lastPoint = Input.getCursorPosition();
            System.out.println("Temporally saved " + lastPoint.getX() + ", " +  lastPoint.getY() + " as the last point. Click while holding shift or alt to save it.");
        }
    }

    @Override
    public void mouseExitedScreen(MouseEvent e) {

    }

    @Override
    public void mouseEnteredScreen(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseEvent e) {

    }
}
