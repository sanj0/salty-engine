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

package de.edgelord.saltyengine.example.screen;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.SceneManager;
import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.transform.Transform;

import java.awt.*;

public class Screen extends GameObject {

    private final Transform screen;
    private final ScreenContent screenContent;

    private final String usageMessage = "Use WASD or the arrow keys to move the screen content!";

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
    public void onCollision(final CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {

        if (Input.inputLeft) {
            screenContent.moveX(7f);
        } else if (Input.inputRight) {
            screenContent.moveX(-7f);
        }

        if (Input.inputUp) {
            screenContent.moveY(7f);
        } else if (Input.inputDown) {
            screenContent.moveY(-7f);
        }
    }

    @Override
    public void draw(final SaltyGraphics saltyGraphics) {

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
