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

package de.edgelord.saltyengine.camera;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Camera {

    private Vector2f position = new Vector2f(0f, 0f);
    // The rotation in degrees.
    private float rotation = 0f;
    /**
     * The scale of the view. 1f means no scaling, 2f would be twice as big
     */
    private float scale = 1f;

    private AffineTransform originalTransform = null;

    private Vector2f lastPosition = new Vector2f(0f, 0f);

    public Camera() {
    }

    public void setViewToGraphics(Graphics2D graphics) {
        if (originalTransform == null) {
            originalTransform = graphics.getTransform();
        }

        graphics.translate(lastPosition.getX() + position.getX(), lastPosition.getY() + position.getY());
        graphics.rotate(Math.toRadians(rotation), Game.getGameWidth() / 2, Game.getGameHeight() / 2);
        graphics.scale(scale, scale);
    }

    public void tmpResetViewToGraphics(SaltyGraphics graphics) {
        graphics.getGraphics2D().rotate(0);
        graphics.getGraphics2D().translate(getX() * -1, getY() * -1);
        graphics.getGraphics2D().scale(1f, 1f);
    }

    public void move(Directions.Direction direction, float delta) {
        switch (direction) {

            case RIGHT:
                setX(getX() + delta);
                break;
            case LEFT:
                setX(getX() - delta);
                break;
            case UP:
                setY(getY() - delta);
                break;
            case DOWN:
                setY(getY() + delta);
                break;
        }
    }

    public Vector2f getAbsolutePosition(Vector2f relativePosition) {
        Vector2f absPos = new Vector2f(relativePosition.getX(), relativePosition.getY());
        absPos.subtract(getPosition());

        return absPos;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public Transform getRelativeCursor() {
        return new Transform(getRelativeCursorPosition(), Dimensions.one());
    }

    public Vector2f getRelativeCursorPosition() {
        return getAbsolutePosition(Input.getCursorPosition());
    }

    public void setPosition(Vector2f position) {
        this.position = position;
    }

    public void setX(float x) {
        position.setX(x);
    }

    public void setY(float y) {
        position.setY(y);
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Vector2f getPosition() {
        return position;
    }

    public float getX() {
        return position.getX();
    }

    public float getY() {
        return position.getY();
    }

    public float getRotation() {
        return rotation;
    }
}
