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

package de.edgelord.saltyengine.cosmetic.geom;

import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.gameobject.DrawingRoutine;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * This class renders a specific {@link SaltyShape} with a specific {@link Stroke}, a specific {@link Paint} and a specific {@link Color}.
 *
 * These values can be assigned using their getters or by passing them into the constructor. All of these parameters are nullable
 * if you e.g. only want to draw the shape with a specific Color.
 */
public class ShapeRenderer extends DrawingRoutine implements TransformedObject {

    private SaltyShape shape;
    private Stroke stroke;
    private Paint paint;
    private Color color;

    private BufferedImage image;

    public ShapeRenderer(SaltyShape shape, Color color, Stroke stroke, Paint paint, DrawingPosition drawingPosition) {
        super(drawingPosition);

        this.shape = shape;
        this.color = color;
        this.stroke = stroke;
        this.paint = paint;

        updateImage();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        saltyGraphics.drawImage(image, 0, 0, 0, 0);
    }

    public void updateImage() {
        image = ImageUtils.createShapeImage(shape, getPrepareDrawable());
    }

    private Drawable getPrepareDrawable() {
        return saltyGraphics -> {

            if (stroke != null) {
                saltyGraphics.setStroke(stroke);
            }

            if (paint != null) {
                saltyGraphics.setPaint(paint);
            }

            if (color != null) {
                saltyGraphics.setColor(color);
            }
        };
    }

    @Override
    public Transform getTransform() {
        return null;
    }

    @Override
    public void setTransform(Transform transform) {

    }
}
