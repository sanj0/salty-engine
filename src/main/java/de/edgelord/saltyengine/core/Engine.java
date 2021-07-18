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

import de.edgelord.saltyengine.utils.SaltySystem;
import de.edgelord.saltyengine.utils.Time;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * The <code>Engine</code> handles the periodic invocation of the functions
 * responsible for repainting and fixed tick tasks and actions. Teh working
 * instance is {@link Game#getEngine()}.
 */
public class Engine {

    /**
     * The milliseconds between each fixed tick.
     */
    private final long fixedTickMillis;

    /**
     * The {@link Timer} for the fixed ticks.
     */
    private final Timer fixedTimer = new Timer("fixed-tasks");

    /**
     * The {@link Timer} that repaints the {@link Host}.
     */
    private final Timer repaintTimer = new Timer("render-thread");

    /**
     * The list of task scheduled for later execution by {@link
     * Game#executeLater(Runnable, long)}
     */
    private final List<ScheduledTask> scheduledTasks = new ArrayList<>();

    /**
     * Only used internally to stop the timers.
     */
    private boolean isCloseRequested;

    /**
     * Creates a new instance with the given fixed tick millis. This happens
     * automatically when initializing the {@link Game}.
     *
     * @param fixedTickMillis the milliseconds between each fixed tick
     */
    public Engine(final long fixedTickMillis) {
        this.fixedTickMillis = fixedTickMillis;
        System.out.printf("initialised the engine with %d fixed tick millis\n", fixedTickMillis);
    }

    /**
     * Starts the rendering and the fixed ticks. This happens automatically when
     * the {@link Game} starts.
     */
    public void start() {
        System.out.println("starting the engine without fps cap...");
        startFixedTicks();
        startRendering();
    }

    /**
     * Starts the rendering with the given fixed FPS and the fixed ticks. This
     * happens automatically.
     *
     * @param FPS the fps of the game.
     */
    public void start(final long FPS) {
        System.out.printf("starting the engine with fps cap at %d...\n", FPS);
        startFixedTicks();
        startRepainting(FPS);

    }

    private void startRendering() {
        startRepainting();
    }

    /**
     * Starts the fixed ticks with {@link #fixedTickMillis}. This happens
     * automatically.
     */
    public void startFixedTicks() {
        SaltySystem.fixedTickMillis = fixedTickMillis;

        if (fixedTickMillis != -1) {
            System.out.println("engine starting the fixed tick thread...");
            fixedTimer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (!Game.isPaused()) {
                        SceneManager.getCurrentScene().onFixedTick();

                        for (int i = 0; i < scheduledTasks.size(); i++) {
                            scheduledTasks.get(i).onFixedTick();
                        }
                    }
                }
            }, 0, fixedTickMillis);
        } else {
            System.out.println("skipped starting the fixed tick thread due to " +
                    "ftm = -1");
        }
    }

    /**
     * Repaints the {@link Host} {@link Game#getHost()} with the given FPS. It
     * makes the <code>Host</code> repaint each
     * <code>1000 / FPS</code>. This is limited
     * to 1000 FPS, for higher FPS please use {@link #startRepainting()}
     *
     * @param FPS the fps to use for the game to be repainted
     */
    private void startRepainting(final long FPS) {
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
        System.out.println("engine starting the render thread...");
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
