/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, 2018
 * All rights reserved
 */

package testing;

import de.me.edgelord.sjgl.camera.Camera;
import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.factory.ImageFactory;
import de.me.edgelord.sjgl.location.Coordinates;
import de.me.edgelord.sjgl.main.MainLoops;
import de.me.edgelord.sjgl.resource.InnerResource;
import de.me.edgelord.sjgl.utils.StaticSystem;
import testing.dummys.DummyDisplayKeyHandler;
import testing.dummys.DummyScene;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tester {

    public static Camera camera;
    public static DisplayManager displayManager;
    private static DummyDisplayKeyHandler dummyDisplayKeyHandler;

    public static void main(String[] args) {

        System.out.println("INFO: Welcome to sjgl version 0.3 Zeus!\n");

        MainLoops mainLoops = new MainLoops(1);
        StaticSystem.currentScene = new DummyScene();

        displayManager = new DisplayManager(1200, 909, mainLoops);
        displayManager.create();

        try {
            initGameObjects();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainLoops.startFixedTicks();
        mainLoops.startRendering(displayManager);

        camera = new Camera(mainLoops);
        dummyDisplayKeyHandler = new DummyDisplayKeyHandler();
        displayManager.setDisplayKeyHandler(dummyDisplayKeyHandler);
    }

    private static void initGameObjects() throws IOException {

        ImageFactory imageFactory = new ImageFactory(new InnerResource());

        HugeImageRenderingTest hugeImageRenderingTest = new HugeImageRenderingTest(imageFactory.getImageResource("res/pictures/bg.png"), 1920, 1080);
        BirdPlayer player = new BirdPlayer(imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"), displayManager, camera, new Coordinates(0, 0));

        StaticSystem.currentScene.addGameObject(hugeImageRenderingTest);
        StaticSystem.currentScene.addGameObject(player);

        BufferedImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        // Adding all those cute birds to the Scene

        int index = 0;
        int xPos = 0;
        int yPos = 0;

        while (index < 16) {

            // StaticSystem.currentScene.addGameObject(new Bird(birdSpritesheet, xPos, yPos));

            if (index == 7) {

                xPos++;
                yPos = 0;
            } else {

                yPos++;
            }

            index++;
        }
    }
}
