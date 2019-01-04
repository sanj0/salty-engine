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

package de.edgelord.saltyengine.core.camera;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * Class which controls how entities are rendered onto the screen.
 *
 * <p>
 * You can manipulate that by using the {@link #position} of the camera. You can edit that value by
 * using the methods {@link #move(Directions.Direction, float)} and {@link #setPosition(Coordinates2f)}.
 *
 * <p>
 * You can also manipulate the manner of entity rendering with the {@link #rotation} of the camera.
 * For that, you can use the method {@link #setRotation(float)}
 *
 * <p>
 * The instance of this class that is useful for the user is {@link Game#getCamera()} which can be accessed by using
 * {@link Game#getCamera()}
 */
public class Camera {

    /**
     * The position of the camera in user-space. This is only a virtual position because there is no really existing
     * camera in the scene.
     *
     * @see #move(Directions.Direction, float)
     */
    private Coordinates2f position = new Coordinates2f(0f, 0f);

    /**
     * The rotation of the camera in degrees
     */
    private float rotation = 0f;

    private AffineTransform originalTransform = null;

    private Coordinates2f lastPosition = new Coordinates2f(0f, 0f);

    /**
     * The standard constructor.
     */
    public Camera() {
    }

    /**
     * Sets the view of this camera to the given {@link Graphics2D}
     *
     * @param graphics the {@link Graphics2D} to be manipulated
     */
    public void setViewToGraphics(Graphics2D graphics) {
        if (originalTransform == null) {
            originalTransform = graphics.getTransform();
        }

        graphics.translate(lastPosition.getX() + position.getX(), lastPosition.getY() + position.getY());
        graphics.rotate(Math.toRadians(rotation), Game.getGameWidth() / 2, Game.getGameHeight() / 2);
    }

    /**
     * Resets the view of the given {@link SaltyGraphics} to a rotation of 0 and a position of 0|0.
     * This only works properly when the {@link Graphics2D} was manipulated by {@link #setViewToGraphics(Graphics2D)}
     * before.
     *
     * @param graphics the {@link Graphics2D} to reset
     */
    public void tmpResetViewToGraphics(SaltyGraphics graphics) {
        graphics.getGraphics2D().rotate(0);
        graphics.getGraphics2D().translate(getX() * -1, getY() * -1);
    }

    /**
     * Moves the camera.
     *
     * @param direction the {@link de.edgelord.saltyengine.utils.Directions.Direction} of the movement.
     *                  Actually, there is no camera movement simulated and all of the rendered entities will move
     *                  in this direction instead of the camera to move.
     * @param delta     the length of the movement in pixels.
     */
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

    /**
     * Returns the position relative to the position of the camera.
     * Example:
     *
     * <pre>
     *     {@code
     *     // Create a Coordinates2f at 100|100
     *     Coordinates2f absolutePosition = new Coordinates2f(100, 100);
     *
     *     // Moves the camera to 10|10
     *     Game.getCamera().setPosition(10, 10);
     *
     *     // The Coordinates2f relativePosition would be at 90|90
     *     // so that when you draw a GameObject at this position with the camera being moved to 10|10,
     *     // it still has those original 100|100 relative to the window.
     *     Coordinates2f relativePosition = Game.getCamera().getRelativePosition(absolutePosition);
     *     }
     * </pre>
     *
     * @param absolutePosition the position relative to
     * @return the absolute position with considering the position of this camera for the given relative position
     */
    public Coordinates2f getRelativePosition(Coordinates2f absolutePosition) {
        Coordinates2f absPos = new Coordinates2f(absolutePosition.getX(), absolutePosition.getY());
        absPos.subtract(getPosition());

        return absPos;
    }

    /**
     * Returns the cursor as a {@link Transform} manipulated in a way that it fits the camera position.
     * You can obtain this value easier by using {@link Input#getCursor()}
     *
     * @return the cursor with the position of this camera considered.
     * @see #getRelativePosition(Coordinates2f)
     */
    public Transform getRelativeCursor() {
        return new Transform(getRelativeCursorPosition(), Dimensions.one());
    }

    /**
     * Returns the cursor position relative to the camera position.
     * You can obtain this value easier by using {@link Input#getCursorPosition()} ()}.
     *
     * @return the cursor position manipulated in a way that it fits the position of this camera.
     */
    public Coordinates2f getRelativeCursorPosition() {
        return getRelativePosition(Input.getCursorPosition());
    }

    /**
     * Sets the position of this virtual camera in the scene.
     *
     * @param position the new position of this camera
     * @see #move(Directions.Direction, float)
     */
    public void setPosition(Coordinates2f position) {
        this.position = position;
    }

    /**
     * Sets the position of this virtual camera in the scene.
     *
     * @param x the x position of this camera
     * @param y the y position of this camera
     */
    public void setPosition(float x, float y) {
        this.position = new Coordinates2f(x, y);
    }

    public void setX(float x) {
        position.setX(x);
    }

    public void setY(float y) {
        position.setY(y);
    }

    /**
     * Sets the rotation of this camera in degrees.
     *
     * @param rotation the rotation degrees of this camera.
     */
    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    public Coordinates2f getPosition() {
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
