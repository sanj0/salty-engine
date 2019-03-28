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
import java.util.ArrayList;
import java.util.List;

public abstract class SaltyClient extends Thread implements NetworkingInterface {

    private InetAddress serverIP;
    private DatagramSocket socket;

    private List<MPEntity> remoteEntities = new ArrayList<>();

    public SaltyClient(String serverIPAddress) throws SocketException, UnknownHostException {

        this.socket = new DatagramSocket();
        serverIP = InetAddress.getByName(serverIPAddress);
    }

    @Override
    public void run() {
        while (true) {

            DatagramPacket packet = receivePacket(socket);
            handleIncoming(new String(packet.getData()).trim(), packet.getAddress(), packet.getPort());
        }
    }

    public void sendData(String data) throws IOException {

        byte[] dataBytes = data.getBytes();
        DatagramPacket packet = new DatagramPacket(dataBytes, dataBytes.length, serverIP, NetworkingInterface.PORT);
        socket.send(packet);
    }

    public int getPort() {
        return socket.getPort();
    }

    public InetAddress getServerIP() {
        return serverIP;
    }

    public MPEntity getEntityByName(String name) {
        for (MPEntity entity : remoteEntities) {
            if (entity.getUsername().equals(name)) {
                return entity;
            }
        }

        return null;
    }

    public int size() {
        return remoteEntities.size();
    }

    public boolean isEmpty() {
        return remoteEntities.isEmpty();
    }

    public boolean contains(Object o) {
        return remoteEntities.contains(o);
    }

    public boolean addEntity(MPEntity mpEntity) {
        return remoteEntities.add(mpEntity);
    }

    public boolean removeEntity(Object o) {
        return remoteEntities.remove(o);
    }

    public void clear() {
        remoteEntities.clear();
    }

    public MPEntity getEntity(int index) {
        return remoteEntities.get(index);
    }

    public void addEntity(int index, MPEntity element) {
        remoteEntities.add(index, element);
    }

    public MPEntity removeEntity(int index) {
        return remoteEntities.remove(index);
    }
}
