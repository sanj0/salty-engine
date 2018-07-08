/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, since 2028
 * All rights reserved
 */

package testing.dummys;

import de.me.edgelord.sjgl.StaticVars.Directions;
import de.me.edgelord.sjgl.StaticVars.GameStats;
import de.me.edgelord.sjgl.StaticVars.StaticSystem;
import de.me.edgelord.sjgl.gameobject.FixedTask;
import de.me.edgelord.sjgl.input.DisplayKeyHandler;
import testing.Tester;

import java.awt.event.KeyEvent;

public class DummyDisplayKeyHandler implements DisplayKeyHandler {

    private final int cameraSpeed = 1;
    private int fixedTicks = 0;
    private double currentZoom = 1;
    private boolean cameraRight = false;
    private boolean cameraLeft = false;
    private boolean cameraUp = false;
    private boolean cameraDown = false;
    private boolean zoomIn, zoomOut, resetZoom;
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

            if (e.getKeyCode() == KeyEvent.VK_Z) {
                zoomIn = true;
                System.out.println("Zoom!");
            }

            if (e.getKeyCode() == KeyEvent.VK_U) {

                zoomOut = true;
                System.out.println("Zoom out!");
            }

            if (e.getKeyCode() == KeyEvent.VK_R) {

                resetZoom = true;
                System.out.println("Reset Zoom!");
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

        if (e.getKeyCode() == KeyEvent.VK_Z) {

            zoomIn = false;
            System.out.println("Zoom!");
        }

        if (e.getKeyCode() == KeyEvent.VK_U) {

            zoomOut = false;
            System.out.println("Zoom out!");
        }

        if (e.getKeyCode() == KeyEvent.VK_R) {

            resetZoom = false;
            System.out.println("Reset Zoom!");
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
                zoomIn = false;
                zoomOut = false;
            }

            if (zoomIn) {

                //StaticSystem.currentScene.zoom(2, Tester.displayManager);
                currentZoom += 0.001;
            }

            if (zoomOut) {

                //StaticSystem.currentScene.zoom(-2, Tester.displayManager);
                currentZoom -= 0.001;
            }

            if (resetZoom) {

                StaticSystem.currentScene.resetZoom();
                Tester.camera.resetPosition();
            }

            if (cameraRight) {

                Tester.camera.moveCamera(Directions.BasicDirection.x, -cameraSpeed);
            }
            if (cameraLeft) {

                Tester.camera.moveCamera(Directions.BasicDirection.x, cameraSpeed);
            }
            if (cameraUp) {

                Tester.camera.moveCamera(Directions.BasicDirection.y, cameraSpeed);
            }
            if (cameraDown) {

                Tester.camera.moveCamera(Directions.BasicDirection.y, -cameraSpeed);
            }

            // Tester.displayManager.scale(currentZoom);
        } else {

            fixedTicks++;
        }
    }
}
