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
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.effect.Animation;
import de.edgelord.saltyengine.effect.Spritesheet;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.io.serialization.Serializable;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.utils.Directions;
import de.edgelord.stdf.Species;

import java.awt.image.BufferedImage;

public class Bird extends GameObject implements Serializable {

    private final Animation animation;
    private final Spritesheet spritesheet;

    public Bird(final BufferedImage image, final int xPos, final int yPos) {
        super(xPos * 150, yPos * 101, 150, 101, "testing.bird");

        animation = new Animation(this);
        spritesheet = new Spritesheet(image, getWidthAsInt(), getHeightAsInt());

        animation.setFrames(spritesheet.getManualFrames(new Coordinates(1, 1), new Coordinates(2, 2), new Coordinates(3, 2), new Coordinates(4, 1)));

        addComponent(new AnimationRender(this, "de.edgelord.saltyengine.testing.bird.animationRender", animation, 90));

        getHitboxAsSimpleHitbox().setOffsetX(25);
        getHitboxAsSimpleHitbox().setOffsetY(12);
        getHitboxAsSimpleHitbox().setWidth(85);
        getHitboxAsSimpleHitbox().setHeight(75);

        // Improves performance a lot!
        //setStationary(true);
    }

    @Override
    public void initialize() {

        getPhysics().addForce("testing.Bird.testingForce", Directions.Direction.RIGHT);
        // getDefaultAccelerator().accelerate("testing.Bird.testingForce", 0.01f, 100);

        System.out.println("Info: Initialised a new Bird");
    }

    @Override
    public void onCollision(final CollisionEvent e) {

    }

    @Override
    public void onFixedTick() {

        // getTransform().rotateToPoint(Game.cursorPosition);

        moveToFacedDirection(.2f);

        if (Input.keyboardInput.isSpace()) {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(1000f);
        } else {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(0f);
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
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
