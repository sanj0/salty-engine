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

public abstract class SaltyServer extends Thread implements NetworkingInterface {

    private DatagramSocket socket;
    private List<MPEntity> connectedEntities = new ArrayList<>();

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

    public void sendDataToAll(byte[] data) throws IOException {
        for (MPEntity entity : connectedEntities) {
            sendData(new String(data), entity.getIpAddress(), entity.getPort());
        }
    }

    public MPEntity getEntityByName(String name) {
        for (MPEntity entity : connectedEntities) {
            if (entity.getUsername().equals(name)) {
                return entity;
            }
        }

        return null;
    }

    public int size() {
        return connectedEntities.size();
    }

    public boolean isEmpty() {
        return connectedEntities.isEmpty();
    }

    public boolean contains(Object o) {
        return connectedEntities.contains(o);
    }

    public boolean addEntity(MPEntity mpEntity) {
        return connectedEntities.add(mpEntity);
    }

    public boolean removeEntity(Object o) {
        return connectedEntities.remove(o);
    }

    public void clear() {
        connectedEntities.clear();
    }

    public MPEntity getEntity(int index) {
        return connectedEntities.get(index);
    }

    public void addEntity(int index, MPEntity element) {
        connectedEntities.add(index, element);
    }

    public MPEntity removeEntity(int index) {
        return connectedEntities.remove(index);
    }
}
