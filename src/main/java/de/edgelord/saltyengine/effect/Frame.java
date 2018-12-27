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

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.image.BufferedImage;

public class Frame implements Cosmetic {

    private BufferedImage image = null;
    //private AdvancedCosmetics advancedCosmetics = null;

    public Frame(BufferedImage image) {

        this.image = image;
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics, Vector2f position, float width, float height) {
        saltyGraphics.drawImage(image, position.getX(), position.getY(), width, height);
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;
    }
}