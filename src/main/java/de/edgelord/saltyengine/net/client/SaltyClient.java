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

package de.edgelord.saltyengine.net.client;

import de.edgelord.saltyengine.net.packet.PacketConnect;
import de.edgelord.saltyengine.net.packet.PacketDisconnect;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public abstract class SaltyClient implements Runnable {

    private Socket server;

    private int refreshRate = 1;
    private Thread thread;

    private DataInputStream reader;
    private DataOutputStream writer;

    private boolean connected = false;

    private String serverIP;
    private int serverPort;

    private String username;
    private String ip;

    public SaltyClient(String serverIP, int serverPort, String username, String ip) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;

        this.username = username;
        this.ip = ip;

        serverHandshake();
        joinServer();
    }

    public abstract void handleIncomingData(String data);

    public void serverHandshake() {
        try {

            server = new Socket();
            server.connect(new InetSocketAddress(serverIP, serverPort));
            connected = true;

            writer = new DataOutputStream(server.getOutputStream());
            reader = new DataInputStream(server.getInputStream());

            writeString("HandshakeRequest");

            thread = new Thread(this, "salty-client");
            thread.start();

            System.out.println("Client ready, waiting for the server to complete the handshake... ");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void joinServer() {
        String connectionRequest = new PacketConnect(username, ip).serialize();
        writeString(connectionRequest);
    }

    public void close() throws IOException {
        String disconnectionRequest = new PacketDisconnect(username, ip).serialize();
        writeString(disconnectionRequest);
        server.close();
    }

    public void parseIncoming(String s) {

        if(s.toLowerCase().contains("handshakecomplete")) {
            System.out.println("Server completed the handshake request...");
        } else {
            handleIncomingData(s);
        }
    }

    @Override
    public void run() {
        while (connected) {
            try {

                String data = reader.readUTF();

                this.parseIncoming(data);
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                Thread.sleep(refreshRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void writeString(String s) {
        try {
            if (server != null) {

                server = new Socket(serverIP, serverPort);

                writer = new DataOutputStream(server.getOutputStream());
                writer.writeUTF(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
