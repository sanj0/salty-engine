/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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

package de.edgelord.saltyengine.cosmetic.lighting;

import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Light {

    private BufferedImage image;
    private Shape shape;

    public Light(BufferedImage lightSource) {
        this.image = lightSource;

        shape = Shape.CUSTOM;
    }

    public Light(Shape shape) {
        this.shape = shape;

        switch (shape) {

            case ROUND:
                this.image = StaticSystem.defaultImageFactory.getOptimizedImageResource("res/pictures/light/default_light_round.png");
                break;
            case CUSTOM:
                System.err.println("ERROR: CAN'T SET SHAPE OF LIGHT TO CUSTOM WITHOUT HAVING A CUSTOM IMAGE!");
                break;
        }
    }

    public void draw(Graphics2D graphics) {

    }

    public enum Shape {ROUND, CUSTOM}
}
