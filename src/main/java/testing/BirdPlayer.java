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
import de.edgelord.saltyengine.components.SimplePhysicsComponent;
import de.edgelord.saltyengine.components.animation.BasicGameObjectAnimation;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.cosmetic.Animation;
import de.edgelord.saltyengine.cosmetic.Spritesheet;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Vector2f;

import java.awt.image.BufferedImage;

public class BirdPlayer extends GameObject {

    private Animation animation;
    private Spritesheet spritesheet;

    private BasicGameObjectAnimation keyFrameAnimationX = new BasicGameObjectAnimation(this, "mySuperAnimationX", BasicGameObjectAnimation.Control.xPos);
    private BasicGameObjectAnimation keyFrameAnimationWidth = new BasicGameObjectAnimation(this, "mySuperAnimationWidth", BasicGameObjectAnimation.Control.width);
    private BasicGameObjectAnimation keyFrameAnimationHeight = new BasicGameObjectAnimation(this, "mySuperAnimationHeight", BasicGameObjectAnimation.Control.height);

    private FixedRate animationTiming = new FixedRate(this, "animationTiming", 75);
    private FixedRate soundTiming = new FixedRate(this, "soundTiming", 75);

    public BirdPlayer(final Vector2f position, final BufferedImage spriteSheetImage) {
        super(position.getX(), position.getY(), 0, 0, "testing.birdPlayer");

        initAnimations(spriteSheetImage);

        addComponent(animationTiming);
        addComponent(soundTiming);
    }

    private void initAnimations(final BufferedImage spriteSheetImage) {

        animation = new Animation(this);
        spritesheet = new Spritesheet(spriteSheetImage, 150, 101);

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        keyFrameAnimationX.addKeyFrame(3000, 0);
        keyFrameAnimationX.addKeyFrame(9000, 700);
        keyFrameAnimationX.addKeyFrame(10000, 1000);

        keyFrameAnimationWidth.addKeyFrame(1000, 0);
        keyFrameAnimationWidth.addKeyFrame(5000, 150);

        keyFrameAnimationHeight.addKeyFrame(1000, 0);
        keyFrameAnimationHeight.addKeyFrame(5000, 101);


        addComponent(keyFrameAnimationX);
        addComponent(keyFrameAnimationWidth);
        addComponent(keyFrameAnimationHeight);
        keyFrameAnimationX.start();
        keyFrameAnimationWidth.start();
        keyFrameAnimationHeight.start();
    }

    @Override
    public void initialize() {

        System.out.println("INFO: Initialized " + getClass());
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {

        if (Game.inputUp) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setVelocity(0.5f);

            if (animationTiming.now()) {
                animation.nextFrame();
            }

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_UPWARDS_FORCE).setVelocity(0f);
        }

        if (Game.inputDown) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setVelocity(0.5f);

            if (animationTiming.now()) {
                animation.nextFrame();
            }

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_DOWNWARDS_FORCE).setVelocity(0f);
        }

        if (Game.inputRight) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setVelocity(0.5f);

            if (animationTiming.now()) {
                animation.nextFrame();
            }

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_RIGHTWARDS_FORCE).setVelocity(0f);
        }

        if (Game.inputLeft) {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setVelocity(0.5f);

            if (animationTiming.now()) {
                animation.nextFrame();
            }

            if (soundTiming.now()) {
                Tester.getAudioSystem().play("bird_flap");
            }
        } else {
            getPhysics().getForce(SimplePhysicsComponent.DEFAULT_LEFTWARDS_FORCE).setVelocity(0f);
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

        animation.drawCurrentFrame(saltyGraphics);
    }
}
