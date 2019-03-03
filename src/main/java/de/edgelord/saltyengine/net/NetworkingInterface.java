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

package de.edgelord.saltyengine.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public interface NetworkingInterface {

    int PACKET_SIZE = 1024;
    int PORT = 1331;

    void handleIncomingData(String data);

    static void sendString(String data, DatagramSocket socket, InetAddress ipAddress, int port) {
        byte[] dataBytes = data.trim().getBytes();
        DatagramPacket packet = new DatagramPacket(dataBytes, dataBytes.length, ipAddress, port);
        try {
            socket.send(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    default String receiveData(DatagramSocket socket) {
        byte[] data = new byte[PACKET_SIZE];
        DatagramPacket packet = new DatagramPacket(data, data.length);

        try {
            socket.receive(packet);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new String(packet.getData()).trim();
    }
}
