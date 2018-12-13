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

import de.edgelord.saltyengine.components.gfx.WobblingEffect;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.ui.elements.Button;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PauseButton extends Button {

    public PauseButton() {
        super("Pause", 0, 750, 175, 75);

        centreHorizontalPosition();

        WobblingEffect wobblingEffect = new WobblingEffect(this, "wobblingGFX");
        wobblingEffect.setPause(25);
        wobblingEffect.init(5, 5, -5, -5);
        wobblingEffect.startGFX();

        setBackgroundColor(Color.orange);
        setForegroundColor(Color.white);
        setFont(getFont().deriveFont(25f));

        this.addComponent(wobblingEffect);
    }

    @Override
    public void onClick(MouseEvent e) {

        if (e.getClickCount() == 2) {
            System.out.println("Exit Game due to double-click onto that pause button");
            System.exit(0);
        }

        if (Game.isPaused()) {

            System.out.println("Unpause game!");
            Game.setPaused(false);
        } else {
            System.out.println("Pause game!");
            Game.setPaused(true);
        }
    }
}
