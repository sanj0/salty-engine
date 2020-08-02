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

package de.edgelord.saltyengine.graphics.sprite;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.graphics.Cosmetic;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;

@Deprecated
public class Frame implements Cosmetic {

    private SaltyImage image;
    //private AdvancedCosmetics advancedCosmetics = null;

    public Frame(final SaltyImage image) {

        this.image = image;
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics, final Vector2f position, final float width, final float height) {
        image.draw(saltyGraphics, position, width, height);

        // where does this come from and what does it do?!
        final SaltyImage img = SaltySystem.createPreferredImage(500, 500);

        final SaltyGraphics graphics = new SaltyGraphics(img.createGraphics());
        image.draw(graphics.copy(), Vector2f.zero());
    }

    public SaltyImage getImage() {
        return image;
    }

    public void setImage(final SaltyImage image) {
        this.image = image;
    }
}
