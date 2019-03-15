/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package testing;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.effect.geom.EnumShape;
import de.edgelord.saltyengine.effect.image.SaltyImage;
import de.edgelord.saltyengine.effect.light.GradientLight;
import de.edgelord.saltyengine.effect.light.Light;
import de.edgelord.saltyengine.effect.light.LightSystem;
import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.io.LanguageManager;
import de.edgelord.saltyengine.io.serialization.Serializer;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.Scene;
import de.edgelord.saltyengine.transform.Coordinates2f;
import de.edgelord.saltyengine.transform.Transform;
import de.edgelord.saltyengine.ui.elements.FloatingLabel;
import de.edgelord.saltyengine.ui.elements.ProgressBar;
import de.edgelord.saltyengine.ui.elements.RoundedTextBox;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.SaltySystem;

import java.awt.*;
import java.awt.image.VolatileImage;
import java.io.IOException;

public class TestingScene extends Scene {

    private Light light = new GradientLight(0, 0, 300, 300, ColorUtil.GOLD, EnumShape.OVAL);
    private ProgressBar progress = new ProgressBar(Game.getHost().getHorizontalCentrePosition(500f), 75, 500, 25);

    public TestingScene(String foo, Integer bar) {
        setFriction(0.005f);

        try {
            LanguageManager.init("res/lyrics", SaltySystem.defaultResource);
        } catch (IOException e) {
            e.printStackTrace();
        }

        addLight();
        initForcesTest();
        initUITest();
        addUI();

        disableGravity();

        /*
        try {
            addDrawingRoutine(StaticTileGrid.readSTM(new File("/Users/edgelord/Salty Tilemap Creator/map.stm.sdb"), new Coordinates2f(110, 110), DrawingRoutine.DrawingPosition.BEFORE_GAMEOBJECTS));
        } catch (IOException e) {
            e.printStackTrace();
        }
        */
    }

    private void addLight() {
        setLightSystem(new LightSystem(new Color(0, 0, 0, 225)));
        getLightSystem().addLight(light);
        getLightSystem().addLight(new GradientLight(new Transform(0, 0, 200, 200), EnumShape.ROUND_RECTANGLE));
    }

    private void initUITest() {
        FloatingLabel floatingLabel = new FloatingLabel("Use WASD or the arrow keys to move the red bird!", new Coordinates2f(0, 25));
        floatingLabel.centreOnXAxis(true);
        floatingLabel.setForegroundColor(Color.black);
        floatingLabel.setFont(floatingLabel.getFont().deriveFont(20f));

        getUI().addElement(floatingLabel);
    }

    @Override
    public void onFixedTick() {
        light.positionByCentre(Input.getAbsoluteCursorPosition());

        if (progress.getCurrentValue() >= progress.getMaxValue()) {
            progress.setCurrentValue(0f);
        } else {
            progress.setCurrentValue(progress.getCurrentValue() + 0.01f);
        }
        super.onFixedTick();
    }

    private void addUI() {

        SaltySystem.defaultFont = SaltySystem.defaultFont.deriveFont(20f);

        PauseButton pauseButton = new PauseButton();
        RoundedTextBox textBox = new RoundedTextBox(LanguageManager.getText("textBox"), 10, 600, 1180, 100, 25, 50);

        progress.setCornerArc(20);
        progress.setBackgroundColor(ColorUtil.ACTIVE_GREEN);
        textBox.setFont(textBox.getFont().deriveFont(18f));

        getUI().addElement(pauseButton);
        getUI().addElement(textBox);
        getUI().addElement(progress);
    }

    private void initPhysicsTest() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());
        final SaltyImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        final Bird bird1_1 = new Bird(birdSpritesheet, 1, 1);
        final Bird bird3_1 = new Bird(birdSpritesheet, 3, 1);
        final Bird bird1_3 = new Bird(birdSpritesheet, 1, 3);
        final Bird bird3_3 = new Bird(birdSpritesheet, 3, 3);

        bird1_1.setTag("de.edgelord.saltyengine.testing.bird1_1");
        bird3_1.setTag("de.edgelord.saltyengine.testing.bird3_1");
        bird1_3.setTag("de.edgelord.saltyengine.testing.bird1_3");
        bird3_3.setTag("de.edgelord.saltyengine.testing.bird3_3");

        addGameObject(bird1_1);
        addGameObject(bird1_3);
        addGameObject(bird3_1);
        addGameObject(bird3_3);
    }

    public void initForcesTest() {

        final SaltyImage birdSpritesheet = SaltySystem.defaultImageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        final Bird upperBird = new Bird(birdSpritesheet, 2, 2);
        final Bird bottomBird = new Bird(birdSpritesheet, 3, 4);
        final BirdPlayer player = new BirdPlayer(new Coordinates2f(0, 100), SaltySystem.defaultImageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"));

        addGameObject(bottomBird);
        addGameObject(upperBird);
        addGameObject(player);

        Serializer.add(player);
        Serializer.add(bottomBird);
        try {
            Serializer.doDeserialization();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initSampleScene() {

        final ImageFactory imageFactory = new ImageFactory(new InnerResource());

        final BirdPlayer player = new BirdPlayer(new Coordinates2f(0, 0), SaltySystem.defaultImageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet_player.png"));

        addGameObject(player);

        final SaltyImage birdSpritesheet = imageFactory.getImageResource("res/pictures/spritesheets/bird_spritesheet.png");

        // Adding all those cute birds to the Scene

        int index = 0;
        int xPos = 0;
        int yPos = 0;

        while (index < 16) {

            addGameObject(new Bird(birdSpritesheet, xPos, yPos));

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
