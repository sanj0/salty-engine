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

package de.edgelord.saltyengine.effect;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Coordinates2f;

import java.awt.image.VolatileImage;

public class Frame implements Cosmetic {

    private SaltyImage image;
    //private AdvancedCosmetics advancedCosmetics = null;

    public Frame(SaltyImage image) {

        this.image = image;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, Coordinates2f position, float width, float height) {
        saltyGraphics.drawImage(image, position.getX(), position.getY(), width, height);
    }

    public SaltyImage getImage() {
        return image;
    }

    public void setImage(SaltyImage image) {
        this.image = image;
    }
}
