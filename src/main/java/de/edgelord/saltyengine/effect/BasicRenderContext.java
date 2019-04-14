/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.effect;

import de.edgelord.saltyengine.core.graphics.RenderContext;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;

/**
 * A basic implementation of {@link RenderContext} with: <br>
 * - a{@link Color} <br>
 * - a {@link Paint} <br>
 * - a {@link Stroke} <br>
 * - a {@link #alpha} <br>
 * - and a {@link Font}
 */
public class BasicRenderContext implements RenderContext {

    /**
     * The Color with which to render. <br>
     * It may be set using {@link #setColor(Color)} and may be obtained using {@link #getColor()}
     */
    private Color color;

    /**
     * The Paint with which to render. <br>
     * It may be set using {@link #setPaint(Paint)} and may be obtained using {@link #getPaint()}
     */
    private Paint paint;

    /**
     * The Stroke to use for rendering. <br>
     * It may be set using {@link #setStroke(Stroke)} and may be obtained using {@link #getStroke()}
     */
    private Stroke stroke;

    /**
     * The alpha value of the color, separated for easier control. The value may range from 0f to 1f. <br>
     * It may be set using {@link #setAlpha(float)} and may be obtained using {@link #getAlpha()}
     */
    private float alpha = 1f;

    /**
     * The font to use. This is already pre-defined, as it may never vary over the whole rendering process. As well, this is excluded from the {@link #applyConfiguration(SaltyGraphics)} implementation. <br>
     * It may be set using {@link #setFont(Font)} and may be obtained using {@link #getFont()}
     */
    private Font font = SaltySystem.defaultFont;

    /**
     * The constructor.
     *
     * @param color  the {@link Color} to render with
     * @param paint  the {@link Paint} to render with
     * @param stroke the {@link Stroke} to render with
     */
    public BasicRenderContext(Color color, Paint paint, Stroke stroke) {
        this.color = color;
        this.paint = paint;
        this.stroke = stroke;
    }

    /**
     * Returns the {@link #color} used by this {@link RenderContext} with its own alpha value instead of the {@link #alpha} value hold by this.
     * To get the {@link Color} with the correct alpha value, use {@link #getColorWithAlpha()}.
     *
     * @return the plain color used by this <code>RenderContext</code>.
     */
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

    /**
     * Sets the {@link #color} used by this <code>RenderContext</code> and the {@link #paint} to the given {@link Color}.
     *
     * @param color the new {@link #color} and {@link #paint}
     */
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
     * Applies the graphics configurations described by this context to the given graphics and returns previous one.
     *
     * @param graphics the graphics to configure.
     */
    public void applyConfiguration(SaltyGraphics graphics) {

        graphics.setColor(getColorWithAlpha());
        graphics.setPaint(paint);
        graphics.setStroke(stroke);
    }
}
