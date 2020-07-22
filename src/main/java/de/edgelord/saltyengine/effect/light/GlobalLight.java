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

package de.edgelord.saltyengine.effect.light;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;

/**
 * An implementation of {@link Light} that emits
 * light to every pixel of the game. The intensity
 * controls nothing in this implementation.
 */
public class GlobalLight extends Light {

    public GlobalLight(final Color color) {
        super(Game.getGameTransform(), color);
    }

    public GlobalLight() {
        super(Game.getGameTransform());
    }

    public GlobalLight(final Color color, final float brightness) {
        super(Game.getGameTransform(), color, brightness, 0f);
    }

    @Override
    public void draw(final Graphics2D graphics) {
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, Math.round(Game.getGameWidth()), Math.round(Game.getGameHeight()));
    }

    @Override
    public void drawColorMap(final Graphics2D graphics) {
        graphics.setColor(ColorUtil.withAlpha(getColor(), getColorAlpha()));
        graphics.fillRect(0, 0, Math.round(Game.getGameWidth()), Math.round(Game.getGameHeight()));
    }

    @Override
    public void setIntensity(final float intensity) {
        super.setIntensity(intensity);
        System.out.printf("[WARNING]: %s", "setting the intensity of a GlobalLight has no effect!");
    }
}
