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

package de.edgelord.saltyengine.cosmetic.light;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.*;

/**
 * This {@link LightSystem} is much more efficient because it is not updated over and over again but only when requested
 * via {@link #scheduleUpdate()}
 */
public class StaticLightSystem extends LightSystem {

    private boolean scheduleUpdate = true;

    public StaticLightSystem(Color lightMapColor) {
        super(lightMapColor);
    }

    public StaticLightSystem() {
        super();
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {
        if (scheduleUpdate) {
            updateLightMap();
            scheduleUpdate = false;
        }

        saltyGraphics.drawImage(lightMap, Vector2f.zero());
    }

    public void scheduleUpdate() {
        scheduleUpdate = true;
    }
}
