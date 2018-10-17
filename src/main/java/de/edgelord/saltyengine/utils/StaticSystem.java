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

package de.edgelord.saltyengine.utils;

import de.edgelord.saltyengine.factory.FontFactory;
import de.edgelord.saltyengine.factory.ImageFactory;
import de.edgelord.saltyengine.output.Output;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.ui.UIElement;

import java.awt.*;

public class StaticSystem {

    public static String versionTag = "0.12.0-SNAPSHOT";
    public static String version = "0.12.0";
    public static VersionMode versionMode = VersionMode.SNAPSHOT;

    public static long fixedTickMillis = 1;

    public static InnerResource defaultResource = new InnerResource();
    public static ImageFactory defaultImageFactory = new ImageFactory(defaultResource);
    public static FontFactory defaultFontFactory = new FontFactory(defaultResource);

    public static Output systemOutput = new Output(System.out);

    public static Font defaultFont = new Font(Font.SERIF, Font.PLAIN, 15);

    /**
     * Sets the {@link Font} {@link #defaultFont} to all {@link UIElement}s in the
     * current {@link de.edgelord.saltyengine.scene.Scene}s {@link de.edgelord.saltyengine.ui.UISystem}
     */
    public static void updateDefaultFontGlobally() {
        updateFontGlobally(defaultFont);
    }

    public static void updateFontGlobally(Font font) {

        for (UIElement uiElement : SceneManager.getCurrentScene().getUI().getElements()) {
            uiElement.setFont(font);
        }
    }

    public enum VersionMode {
        SNAPSHOT,
        ALPHA,
        BETA,
        RELEASE
    }
}
