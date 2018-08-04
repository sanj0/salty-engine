/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2018
 * All rights reserved
 */

package de.edgelord.sjgl.periodical;

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
