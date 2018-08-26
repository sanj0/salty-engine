/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.periodical;

import java.util.Timer;
import java.util.TimerTask;

@Deprecated
public abstract class Periodical {

    private Timer timer;
    private long period;

    public Periodical(long period) {

        this.period = period;

        timer = new Timer();

    }

    public abstract void doMe();

    public void go(long delay) {

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                doMe();
            }
        }, delay, period);
    }

    public long getPeriod() {
        return period;
    }

    public void setPeriod(long period) {
        this.period = period;
    }
}
