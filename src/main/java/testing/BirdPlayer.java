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

import de.edgelord.saltyengine.components.DeflectionOnMouseOverComponent;
import de.edgelord.saltyengine.components.FixedRate;
import de.edgelord.saltyengine.components.animation.AnimationRender;
import de.edgelord.saltyengine.components.animation.LinearTransformAnimations;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.graphics.sprite.Spritesheet;
import de.edgelord.saltyengine.graphics.sprite.SpritesheetAnimation;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.transform.Vector2f;
import de.edgelord.sanjo.SJClass;

public class BirdPlayer extends GameObject implements Serializable {

    private static final float speed = 2500f;
    private final LinearTransformAnimations keyFrameAnimationX = new LinearTransformAnimations(this, "mySuperAnimationX", LinearTransformAnimations.Control.X_POS);
    private final LinearTransformAnimations keyFrameAnimationRotation = new LinearTransformAnimations(this, "mySuperAnimationRotation", LinearTransformAnimations.Control.ROTATION);
    private final LinearTransformAnimations keyFrameAnimationWidth = new LinearTransformAnimations(this, "mySuperAnimationWidth", LinearTransformAnimations.Control.WIDTH);
    private final LinearTransformAnimations keyFrameAnimationHeight = new LinearTransformAnimations(this, "mySuperAnimationHeight", LinearTransformAnimations.Control.HEIGHT);
    private final AnimationRender animationRender;
    private final FixedRate soundTiming = new FixedRate(this, "soundTiming", 350);
    private SpritesheetAnimation spritesheetAnimation;

    public BirdPlayer(final Vector2f position, final SaltyImage spriteSheetImage) {
        super(position.getX(), position.getY(), 0, 0, "testing.birdPlayer");

        initAnimations(spriteSheetImage);

        animationRender = new AnimationRender(this, "animationRender", spritesheetAnimation, 75);

        getHitboxAsSimpleHitbox().setOffsetX(25);
        getHitboxAsSimpleHitbox().setOffsetY(12);
        getHitboxAsSimpleHitbox().setWidth(85);
        getHitboxAsSimpleHitbox().setHeight(75);

        addComponent(animationRender);
        addComponent(soundTiming);
        DeflectionOnMouseOverComponent deflection = new DeflectionOnMouseOverComponent(this, "deflection", 50, 500, true);
        addComponent(deflection);
    }

    private void initAnimations(final SaltyImage spriteSheetImage) {

        spritesheetAnimation = new SpritesheetAnimation();
        final Spritesheet spritesheet = new Spritesheet(spriteSheetImage, 150, 101);

        spritesheetAnimation.setFrames(spritesheet.getFrames(new Coordinates(0, 0), new Coordinates(1, 1), new Coordinates(2, 1), new Coordinates(3, 0)));

        keyFrameAnimationX.addKeyframe(3000, 0);
        keyFrameAnimationX.addKeyframe(9000, 700);
        keyFrameAnimationX.addKeyframe(10000, 1000);

        keyFrameAnimationRotation.addKeyframe(1500, 0);
        keyFrameAnimationRotation.addKeyframe(5000, 360);
        keyFrameAnimationRotation.addKeyframe(7500, 180);
        keyFrameAnimationRotation.addKeyframe(9000, 0);

        keyFrameAnimationWidth.addKeyframe(1000, 0);
        keyFrameAnimationWidth.addKeyframe(5000, 150);

        keyFrameAnimationHeight.addKeyframe(1000, 0);
        keyFrameAnimationHeight.addKeyframe(5000, 101);


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
        // nothing to do
    }

    @Override
    public void onFixedTick() {

        // a very bad and slow call for lazy developers
        DeflectionOnMouseOverComponent component = (DeflectionOnMouseOverComponent) getComponent("deflection");

        if (Input.getKeyboardInput().isY()) {
            component.cancel();
        }

        if (Input.getKeyboardInput().isX()) {
            component.impact();
        }

        getTransform().setRotationCentreToCentre();

        accelerateTo(speed, Input.getInput());
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        // drawing is done by a component
    }

    @Override
    public void serialize(final SJClass data) {
        data.addValue("camPos", Game.getCamera().getX() + "," + Game.getCamera().getY());
        System.out.println("serializing!");
    }

    @Override
    public void deserialize(final SJClass data) {
        System.out.println("Deserializing!");
    }

    @Override
    public String getDataSetName() {
        return "player";
    }
}
