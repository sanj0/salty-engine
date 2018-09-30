/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package testing;

import de.edgelord.saltyengine.audio.AudioSystem;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.sgs.ScriptedGameObject;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.ui.UISystem;
import de.edgelord.saltyengine.ui.elements.DumbPanel;
import de.edgelord.saltyengine.ui.elements.FloatingLabel;
import de.edgelord.saltyengine.utils.StaticSystem;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tester extends Game {

    private static AudioSystem audioSystem;

    public Tester(final int windowWidth, final int windowHeight, final String gameName, final long fixedTickMillis) {
        super(windowWidth, windowHeight, gameName, fixedTickMillis);

        System.out.println("INFO: Welcome to Salty Engine v" + StaticSystem.versionTag);

        Tester.initForcesTest();
        Tester.addUI();
        Tester.initUITest();

        Tester.audioSystem = new AudioSystem(new AudioFactory(new InnerResource()));

        Tester.audioSystem.loadNewAudio("joy_sticky", "res/audio/music/Joy Sticky.wav");
        Tester.audioSystem.loadNewAudio("bird_flap", "res/audio/sound/flap.wav");

        Tester.audioSystem.loop("joy_sticky");

        audioSystem.setClipVolume("joy_sticky", 0.75f);
    }

    public static void main(final String[] args) {

        final Tester tester = new Tester(1200, 900, "testing", 1);

        start(60);
    }

    private static void initUITest() {
        FloatingLabel floatingLabel = new FloatingLabel("Use WASD or the arrow keys to move the red bird!", new Vector2f(0, 25));
        floatingLabel.centreOnXAxis(true);
        floatingLabel.setTextColor(Color.black);
        floatingLabel.setFont(floatingLabel.getFont().deriveFont(20f));

        StaticSystem.currentScene.getUI().addElement(floatingLabel);
    }

    private static void addUI() {

        StaticSystem.font = StaticSystem.font.deriveFont(20f);

        UISystem uiSystem = new UISystem();

        PauseButton pauseButton = new PauseButton();

        uiSystem.addElement(pauseButton);

        StaticSystem.currentScene.setUI(uiSystem);
    }

    private static void initSGSTest() {
        StaticSystem.currentScene.addGameObject(new ScriptedGameObject("res/sgs/scripts/testing.sgs"));
    }

    private static void initPhysicsTest() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());
        final BufferedImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        final Bird bird1_1 = new Bird(birdSpritesheet, 1, 1);
        final Bird bird3_1 = new Bird(birdSpritesheet, 3, 1);
        final Bird bird1_3 = new Bird(birdSpritesheet, 1, 3);
        final Bird bird3_3 = new Bird(birdSpritesheet, 3, 3);

        bird1_1.setTag("de.edgelord.saltyengine.testing.bird1_1");
        bird3_1.setTag("de.edgelord.saltyengine.testing.bird3_1");
        bird1_3.setTag("de.edgelord.saltyengine.testing.bird1_3");
        bird3_3.setTag("de.edgelord.saltyengine.testing.bird3_3");

        StaticSystem.currentScene.addGameObject(bird1_1);
        StaticSystem.currentScene.addGameObject(bird1_3);
        StaticSystem.currentScene.addGameObject(bird3_1);
        StaticSystem.currentScene.addGameObject(bird3_3);
    }

    public static void initForcesTest() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());
        final BufferedImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        final Bird upperBird = new Bird(birdSpritesheet, 2, 2);
        final Bird bottomBird = new Bird(birdSpritesheet, 2, 4);
        final BirdPlayer player = new BirdPlayer(new Vector2f(0, 0), StaticSystem.defaultImageFactory.getOptimizedImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"));

        bottomBird.getPhysics().removeGravity();
        upperBird.getPhysics().removeGravity();
        player.getPhysics().removeGravity();

        StaticSystem.currentScene.addGameObject(bottomBird);

        StaticSystem.currentScene.addGameObject(upperBird);

        StaticSystem.currentScene.addGameObject(player);
    }

    private static void initSampleScene() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());

        final BirdPlayer player = new BirdPlayer(new Vector2f(0, 0), StaticSystem.defaultImageFactory.getOptimizedImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"));

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
