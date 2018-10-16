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

import de.edgelord.saltyengine.components.gfx.WobblingEffect;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.ui.elements.Button;

import java.awt.*;
import java.awt.event.MouseEvent;

public class PauseButton extends Button {

    public PauseButton() {
        super("Pause", 0, 0, 500, 175);

        centrePosition();

        WobblingEffect wobblingEffect = new WobblingEffect(this, "wobblingGFX");
        wobblingEffect.setPause(25);
        wobblingEffect.init(10, 10, -10, -10);
        wobblingEffect.startGFX();

        setBackgroundColor(Color.orange);
        setForegroundColor(Color.white);
        setFont(getFont().deriveFont(100f));

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
