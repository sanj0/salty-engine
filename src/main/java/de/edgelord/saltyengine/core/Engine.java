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

import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    private long fixedTickMillis;
    private Timer fixedTimer = new Timer();
    private Timer repaintTimer = new Timer();
    private boolean isCloseRequested = false;

    public Engine(long fixedTickMillis) {
        this.fixedTickMillis = fixedTickMillis;
    }

    public void start() {

        startFixedTicks();
        startRendering();
    }

    public void start(long FPS) {
        startFixedTicks();
        startRepainting(FPS);

    }

    private void startRendering() {

        startRepainting();
    }

    public void render(SaltyGraphics saltyGraphics) {

        SceneManager.getCurrentScene().draw(saltyGraphics);
    }

    public void startFixedTicks() {

        SaltySystem.fixedTickMillis = fixedTickMillis;

        fixedTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!Game.isPaused()) {
                    SceneManager.getCurrentScene().onFixedTick();
                }
            }
        }, 0, fixedTickMillis);
    }

    /**
     * Repaints the {@link Host} {@link Game#getHost()} with the given FPS.
     * It makes the <code>Host</code> repaint each <code>1000 / FPS</code>.
     * This is limited to 1000 FPS, for higher FPS please use {@link #startRepainting()}
     *
     * @param FPS the fps to use for the game to be repainted
     */
    private void startRepainting(long FPS) {

        repaintTimer.scheduleAtFixedRate(new TimerTask() {

            long nanosBeforeLastTime = 0;

            @Override
            public void run() {

                Time.setDeltaNanos((System.nanoTime() - nanosBeforeLastTime) / 1000);
                nanosBeforeLastTime = System.nanoTime();

                Game.getHost().repaint();
            }
        }, 0, 1000 / FPS);
    }

    private void startRepainting() {

        repaintTimer.schedule(new TimerTask() {

            long nanosBeforeLastTime = 0;

            @Override
            public void run() {

                while (!isCloseRequested) {

                    Game.getHost().repaint();

                    Time.setDeltaNanos((System.nanoTime() - nanosBeforeLastTime) / 1000);
                    nanosBeforeLastTime = System.nanoTime();
                }
            }
        }, 0);
    }

    public void close() {

        isCloseRequested = true;
    }
}
