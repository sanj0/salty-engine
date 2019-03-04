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

import de.edgelord.saltyengine.net.SaltyClient;
import de.edgelord.saltyengine.net.SaltyServer;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Scanner;

public class NetworkingExampleMain {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        /*
        SaltyServer saltyServer = new SaltyServer() {
            @Override
            public void handleIncoming(String data, InetAddress ipAddress, int port) {
                System.out.println("client > " + data);
                System.out.println("Type and answer: ");

                try {
                    sendData(scanner.nextLine(), ipAddress, port);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        saltyServer.start();
        */


        SaltyClient saltyClient = new SaltyClient("localhost") {
            @Override
            public void handleIncoming(String data, InetAddress ipAddress, int port) {
                System.out.println("server > " + data);
            }
        };
        saltyClient.start();

        while (true) {
            String msg = scanner.nextLine();
            saltyClient.sendData(msg);
        }
    }
}
