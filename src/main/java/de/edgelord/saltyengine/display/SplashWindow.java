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

package de.edgelord.saltyengine.display;

import javax.swing.*;

public class SplashWindow extends JFrame {

    public enum Splash {
        DEFAULT_SPLASH,
        SPOTLIGHT_SPLASH,
        NO_SPLASH
    }

    public SplashWindow(Splash splash) {
        ImageIcon image = null;

        switch (splash) {

            case DEFAULT_SPLASH:
                image = new ImageIcon(getClass().getResource("/splashes/DefaultSplash.gif"));
                break;
            case SPOTLIGHT_SPLASH:
                image = new ImageIcon(getClass().getResource("/splashes/SpotlightSplash.gif"));
                break;
        }
        getContentPane().add(new JLabel("", image, SwingConstants.CENTER));
        setSize(1280, 854);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }
}
