/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.render;

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.layer.LayerCollection;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.utils.StaticSystem;

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
            "This is saltyengine.",
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
            StaticSystem.currentScene.draw(saltyGraphics);
        }

        if (mode == Mode.layerCollection) {
            StaticSystem.currentLayerCollection.draw(saltyGraphics);
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
            saltyGraphics.fillOval(x, y, 100, 100);

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

        saltyGraphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        saltyGraphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
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

    public static LayerCollection getCurrentLayCollection() {

        return StaticSystem.currentLayerCollection;
    }

    public static Scene getCurrentScene() {

        return StaticSystem.currentScene;
    }
}
