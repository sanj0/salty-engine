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

        Dimensions res = Game.getHost().getOriginalResolution();
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
