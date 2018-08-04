/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2028
 * All rights reserved
 */

package testing.dummys;

import de.edgelord.sjgl.gameobject.FixedTask;
import de.edgelord.sjgl.input.DisplayKeyHandler;
import de.edgelord.sjgl.utils.GameStats;
import de.edgelord.sjgl.utils.StaticSystem;

import java.awt.event.KeyEvent;

public class DummyDisplayKeyHandler implements DisplayKeyHandler {

    private final int cameraSpeed = 1;
    private int fixedTicks = 0;
    private double currentZoom = 1;
    private boolean cameraRight = false;
    private boolean cameraLeft = false;
    private boolean cameraUp = false;
    private boolean cameraDown = false;
    private FixedTask cameraMovement = this::doCameraMovement;

    public DummyDisplayKeyHandler() {

        StaticSystem.currentScene.addFixedTask(cameraMovement);
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        if (!GameStats.isPaused()) {

            if (e.getKeyCode() == KeyEvent.VK_D) {

                cameraRight = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_A) {

                cameraLeft = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_W) {

                cameraUp = true;
            }

            if (e.getKeyCode() == KeyEvent.VK_S) {

                cameraDown = true;
            }
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {

            if (GameStats.isPaused()) {
                GameStats.setPaused(false);
            } else {
                GameStats.setPaused(true);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_D) {

            cameraRight = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_A) {

            cameraLeft = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_W) {

            cameraUp = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_S) {

            cameraDown = false;
        }
    }

    public void doCameraMovement() {

        if (fixedTicks == 2) {

            fixedTicks = 0;

            if (GameStats.isPaused()) {

                cameraDown = false;
                cameraUp = false;
                cameraLeft = false;
                cameraRight = false;
            }

            if (cameraRight) {

                // Tester.camera.moveCamera(Directions.BasicDirection.x, -cameraSpeed);
            }
            if (cameraLeft) {

                // Tester.camera.moveCamera(Directions.BasicDirection.x, cameraSpeed);
            }
            if (cameraUp) {

                // Tester.camera.moveCamera(Directions.BasicDirection.y, cameraSpeed);
            }
            if (cameraDown) {

                // Tester.camera.moveCamera(Directions.BasicDirection.y, -cameraSpeed);
            }
        } else {

            fixedTicks++;
        }
    }
}
