/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 The Salty Engine Developer
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

package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.image.BufferedImage;

public class ScreenContent {

    private float xOrigin;
    private float yOrigin;

    private BufferedImage bgImage = StaticSystem.defaultImageFactory.getOptimizedImageResource("res/pictures/bg.png");

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
