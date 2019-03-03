/*
 * Copyright 2018 Malte Dostal
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package testing;

import de.edgelord.saltyengine.audio.AudioPlayer;
import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.displaymanager.display.SplashWindow;
import de.edgelord.saltyengine.factory.AudioFactory;
import de.edgelord.saltyengine.net.NetworkingInterface;
import de.edgelord.saltyengine.net.Packet;
import de.edgelord.saltyengine.net.SaltyClient;
import de.edgelord.saltyengine.net.SaltyServer;
import de.edgelord.saltyengine.resource.InnerResource;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.PointLogger;
import de.edgelord.saltyengine.utils.RectangleCreator;
import de.edgelord.saltyengine.utils.SaltySystem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Tester extends Game {

    private static AudioPlayer audioPlayer;

    private static void initGame() {

        Game.init(GameConfig.config(1200f, 900f, "testing", 1L));

        System.out.println("Welcome to Salty Engine v" + SaltySystem.versionTag);

        Tester.audioPlayer = new AudioPlayer(new AudioFactory(new InnerResource()));

        //Tester.audioPlayer.loadNewAudio("joy_sticky", "res/audio/music/Joy Sticky.wav");
        Tester.audioPlayer.loadNewAudio("bird_flap", "res/audio/sound/flap.wav");

        //Tester.audioPlayer.loop("joy_sticky");

        //audioPlayer.setClipVolume("joy_sticky", 0.75f);

        getHost().setBackgroundColor(Color.lightGray);
    }

    public static void main(final String[] args) throws InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        initGame();

        SceneManager.addScene("testingScene", TestingScene.class);

        start(60, SplashWindow.Splash.NO_SPLASH);
        serializeOnExit();

        SceneManager.setCurrentScene("testingScene", "foo", 842);
        // PointLogger.init("points");
        RectangleCreator.init();

        try {
            testNet();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //SceneFade fadeIn = new SceneFade("fadeIn", SceneFade.Mode.FADE_IN, Color.BLACK);

        //fadeIn.setDuration(3500);
        //fadeIn.fadeInit();
        // Game.getDefaultGFXController().addGFX(fadeIn);
        // Game.getDefaultGFXController().startAll();
    }

    public static void testNet() throws IOException {

        if (JOptionPane.showConfirmDialog(null, "Would you like to start the server?") == 1) {
            SaltyServer saltyServer = new SaltyServer() {
                @Override
                public void handleIncomingData(String data) {

                    System.out.println("client > " + data);

                    if (data.equals("handshake")) {
                        try {
                            sendData("hello world", InetAddress.getByName("localhost"), NetworkingInterface.PORT);
                        } catch (UnknownHostException e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            saltyServer.start();
        }

        SaltyClient saltyClient = new SaltyClient("localhost") {
            @Override
            public void handleIncomingData(String data) {
                System.out.println("server > " + data);
            }
        };
        saltyClient.start();
        saltyClient.sendData("handshake");
    }

    public static AudioPlayer getAudioPlayer() {
        return audioPlayer;
    }
}
