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

package testing.dummys;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.input.KeyboardInputHandler;

import java.awt.event.KeyEvent;

public class DummyDisplayKeyHandler implements KeyboardInputHandler {

    public DummyDisplayKeyHandler() {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_P) {

            if (Game.isPaused()) {
                Game.setPaused(false);
            } else {
                Game.setPaused(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
