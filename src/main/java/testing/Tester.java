/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, 2018
 * All rights reserved
 */

package testing;

import de.me.edgelord.sjgl.audio.AudioSystem;
import de.me.edgelord.sjgl.camera.Camera;
import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.factory.AudioFactory;
import de.me.edgelord.sjgl.factory.ImageFactory;
import de.me.edgelord.sjgl.location.Coordinates;
import de.me.edgelord.sjgl.main.MainLoops;
import de.me.edgelord.sjgl.resource.InnerResource;
import de.me.edgelord.sjgl.ui.Button;
import de.me.edgelord.sjgl.ui.UISystem;
import de.me.edgelord.sjgl.utils.GameStats;
import de.me.edgelord.sjgl.utils.StaticSystem;
import testing.dummys.DummyDisplayKeyHandler;
import testing.dummys.DummyScene;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Tester {

    public static Camera camera;
    public static DisplayManager displayManager;
    private static DummyDisplayKeyHandler dummyDisplayKeyHandler;
    private static AudioSystem audioSystem;

    public static void main(String[] args) throws InterruptedException {

        System.out.println("INFO: Welcome to sjgl version 0.3 Zeus!\n");

        MainLoops mainLoops = new MainLoops(1);
        StaticSystem.currentScene = new DummyScene();

        displayManager = new DisplayManager(1200, 909, mainLoops);
        displayManager.create();

        try {
            initGameObjects();
            addUI();
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainLoops.start(displayManager);

        camera = new Camera(mainLoops);
        dummyDisplayKeyHandler = new DummyDisplayKeyHandler();
        displayManager.setDisplayKeyHandler(dummyDisplayKeyHandler);

        audioSystem = new AudioSystem(new AudioFactory(new InnerResource()));

        audioSystem.loadNewAudio("joy_sticky", "res/audio/music/Joy Sticky.wav");
        audioSystem.loadNewAudio("bird_flap", "res/audio/sound/flap.wav");

        audioSystem.loop("joy_sticky");
    }

    private static void addUI(){

        StaticSystem.font = StaticSystem.font.deriveFont(20f);

        UISystem uiSystem = new UISystem();

        Button button = new Button("Pause", new Coordinates(550, 500), 100, 35) {
            @Override
            public void onClick(MouseEvent e) {

                if (e.getClickCount() == 2){
                    System.out.println("Exit Game due to double-click onto that pause button");
                    System.exit(0);
                }

                if (GameStats.isPaused()){

                    System.out.println("Unpause game!");
                    GameStats.setPaused(false);
                } else {
                    System.out.println("Pause game!");
                    GameStats.setPaused(true);
                }
            }
        };

        button.setBackgroundColor(Color.orange);
        button.setForegroundColor(Color.white);

        uiSystem.addElement(button);

        StaticSystem.currentScene.setUI(uiSystem);
    }

    private static void initGameObjects() throws IOException {

        ImageFactory imageFactory = new ImageFactory(new InnerResource());

        HugeImageRenderingTest hugeImageRenderingTest = new HugeImageRenderingTest(imageFactory.getImageResource("res/pictures/bg.png"), 1920, 1080);
        BirdPlayer player = new BirdPlayer(imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"), displayManager, camera, new Coordinates(1, 1));

        StaticSystem.currentScene.addGameObject(hugeImageRenderingTest);
        StaticSystem.currentScene.addGameObject(player);

        BufferedImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        // Adding all those cute birds to the Scene

        int index = 0;
        int xPos = 0;
        int yPos = 0;

        while (index < 16) {

            StaticSystem.currentScene.addGameObject(new Bird(birdSpritesheet, xPos, yPos));

            if (index == 7) {

                xPos++;
                yPos = 0;
            } else {

                yPos++;
            }

            index++;
        }
    }

    public static AudioSystem getAudioSystem() {
        return audioSystem;
    }
}
