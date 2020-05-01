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

package de.edgelord.saltyengine.effect.geom;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class SaltyShape implements Drawable, TransformedObject {

    private final EnumShape shapeType;
    private Transform transform;
    private boolean filled = true;

    private Directions lockedDirections = new Directions();

    public SaltyShape(final TransformedObject parent, final EnumShape shapeType) {
        this(parent.getTransform(), shapeType);
    }

    public SaltyShape(final Transform transform, final EnumShape shapeType) {
        this.shapeType = shapeType;
        this.transform = transform;
    }

    public SaltyShape(final Vector2f position, final Dimensions dimensions, final EnumShape shapeType) {
        this(new Transform(position, dimensions), shapeType);
    }

    public SaltyShape(final float x, final float y, final float width, final float height, final EnumShape shapeType) {
        this(new Transform(x, y, width, height), shapeType);
    }

    public static SaltyShape createShape(final EnumShape shapeType, final Transform transform, final float... arcIfRoundRect) {
        switch (shapeType) {

            case RECTANGLE:
                return new RectangleShape(transform);
            case OVAL:
                return new OvalShape(transform);
            case ROUND_RECTANGLE:
                if (arcIfRoundRect.length < 1) {
                    return new RoundRectShape(transform);
                } else {
                    return new RoundRectShape(transform, arcIfRoundRect[0]);
                }
            case LINE:
                return new LineShape(transform);
            case TRIANGLE:
                final Vector2f centre = transform.getCentre();
                return new TriangleShape(new Vector2f(transform.getX(), transform.getMaxY()), new Vector2f(centre.getX(), transform.getY()), new Vector2f(transform.getMaxX(), transform.getMaxY()));
        }

        return null;
    }

    @Override
    public abstract void draw(SaltyGraphics saltyGraphics);

    @Override
    public Transform getTransform() {
        return this.transform;
    }

    @Override
    public void setTransform(final Transform transform) {
        this.transform = transform;
    }

    @Override
    public Directions getLockedDirections() {
        return lockedDirections;
    }

    @Override
    public void setLockedDirections(final Directions lockedDirections) {
        this.lockedDirections = lockedDirections;
    }

    public void drawAtZero(final Graphics2D graphics) {
        final Vector2f pos = getPosition();
        setPosition(Vector2f.zero());
        draw(new SaltyGraphics(graphics));
        setPosition(pos);
    }

    public EnumShape getShapeType() {
        return shapeType;
    }

    public boolean isFilled() {
        return filled;
    }

    public void setFilled(final boolean filled) {
        this.filled = filled;
    }
}
