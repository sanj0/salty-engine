/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) 2018 Malte Dostal
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

package testing;

import de.edgelord.saltyengine.components.DebugLogGameObjectStat;
import de.edgelord.saltyengine.components.FixedRate;
import de.edgelord.saltyengine.components.animation.BasicGameObjectAnimation;
import de.edgelord.saltyengine.components.rendering.AnimationRender;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.physics.Force;
import de.edgelord.saltyengine.cosmetic.Animation;
import de.edgelord.saltyengine.cosmetic.Spritesheet;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.image.BufferedImage;

public class BirdPlayer extends GameObject {

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
        super(position.getX(), position.getY() + 700, 0, 0, "testing.birdPlayer");

        initAnimations(spriteSheetImage);

        animationRender = new AnimationRender(this, "animationRender", animation, 75);

        addComponent(animationRender);
        addComponent(soundTiming);
        addComponent(new DebugLogGameObjectStat(this, "blabliblub"));
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
        // keyFrameAnimationX.start();
        // keyFrameAnimationRotation.start();
        keyFrameAnimationWidth.start();
        keyFrameAnimationHeight.start();

        setMass(0.5f);
    }

    @Override
    public void initialize() {

        System.out.println("INFO: Initialized " + getClass());
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    String[] previousCollection = new String[]{};
    @Override
    public void onFixedTick() {

        getTransform().setRotationCentreToMiddle();

        getTransform().setRotationDegrees(getTransform().getRotationDegrees() + 1);
        getPhysics().setAccelerationToCollection(previousCollection, 0f);
        previousCollection = getPhysics().accelerateToAngle(getTransform().getRotationDegrees(), 1f, "lol");

        if (Game.inputUp) {
            accelerate(speed, Directions.Direction.UP);

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }

        if (Game.inputDown) {
            accelerate(speed, Directions.Direction.DOWN);

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }

        if (Game.inputRight) {
            accelerate(speed, Directions.Direction.RIGHT);

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }

        if (Game.inputLeft) {
            accelerate(speed, Directions.Direction.LEFT);

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        animation.drawCurrentFrame(saltyGraphics);
    }
}
