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

package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.transform.Transform;

import java.awt.*;

public class Screen extends GameObject {

    private final Transform screen;
    private ScreenContent screenContent;

    private String usageMessage = "Use WASD or the arrow keys to move the screen content!";

    public Screen() {
        super(Game.getHost().getCentrePosition(250, 450), 250, 450, "screen");

        screen = new Transform(getX() + 10, getY() + 10, getWidth() - 20, getHeight() - 20);
        screenContent = new ScreenContent(getX(), getY());

        SceneManager.getCurrentScene().disableGravity();
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {

        if (Game.inputLeft) {
            screenContent.moveX(7f);
        } else if (Game.inputRight) {
            screenContent.moveX(-7f);
        }

        if (Game.inputUp) {
            screenContent.moveY(7f);
        } else if (Game.inputDown) {
            screenContent.moveY(-7f);
        }
    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

        // Draw the screen surrounding
        saltyGraphics.setStroke(new BasicStroke(20));
        saltyGraphics.drawRoundRect(this, 50);
        saltyGraphics.setStroke(new BasicStroke(20));
        saltyGraphics.drawRect(screen);

        // set the clip so that the following things only get drawn within the area of the screen
        saltyGraphics.setClip(screen);

        // Draw the screen content
        screenContent.draw(saltyGraphics);

        // Reset the clip
        saltyGraphics.resetClip();
    }
}
