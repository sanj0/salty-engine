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
     * @param FPS
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

            long nanosBefore;

            @Override
            public void run() {

                while (!isCloseRequested) {

                    nanosBefore = System.nanoTime();

                    Game.getHost().repaint();

                    Time.setDeltaNanos(System.nanoTime() - nanosBefore);
                }
            }
        }, 0);
    }

    public void close() {

        isCloseRequested = true;
    }
}
