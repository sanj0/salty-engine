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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.display.SplashWindow;

import javax.swing.*;

public class GameStarter {

    private static boolean locked = true;

    protected static void startGame(long fps, SplashWindow.Splash splash) {

        if (splash != SplashWindow.Splash.NO_SPLASH) {

            SplashWindow splashWindow = new SplashWindow(splash);
            splashWindow.setVisible(true);

            SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
                @Override
                protected Void doInBackground() throws Exception {
                    Thread.sleep(6000);
                    return null;
                }

                protected void done() {
                    startGame(true, splashWindow, fps);
                }
            };
            worker.execute();

            while (isLocked()) {
                System.out.print("");
            }
        } else {
            startGame(false, null, fps);
        }
    }

    private static void startGame(boolean splash, SplashWindow splashWindow, long fps) {
        if (splash) {
            splashWindow.setVisible(false);
        }

        Game.getHost().create();

        if (fps == -1) {
            Game.getEngine().start();
        } else {
            Game.getEngine().start(fps);
        }

        if (splash) {
            splashWindow.dispose();
        }
        setLocked(false);

        printLogo();
    }

    private static final void printLogo() {
        System.out.println();
        System.out.println();
        System.out.println(
                "               .://:`  \n" +
                        "           `-+yyhysos: \n" +
                        "         .+yhhs/-` `yh-\n" +
                        "       `+yhyo-`   .shh-\n" +
                        "      .shho.     -yhy/ \n" +
                        "     `shh/`     `yhy-  \n" +
                        "     -hhy       `ss.   \n" +
                        "     .yhy:       ``    \n" +
                        "      .oyhs:.          \n" +
                        "        ./syys/.       \n" +
                        "-oo/-`    `-/shho      \n" +
                        " -syhs/.`    `oho      \n" +
                        "   -+syyso+//+s/`      \n"
        );
        System.out.println();
        System.out.println();
    }

    private static boolean isLocked() {
        return locked;
    }

    public static void setLocked(boolean locked) {
        GameStarter.locked = locked;
    }
}
