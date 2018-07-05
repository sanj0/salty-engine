/*
 * Copyright (c) by Malte Dostal
 * Lindenberg, 2018
 * All rights reserved
 */

package testing;

import de.me.edgelord.sjgl.StaticVars.StaticSystem;
import de.me.edgelord.sjgl.display.DisplayManager;
import de.me.edgelord.sjgl.camera.Camera;
import de.me.edgelord.sjgl.main.MainLoops;
import de.me.edgelord.sjgl.resource.OuterResource;
import de.me.edgelord.sjgl.utils.FPSViewer;
import testing.dummys.DummyDisplayKeyHandler;
import testing.dummys.DummyScene;

import java.io.IOException;

public class Tester {

    public static Camera camera;
    public  static DisplayManager displayManager;
    private static DummyDisplayKeyHandler dummyDisplayKeyHandler;

    public static void main(String[] args) {

        System.out.println("INFO: Welcome to sjgl version 0.3 Zeus!");

        OuterResource outerResource = new OuterResource("sjgl testing", false);

        StaticSystem.setOuterResource(outerResource);

        MainLoops mainLoops = new MainLoops(1);
        StaticSystem.currentScene = new DummyScene();
        try {
            initGameObjects();
        } catch (IOException e) {
            e.printStackTrace();
        }
        displayManager = new DisplayManager(1200, 909, mainLoops);
        displayManager.create();
        mainLoops.startFixedTicks();
        mainLoops.startRendering(displayManager);

        camera = new Camera(mainLoops);
        dummyDisplayKeyHandler = new DummyDisplayKeyHandler();
        displayManager.setDisplayKeyHandler(dummyDisplayKeyHandler);
    }

    private static void initGameObjects() throws IOException {

        Bird bird1 = new Bird(0, 0);
        Bird bird2 = new Bird(0, 1);
        Bird bird3 = new Bird(0, 2);
        Bird bird4 = new Bird(0, 3);
        Bird bird5 = new Bird(0, 4);
        Bird bird6 = new Bird(0, 5);
        Bird bird7 = new Bird(0, 6);
        Bird bird8 = new Bird(0, 7);
        Bird bird10 = new Bird(1, 0);
        Bird bird11 = new Bird(1, 1);
        Bird bird12 = new Bird(1, 2);
        Bird bird13 = new Bird(1, 3);
        Bird bird14 = new Bird(1, 4);
        Bird bird15 = new Bird(1, 5);
        Bird bird16 = new Bird(1, 6);
        Bird bird17 = new Bird(1, 7);
        HugeImagerenderingTest hugeImagerenderingTest = new HugeImagerenderingTest();
        FPSViewer fpsViewer = new FPSViewer();

        StaticSystem.currentScene.addGameObject(hugeImagerenderingTest);
        StaticSystem.currentScene.addGameObject(bird1);
        StaticSystem.currentScene.addGameObject(bird2);
        StaticSystem.currentScene.addGameObject(bird3);
        StaticSystem.currentScene.addGameObject(bird4);
        StaticSystem.currentScene.addGameObject(bird5);
        StaticSystem.currentScene.addGameObject(bird6);
        StaticSystem.currentScene.addGameObject(bird7);
        StaticSystem.currentScene.addGameObject(bird8);
        StaticSystem.currentScene.addGameObject(bird10);
        StaticSystem.currentScene.addGameObject(bird11);
        StaticSystem.currentScene.addGameObject(bird12);
        StaticSystem.currentScene.addGameObject(bird13);
        StaticSystem.currentScene.addGameObject(bird14);
        StaticSystem.currentScene.addGameObject(bird15);
        StaticSystem.currentScene.addGameObject(bird16);
        StaticSystem.currentScene.addGameObject(bird17);
        StaticSystem.currentScene.addGameObject(fpsViewer);
    }
}
