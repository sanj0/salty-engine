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

package de.edgelord.saltyengine.emitter.prc;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.emitter.Particle;
import de.edgelord.saltyengine.emitter.ParticleRenderContext;
import de.edgelord.saltyengine.utils.GeneralUtil;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * Adds for every {@link Particle} whose rendering process is prepared by this context a new profile to {@link #profiles}
 * with a new random {@link Color} from {@link #colors} and uses it for each next request for this particle.
 * -- every <code>Particle</code> has a random <code>Color</code> from {@link #colors}
 */
public class RandomColorProfileParticleRenderContext extends ParticleRenderContext {

    /**
     * The stored profiles for {@link Particle}s.
     */
    private HashMap<Particle, Color> profiles = new HashMap<>();

    /**
     * The colors to be used.
     */
    private List<Color> colors = new ArrayList<>();

    /**
     * Constructs this context with the {@link Color}s from the given vararg added to {@link #colors}
     * <p>
     * Examples:
     *
     * <pre>
     *     {@code
     *     // Initializes a new instance without any colors:
     *     RandomColorProfileParticleRenderContext context = new RandomColorProfileParticleRenderContext();
     *
     *     // You can also pass in as many colors as you like, separated with a comma each
     *     RandomColorProfileParticleRenderContext colorfulContext = new RandomColorProfileParticleRenderContext(Color.RED, Color.YELLOW, Color.BLUE);
     *     }
     * </pre>
     *
     * @param colors colors to be used, each separated with a comma
     */
    public RandomColorProfileParticleRenderContext(Color... colors) {
        super(Color.BLACK, Color.BLACK, new BasicStroke());

        this.colors.addAll(Arrays.asList(colors));
    }

    @Override
    public void nextParticleRenderConfig(SaltyGraphics graphics, Particle subject) {

        if (profiles.containsKey(subject)) {
            graphics.setColor(profiles.get(subject));
        } else {
            Color color = (Color) GeneralUtil.randomObjectFromList(colors);
            profiles.put(subject, color);
            graphics.setColor(color);
        }
    }

    public boolean addColor(Color color) {
        return colors.add(color);
    }

    public boolean removeColor(Color color) {
        return colors.remove(color);
    }

    public boolean addAllColors(Collection<? extends Color> colors) {
        return this.colors.addAll(colors);
    }

    public boolean addAllColors(int index, Collection<? extends Color> colors) {
        return this.colors.addAll(index, colors);
    }

    public boolean removeAllColors(Collection<?> colors) {
        return this.colors.removeAll(colors);
    }

    public void clearColors() {
        colors.clear();
    }
}
