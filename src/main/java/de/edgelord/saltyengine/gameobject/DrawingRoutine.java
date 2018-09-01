/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.gameobject;

import de.edgelord.saltyengine.graphics.SaltyGraphics;

public abstract class DrawingRoutine {

    public enum DrawingPosition {BEFORE_GAMEOBJECTS, AFTER_GAMEOBJECTS}

    private DrawingPosition drawingPosition;

    public DrawingRoutine(DrawingPosition drawingPosition) {
        this.drawingPosition = drawingPosition;
    }

    public abstract void draw(SaltyGraphics saltyGraphics);

    public DrawingPosition getDrawingPosition() {
        return drawingPosition;
    }

    public void setDrawingPosition(DrawingPosition drawingPosition) {
        this.drawingPosition = drawingPosition;
    }
}
