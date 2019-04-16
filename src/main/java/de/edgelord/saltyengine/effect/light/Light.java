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

package de.edgelord.saltyengine.effect.light;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public abstract class Light implements TransformedObject {

    /**
     * The brightness of the alpha-blend of this light, between 1f and 1f, with 0f being completely dark and 1f being max bright
     */
    private float brightness = .35f;

    /**
     * The intensity of this light, influences e.g. the gradient of a {@link GradientLight}
     */
    private float intensity = .1f;

    /**
     * The color of the light.
     */
    private Color color;

    /**
     * The start alpha value of the gradient with color being drawn over the light. 0 is min, 255 is max.
     */
    private int colorAlpha = 75;

    /**
     * The <code>Transform</code> of the light
     */
    private Transform transform;

    /**
     * The directions in which this light can't be moved.
     */
    private Directions lockedDirections = new Directions();

    public Light(Transform transform, Color color) {
        this.transform = transform;
        this.color = color;
    }

    public Light(Transform transform) {
        this.transform = transform;
        this.color = ColorUtil.TRANSPARENT_COLOR;
    }

    public Light(Vector2f position, Dimensions dimensions, Color color) {
        this(new Transform(position, dimensions), color);
    }

    public Light(Vector2f position, Dimensions dimensions) {
        this(new Transform(position, dimensions));
    }

    public Light(float x, float y, float width, float height, Color color) {
        this(new Transform(x, y, width, height), color);
    }

    public Light(float x, float y, float width, float height) {
        this(new Transform(x, y, width, height));
    }

    public void prepareGraphics(SaltyGraphics graphics) {
        graphics.setColor(color);
    }

    public abstract void draw(Graphics2D graphics);

    public abstract void drawColorMap(Graphics2D graphics);

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    @Override
    public Directions getLockedDirections() {
        return lockedDirections;
    }

    @Override
    public void setLockedDirections(Directions lockedDirections) {
        this.lockedDirections = lockedDirections;
    }

    public float getBrightness() {
        return brightness;
    }

    public void setBrightness(float brightness) {

        if (brightness < 0f || brightness > 1f) {
            throw new IllegalArgumentException("Brightness of a light has to be in between 0f and 1f!");
        }

        this.brightness = brightness;
    }

    public float getIntensity() {
        return intensity;
    }

    public void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getColorAlpha() {
        return colorAlpha;
    }

    public void setColorAlpha(int colorAlpha) {
        this.colorAlpha = colorAlpha;
    }
}
