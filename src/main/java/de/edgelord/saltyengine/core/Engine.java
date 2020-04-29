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

import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The {@link Game#getEngine() engine} handles the repainting of the {@link Host} and the fixed ticks.
 * The user of this library normally doesn't need to get in contact with this class.
 */
public class Engine {

    /**
     * The milliseconds between each fixed tick.
     */
    private long fixedTickMillis;

    /**
     * The {@link Timer} for the fixed ticks.
     */
    private Timer fixedTimer = new Timer();

    /**
     * The {@link Timer} that repaints the {@link Host}.
     */
    private Timer repaintTimer = new Timer();

    /**
     * Only used internally to stop the timers.
     */
    private boolean isCloseRequested = false;

    /**
     * The lost of task scheduled for
     * later execution by
     * {@link Game#executeLater(Runnable, long)}
     */
    private List<ScheduledTask> scheduledTasks = new ArrayList<>();

    /**
     * Creates a new instance with the given fixed tick millis. This happens automatically when initializing the {@link Game}.
     *
     * @param fixedTickMillis the milliseconds between each fixed tick
     */
    public Engine(long fixedTickMillis) {
        this.fixedTickMillis = fixedTickMillis;
    }

    /**
     * Starts the rendering and the fixed ticks. This happens automatically when the {@link Game} starts.
     */
    public void start() {
        startFixedTicks();
        startRendering();
    }

    /**
     * Starts the rendering with the given fixed FPS and the fixed ticks. This happens automatically.
     *
     * @param FPS the fps of the game.
     */
    public void start(long FPS) {
        startFixedTicks();
        startRepainting(FPS);

    }

    private void startRendering() {
        startRepainting();
    }

    /**
     * Starts the fixed ticks with {@link #fixedTickMillis}. This happens automatically.
     */
    public void startFixedTicks() {

        SaltySystem.fixedTickMillis = fixedTickMillis;

        fixedTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!Game.isPaused()) {
                    SceneManager.getCurrentScene().onFixedTick();

                    for (int i = 0; i < scheduledTasks.size(); i++) {
                        scheduledTasks.get(i).onFixedTick();
                    }
                    System.out.println("scheduled tasks: " + scheduledTasks.size());
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

    /**
     * Signalizes the engine that it should end.
     */
    public void close() {
        isCloseRequested = true;
    }

    /**
     * Gets {@link #scheduledTasks}.
     *
     * @return the value of {@link #scheduledTasks}
     */
    protected List<ScheduledTask> getScheduledTasks() {
        return scheduledTasks;
    }
}
