/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
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

    private void doInitialising() {

        SceneManager.getCurrentScene().initGameObjects();
    }

    public void render(SaltyGraphics saltyGraphics) {

        SceneManager.getCurrentScene().draw(saltyGraphics);
    }

    public void startFixedTicks() {

        StaticSystem.fixedTickMillis = fixedTickMillis;

        fixedTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                doInitialising();

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

                Thread.yield();

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
                    Thread.yield();
                }
            }
        }, 0);
    }

    public void close() {

        isCloseRequested = true;
    }
}
