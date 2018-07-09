/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.me.edgelord.sjgl.render;

import de.me.edgelord.sjgl.layer.LayerCollection;
import de.me.edgelord.sjgl.scene.Scene;
import de.me.edgelord.sjgl.utils.StaticSystem;

import java.awt.*;
import java.util.Random;

public class DrawingAlgorithm {

    private Mode mode;
    private int index = 0;
    private int cameraMoved = 0;
    private boolean backWards = false;

    Random random = new Random();

    String[] messages = {
            "",
            "Hello World!",
            "This is sjgl.",
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

    public void draw(Graphics2D graphics) {

        if (mode == Mode.scene) {
            StaticSystem.currentScene.draw(graphics);
        }

        if (mode == Mode.layerCollection) {
            StaticSystem.currentLayerCollection.draw(graphics);
        }

        if (mode == Mode.messages)
            drawMessages(graphics);
        if (mode == Mode.crackBrained)
            crackBrainedBalls(graphics);
        if (mode == Mode.crazy)
            crazyBalls(graphics);
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
    }

    private void crackBrainedBalls(Graphics2D graphics) {

        int x = 0;
        int y = 0;
        boolean goOn = true;

        while (goOn) {

            graphics.setColor(colors[random.nextInt(9)]);
            graphics.fillOval(x, y, 100, 100);

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

    private void crazyBalls(Graphics2D graphics) {

        graphics.setFont(new Font(Font.MONOSPACED, Font.BOLD, 20));
        graphics.setColor(colors[random.nextInt(9)]);

        graphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        graphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        graphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        graphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
        graphics.fillOval(random.nextInt(1100), random.nextInt(800), 100, 100);
    }

    public void drawMessages(Graphics2D graphics) {

        graphics.setColor(Color.BLACK);
        graphics.setFont(new Font(Font.SERIF, 0, 30));

        graphics.drawString(messages[index], 100, 100);

        System.out.println("sjgl 0.0.2 > DrawingAlgorithm > \"Current mode is messages and current message is " + messages[index] + "\"");

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
