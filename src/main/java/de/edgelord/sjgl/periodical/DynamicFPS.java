/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.sjgl.periodical;

import de.edgelord.sjgl.display.DisplayManager;

import java.util.Timer;
import java.util.TimerTask;

@Deprecated
public class DynamicFPS extends Periodical {

    private DisplayManager displayManager;
    private Thread thread;
    private long currentFPS;
    private long maxFPS;
    private long frameCount;

    private Timer delayTimer = new Timer();
    private Timer FPSCalculationPeriod = new Timer();

    public DynamicFPS(DisplayManager displayManager, long maxFPS) throws UnsupportedOperationException {
        super(0);

        throwException();

        currentFPS = maxFPS + 1;

        this.displayManager = displayManager;
        this.maxFPS = maxFPS;

        FPSCalculationPeriod.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                currentFPS = frameCount / 1;
                frameCount = 0;
                System.out.println("Current FPS: " + currentFPS);
            }
        }, 0, 1000);

        this.thread = new Thread(() -> {

            if (currentFPS > maxFPS) {
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            doMe();
            frameCount++;
        });
    }

    @Override
    public void go(long delay) {

        delayTimer.schedule(new TimerTask() {
            @Override
            public void run() {

                while (true)
                    thread.run();
            }
        }, delay);
    }

    private void throwException() throws UnsupportedOperationException {

        throw new UnsupportedOperationException("This Function is not Supported yet. Please use PeriodicalRepaint instead!");
    }

    @Override
    public void doMe() {

        displayManager.repaintStage();
    }

    public long getCurrentFPS() {
        return currentFPS;
    }
}
