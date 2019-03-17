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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;

/**
 * A set of configurations on how to render certain objects.
 */
public abstract class RenderContext {

    /**
     * The Color with which to render.
     * It may be set using {@link #setColor(Color)} and may be obtained using {@link #getColor()}
     */
    private Color color;

    /**
     * The Paint with which to render.
     * It may be set using {@link #setPaint(Paint)} and may be obtained using {@link #getPaint()}
     */
    private Paint paint;

    /**
     * The Stroke to use for rendering.
     * It may be set using {@link #setStroke(Stroke)} and may be obtained using {@link #getStroke()}
     */
    private Stroke stroke;

    /**
     * The alpha value of the color, separated for easier control
     * It may be set using {@link #setAlpha(float)} and may be obtained using {@link #getAlpha()}
     */
    private float alpha = 1f;

    /**
     * The font to use. This is already pre-defined, as it may never vary over the whole rendering process.
     * It may be set using {@link #setFont(Font)} and may be obtained using {@link #getFont()}
     */
    private Font font = SaltySystem.defaultFont;

    public RenderContext(Color color, Paint paint, Stroke stroke) {
        this.color = color;
        this.paint = paint;
        this.stroke = stroke;
    }

    public Color getColor() {
        return color;
    }

    /**
     * Returns the {@link #color} with the correct {@link #alpha} value.
     *
     * @return the color used by this configuration with the correct alpha value
     */
    public Color getColorWithAlpha() {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), Math.round(alpha * 255f));
    }

    public void setColor(Color color) {
        this.color = color;
        this.paint = color;
    }

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public Stroke getStroke() {
        return stroke;
    }

    public void setStroke(Stroke stroke) {
        this.stroke = stroke;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    /**
     * Applies the graphics configurations described by this context to the given graphics.
     *
     * @param graphics the graphics to configure.
     */
    public void applyConfiguration(SaltyGraphics graphics) {
        graphics.setColor(getColorWithAlpha());
        graphics.setPaint(paint);
        graphics.setStroke(stroke);
    }
}
