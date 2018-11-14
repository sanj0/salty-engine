/*
 * This software was published under the MIT License.
 * The full LICENSE file can be found here: https://github.com/edgelord314/salty-enigne/tree/master/LICENSE
 *
 * Copyright (c) since 2018 by the Salty Engine developers,
 * Maintained by Malte Dostal
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

import de.edgelord.saltyengine.components.rendering.AnimationRender;
import de.edgelord.saltyengine.components.rendering.OvalRender;
import de.edgelord.saltyengine.components.rendering.RectangleRender;
import de.edgelord.saltyengine.components.rendering.RoundRectRender;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.cosmetic.Animation;
import de.edgelord.saltyengine.cosmetic.Spritesheet;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Coordinates;
import de.edgelord.saltyengine.utils.Directions;

import java.awt.image.BufferedImage;

public class Bird extends GameObject {

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
        setStationary(true);
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

        if (Input.keyboardInput.isSpace()) {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(0.01f);
        } else {
            getPhysics().getForce("testing.Bird.testingForce").setAcceleration(0f);
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {
    }
}
