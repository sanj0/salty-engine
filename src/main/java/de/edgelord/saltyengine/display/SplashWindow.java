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

import javax.swing.*;

public class SplashWindow extends JFrame {

    public enum Splash {
        DEFAULT_SPLASH,
        MELTING_SPLASH,
        SPOTLIGHT_SPLASH,
        WATER_SPLASH,
        UNDERWATER_SPLASH,
        NO_SPLASH
    }

    public SplashWindow(Splash splash) {
        ImageIcon image = null;

        switch (splash) {

            case DEFAULT_SPLASH:
                image = new ImageIcon(getClass().getResource("/splashes/DefaultSplash.gif"));
                break;
            case MELTING_SPLASH:
                image = new ImageIcon(getClass().getResource("/splashes/MeltingSplash.gif"));
                break;
            case SPOTLIGHT_SPLASH:
                image = new ImageIcon(getClass().getResource("/splashes/SpotlightSplash.gif"));
                break;
            case WATER_SPLASH:
                image = new ImageIcon(getClass().getResource("/splashes/WaterSplash.gif"));
                break;
            case UNDERWATER_SPLASH:
                image = new ImageIcon(getClass().getResource("/splashes/UnderwaterSplash.gif"));
                break;
        }
        getContentPane().add(new JLabel("", image, SwingConstants.CENTER));
        setSize(1280, 854);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }
}
