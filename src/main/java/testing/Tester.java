/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing;

import de.edgelord.sjgl.audio.AudioSystem;
import de.edgelord.sjgl.core.Game;
import de.edgelord.sjgl.factory.AudioFactory;
import de.edgelord.sjgl.factory.ImageFactory;
import de.edgelord.sjgl.location.Coordinates;
import de.edgelord.sjgl.resource.InnerResource;
import de.edgelord.sjgl.ui.UISystem;
import de.edgelord.sjgl.ui.elements.Button;
import de.edgelord.sjgl.utils.StaticSystem;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class Tester extends Game {

    private static AudioSystem audioSystem;

    public Tester(final int windowWidth, final int windowHeight, final String gameName, final long fixedTickMillis) {
        super(windowWidth, windowHeight, gameName, fixedTickMillis);

        System.out.println("INFO: Welcome to sjgl version " + StaticSystem.versionTag + " " + StaticSystem.versionName);

        Tester.initForcesTest();
        Tester.addUI();

        Tester.audioSystem = new AudioSystem(new AudioFactory(new InnerResource()));

        Tester.audioSystem.loadNewAudio("joy_sticky", "res/audio/music/Joy Sticky.wav");
        Tester.audioSystem.loadNewAudio("bird_flap", "res/audio/sound/flap.wav");

        // Tester.audioSystem.loop("joy_sticky");
    }

    public static void main(final String[] args) {

        final Tester tester = new Tester(1200, 900, "testing", 1);

        Game.start(60);
    }

    private static void addUI() {

        StaticSystem.font = StaticSystem.font.deriveFont(20f);

        final UISystem uiSystem = new UISystem();

        final Button button = new Button("Pause", new Coordinates(550, 500), 100, 35) {
            @Override
            public void onClick(final MouseEvent e) {

                if (e.getClickCount() == 2) {
                    System.out.println("Exit Game due to double-click onto that pause button");
                    System.exit(0);
                }

                if (StaticSystem.isPaused()) {

                    System.out.println("Unpause game!");
                    StaticSystem.setPaused(false);
                } else {
                    System.out.println("Pause game!");
                    StaticSystem.setPaused(true);
                }
            }
        };

        button.setBackgroundColor(Color.orange);
        button.setForegroundColor(Color.white);

        uiSystem.addElement(button);

        StaticSystem.currentScene.setUI(uiSystem);
    }

    private static void initPhysicsTest() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());
        final BufferedImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        final Bird bird1_1 = new Bird(birdSpritesheet, 1, 1);
        final Bird bird3_1 = new Bird(birdSpritesheet, 3, 1);
        final Bird bird1_3 = new Bird(birdSpritesheet, 1, 3);
        final Bird bird3_3 = new Bird(birdSpritesheet, 3, 3);

        bird1_1.setTag("de.edgelord.sjgl.testing.bird1_1");
        bird3_1.setTag("de.edgelord.sjgl.testing.bird3_1");
        bird1_3.setTag("de.edgelord.sjgl.testing.bird1_3");
        bird3_3.setTag("de.edgelord.sjgl.testing.bird3_3");

        StaticSystem.currentScene.addGameObject(bird1_1);
        StaticSystem.currentScene.addGameObject(bird1_3);
        StaticSystem.currentScene.addGameObject(bird3_1);
        StaticSystem.currentScene.addGameObject(bird3_3);
    }

    private static void initForcesTest() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());
        final BufferedImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        final Bird upperBird = new Bird(birdSpritesheet, 2, 2);
        final Bird bottomBird = new Bird(birdSpritesheet, 2, 4);
        final BirdPlayer player = new BirdPlayer(imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"), Game.getDisplayManager(), new Coordinates(1, 1));

        bottomBird.getPhysics().removeGravity();
        upperBird.getPhysics().removeGravity();
        player.getPhysics().removeGravity();

        StaticSystem.currentScene.addGameObject(upperBird);
        // StaticSystem.currentScene.addGameObject(bottomBird);
        StaticSystem.currentScene.addGameObject(player);
    }

    private static void initSampleScene() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());

        final HugeImageRenderingTest hugeImageRenderingTest = new HugeImageRenderingTest(imageFactory.getImageResource("res/pictures/bg.png"), 1920, 1080);
        final BirdPlayer player = new BirdPlayer(imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"), Game.getDisplayManager(), new Coordinates(1, 1));

        StaticSystem.currentScene.addGameObject(hugeImageRenderingTest);
        StaticSystem.currentScene.addGameObject(player);

        final BufferedImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

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
        return Tester.audioSystem;
    }
}
