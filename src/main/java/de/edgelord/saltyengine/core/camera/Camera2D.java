/*
 * Copyright 2020 Malte Dostal
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

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Rotation;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.saltyengine.utils.ImageUtils;
import de.edgelord.saltyengine.utils.SaltySystem;

/**
 * The engine-default implementation of {@link Camera}.
 * <p>
 * It provides vision adjustment through:
 * <p>
 * <code>positioning</code> <br>
 * <code>rotation</code> <br>
 * <code>scaling ("zooming")</code> <br>
 * <code>resolution and size adjusting</code>
 */
public class Camera2D implements Camera, TransformedObject {

    // position, size and rotation
    private Transform transform;
    private Dimensions resolution;
    private float scale;

    private Directions lockedDirections = new Directions();

    public Camera2D(final Vector2f position, final Dimensions size, final Dimensions resolution, final float scale) {
        transform = new Transform(position, size);
        this.resolution = resolution;
        this.scale = scale;
    }

    @Override
    public SaltyImage render(final Drawable subject) {
        // create a new image with the resolution as the size to be scaled to the size later
        SaltyImage image = SaltySystem.createPreferredImage(getResolution().getWidth(), getResolution().getHeight());

        if (image == null) {
            throw new RuntimeException("couldn't receive preferred image from SaltySystem");
        }

        final SaltyGraphics graphics = new SaltyGraphics(image.createGraphics());
        graphics.setTransform(getAffineTransform());
        subject.draw(graphics);
        image = ImageUtils.resize(image, getSize().getWidth(), getSize().getHeight());

        return image;
    }

    /**
     * Moves the camera by the given amount
     * in the given direction in userspace.
     *
     * @param direction the {@link de.edgelord.saltyengine.utils.Directions.Direction} of the movement.
     *                  Actually, there is no camera movement simulated and all of the rendered entities will move
     *                  in this direction instead of the camera to move.
     * @param delta     the length of the movement in pixels.
     */
    public void move(final Directions.Direction direction, final float delta) {
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

    @Override
    public Transform getTransform() {
        return transform;
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
    public void setLockedDirections(final Directions directions) {
        this.lockedDirections = directions;
    }

    @Override
    public Vector2f getPosition() {
        return transform.getPosition();
    }

    @Override
    public void setPosition(final Vector2f position) {
        transform.setPosition(position);
    }

    @Override
    public Dimensions getSize() {
        return transform.getDimensions();
    }

    @Override
    public void setSize(final Dimensions size) {
        transform.setDimensions(size);
    }

    @Override
    public Dimensions getResolution() {
        return resolution;
    }

    @Override
    public void setResolution(final Dimensions resolution) {
        this.resolution = resolution;
    }

    @Override
    public Rotation getRotation() {
        return transform.getRotation();
    }

    @Override
    public void setRotation(final Rotation rotation) {
        transform.setRotation(rotation);
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(final float scale) {
        this.scale = scale;
    }
}
