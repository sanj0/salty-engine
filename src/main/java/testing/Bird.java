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

import de.edgelord.saltyengine.components.animation.AnimationRender;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.emitter.components.RandomRadialEmitter;
import de.edgelord.saltyengine.emitter.modifiers.RandomSpeedParticleModifier;
import de.edgelord.saltyengine.emitter.particles.RoundRectangleParticle;
import de.edgelord.saltyengine.emitter.prc.RandomColorProfileParticleRenderContext;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.image.SaltyImage;
import de.edgelord.saltyengine.graphics.sprite.Spritesheet;
import de.edgelord.saltyengine.graphics.sprite.SpritesheetAnimation;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.saltyengine.io.serialization.Species;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.utils.ColorUtil;
import de.edgelord.saltyengine.utils.Directions;

public class Bird extends GameObject implements Serializable {

    private final SpritesheetAnimation spritesheetAnimation;
    private final Spritesheet spritesheet;

    private final RandomRadialEmitter emitter = new RandomRadialEmitter(this, "emitter", RoundRectangleParticle.class, .1f, 1);

    public Bird(final SaltyImage image, final int xPos, final int yPos) {
        super(xPos * 150f, yPos * 101f, 150, 101, "testing.bird");

        spritesheetAnimation = new SpritesheetAnimation();
        spritesheet = new Spritesheet(image, getWidth(), getHeight());

        spritesheetAnimation.setFrames(spritesheet.getFrames(new Coordinates(0, 0), new Coordinates(1, 1), new Coordinates(2, 1), new Coordinates(3, 0)));

        addComponent(new AnimationRender(this, "de.edgelord.saltyengine.testing.bird.animationRender", spritesheetAnimation, 90));
        getHitboxAsSimpleHitbox().setOffsetX(25);
        getHitboxAsSimpleHitbox().setOffsetY(12);
        getHitboxAsSimpleHitbox().setWidth(85);
        getHitboxAsSimpleHitbox().setHeight(75);

        emitter.setRenderContext(new RandomColorProfileParticleRenderContext(ColorUtil.DODGER_BLUE, ColorUtil.BLUE, ColorUtil.AQUA_MARINE_BLUE));
        emitter.addModifier(new RandomSpeedParticleModifier(1, 10, 0.1f, false));

        addComponent(emitter);
    }

    @Override
    public void initialize() {

        getPhysics().addForce("testing.Bird.testingForce", Directions.Direction.RIGHT);
        System.out.println("Info: Initialised a new Bird");
    }

    @Override
    public void onCollision(final CollisionEvent e) {
        // nothing to do
    }

    @Override
    public void onFixedTick() {

        getTransform().rotateToPoint(Input.getCursorPosition());
        emitter.setSpawnPoint(getTransform().getCentre());

        moveToFacedDirection(.2f);

        if (Input.getKeyboardInput().isSpace()) {
            emitter.impact();
        }

        if (Input.keyboardInput.isSpace()) {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(1000f);
        } else {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(0f);
        }

        if (Input.keyboardInput.isRightArrow()) {
            Game.getCamera().move(Directions.Direction.RIGHT, .2f);
        } else if (Input.keyboardInput.isLeftArrow()) {
            Game.getCamera().move(Directions.Direction.LEFT, .2f);
        }

        if (Input.keyboardInput.isUpArrow()) {
            Game.getCamera().move(Directions.Direction.UP, .2f);
        } else if (Input.keyboardInput.isDownArrow()) {
            Game.getCamera().move(Directions.Direction.DOWN, .2f);
        }

        if (Input.keyboardInput.isQ()) {
            Game.getCamera().setRotationDegrees(Game.getCamera().getRotationDegrees() + .1f);
        } else if (Input.keyboardInput.isE()) {
            Game.getCamera().setRotationDegrees(Game.getCamera().getRotationDegrees() - .1f);
        }

        if (Input.keyboardInput.isW()) {
            Game.getCamera().setScale(Game.getCamera().getScale() + .0001f);
        } else if (Input.keyboardInput.isS()) {
            Game.getCamera().setScale(Game.getCamera().getScale() - .0001f);
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
        // drawing is done by a component
    }

    @Override
    public void serialize(Species species) {
        species.addTag("just-a-random-test-value", "something-really-important" + getClass().hashCode());
    }

    @Override
    public void deserialize(Species species) {
        System.out.println(species.getTagValue("just-a-random-test-value"));
    }

    @Override
    public String getDataSetName() {
        return "bird";
    }
}
