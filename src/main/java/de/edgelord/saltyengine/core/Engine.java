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

import de.edgelord.saltyengine.core.interfaces.Repaintable;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.StaticSystem;
import de.edgelord.saltyengine.utils.Time;

import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    private long fixedTickMillis;
    private Timer fixedTimer = new Timer();
    private Timer repaintTimer = new Timer();
    private boolean isCloseRequested = false;
    private Repaintable host = null;

    public Engine(long fixedTickMillis) {
        this.fixedTickMillis = fixedTickMillis;
    }

    public void start(Repaintable host) {

        startFixedTicks();
        startRendering(host);
    }

    public void start(Repaintable host, long FPS) {
        this.host = host;

        startFixedTicks();
        startRepainting(FPS);

    }

    private void startRendering(Repaintable host) {

        this.host = host;

        startRepainting();
    }

    public void render(SaltyGraphics saltyGraphics) {

        SceneManager.getCurrentScene().draw(saltyGraphics);
    }

    public void startFixedTicks() {

        StaticSystem.fixedTickMillis = fixedTickMillis;

        fixedTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                if (!Game.isPaused()) {

                    SceneManager.getCurrentScene().onFixedTick();
                }
            }
        }, 0, fixedTickMillis);
    }

    private void startRepainting(long FPS) {

        repaintTimer.scheduleAtFixedRate(new TimerTask() {

            long nanosBefore;

            @Override
            public void run() {

                nanosBefore = System.nanoTime();

                host.repaint();

                Time.setDeltaNanos(System.nanoTime() - nanosBefore);
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

                    host.repaint();

                    Time.setDeltaNanos(System.nanoTime() - nanosBefore);
                }
            }
        }, 0);
    }

    public void close() {

        isCloseRequested = true;
    }
}
