/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject;

import java.awt.*;

public abstract class DrawingRoutine {

    public enum DrawingPosition {BEFORE_GAMEOBJECTS, AFTER_GAMEOBJECTS}

    private DrawingPosition drawingPosition;

    public DrawingRoutine(DrawingPosition drawingPosition) {
        this.drawingPosition = drawingPosition;
    }

    public abstract void draw(Graphics2D graphics2D);

    public DrawingPosition getDrawingPosition() {
        return drawingPosition;
    }

    public void setDrawingPosition(DrawingPosition drawingPosition) {
        this.drawingPosition = drawingPosition;
    }
}
