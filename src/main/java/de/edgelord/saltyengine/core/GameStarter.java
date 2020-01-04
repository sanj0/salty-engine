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

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.displaymanager.display.SplashWindow;

import javax.swing.*;

public class GameStarter {

    private static boolean locked = true;

    protected static void startGame(long fps, SplashWindow.Splash splash) {

        try {
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
                Game.forEachGameListener(GameListener::onStart);
            }
        } catch (Exception e) {
            System.err.println("Seems like something gone wrong while starting the game. Maybe there was a call to Game#start before Game#init?");
            e.printStackTrace();
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
    }

    private static boolean isLocked() {
        return locked;
    }

    public static void setLocked(boolean locked) {
        GameStarter.locked = locked;
    }
}
