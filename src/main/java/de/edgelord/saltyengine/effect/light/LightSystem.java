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
import de.edgelord.saltyengine.core.GraphicsConfiguration;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.core.interfaces.Drawable;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Dimensions;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LightSystem implements Drawable {

    private final Color lightMapColor;
    //private BufferedImage plainLightMap;
    private final List<Light> lights = new ArrayList<>();
    protected SaltyImage lightMap;

    public LightSystem(final Color lightMapColor) {
        this.lightMapColor = lightMapColor;

        final Dimensions res = Game.getGameDimensions();
        lightMap = SaltySystem.createPreferredImage(res.getWidth(), res.getHeight());
        //plainLightMap = new BufferedImage((int) res.getWidth() + 500, (int) res.getHeight() + 500, BufferedImage.TYPE_INT_ARGB);
    }

    /**
     * A overload constructor setting {@link #lightMapColor} to plain black
     */
    public LightSystem() {
        this(Color.BLACK);
    }

    public void addLight(final Light light) {
        lights.add(light);
    }

    public void removeLight(final Light light) {
        lights.remove(light);
    }

    public int getLightCount() {
        return lights.size();
    }

    protected void updateLightMap() {
        //updatePlainLightMap();
        final Graphics2D graphics = drawBackgroundToImage(lightMap);
        graphics.setRenderingHints(GraphicsConfiguration.renderingHints);

        final Composite oldComp = graphics.getComposite();

        lights.forEach(light -> {
            graphics.setComposite(oldComp);
            light.drawColorMap(graphics);
            graphics.setComposite(AlphaComposite.getInstance(AlphaComposite.DST_OUT, light.getBrightness()));
            light.draw(graphics);
        });
        graphics.dispose();
    }

    private void updatePlainLightMap() {
        //Graphics2D graphics2D = drawBackgroundToImage(plainLightMap);
        //graphics2D.dispose();
    }

    private Graphics2D drawBackgroundToImage(final SaltyImage image) {
        final Graphics2D graphics2D = image.createGraphics();
        graphics2D.setBackground(ColorUtil.TRANSPARENT_COLOR);
        graphics2D.clearRect(0, 0, image.getWidth(), image.getHeight());
        graphics2D.setColor(lightMapColor);
        graphics2D.fillRect(0, 0, image.getWidth(), image.getHeight());

        return graphics2D;
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        updateLightMap();
        //saltyGraphics.drawImage(plainLightMap, Game.getCamera().getRelativePosition(new Vector2f(-250, -250)));
        saltyGraphics.drawImage(lightMap, Vector2f.zero());
    }

    /**
     * Gets {@link #lights}.
     *
     * @return the value of {@link #lights}
     */
    public List<Light> getLights() {
        return lights;
    }
}
