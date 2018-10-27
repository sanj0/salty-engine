/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package de.edgelord.saltyengine.camera;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;
import java.awt.geom.AffineTransform;

public class Camera {

    private Vector2f position = new Vector2f(0f, 0f);
    // The rotation in degrees.
    private float rotation = 0f;

    private AffineTransform originalTransform = null;

    private Vector2f lastPosition = new Vector2f(0f, 0f);

    public Camera() {
    }

    public void setViewToGraphics(Graphics2D graphics) {
        if (originalTransform == null) {
            originalTransform = graphics.getTransform();
        }

        graphics.translate(lastPosition.getX() + position.getX(), lastPosition.getY() + position.getY());
        graphics.rotate(Math.toRadians(rotation), Game.getHost().getWidth() / 2, Game.getHost().getHeight() / 2);
    }

    public void tmpResetViewToGraphics(SaltyGraphics graphics) {
        graphics.setTransform(originalTransform);
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
