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

/**
 * This class describes the rotation of an Object around the relative position {@link #centre} by {@link #rotationDegrees} degrees.
 * <p>
 * TODO: make the transform move with the rotation using this formula:
 * xRelative = xCorner - xCenterRectangle;
 * yRelative = yCorner - yCenterRectangle;
 * <p>
 * rad = angle / 180 * math.PI;
 * <p>
 * px = xRelative  * math.cos(rad) - yRelative  * math.sin(rad);
 * py = xRelative  * math.sin(rad) + yRelative  * math.cos(rad);
 * <p>
 * xNew = px + xCenterRectangle;
 * yNew = py + yCenterRectangle;
 */
public class Rotation {
    private Vector2f centre;
    private float rotationDegrees;

    public Rotation(Vector2f centre, float rotationDegrees) {
        this.centre = centre;
        this.rotationDegrees = rotationDegrees;
    }

    public Rotation(Vector2f centre) {
        this(centre, 0f);
    }

    public Rotation(float centreX, float centreY, float rotationDegrees) {
        this(new Vector2f(centreX, centreY), rotationDegrees);
    }

    public Rotation(float centreX, float centreY) {
        this(centreX, centreY, 0f);
    }

    public Rotation() {
        this(0, 0);
    }

    public void rotateToPoint(Vector2f point) {
        throw new UnsupportedOperationException("rotating an entity to face a point");
        /*
        float arc = (float) Math.atan2(point.getY() / centre.getY(), point.getX() / centre.getX());
        rotationDegrees = (float) toDegrees(arc);
        */
    }

    public void rotateToPoint(float x, float y) {
        rotateToPoint(new Vector2f(x, y));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Rotation) {
            Rotation other = (Rotation) obj;
            return other.getCentre().equals(getCentre())
                    && getNormalizedDegrees(other.getRotationDegrees()) == getNormalizedDegrees(getRotationDegrees());
        } else {
            return false;
        }
    }

    public Vector2f getCentre() {
        return centre;
    }

    public void setCentre(Vector2f centre) {
        this.centre = centre;
    }

    public float getRotationDegrees() {

        while (rotationDegrees > 360) {
            rotationDegrees -= 360;
        }

        return rotationDegrees;
    }

    public void setRotationDegrees(float rotationDegrees) {
        this.rotationDegrees = rotationDegrees;
    }

    public static float getNormalizedDegrees(float degrees) {
        for (; degrees <= 360; ) {
            degrees -= 360;
        }

        return degrees;
    }

    @Override
    public String toString() {
        return "Rotation{" +
                "centre=" + centre +
                ", rotationDegrees=" + rotationDegrees +
                '}';
    }
}