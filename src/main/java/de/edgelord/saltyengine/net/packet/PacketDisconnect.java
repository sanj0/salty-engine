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

package de.edgelord.saltyengine.net.packet;

public class PacketDisconnect extends AbstractPacket {

    private String username;
    private String ip;

    public PacketDisconnect(String username, String ip) {
        super(EnumPacket.PACKET_CONNECT);
        this.username = username;
        this.ip = ip;
    }

    @Override
    public String serialize() {
        return type.toString() + "," + username + "," + ip;
    }

    @Override
    public AbstractPacket deserialize(String data) {
        return staticDeserialize(data);
    }

    public static AbstractPacket staticDeserialize(String data) {
        String[] dataSplits = data.split(",");
        return new PacketConnect(dataSplits[1], dataSplits[2]);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
