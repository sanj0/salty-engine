/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
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

package de.edgelord.saltyengine.cosmetic.light;

import de.edgelord.saltyengine.core.interfaces.TransformedObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

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
     * The <code>Transform</code> of the light
     */
    private Transform transform;

    public Light(Transform transform, Color color) {
        this.transform = transform;
        this.color = color;
    }

    public Light(Transform transform) {
        this.transform = transform;
        this.color = Color.white;
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

    @Override
    public Transform getTransform() {
        return transform;
    }

    @Override
    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
