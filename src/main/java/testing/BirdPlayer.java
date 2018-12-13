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

import de.edgelord.saltyengine.components.FixedRate;
import de.edgelord.saltyengine.components.animation.BasicGameObjectAnimation;
import de.edgelord.saltyengine.components.rendering.AnimationRender;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.cosmetic.Animation;
import de.edgelord.saltyengine.cosmetic.Spritesheet;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.stdf.Species;
import de.edgelord.stdf.reading.ValueToListConverter;

import java.awt.image.BufferedImage;
import java.util.List;

public class BirdPlayer extends GameObject implements Serializable {

    private Animation animation;
    private Spritesheet spritesheet;

    private final float speed = 2500f;

    private BasicGameObjectAnimation keyFrameAnimationX = new BasicGameObjectAnimation(this, "mySuperAnimationX", BasicGameObjectAnimation.Control.X_POS);
    private BasicGameObjectAnimation keyFrameAnimationRotation = new BasicGameObjectAnimation(this, "mySuperAnimationRotation", BasicGameObjectAnimation.Control.ROTATION);
    private BasicGameObjectAnimation keyFrameAnimationWidth = new BasicGameObjectAnimation(this, "mySuperAnimationWidth", BasicGameObjectAnimation.Control.WIDTH);
    private BasicGameObjectAnimation keyFrameAnimationHeight = new BasicGameObjectAnimation(this, "mySuperAnimationHeight", BasicGameObjectAnimation.Control.HEIGHT);

    private AnimationRender animationRender;
    private FixedRate soundTiming = new FixedRate(this, "soundTiming", 350);

    public BirdPlayer(final Vector2f position, final BufferedImage spriteSheetImage) {
        super(position.getX(), position.getY(), 0, 0, "testing.birdPlayer");

        initAnimations(spriteSheetImage);

        animationRender = new AnimationRender(this, "animationRender", animation, 75);

        getHitboxAsSimpleHitbox().setOffsetX(25);
        getHitboxAsSimpleHitbox().setOffsetY(12);
        getHitboxAsSimpleHitbox().setWidth(85);
        getHitboxAsSimpleHitbox().setHeight(75);

        addComponent(animationRender);
        addComponent(soundTiming);
    }

    private void initAnimations(final BufferedImage spriteSheetImage) {

        animation = new Animation(this);
        spritesheet = new Spritesheet(spriteSheetImage, 150, 101);

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        keyFrameAnimationX.addKeyFrame(3000, 0);
        keyFrameAnimationX.addKeyFrame(9000, 700);
        keyFrameAnimationX.addKeyFrame(10000, 1000);

        keyFrameAnimationRotation.addKeyFrame(1500, 0);
        keyFrameAnimationRotation.addKeyFrame(5000, 360);
        keyFrameAnimationRotation.addKeyFrame(7500, 180);
        keyFrameAnimationRotation.addKeyFrame(9000, 0);

        keyFrameAnimationWidth.addKeyFrame(1000, 0);
        keyFrameAnimationWidth.addKeyFrame(5000, 150);

        keyFrameAnimationHeight.addKeyFrame(1000, 0);
        keyFrameAnimationHeight.addKeyFrame(5000, 101);


        addComponent(keyFrameAnimationX);
        addComponent(keyFrameAnimationRotation);
        addComponent(keyFrameAnimationWidth);
        addComponent(keyFrameAnimationHeight);
        keyFrameAnimationX.start();
        keyFrameAnimationRotation.start();
        keyFrameAnimationWidth.start();
        keyFrameAnimationHeight.start();

        setMass(0.5f);
    }

    @Override
    public void initialize() {

        System.out.println("Info: Initialised a new BirdPlayer");
    }

    @Override
    public void onCollision(final CollisionEvent e) {
    }

    @Override
    public void onFixedTick() {

        getTransform().setRotationCentreToMiddle();

        accelerateTo(speed, Input.getInput());

        if (Input.inputUp) {
            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }

        if (Input.inputDown) {
            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }

        if (Input.inputRight) {
            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }

        if (Input.inputLeft) {
            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
    }

    @Override
    public void serialize(Species species) {
        species.addTag("camPos", Game.getCamera().getX() + "," + Game.getCamera().getY());
    }

    @Override
    public void deserialize(Species species) {
        List<String> camPos = ValueToListConverter.convertToList(species, "camPos", ",");
        Game.getCamera().setX(Float.valueOf(camPos.get(0)));
        Game.getCamera().setY(Float.valueOf(camPos.get(1)));
    }

    @Override
    public String getDataSetName() {
        return "player";
    }
}
