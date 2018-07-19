package de.me.edgelord.sjgl.core;

import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.utils.GameStats;
import de.me.edgelord.sjgl.utils.StaticSystem;
import de.me.edgelord.sjgl.utils.Time;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class MainLoops {

    private long fixedLoopMillis;
    private Timer fixedTimer = new Timer();
    private Timer repaintTimer = new Timer();
    private boolean isCloseRequested = false;
    private DisplayManager displayManager = null;

    public MainLoops(long fixedLoopMillis) {
        this.fixedLoopMillis = fixedLoopMillis;
    }

    public void start(DisplayManager displayManager){

        this.displayManager = displayManager;
        startFixedTicks();
        startRepainting();
    }

    public void startRendering(DisplayManager displayManager) {

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

        fixedTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                doInitialising();

                if (!GameStats.isPaused()) {

                    if (StaticSystem.currentMode == StaticSystem.Mode.scene) {

                        StaticSystem.currentScene.onFixedTick();
                    } else if (StaticSystem.currentMode == StaticSystem.Mode.layerCollection) {

                        StaticSystem.currentLayerCollection.onFixedTick();
                    }
                }
            }
        }, 0, fixedLoopMillis);
    }

    public void startRepainting() {

        repaintTimer.schedule(new TimerTask() {

            long millisBefore;

            @Override
            public void run() {

                while (!isCloseRequested) {

                    millisBefore = System.currentTimeMillis();

                    onTick();
                    displayManager.repaintStage();

                    Time.setDeltaMillis(System.currentTimeMillis() - millisBefore);

                    Thread.yield();
                }
            }
        }, 0);
    }

    public void close() {

        isCloseRequested = true;
    }
}
