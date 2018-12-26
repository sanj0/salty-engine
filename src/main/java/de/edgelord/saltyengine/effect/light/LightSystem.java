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

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class LightSystem implements Drawable {

    private Color lightMapColor;
    protected BufferedImage lightMap;
    private List<Light> lights = new ArrayList<>();

    public LightSystem(Color lightMapColor) {
        this.lightMapColor = lightMapColor;

        Dimensions res = Game.getGameDimensions();
        lightMap = new BufferedImage((int) res.getWidth(), (int) res.getHeight(), BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * A overload constructor setting {@link #lightMapColor} to plain black
     */
    public LightSystem() {
        this(Color.BLACK);
    }

    public void addLight(Light light) {
        lights.add(light);
    }

    public void removeLight(Light light) {
        lights.remove(light);
    }

    public int getLightCount() {
        return lights.size();
    }

    protected void updateLightMap() {
        Graphics2D graphics = lightMap.createGraphics();
        graphics.setBackground(ColorUtil.TRANSPARENT_COLOR);
        graphics.clearRect(0, 0, lightMap.getWidth(), lightMap.getHeight());
        graphics.setColor(lightMapColor);
        graphics.fillRect(0, 0, lightMap.getWidth(), lightMap.getHeight());

        graphics.setRenderingHints(Game.getHost().getRenderHints());

        Composite oldComp = graphics.getComposite();

        lights.forEach(light -> {
            graphics.setComposite(oldComp);
            light.drawColorMap(graphics);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT, light.getBrightness()));
            light.draw(graphics);
        });
        graphics.dispose();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        updateLightMap();
        saltyGraphics.drawImage(lightMap, Vector2f.zero());
    }
}
