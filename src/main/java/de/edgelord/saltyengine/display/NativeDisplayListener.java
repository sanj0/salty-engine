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

package de.edgelord.saltyengine.display;

import de.edgelord.saltyengine.core.Engine;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.WindowClosingHooks;
import de.edgelord.saltyengine.input.DisplayListener;
import de.edgelord.saltyengine.io.serialization.Serializer;

import java.awt.event.WindowEvent;
import java.io.IOException;

/**
 * The native listener on a {@link de.edgelord.saltyengine.display.Display}, which does the following (maybe expand in the future):
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
