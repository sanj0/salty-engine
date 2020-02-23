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

import de.edgelord.saltyengine.core.GraphicsConfiguration;
import de.edgelord.saltyengine.core.annotations.DefaultPlacement;
import de.edgelord.saltyengine.effect.geom.EnumShape;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.ImageUtils;

import java.awt.*;

@DefaultPlacement(method = DefaultPlacement.Method.TOP_LEFT_CORNER)
public class GradientLight extends Light {

    private SaltyImage light;
    private SaltyImage coloredLight;
    private EnumShape shape;

    private float[] arcIfRoundRect;

    public GradientLight(Transform transform, Color color, EnumShape shape, float... arcIfRoundRect) {
        super(transform, color);
        this.shape = shape;
        this.arcIfRoundRect = arcIfRoundRect;
        updateLightImage();
    }

    public GradientLight(Transform transform, EnumShape shape, float... arcIfRoundRect) {
        this(transform, ColorUtil.TRANSPARENT_COLOR, shape, arcIfRoundRect);
    }

    public GradientLight(Vector2f position, Dimensions dimensions, Color color, EnumShape shape, float... arcIfRoundRect) {
        this(new Transform(position, dimensions), color, shape, arcIfRoundRect);
    }

    public GradientLight(Vector2f position, Dimensions dimensions, EnumShape shape, float... arcIfRoundRect) {
        this(new Transform(position, dimensions), shape, arcIfRoundRect);
    }

    public GradientLight(float x, float y, float width, float height, Color color, EnumShape shape, float... arcIfRoundRect) {
        this(new Transform(x, y, width, height), color, shape, arcIfRoundRect);
    }

    public GradientLight(float x, float y, float width, float height, EnumShape shape, float... arcIfRoundRect) {
        this(new Transform(x, y, width, height), shape, arcIfRoundRect);
    }

    public GradientLight(Transform transform, Color color, float brightness, float intensity, EnumShape shape, float... arcIfRoundRect) {
        this(transform, color, shape, arcIfRoundRect);
        this.shape = shape;
        this.arcIfRoundRect = arcIfRoundRect;
    }

    @Override
    public void draw(Graphics2D graphics) {
        graphics.drawImage(light.getImage(), Math.round(getX()), Math.round(getY()), null);
    }

    @Override
    public void drawColorMap(Graphics2D graphics) {
        graphics.drawImage(coloredLight.getImage(), Math.round(getX()), Math.round(getY()), null);
    }

    public EnumShape getShape() {
        return shape;
    }

    public void setShape(EnumShape shape) {
        this.shape = shape;
    }

    @Override
    public void setTransform(Transform transform) {
        super.setTransform(transform);
        updateLightImage();
    }

    @Override
    public void setDimensions(Dimensions dimensions) {
        super.setDimensions(dimensions);
        updateLightImage();
    }

    @Override
    public void setPosition(Vector2f position) {
        super.setPosition(position);
        updateLightImage();
    }

    @Override
    public void setWidth(float width) {
        super.setWidth(width);
        updateLightImage();
    }

    @Override
    public void setHeight(float height) {
        super.setHeight(height);
        updateLightImage();
    }

    @Override
    public void setX(float x) {
        super.setX(x);
        updateLightImage();
    }

    @Override
    public void setY(float y) {
        super.setY(y);
        updateLightImage();
    }

    @Override
    public void setBrightness(float brightness) {
        super.setBrightness(brightness);
        updateLightImage();
    }

    @Override
    public void setIntensity(float intensity) {
        super.setIntensity(intensity);
        updateLightImage();
    }

    @Override
    public void setColor(Color color) {
        super.setColor(color);
        updateLightImage();
    }

    @Override
    public void setColorAlpha(int colorAlpha) {
        super.setColorAlpha(colorAlpha);
        updateLightImage();
    }

    public void updateLightImage() {
        light = ImageUtils.createPrimitiveGradient(shape, saltyGraphics -> {
        }, GraphicsConfiguration.renderingHints, getIntensity(), getDimensions(), arcIfRoundRect);
        coloredLight = ImageUtils.createPrimitiveGradient(shape, this::prepareGraphics, GraphicsConfiguration.renderingHints, getIntensity(), getColorAlpha(), getDimensions(), arcIfRoundRect);
    }
}
