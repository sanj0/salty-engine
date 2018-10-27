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

package de.edgelord.saltyengine.render;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.scene.SceneManager;

import java.awt.*;
import java.util.Random;

@Deprecated
public class DrawingAlgorithm {

    private Mode mode;
    private int index = 0;
    private int cameraMoved = 0;
    private boolean backWards = false;

    Random random = new Random();

    String[] messages = {
            "",
            "Hello World!",
            "This is Salty Engine.",
            "A Java library ",
            "for making 2D games"
    };

    Color[] colors = {

            Color.RED,
            Color.WHITE,
            Color.DARK_GRAY,
            Color.GRAY,
            Color.YELLOW,
            Color.GREEN,
            Color.BLUE,
            Color.CYAN,
            Color.MAGENTA,
            Color.LIGHT_GRAY
    };

    public enum Mode {
        layerCollection, scene, messages, crazy, crackBrained
    }

    @Deprecated
    public DrawingAlgorithm(Mode mode) {
        this.mode = mode;
    }

    public void draw(SaltyGraphics saltyGraphics) {

        if (mode == Mode.scene) {
            SceneManager.getCurrentScene().draw(saltyGraphics);
        }

        if (mode == Mode.messages)
            drawMessages(saltyGraphics);
        if (mode == Mode.crackBrained)
            crackBrainedBalls(saltyGraphics);
        if (mode == Mode.crazy)
            crazyBalls(saltyGraphics);
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    private void crackBrainedBalls(SaltyGraphics saltyGraphics) {

        int x = 0;
        int y = 0;
        boolean goOn = true;

        while (goOn) {

            saltyGraphics.setColor(colors[random.nextInt(9)]);
            saltyGraphics.drawOval(x, y, 100, 100);

            if (x == 1100) {

                x = 0;
                y += 100;
            } else {

                x += 100;
            }


            if (y == 900) {

                goOn = false;
            }
        }
    }

    private void crazyBalls(SaltyGraphics saltyGraphics) {

        saltyGraphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        saltyGraphics.setColor(colors[random.nextInt(9)]);

        saltyGraphics.drawOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.drawOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.drawOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.drawOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.drawOval(random.nextInt(1100), random.nextInt(800), 100, 100);
    }

    public void drawMessages(SaltyGraphics saltyGraphics) {

        saltyGraphics.setColor(Color.BLACK);
        saltyGraphics.setFont(new Font(Font.SERIF, 0, 30));

        saltyGraphics.drawText(messages[index], 100, 100);

        System.out.println("saltyengine 0.0.2 > DrawingAlgorithm > \"Current mode is messages and current message is " + messages[index] + "\"");

        if (index != messages.length - 1)
            index++;
        else
            index = 0;
    }

    public static Scene getCurrentScene() {

        return SceneManager.getCurrentScene();
    }
}
