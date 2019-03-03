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

import java.net.*;

public abstract class SaltyServer extends Thread implements NetworkingInterface {

    private DatagramSocket socket;

    public SaltyServer() throws SocketException {
        socket = new DatagramSocket(NetworkingInterface.PORT);
    }

    @Override
    public void run() {
        while (true) {
            handleIncomingData(receiveData(socket));
        }
    }

    public void sendData(String data, InetAddress ipAddress, int port) {

        NetworkingInterface.sendString(data, socket, ipAddress, port);
    }
}
