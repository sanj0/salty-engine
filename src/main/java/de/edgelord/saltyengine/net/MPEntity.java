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

import de.edgelord.saltyengine.core.event.CollisionEvent;
import de.edgelord.saltyengine.core.graphics.SaltyGraphics;
import de.edgelord.saltyengine.gameobject.GameObject;

import java.net.InetAddress;

public class MPEntity extends GameObject {

    private InetAddress ipAddress;
    private int port;
    private String username;

    public MPEntity(float xPos, float yPos, float width, float height, String username) {
        super(xPos, yPos, width, height, "de.edgelord.saltyengine.net.MPEntity");

        this.username = username;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void onCollision(CollisionEvent event) {

    }

    @Override
    public void onFixedTick() {

    }

    @Override
    public void draw(SaltyGraphics saltyGraphics) {

    }

    public InetAddress getIpAddress() {
        return ipAddress;
    }

    public int getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }
}
