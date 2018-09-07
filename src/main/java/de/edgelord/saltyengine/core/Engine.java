/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.display.DisplayManager;
import de.edgelord.saltyengine.graphics.SaltyGraphics;
import de.edgelord.saltyengine.utils.StaticSystem;
import de.edgelord.saltyengine.utils.Time;

import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    private long fixedTickMillis;
    private Timer fixedTimer = new Timer();
    private Timer repaintTimer = new Timer();
    private boolean isCloseRequested = false;
    private RepaintAble host = null;

    public Engine(long fixedTickMillis) {
        this.fixedTickMillis = fixedTickMillis;
    }

    public void start(RepaintAble host) {

        startFixedTicks();
        startRendering(host);
    }

    public void start(RepaintAble host, long FPS) {
        this.host = host;

        startFixedTicks();
        startRepainting(FPS);

    }

    private void startRendering(RepaintAble host) {

        this.host = host;

        startRepainting();
    }

    private void doInitialising() {

        if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

            StaticSystem.currentScene.initGameObjects();
        } else if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

            StaticSystem.currentLayerCollection.initGameObjects();
        }
    }

    private void onTick() {

        if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

            StaticSystem.currentScene.onTick();
        } else if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

            StaticSystem.currentLayerCollection.onTick();
        }
    }

    public void render(SaltyGraphics saltyGraphics) {

        if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

            StaticSystem.currentScene.draw(saltyGraphics);
        } else if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

            StaticSystem.currentLayerCollection.draw(saltyGraphics);
        }
    }

    public void startFixedTicks() {

        StaticSystem.fixedTickMillis = fixedTickMillis;

        fixedTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                doInitialising();

                if (!StaticSystem.isPaused()) {

                    if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

                        StaticSystem.currentScene.onFixedTick();
                    } else if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

                        StaticSystem.currentLayerCollection.onFixedTick();
                    }
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

                onTick();
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

                    onTick();
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
