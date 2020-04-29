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

package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.SaltySystem;

public class ScreenContent {

    private final SaltyImage bgImage = SaltySystem.defaultImageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");
    private float xOrigin;
    private float yOrigin;
    private Vector2f origin;

    public ScreenContent(float xOrigin, float yOrigin) {

        this.xOrigin = xOrigin;
        this.yOrigin = yOrigin;

        updateOrigin();
    }

    public void draw(SaltyGraphics graphics) {

        graphics.drawImage(bgImage, origin);
    }

    public void moveX(float delta) {
        xOrigin += delta;
        updateOrigin();
    }

    public void moveY(float delta) {
        yOrigin += delta;
        updateOrigin();
    }

    private void updateOrigin() {
        origin = new Vector2f(xOrigin, yOrigin);
    }
}
