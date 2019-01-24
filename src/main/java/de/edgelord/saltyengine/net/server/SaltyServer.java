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

package de.edgelord.saltyengine.net.server;

import de.edgelord.saltyengine.net.packet.EnumPacket;
import de.edgelord.saltyengine.net.packet.PacketConnect;
import de.edgelord.saltyengine.net.packet.GenericPacketFactory;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public abstract class SaltyServer extends Thread {

    private DataInputStream reader;
    private DataOutputStream writer;

    private ServerSocket server;

    public boolean online = true;

    public SaltyServer(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Started hosting a server at " + server.getInetAddress().getHostAddress() + " on " + port);
            start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void goOffline() {
        online = false;
    }

    public void run() {
        Socket client;

        while (online) {
            try {
                client = server.accept();
                String data;

                reader = new DataInputStream(client.getInputStream());
                data = reader.readUTF();

                parseIncoming(client, data);

                reader.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    public abstract void handleIncomingData(DataOutputStream answerStream, String data);

    public void parseIncoming(Socket c, String data) throws Exception {

        System.out.println(data);

        if (data.toLowerCase().contains("handshakerequest")) {

            sendString(c, data);
        }

        if (data.contains(EnumPacket.PACKET_CONNECT.toString())) {
            PacketConnect packet = GenericPacketFactory.encodeConnectPacket(data);
            System.out.println(packet.getUsername() + " with ip " + packet.getIp() + " has joined.");
        }
    }

    public void sendString(Socket socket, String data) throws IOException {

        writer = new DataOutputStream(socket.getOutputStream());

        writer.writeUTF(data);
        writer.flush();
    }
}
