/*
 * Copyright 2019 Malte Dostal
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

package de.edgelord.saltyengine.example.net;

import de.edgelord.saltyengine.core.Game;
import de.edgelord.saltyengine.core.GameConfig;
import de.edgelord.saltyengine.displaymanager.display.SplashWindow;
import de.edgelord.saltyengine.input.Input;
import de.edgelord.saltyengine.scene.SceneManager;
import de.edgelord.saltyengine.utils.GeneralUtil;

import java.io.IOException;
import java.net.InetAddress;

public class ClientExample extends Game {

    public static String username = "";

    public static void main(String[] args) throws IOException {
        init(GameConfig.config(1920, 1080, "Simple Game Client", 5));
        start(60, SplashWindow.Splash.NO_SPLASH);

        username = args[0];

        ExampleClient client = new ExampleClient("192.168.178.27");
        client.start();

        Player player = new Player(GeneralUtil.randomInt(0, 1920), GeneralUtil.randomInt(0, 1080), 100, 100, username, InetAddress.getLocalHost(), client.getPort()) {
            @Override
            public void onFixedTick() {
                accelerate(2500, Input.getInput());

                try {
                    client.sendData("1:" + getUsername() + ":" + getX() + ":" + getY());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        client.sendData("0:" + player.getUsername() + ":" + player.getX() + ":" + player.getY());

        SceneManager.getCurrentScene().addGameObject(player);
    }
}
