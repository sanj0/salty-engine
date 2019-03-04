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
import java.net.*;

public abstract class SaltyServer extends Thread implements NetworkingInterface {

    private DatagramSocket socket;

    public SaltyServer() throws SocketException {

        this.socket = new DatagramSocket(NetworkingInterface.PORT);
    }

    @Override
    public void run() {
        while (true) {

            DatagramPacket packet = receivePacket(socket);
            handleIncoming(new String(packet.getData()).trim(), packet.getAddress(), packet.getPort());
        }
    }

    public void sendData(String data, InetAddress ipAddress, int port) throws IOException {

        byte[] dataBytes = data.getBytes();
        DatagramPacket packet = new DatagramPacket(dataBytes, dataBytes.length, ipAddress, port);
        socket.send(packet);
    }
}
