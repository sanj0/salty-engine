/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, 2018
 * All rights reserved
 */

package testing;

import de.edgelord.sjgl.audio.AudioSystem;
import de.edgelord.sjgl.core.Engine;
import de.edgelord.sjgl.display.DisplayManager;
import de.edgelord.sjgl.factory.AudioFactory;
import de.edgelord.sjgl.factory.ImageFactory;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.resource.InnerResource;
import de.edgelord.sjgl.ui.Button;
import de.edgelord.sjgl.ui.UISystem;
import de.edgelord.sjgl.utils.GameStats;
import de.edgelord.sjgl.utils.StaticSystem;
import testing.dummys.DummyDisplayKeyHandler;
import testing.dummys.DummyScene;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Tester {

    public static DisplayManager displayManager;
    private static DummyDisplayKeyHandler dummyDisplayKeyHandler;
    private static AudioSystem audioSystem;

    public static void main(String[] args) {

        System.out.println("INFO: Welcome to sjgl version 0.3 Zeus!\n");

        Engine engine = new Engine(1);
        StaticSystem.currentScene = new DummyScene();

        displayManager = new DisplayManager(1200, 909, engine);
        displayManager.create();
        initGameObjects();
        addUI();

        engine.start(displayManager);

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

    private static void initGameObjects() {

        ImageFactory imageFactory = new ImageFactory(new InnerResource());

        HugeImageRenderingTest hugeImageRenderingTest = new HugeImageRenderingTest(imageFactory.getImageResource("res/pictures/bg.png"), 1920, 1080);
        BirdPlayer player = new BirdPlayer(imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"), displayManager, new Coordinates(1, 1));

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
