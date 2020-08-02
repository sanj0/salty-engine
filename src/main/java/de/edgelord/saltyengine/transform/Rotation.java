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

package de.edgelord.saltyengine.transform;

import java.util.Objects;

import static java.lang.Math.toDegrees;

/**
 * This class describes the rotation of an Object around the relative position
 * {@link #centre} by {@link #rotationDegrees} degrees.
 * <p>
 * TODO: make the transform move with the rotation using this formula: xRelative
 * = xCorner - xCenterRectangle; yRelative = yCorner - yCenterRectangle;
 * <p>
 * rad = angle / 180 * math.PI;
 * <p>
 * px = xRelative  * math.cos(rad) - yRelative * math.sin(rad); py = xRelative *
 * math.sin(rad) + yRelative * math.cos(rad);
 * <p>
 * xNew = px + xCenterRectangle; yNew = py + yCenterRectangle;
 */
public class Rotation {
    private Vector2f centre;
    private float rotationDegrees;

    public Rotation(final Vector2f centre, final float rotationDegrees) {
        this.centre = centre;
        this.rotationDegrees = rotationDegrees;
    }

    public Rotation(final Vector2f centre) {
        this(centre, 0f);
    }

    public Rotation(final float centreX, final float centreY, final float rotationDegrees) {
        this(new Vector2f(centreX, centreY), rotationDegrees);
    }

    public Rotation(final float centreX, final float centreY) {
        this(centreX, centreY, 0f);
    }

    public Rotation() {
        this(0, 0);
    }

    public static float getNormalizedDegrees(float degrees) {

        if (degrees < 0) {
            while (degrees <= -360) {
                degrees += 360;
            }
        } else {
            while (degrees >= 360) {
                degrees -= 360;
            }
        }

        return degrees;
    }

    public void rotateToPoint(final Vector2f point, final Transform parent) {
        final Vector2f absCentre = getCentreAbsolute(parent);
        final double arc = Math.atan2(point.getY() - (absCentre.getY()), point.getX() - (absCentre.getX()));
        rotationDegrees = (float) toDegrees(arc);
    }

    public void rotateToPoint(final float x, final float y, final Transform parent) {
        rotateToPoint(new Vector2f(x, y), parent);
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Rotation) {
            final Rotation other = (Rotation) obj;
            return other.getCentre().equals(getCentre())
                    && getNormalizedDegrees(other.getRotationDegrees()) == getNormalizedDegrees(getRotationDegrees());
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(centre, rotationDegrees);
    }

    public Vector2f getCentreAbsolute(final Transform parent) {
        return new Vector2f(centre.getX() + parent.getX(), centre.getY() + parent.getY());
    }

    public Vector2f getCentre() {
        return centre;
    }

    public void setCentre(final Vector2f centre) {
        this.centre = centre;
    }

    public float getRotationDegrees() {

        while (rotationDegrees > 360) {
            rotationDegrees -= 360;
        }

        return rotationDegrees;
    }

    public void setRotationDegrees(final float rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "centre=" + centre +
                ", rotationDegrees=" + rotationDegrees +
                '}';
    }
}