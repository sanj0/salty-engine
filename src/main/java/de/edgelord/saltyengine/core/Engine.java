/*
 * Copyright (c) by Malte Dostal
 * Germany, 8.2018
 * All rights reserved
 */

package de.edgelord.saltyengine.core;

import de.edgelord.saltyengine.display.DisplayManager;
import de.edgelord.saltyengine.utils.StaticSystem;
import de.edgelord.saltyengine.utils.Time;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class Engine {

    private long fixedTickMillis;
    private Timer fixedTimer = new Timer();
    private Timer repaintTimer = new Timer();
    private boolean isCloseRequested = false;
    private DisplayManager displayManager = null;

    public Engine(long fixedTickMillis) {
        this.fixedTickMillis = fixedTickMillis;
    }

    public void start(DisplayManager displayManager) {

        startFixedTicks();
        startRendering(displayManager);
    }

    public void start(DisplayManager displayManager, long FPS) {
        this.displayManager = displayManager;

        startFixedTicks();
        startRepainting(FPS);

    }

    private void startRendering(DisplayManager displayManager) {

        this.displayManager = displayManager;

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

    public void render(Graphics2D graphics) {

        if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

            StaticSystem.currentScene.draw(graphics);
        } else if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

            StaticSystem.currentLayerCollection.draw(graphics);
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
                displayManager.repaintStage();

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
                    displayManager.repaintStage();

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
