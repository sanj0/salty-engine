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

package de.edgelord.saltyengine.graphics.light;

import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.graphics.GraphicsConfiguration;
import de.edgelord.saltyengine.graphics.geom.EnumShape;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
//fixme: insanely bad performance - gradient creation sucks!!
public class GradientLight extends Light {

    private final float[] arcIfRoundRect;
    protected SaltyImage light;
    protected SaltyImage coloredLight;
    private EnumShape shape;

    public GradientLight(final Transform transform, final Color color, final EnumShape shape, final float... arcIfRoundRect) {
        super(transform, color);
        this.shape = shape;
        this.arcIfRoundRect = arcIfRoundRect;
        updateLightImage();
    }

    public GradientLight(final Transform transform, final EnumShape shape, final float... arcIfRoundRect) {
        this(transform, ColorUtil.TRANSPARENT_COLOR, shape, arcIfRoundRect);
    }

    public GradientLight(final Vector2f position, final Dimensions dimensions, final Color color, final EnumShape shape, final float... arcIfRoundRect) {
        this(new Transform(position, dimensions), color, shape, arcIfRoundRect);
    }

    public GradientLight(final Vector2f position, final Dimensions dimensions, final EnumShape shape, final float... arcIfRoundRect) {
        this(new Transform(position, dimensions), shape, arcIfRoundRect);
    }

    public GradientLight(final float x, final float y, final float width, final float height, final Color color, final EnumShape shape, final float... arcIfRoundRect) {
        this(new Transform(x, y, width, height), color, shape, arcIfRoundRect);
    }

    public GradientLight(final float x, final float y, final float width, final float height, final EnumShape shape, final float... arcIfRoundRect) {
        this(new Transform(x, y, width, height), shape, arcIfRoundRect);
    }

    public GradientLight(final Transform transform, final Color color, final float brightness, final float intensity, final EnumShape shape, final float... arcIfRoundRect) {
        this(transform, color, shape, arcIfRoundRect);
        setBrightness(brightness);
        setIntensity(intensity);
    }

    @Override
    public void draw(final Graphics2D graphics) {
        graphics.drawImage(light.getImage(), Math.round(getX()), Math.round(getY()), null);
    }

    @Override
    public void drawColorMap(final Graphics2D graphics) {
        graphics.drawImage(coloredLight.getImage(), Math.round(getX()), Math.round(getY()), null);
    }

    public void updateLightImage() {
        light = ImageUtils.createPrimitiveGradient(shape, saltyGraphics -> {
        }, GraphicsConfiguration.renderingHints, getIntensity(), getDimensions(), arcIfRoundRect);
        coloredLight = ImageUtils.createPrimitiveGradient(shape, this::prepareGraphics, GraphicsConfiguration.renderingHints, getIntensity(), getColorAlpha(), getDimensions(), arcIfRoundRect);
    }

    public EnumShape getShape() {
        return shape;
    }

    public void setShape(final EnumShape shape) {
        this.shape = shape;
    }

    @Override
    public void setTransform(final Transform transform) {
        super.setTransform(transform);
        updateLightImage();
    }

    @Override
    public void setDimensions(final Dimensions dimensions) {
        super.setDimensions(dimensions);
        updateLightImage();
    }

    @Override
    public void setPosition(final Vector2f position) {
        super.setPosition(position);
        updateLightImage();
    }

    @Override
    public void setWidth(final float width) {
        super.setWidth(width);
        updateLightImage();
    }

    @Override
    public void setHeight(final float height) {
        super.setHeight(height);
        updateLightImage();
    }

    @Override
    public void setX(final float x) {
        super.setX(x);
        updateLightImage();
    }

    @Override
    public void setY(final float y) {
        super.setY(y);
        updateLightImage();
    }

    @Override
    public void setBrightness(final float brightness) {
        super.setBrightness(brightness);
        updateLightImage();
    }

    @Override
    public void setIntensity(final float intensity) {
        super.setIntensity(intensity);
        updateLightImage();
    }

    @Override
    public void setColor(final Color color) {
        super.setColor(color);
        updateLightImage();
    }

    @Override
    public void setColorAlpha(final int colorAlpha) {
        super.setColorAlpha(colorAlpha);
        updateLightImage();
    }
}
