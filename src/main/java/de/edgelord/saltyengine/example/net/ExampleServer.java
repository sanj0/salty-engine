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

import de.edgelord.saltyengine.net.SaltyServer;
import de.edgelord.saltyengine.transform.Coordinates2f;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

public class ExampleServer extends SaltyServer {

    public ExampleServer() throws SocketException {
    }

    @Override
    public void handleIncoming(String data, InetAddress ipAddress, int port) {

        System.out.println("[incoming] : " + data);

        // an example data:
        // 0:edgelord314:314:981
        // which means that the user wants to connect and that his position is (314 | 981)

        // if the message starts with 0 someone wants to connect
        if (data.startsWith("0")) {
            String[] capsules = data.split(":");
            Player newPlayer = new Player(Float.valueOf(capsules[2]), Float.valueOf(capsules[3]), 100, 100, capsules[1], ipAddress, port);

            try {
                sendDataToAll((data + ":" + ipAddress.getHostAddress() + ":" + port).getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            addEntity(newPlayer);

            // if the message starts with 1 it means an entity repositioning.
        } else if (data.startsWith("1")) {
            String[] capsules = data.split(":");

            getEntityByName(capsules[1]).setPosition(new Coordinates2f(Float.valueOf(capsules[2]), Float.valueOf(capsules[3])));

            try {
                sendDataToAll(data.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
