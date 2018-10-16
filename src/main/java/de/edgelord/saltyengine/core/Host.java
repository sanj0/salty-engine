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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.core.interfaces.CentrePositionProvider;
import de.edgelord.saltyengine.core.interfaces.Creatable;
import de.edgelord.saltyengine.core.interfaces.DimensionsProvider;
import de.edgelord.saltyengine.core.interfaces.Repaintable;

import java.awt.*;

/**
 * This is the class to implement for the host of a game.
 * The default host is {@link de.edgelord.saltyengine.display.DisplayManager}
 * <p>
 * Every host has to be repaintable, has to provide centre position for better placing of
 * objects inside the game, it has to provide its dimensions, has to be creatable and has to implement {@link #setBackgroundColor(Color)}
 * <p>
 * Apart from that, it can be literally everything. A window, a panel, an applet...
 * <p>
 * A host should also draw the content of {@link Engine}, an example: {@link de.edgelord.saltyengine.stage.Stage#paint(Graphics)}
 */
public abstract class Host implements Repaintable, CentrePositionProvider, DimensionsProvider, Creatable {

    /**
     * This method sets the background color of the host.
     * This is the color that is seen there, where nothing is drawn over.
     *
     * @param color the new background color
     */
    public abstract void setBackgroundColor(Color color);
}
