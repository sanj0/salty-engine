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

package de.edgelord.saltyengine.displaymanager.display;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.WindowClosingHooks;
import de.edgelord.saltyengine.displaymanager.input.DisplayListener;

import java.awt.event.WindowEvent;

/**
 * The native listener on a {@link de.edgelord.saltyengine.displaymanager.display.Display}, which does the following (maybe expand in the future):
 * <p>
 * - when the window is closing:
 * <p>
 * - close the {@link de.edgelord.saltyengine.core.Engine} by calling {@link Engine#close()} to {@link Game#engine}
 * <p>
 * - run the shutdown hooks by calling {@link WindowClosingHooks#runHooks()}
 * <p>
 * - exiting the program by calling {@link System#exit(int)}
 */
public class NativeDisplayListener extends DisplayListener {

    @Override
    public void windowClosing(WindowEvent e) {
        WindowClosingHooks.runHooks();
        Game.getEngine().close();
        System.exit(0);
    }
}
